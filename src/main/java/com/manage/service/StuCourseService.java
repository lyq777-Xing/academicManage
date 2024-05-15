package com.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.entity.StuCourse;

import java.util.List;

/**
 * @author lyq
 * @time 2024/4/27 23:12
 */
public interface StuCourseService extends IService<StuCourse> {
    /**
     * 根据用户id查询
     * @param id 用户id
     * @return StuCourse
     */
    StuCourse findByUserId(Integer id);

    /**
     * 更新分数
     * @param stuId 学生id
     * @param courseId 课程id
     * @param score 分数
     */
    void updateScore(Integer stuId, Integer courseId, double score);

    /**
     * 根据用户id和课程id查询
     * @param id 用户id
     * @param id1 课程id
     * @return StuCourse
     */
    StuCourse findByUserIdAndCourseId(Integer id, Integer id1);

    /**
     * 根据用户id和课程id删除
     * @param userId 用户id
     * @param courseId 课程id
     */
    void deleteByUserIdAndCourseId(Integer userId, Integer courseId);

    /**
     * 添加学生课程
     * @param stuCourse 学生课程
     */
    void addStuCourse(StuCourse stuCourse);

    /**
     * 根据学生id查询
     * @param stuId 学生id
     * @return List<StuCourse>
     */
    List<StuCourse> findByStuId(Integer stuId);

}
