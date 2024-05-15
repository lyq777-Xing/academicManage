package com.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.Course;
import com.manage.entity.StuCourse;
import com.manage.entity.User;
import com.manage.mapper.CourseMapper;
import com.manage.service.CourseService;
import com.manage.service.StuCourseService;
import com.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author lyq
 * @time 2024/4/14 12:45
 */

@Transactional
@Service
public class ICourseService extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private StuCourseService stuCourseService;

    /**
     * 查询所有课程列表（分页）
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param query 查询条件
     * @return
     */
    @Override
    public IPage<Course> findAllCourse(Integer pageNum, Integer pageSize, String query) {
        Page<Object> page = new Page<>(pageNum, pageSize);
        IPage<Course> allCourses = null;
/*        if(query == null){
           allCourses = courseMapper.findAllCourses(page, null);
       }else {
//           LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
//           wrapper.like(Course::getCode,query)
//                   .or()
//                   .like(Course::getName,query);
           QueryWrapper<Course> wrapper = new QueryWrapper<>();
           wrapper.like("code",query)
                   .or()
                   .like("name",query);
           allCourses = courseMapper.findAllCourses(page, query);
       }*/
        allCourses = courseMapper.findAllCourses(page, query);
        for (int k = 0; k < allCourses.getRecords().size(); k++) {
            Course course = allCourses.getRecords().get(k);
            if(course.getTeacherIds() != null && course.getTeacherIds().length() > 0 && !course.getTeacherIds().equals("")){
                String[] teacherIds = course.getTeacherIds().split(",");
                ArrayList<User> teachers = new ArrayList<>();
                for (int i = 0; i < teacherIds.length; i++) {
                    User teacher = userService.findById(Integer.parseInt(teacherIds[i]));
                    if(teacher != null){
                        teachers.add(teacher);
                    }
                }
                allCourses.getRecords().get(k).setTeachers(teachers);
            }
            if(course.getStudentIds() != null && course.getStudentIds().length() > 0 && !course.getStudentIds().equals("")){
                String[] studentIds = course.getStudentIds().split(",");
                ArrayList<User> students = new ArrayList<>();
                for (int i = 0; i < studentIds.length; i++) {
                    User student = userService.findById(Integer.parseInt(studentIds[i]));
                    if(student != null){
                        StuCourse sc = stuCourseService.findByUserIdAndCourseId(student.getId(), course.getId());
                        if(sc != null){
                            User tea = userService.findById(sc.getTeacherId());
                            if (tea != null){
                                student.setTeacherName(tea.getUsername());
                                student.setTeacherId(tea.getId());
                                student.setScore(sc.getScore());
                            }
                            students.add(student);
                        }
                    }
                }
                allCourses.getRecords().get(k).setStudents(students);
            }
        }
        return allCourses;
    }

    /**
     * 添加课程
     * @param course 课程
     */
    @Override
    public void addCourse(Course course) {
        course.setCreateTime(LocalDateTime.now());
        int insert = courseMapper.insert(course);
        if(insert == 0){
            throw new RuntimeException("添加课程失败");
        }
    }

    /**
     * 删除课程
     * @param id 课程id
     */
    @Override
    public void deleteCourse(Integer id) {
        int i = courseMapper.deleteById(id);
        if(i == 0){
            throw new RuntimeException("删除课程失败");
        }
    }

    /**
     * 更新课程
     * @param course 课程
     */
    @Override
    public void updateCourse(Course course) {
        course.setUpdateTime(LocalDateTime.now());
        int i = courseMapper.updateById(course);
        if(i == 0){
            throw new RuntimeException("更新课程失败");
        }
    }
}
