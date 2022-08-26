package com.lee.www.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.www.common.Constants;
import com.lee.www.entity.Files;
import com.lee.www.mapper.CourseMapper;
import com.lee.www.mapper.FileMapper;
import com.lee.www.mapper.UserMapper;
import com.lee.www.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.www.common.Result;

import com.lee.www.service.ICourseService;
import com.lee.www.entity.Course;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李海波
 * @since 2022-07-27
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private ICourseService courseService;

    @Resource
    private IUserService userService;

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private UserMapper userMapper;




    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Course course) {
        courseService.saveOrUpdate(course);
        return Result.success();
    }

    //更新或新增选课
    @PostMapping("/update")
    public Result update(@RequestBody Course course) {
        courseMapper.updateById(course);
        return Result.success();
    }

    // 选课
    @PostMapping("/studentCourse/{courseId}/{studentId}")
    public Result studentCourse(@PathVariable Integer courseId ,@PathVariable Integer studentId) {
        courseService.setStudentCourse(courseId, studentId);
        return Result.success();
    }

    // 弃课
    @PostMapping("/deleteStudentCourse/{courseId}/{studentId}")
    public Result deleteStudentCourse(@PathVariable Integer courseId ,@PathVariable Integer studentId) {
        courseService.deleteStudentCourse(courseId, studentId);
        return Result.success();
    }

    // 学生查某一个课
    @GetMapping("/findStudentCourse/{courseId}/{studentId}")
    public Result findStudentCourse(@PathVariable Integer courseId ,@PathVariable Integer studentId) {
        return Result.success(courseService.findStudentCourse(courseId, studentId));
    }



    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        courseService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        courseService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(courseService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(courseService.getById(id));
    }

    @GetMapping("/findState/{id}")
    public Result findState(@PathVariable Integer id) {
        return Result.success(courseMapper.selectById(id));
    }



   //学生查所有课表
    @GetMapping("/findMyCourses/{studentId}")
    public Result findMyCourses(@PathVariable Integer studentId) {

        //方式二：关联查询
        Page<Course> page = courseService.findMyCourses(new Page<>(1, 10), studentId);
        return Result.success(page);
    }


    // 老师查所有课表
    @GetMapping("/findTeacherCourses/{teacherId}")
    public Result findTeacherCourses(@PathVariable Integer teacherId) {

        //方式二：关联查询
        Page<Course> page = courseService.findTeacherCourses(new Page<>(1, 10), teacherId);
        return Result.success(page);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        //方式一：mybatis查询
//        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//        Page<Course> page = courseService.page(new Page<>(pageNum, pageSize), queryWrapper);
//        List<Course> records = page.getRecords();
//        for (Course record : records) {
//            User user = userService.getById(record.getTeacherId());
//            if(user != null) {
//                record.setTeacher(user.getNickname());
//            }
//
//        }

        //方式二：关联查询
        Page<Course> page = courseService.findPage(new Page<>(pageNum, pageSize), name);
        return Result.success(page);
    }

}

