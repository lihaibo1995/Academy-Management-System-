package com.lee.www.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.www.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李海波
 * @since 2022-07-27
 */
public interface ICourseService extends IService<Course> {

    Page<Course> findPage(Page<Course> page, String name);

    void setStudentCourse(Integer courseId, Integer studentId);


    Integer findStudentCourse(Integer courseId, Integer studentId);


    void deleteStudentCourse(Integer courseId, Integer studentId);


    Page<Course>  findMyCourses(Page<Course> page,Integer studentId);

    Page<Course>  findTeacherCourses(Page<Course> page,Integer teacherId);


}
