package com.lee.www.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.www.entity.Course;
import com.lee.www.mapper.CourseMapper;
import com.lee.www.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李海波
 * @since 2022-07-27
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public Page<Course> findPage(Page<Course> page, String name) {
        return courseMapper.findPage(page, name);
    }




    @Transactional
    @Override
    public void setStudentCourse(Integer courseId, Integer studentId) {
        courseMapper.deleteStudentCourse(courseId, studentId);
        courseMapper.setStudentCourse(courseId, studentId);
    }

    @Override
    public Integer findStudentCourse(Integer courseId, Integer studentId) {
        return courseMapper.findStudentCourse(courseId,studentId);
    }


    @Transactional
    @Override
    public void deleteStudentCourse(Integer courseId, Integer studentId) {
        courseMapper.deleteStudentCourse(courseId, studentId);
    }

    @Override
    public Page<Course> findMyCourses(Page<Course> page,Integer studentId ){
        return courseMapper.findMyCourses(page,studentId);
    }

    @Override
    public Page<Course> findTeacherCourses(Page<Course> page, Integer teacherId) {
        return courseMapper.findTeacherCourses(page,teacherId);
    }


}
