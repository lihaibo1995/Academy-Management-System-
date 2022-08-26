<template>
  <div style="padding: 10px">
    <el-card>
      <h1 style="font-size: 40px;color: #FC466B">
        点击即可观看学院风采
      </h1>
      <div v-for="item in videos" :key="item.id" style="margin: 10px 0; padding: 20px 0; color: #3a8ee6; border-bottom: 2px dashed #333">
        <span style="font-size: 18px; cursor: pointer" class="item" @click="detail(item.id)">{{ item.name }}</span>
        <span style="float: right; font-size: 12px; margin-top: 10px">文件大小：{{ item.size }} kb</span>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Video",
  data() {
    return {
      videos: []
    }
  },
  created() {
    this.request("/echarts/file/front/all").then(res => {
      console.log(res.data)
      this.videos = res.data.filter(v => v.type === 'mp4')
    })
  },
  methods: {
    detail(id) {
      this.$router.push({path: "/videoDetail", query: {id: id}})
    }
  }
}
</script>

<style>
.item:hover{
  color: #3a8ee6;
  background: none;
}



</style>
