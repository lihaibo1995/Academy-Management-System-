package com.lee.www.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.www.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.www.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李海波
 * @since 2022-07-27
 */
public interface CourseMapper extends BaseMapper<Course> {


    Page<Course> findPage(Page<Course> page, @Param("name") String name);

    void deleteStudentCourse(@Param("courseId")Integer courseId, @Param("studentId") Integer studentId);

    void setStudentCourse(@Param("courseId")Integer courseId, @Param("studentId") Integer studentId);


    Integer findStudentCourse(@Param("courseId")Integer courseId, @Param("studentId") Integer studentId);


    Page<Course> findMyCourses(Page<Course> page, @Param("studentId")Integer studentId);

    Page<Course> findTeacherCourses(Page<Course> page, @Param("teacherId")Integer teacherId);



}
