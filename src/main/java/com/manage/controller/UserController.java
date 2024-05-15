package com.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.common.ResponseResult;
import com.manage.entity.Role;
import com.manage.entity.User;
import com.manage.service.RoleService;
import com.manage.service.UserService;
import com.manage.utils.GetSecurityUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author lyq
 * @time 2024/1/29 19:19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private GetSecurityUserUtil getSecurityUserUtil;
/*    @GetMapping("/list")
//    @PreAuthorize("hasRole('ROLE_SuperAdmin')")
    public ResponseResult<Object> getUsers() {
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<User> students = userService.findAllStudents();
            result.Success("获取用户列表成功");
            result.setData(students);
        }catch (Exception e){
            result.Error("查询失败");
        }
        return result;
    }*/

    /**
     * 分页查询用户列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param query 查询条件
     * @return ResponseResult Object
     */
    @GetMapping("/list")
//    @PreAuthorize("hasRole('ROLE_SuperAdmin')")
    public ResponseResult<Object> getUsersPage(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            @RequestParam(value = "query",required = false) String query
    ){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            IPage<User> students = userService.findAllUsers(pageNum, pageSize, query);
            if(students.getRecords().isEmpty()){
                result.Success("查询结果为空");
            }else{
                result.Success("获取用户列表成功");
                result.setData(students);
            }
        }catch (Exception e){
            result.Error("查询失败");
        }
        return result;
    }

    /**
     * 添加用户
     * @param user 用户
     * @return ResponseResult Object
     */
    @PostMapping("/add")
    public ResponseResult<Object> addUser(@RequestBody User user){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            // 判断用户名是否已存在
            User userServiceByUsername = userService.findByUsername(user.getUsername());
            if(userServiceByUsername != null){
                result.Error("用户名已存在");
                return result;
            }
            userService.addUser(user);
            result.Success("添加用户成功");
        }catch (Exception e){
            result.Error("添加用户失败");
        }
        return result;
    }

    /**
     * 根据id删除用户
     * @param id 用户id
     * @return ResponseResult Object
     */
    @DeleteMapping("/delete")
    public ResponseResult<Object> deleteUser(@RequestParam("id") Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            int i = userService.deleteById(id);
            if(i == 0){
                result.Error("删除失败");
            }else{
                result.Success("删除成功");
            }
        }catch (Exception e){
            result.Error("删除失败");
        }
        return result;
    }

    /**
     * 更新用户
     * @param user 用户
     * @return ResponseResult Object
     */
    @PutMapping("/update")
    public ResponseResult<Object> updateUser(@RequestBody User user){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            int i = userService.updateUser(user);
            if(i == 0){
                result.Error("更新失败");
            }else{
                result.Success("更新成功");
            }
        }catch (Exception e){
            result.Error("更新失败");
        }
        return result;
    }

    /**
     * 获取登录用户详情
     * @return ResponseResult Object
     */
    @GetMapping("/detail")
    public ResponseResult<Object> getUserDetail(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            String username = getSecurityUserUtil.getCurrentUser();
            User user = userService.findByUsername(username);
            List<Role> roles = roleService.findRolesByUserId(user.getId());
            user.setRoleId(roles.get(0).getId());
            result.Success("获取成功",user);
        }catch (Exception e){
            result.Error("获取失败");
        }
        return result;
    }

    /**
     * 获取学生列表
     * @return ResponseResult Object
     */
    @GetMapping("/studentList")
    public ResponseResult<Object> getStudentList(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<User> students = userService.findAllStudents();
            result.Success("获取用户列表成功");
            result.setData(students);
        }catch (Exception e){
            result.Error("查询失败");
        }
        return result;
    }

    /**
     * 获取教师列表
     * @return ResponseResult Object
     */
    @GetMapping("/teacherList")
    public ResponseResult<Object> getTeacherList(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<User> teachers = userService.findAllTeachers();
            result.Success("获取用户列表成功");
            result.setData(teachers);
        }catch (Exception e){
            result.Error("查询失败");
        }
        return result;
    }

    /**
     * 获取教师名称和id列表
     * @return ResponseResult Object
     */
    @GetMapping("/getTeacherNameAndIdList")
    public ResponseResult<Object> getTeacherNameAndIdList(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<Map<String, String>> teachers = userService.getTeacherNameAndIdList();
            result.Success("获取教师名称和id列表成功");
            result.setData(teachers);
        }catch (Exception e){
            result.Error("获取教师名称和id列表失败");
        }
        return result;
    }
}
