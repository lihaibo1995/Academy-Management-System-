<template>
  <div style="color: #666;font-size: 14px;">
    <div style="padding-bottom: 20px">
      <b>你好！{{ user.nickname }}</b>
    </div>

    <el-card >

        <h1 style="font-size: 40px;color: #FC466B">
          欢迎来到学院后台管理系统
        </h1>

      <el-divider />
      这是我在b站学习up主青哥的一个项目，是一个前后端分离的后台管理系统<br><br>
      根据不同角色的权限，你将会看到属于你的管理界面<br><br>

    </el-card>
    <div style="height: 1px; background: #ddd; margin: 20px 0"/>

    <el-row :gutter="30">
      <el-col :span="12">
        <div style="padding: 20px 0; font-size: 20px">青哥讲的课程相当不错，如果你想做Java开发，一定不要错过哦</div>
        <div>
          项目学习来源如下
        </div>
        <div class="m-10"><el-button type="danger"><i class="el-icon-coin"></i> 免费开源</el-button></div>
        <div class="m-10"><el-button type="primary"><i class="el-icon-s-custom"></i> 交流QQ群：529910361</el-button></div>
        <div class="m-10">
          <el-button type="primary"><i class="el-icon-link"></i> <a style="color: #fff" href="https://gitee.com/xqnode/pure-design">Gitee源码</a></el-button>
          <el-button type="primary"><i class="el-icon-link"></i> <a style="color: #fff" href="https://www.bilibili.com/video/BV1U44y1W77D">B站视频讲解</a></el-button>
        </div>
      </el-col>
      <el-col :span="12">
        <div style="padding: 20px 0; font-size: 20px">技术栈</div>
        <el-row>
          <el-col :span="12" style="line-height: 30px">
            <div><b>后端</b></div>
            <div>SpringBoot2</div>
            <div>MySQL</div>
            <div>Redis</div>
            <div>SpringMVC</div>
            <div>Mybatis/Mybatis-plus</div>
          </el-col>
          <el-col :span="12" style="line-height: 30px">
            <b>前端</b>
            <div>Vue2</div>
            <div>Vue-Router</div>
            <div>VueX</div>
            <div>ElementUI</div>
            <div>Axios</div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  }
}
</script>
