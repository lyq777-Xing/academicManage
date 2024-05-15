package com.manage.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lyq
 * @time 2024/4/14 12:41
 */

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 查询所有课程列表（分页）
     * @return List<Course>
     */
    IPage<Course> findAllCourses(Page<?> page, @Param("query")String query);
}
