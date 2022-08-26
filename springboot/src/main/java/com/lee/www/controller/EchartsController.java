package com.lee.www.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import com.lee.www.common.Constants;
import com.lee.www.common.Result;
import com.lee.www.config.AuthAccess;
import com.lee.www.entity.Files;
import com.lee.www.entity.User;
import com.lee.www.mapper.FileMapper;
import com.lee.www.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private IUserService userService;

    @Resource
    private FileMapper fileMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //例子
    @GetMapping("/example")
    public Result get() {
        Map<String, Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y", CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return Result.success(map);
    }


    //查user表中总人数
    @GetMapping("/members")
    public Result members() {
        List<User> list = userService.list();
        int q1 = 0; // 第一季度
        int q2 = 0; // 第二季度
        int q3 = 0; // 第三季度
        int q4 = 0; // 第四季度
        for (User user : list) {
            Date createTime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch (quarter) {
                case Q1: q1 += 1; break;
                case Q2: q2 += 1; break;
                case Q3: q3 += 1; break;
                case Q4: q4 += 1; break;
                default: break;
            }
        }
        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));
    }

    //查user表中学生人数
    @GetMapping("/student")
    public Result student() {
        List<User> list = userService.list();
        int q1 = 0; // 第一季度
        int q2 = 0; // 第二季度
        int q3 = 0; // 第三季度
        int q4 = 0; // 第四季度
        for (User user : list) {
            Date createTime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            String role = user.getRole();
            if(role.equals("ROLE_STUDENT")){
                switch (quarter) {
                    case Q1: q1 += 1; break;
                    case Q2: q2 += 1; break;
                    case Q3: q3 += 1; break;
                    case Q4: q4 += 1; break;
                    default: break;
                }

            }

        }

        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));

    }

    //查user表中老师人数
    @GetMapping("/teacher")
    public Result teacher() {
        List<User> list = userService.list();
        int q1 = 0; // 第一季度
        int q2 = 0; // 第二季度
        int q3 = 0; // 第三季度
        int q4 = 0; // 第四季度
        for (User user : list) {
            String role = user.getRole();
            if(role.equals("ROLE_TEACHER")){
                Date createTime = user.getCreateTime();
                Quarter quarter = DateUtil.quarterEnum(createTime);
                switch (quarter) {
                    case Q1: q1 += 1; break;
                    case Q2: q2 += 1; break;
                    case Q3: q3 += 1; break;
                    case Q4: q4 += 1; break;
                    default: break;
                }

            }

        }
        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));
    }

    //查user表中各个角色的人数
    @GetMapping("/getAmountList")
    public Result getAmountList() {
        List<User> list = userService.list();
        int sum ;
        int student = 0;
        int teacher= 0;
        int admin = 0;
        int others=0;

        for (User user : list) {
            String role = user.getRole();
            if(role.equals("ROLE_STUDENT")){
                student++;

            }else if (role.equals("ROLE_TEACHER")){
                teacher++;

            }else if (role.equals("ROLE_ADMIN")){
                admin++;

            }else {
                others++;
            }

         }
        sum=student+teacher+admin+others;
        System.out.println("============"+sum);

        return Result.success(CollUtil.newArrayList(sum, student, teacher, admin,others));
    }






    @AuthAccess
    @GetMapping("/file/front/all")
    public Result frontAll() {
        // 1. 从缓存获取数据
        String jsonStr = stringRedisTemplate.opsForValue().get(Constants.FILES_KEY);
        List<Files> files;
        if (StrUtil.isBlank(jsonStr)) {  // 2. 取出来的json是空的
            files = fileMapper.selectList(null);  // 3. 从数据库取出数据
            // 4. 再去缓存到redis
            stringRedisTemplate.opsForValue().set(Constants.FILES_KEY, JSONUtil.toJsonStr(files));
        } else {
            // 减轻数据库的压力
            // 5. 如果有, 从redis缓存中获取数据
            files = JSONUtil.toBean(jsonStr, new TypeReference<List<Files>>() {
            }, true);
        }
        return Result.success(files);
//        return Result.success(fileMapper.selectList(null));
    }


}
