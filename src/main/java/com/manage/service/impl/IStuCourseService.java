package com.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.Course;
import com.manage.entity.StuCourse;
import com.manage.entity.User;
import com.manage.mapper.StuCourseMapper;
import com.manage.service.CourseService;
import com.manage.service.StuCourseService;
import com.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lyq
 * @time 2024/4/27 23:13
 */

@Service
@Transactional
public class IStuCourseService extends ServiceImpl<StuCourseMapper, StuCourse> implements StuCourseService {

    @Autowired
    private StuCourseMapper stuCourseMapper;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    /**
     * 根据用户id查询
     * @param id 学生id
     * @return StuCourse
     */
    @Override
    public StuCourse findByUserId(Integer id) {
        QueryWrapper<StuCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", id);
        return stuCourseMapper.selectOne(wrapper);
    }

    /**
     * 更新分数
     * @param stuId 学生id
     * @param courseId 课程id
     * @param score 分数
     */
    @Override
    public void updateScore(Integer stuId, Integer courseId, double score) {
        QueryWrapper<StuCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", stuId)
                .eq("course_id", courseId);
        StuCourse stuCourse = stuCourseMapper.selectOne(wrapper);
        stuCourse.setScore(score);
        int i = stuCourseMapper.update(stuCourse, wrapper);
        if (i != 1) {
            throw new RuntimeException("更新失败");
        }
    }

    /**
     * 根据用户id和课程id查询
     * @param id 用户id
     * @param id1 课程id
     * @return StuCourse
     */
    @Override
    public StuCourse findByUserIdAndCourseId(Integer id, Integer id1) {
        QueryWrapper<StuCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", id)
                .eq("course_id", id1);
        return stuCourseMapper.selectOne(wrapper);
    }

    /**
     * 根据用户id和课程id删除
     * @param userId 用户id
     * @param courseId 课程id
     */
    @Override
    public void deleteByUserIdAndCourseId(Integer userId, Integer courseId) {
        QueryWrapper<StuCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", userId)
                .eq("course_id", courseId);
        int i = stuCourseMapper.delete(wrapper);
//        并且删除掉课程表中的数据
        Course course = courseService.getById(courseId);
        String studentIds = course.getStudentIds();
        String[] split = studentIds.split(",");
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < split.length-1; j++) {
            if (split[j].equals(userId.toString())) {
                continue;
            }
            sb.append(split[j]).append(",");
        }
        if(! split[split.length-1].equals(userId.toString())){
            sb.deleteCharAt(sb.length()-1);
        }else {
            sb.append(split[split.length-1]);
        }
        course.setStudentIds(sb.toString());
        courseService.updateById(course);
        if (i != 1) {
            throw new RuntimeException("删除失败");
        }
    }

    /**
     * 添加学生课程
     * @param stuCourse 学生课程
     */
    @Override
    public void addStuCourse(StuCourse stuCourse) {
        int i = stuCourseMapper.insert(stuCourse);
        Course course = courseService.getById(stuCourse.getCourseId());
        String studentIds = course.getStudentIds();
        if (studentIds == null) {
            studentIds = "";
            course.setStudentIds("" +  stuCourse.getStuId());
        }else {
            course.setStudentIds(studentIds  + "," + stuCourse.getStuId());
        }
        courseService.updateById(course);
        if (i != 1) {
            throw new RuntimeException("添加失败");
        }
    }

    /**
     * 根据学生id查询
     * @param stuId 学生id
     * @return List<StuCourse>
     */
    @Override
    public List<StuCourse> findByStuId(Integer stuId) {
        QueryWrapper<StuCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", stuId);
        List<StuCourse> stuCourses = stuCourseMapper.selectList(wrapper);
        for (StuCourse stuCourse : stuCourses) {
            QueryWrapper<User> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("id", stuCourse.getTeacherId());
            User user = userService.getOne(wrapper1);
            stuCourse.setTeacherName(user.getUsername());
            stuCourse.setCourseName(courseService.getById(stuCourse.getCourseId()).getName());
        }
        return stuCourses;
    }
}
