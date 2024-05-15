package com.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.common.ResponseResult;
import com.manage.entity.Course;
import com.manage.entity.StuCourse;
import com.manage.entity.User;
import com.manage.service.CourseService;
import com.manage.service.StuCourseService;
import com.manage.service.UserClassService;
import com.manage.service.UserService;
import com.manage.utils.GetSecurityUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyq
 * @time 2024/4/14 20:31
 */

@RequestMapping("/course")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StuCourseService stuCourseService;

    @Autowired
    private UserService userService;

    @Autowired
    private GetSecurityUserUtil getSecurityUserUtil;


    /**
     * 查询所有课程列表（分页）
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param query 查询条件
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<Object> findAllCourse(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "query", required = false) String query
    ){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            IPage<Course> course = courseService.findAllCourse(pageNum, pageSize, query);
            result.Success("获取成功",course);
        }catch (Exception e){
            e.printStackTrace();
            result.Error("获取失败");
        }
        return result;
    }

    /**
     * 添加课程
     * @param course 课程
     */
    @PostMapping("/add")
    public ResponseResult<Object> addCourse(@RequestBody Course course){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            courseService.addCourse(course);
            result.Success("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            result.Error("添加失败");
        }
        return result;
    }

    /**
     * 删除课程
     * @param id 课程id
     */
    @DeleteMapping("/delete")
    public ResponseResult<Object> deleteCourse(@RequestParam("id") Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            courseService.deleteCourse(id);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.Error("删除失败");
        }
        return result;
    }

    /**
     * 更新课程
     * @param course 课程
     */
    @PutMapping("/update")
    public ResponseResult<Object> updateCourse(@RequestBody Course course){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            courseService.updateCourse(course);
            result.Success("更新成功");
        }catch (Exception e){
            e.printStackTrace();
            result.Error("更新失败");
        }
        return result;
    }

    /**
     * 更新分数
     * @param stuId 学生id
     * @param courseId 课程id
     * @param score 分数
     */
    @PutMapping("/updScore")
    public ResponseResult<Object> updateScore(
            @RequestParam("stuId") Integer stuId,
            @RequestParam("courseId") Integer courseId,
            @RequestParam("score") double score){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            stuCourseService.updateScore(stuId, courseId, score);
            result.Success("更新成功");
        }catch (Exception e){
            e.printStackTrace();
            result.Error("更新失败");
        }
        return result;
    }

    /**
     * 根据用户id和课程id删除
     * @param userId 用户id
     * @param courseId 课程id
     */
    @DeleteMapping("/delStu")
    public ResponseResult<Object> deleteByUserIdAndCourseId(
            @RequestParam("userId") Integer userId,
            @RequestParam("courseId") Integer courseId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            stuCourseService.deleteByUserIdAndCourseId(userId, courseId);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.Error("删除失败");
        }
        return result;
    }

    @DeleteMapping("/delC")
    public ResponseResult<Object> deleteByCourseId(
            @RequestParam("courseId") Integer courseId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            String username = getSecurityUserUtil.getCurrentUser();
            User user = userService.findByUsername(username);
            stuCourseService.deleteByUserIdAndCourseId(user.getId(), courseId);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.Error("删除失败");
        }
        return result;
    }
    /**
     * 添加学生课程
     * @param stuCourse 学生课程
     */
    @PostMapping("/addStu")
    public ResponseResult<Object> addStuCourse(@RequestBody StuCourse stuCourse){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            stuCourseService.addStuCourse(stuCourse);
            result.Success("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            result.Error("添加失败");
        }
        return result;
    }

    /**
     * 根据学生id查询
     * @return List<StuCourse>
     */
    @GetMapping("/mine")
    public ResponseResult<Object> findByStuId(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            String username = getSecurityUserUtil.getCurrentUser();
            User user = userService.findByUsername(username);
            result.Success("获取成功",stuCourseService.findByStuId(user.getId()));
        }catch (Exception e){
            e.printStackTrace();
            result.Error("获取失败");
        }
        return result;
    }
}
