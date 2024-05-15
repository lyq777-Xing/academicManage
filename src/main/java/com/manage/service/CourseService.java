package com.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.entity.Course;

import java.util.List;

/**
 * @author lyq
 * @time 2024/4/14 12:45
 */
public interface CourseService extends IService<Course> {

    /**
     * 查询所有课程列表（分页）
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param query 查询条件
     * @return
     */
    IPage<Course> findAllCourse(Integer pageNum, Integer pageSize, String query);

    /**
     * 添加课程
     * @param course 课程
     */
    void addCourse(Course course);

    /**
     * 删除课程
     * @param id 课程id
     */
    void deleteCourse(Integer id);

    /**
     * 更新课程
     * @param course 课程
     */
    void updateCourse(Course course);

}
