package com.manage.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.entity.Role;
import com.manage.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author lyq
 * @time 2024/1/24 14:57
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询所有学生列表
     * @return List<User>
     */
    List<User> findAllStudents();


    /**
     * 查询所有教师列表
     * @return List<User>
     */
//    List<User>  findAllUsers();
    IPage<User> findAllUsers(Page<?> page, @Param("query") String query);

    /**
     * 查询所有教师列表
     * @return List<User>
     */
    List<User> findAllTeachers();
}
