package com.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.entity.Role;
import com.manage.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author lyq
 * @time 2024/1/24 14:54
 */

public interface UserService extends IService<User> {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findById(Integer id);


    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户id查询权限列表
     * @param userId
     * @return
     */
    String getAuthorityInfo(Integer userId);

    /**
     * 查询所有学生列表（不分页）
     * @return List<User>
     */
    List<User> findAllStudents();

    /**
     * 查询所有学生列表（分页）
     * @return Page<User>
     */
    IPage<User> findAllUsers(Integer pageNum, Integer pageSize, String query);


    /**
     * 添加用户
     * @param user 用户
     */
    void addUser(User user);


    /**
     * 根据Id删除用户
     * @param id 用户id
     * @return int
     */
    int deleteById(Integer id);


    /**
     * 更新用户（不修改密码）
     * @param user 用户
     * @return int
     */
    int updateUser(User user);


    /**
     * 查询所有教师列表
     * @return List<User>
     */
    List<User> findAllTeachers();

    /**
     * 获取教师名称和id列表
     * @return List<Map<String,String>>
     */
    List<Map<String,String>> getTeacherNameAndIdList();

}
