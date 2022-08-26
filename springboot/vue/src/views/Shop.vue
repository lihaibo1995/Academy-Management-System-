<template>
    <div>
        <div style="margin: 10px 0">
<!--            <el-carousel height="450px" :interval="10000">-->
<!--                <el-carousel-item v-for="item in imgs" :key="item">-->
<!--                    <img :src="item" alt="" style="width: 100%;">-->
<!--                </el-carousel-item>-->
<!--            </el-carousel>-->
            <el-carousel :interval="10000" height="450px" ref="carousel" @click.native="linkTo">
                <el-carousel-item v-for="item in imgs" :key="item.url" >
                    <img :src="item.url"style="width: 100%;"/>
                </el-carousel-item>
            </el-carousel>
        </div>

        <div style="margin: 20px 0">
            <el-row :gutter="10">
                <el-col :span="6" v-for="item in files" :key="item.id" style="margin-bottom: 10px">

                    <div style="border: 1px solid #ccc; padding-bottom: 10px">
                        <img :src="item.url" alt=""  style="width: 100%;height: 300px"  >
                        <div style="color: #666; padding: 10px">{{ item.name }}</div>

                        <div style="padding: 10px">

                            <el-button type="primary"><i class="el-icon-link"></i> <a style="color: #fff" href="https://www.jd.com/">购买</a></el-button>
                            <el-button type="warning"><i class="el-icon-link"></i> <a style="color: #fff" href="https://www.jd.com/">加入购物车</a></el-button>
                        </div>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Shop",
        data:function(){
            return{
                imgs:[
                    {url:require('../assets/JD1.webp'),link:'https://www.jd.com'},
                    {url:require('../assets/JD2.webp'),link:'https://www.jd.com'},
                ],
                files: []
            }
        },

        // data() {
        //     return {
        //         imgs: [
        //
        //             'https://img30.360buyimg.com/babel/s1580x830_jfs/t1/109361/24/22897/74054/621ede58E099d37e3/f12730c81df6046a.jpg!cc_1580x830.webp',
        //             'https://img13.360buyimg.com/babel/s1580x830_jfs/t1/96398/30/23715/70228/6221e9d0Ec1b9fe65/f66e2ad76314d6cd.jpg!cc_1580x830.webp'
        //         ],
        //         files: []
        //     }
        // },
        created() {
            this.request.get("/echarts/file/front/all").then(res => {
                console.log(res.data)
                this.files = res.data.filter(v => v.type === 'png' || v.type === 'jpg' || v.type === 'webp'|| v.type === 'jpeg')
            })
        },
        methods: {
            linkTo () {
                let activeIndex = this.$refs.carousel.activeIndex
                window.open(this.imgs[activeIndex].link,"_blank")
            }
        },

    }
</script>

<style>

</style>
