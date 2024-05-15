package com.manage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.common.ResponseResult;
import com.manage.entity.College;
import com.manage.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lyq
 * @time 2024/1/29 19:18
 */

@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    /**
     * 获取学院名称和id列表
     * @return ResponseResult<Object>
     */
    @RequestMapping("/getCollegeNameAndIdList")
    public ResponseResult<Object> getCollegeNameAndIdList() {
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            List<Map<String, String>> collegeNameAndIdList = collegeService.getCollegeNameAndIdList();
            result.Success("获取学院名称和id列表成功");
            result.setData(collegeNameAndIdList);
        } catch (Exception e) {
            e.printStackTrace();
            result.Error("获取学院名称和id列表失败");
        }
        return result;
    }

    /**
     * 查询所有学院列表
     * @return ResponseResult Object
     */
    @GetMapping("/list")
    public ResponseResult<Object> findAllColleges(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "query", required = false) String query
    ){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            Page<College> colleges = collegeService.findAllColleges(pageNum, pageSize, query);
            result.Success("获取成功",colleges);
        }catch (Exception e){
            e.printStackTrace();
            result.Error("获取失败");
        }
        return result;
    }

    /**
     * 添加学院
     * @param college 学院
     * @return ResponseResult Object
     */
    @PostMapping("/add")
    public ResponseResult<Object> addCollege(@RequestBody College college) {
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            collegeService.addCollege(college);
            result.Success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.Error("添加失败");
        }
        return result;
    }

    /**
     * 更新学院
     * @param college 学院
     * @return ResponseResult Object
     */
    @PutMapping("/update")
    public ResponseResult<Object> updateCollege(@RequestBody College college) {
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            collegeService.updateCollege(college);
            result.Success("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.Error("更新失败");
        }
        return result;
    }

    /**
     * 删除学院
     * @param id 学院id
     * @return ResponseResult Object
     */
    @DeleteMapping("/delete")
    public ResponseResult<Object> deleteCollege(@RequestParam("id") Integer id) {
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            collegeService.deleteCollege(id);
            result.Success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.Error("删除失败");
        }
        return result;
    }
}
