package com.manage.controller;

import com.manage.common.ResponseResult;
import com.manage.entity.Class;
import com.manage.entity.User;
import com.manage.entity.vo.UserClassVo;
import com.manage.service.ClassService;
import com.manage.service.UserClassService;
import com.manage.service.UserService;
import com.manage.utils.GetSecurityUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyq
 * @time 2024/4/8 23:35
 */

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserClassService userClassService;

    @Autowired
    private GetSecurityUserUtil getSecurityUserUtil;

    /**
     * 根据学院id获取班级名称和id列表
     * @param collegeId 学院id
     * @return ResponseResult<Object>
     */
    @RequestMapping("/getClassNameAndIdListByCollegeId")
    public ResponseResult<Object> getClassNameAndIdListByCollegeId(
            @RequestParam(value = "collegeId",required = true) Integer collegeId) {
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            result.Success("获取班级名称和id列表成功");
            result.setData(classService.getClassNameAndIdListByCollegeId(collegeId));
        } catch (Exception e) {
            e.printStackTrace();
            result.Error("获取班级名称和id列表失败");
        }
        return result;
    }

    /**
     * 查询所有班级列表（分页）
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param query 查询条件
     * @return ResponseResult<Object>
     */
    @GetMapping("/list")
    public ResponseResult<Object> getListPage(
            @RequestParam(value = "pageNum",required = true) Integer pageNum,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "query",required = false) String query) {
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            result.Success("获取班级列表成功");
            result.setData(classService.findAllClasses(pageNum, pageSize, query));
        } catch (Exception e) {
            e.printStackTrace();
            result.Error("获取班级列表失败");
        }
        return result;
    }

    /**
     * 添加班级
     * @param aClass 班级
     * @return ResponseResult<Object>
     */
    @PostMapping("/add")
    public ResponseResult<Object> addClass(@RequestBody Class aClass) {
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            classService.addClass(aClass);
            result.Success("添加班级成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.Error("添加班级失败");
        }
        return result;
    }

    /**
     * 更新班级
     * @param aClass 班级
     * @return ResponseResult<Object>
     */
    @PutMapping("/update")
    public ResponseResult<Object> updateClass(@RequestBody Class aClass) {
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            classService.updateClass(aClass);
            result.Success("更新班级成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.Error("更新班级失败");
        }
        return result;
    }

    /**
     * 删除班级
     * @param id 班级id
     * @return ResponseResult<Object>
     */
    @DeleteMapping("/delete")
    public ResponseResult<Object> deleteClass(@RequestParam("id") Integer id) {
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            classService.deleteClass(id);
            result.Success("删除班级成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.Error("删除班级失败");
        }
        return result;
    }

    /**
     * 获取同学
     * @return ResponseResult<Object>
     */
    @GetMapping("/mates")
    public ResponseResult<Object> getMates() {
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            String username = getSecurityUserUtil.getCurrentUser();
            User user = userService.findByUsername(username);
            UserClassVo byUserId = userClassService.findByUserId(user.getId());
            result.Success("获取同学成功");
            result.setData(userClassService.getStudentListByClassId(byUserId.getClassId()));
        } catch (Exception e) {
            e.printStackTrace();
            result.Error("获取同学失败");
        }
        return result;
    }

    @GetMapping("/echarts")
    public ResponseResult<Object> echartsData() {
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            result.Success("获取图表信息成功");
            result.setData(classService.echartsData());
        } catch (Exception e) {
            e.printStackTrace();
            result.Error("获取图表信息失败");
        }
        return result;
    }
}
