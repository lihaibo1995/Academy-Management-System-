<template>
  <div>

    <el-row :gutter="10" style="margin-bottom: 60px">
      <el-col :span="5">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid" /> 用户总数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            <span>{{amountList[0]}}人</span><i style="margin-left: 5px"></i>
          </div>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card style="color: #F56C6C">
          <div><i class="el-icon-user-solid" /> 学生人数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            <span>{{amountList[1]}}人</span><i style="margin-left: 5px"></i>
          </div>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-user-solid" /> 老师人数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            <span>{{amountList[2]}}人</span><i style="margin-left: 5px"></i>
          </div>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card style="color: #E6A23C">
          <div><i class="el-icon-user-solid" /> 管理员人数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            <span>{{amountList[3]}}人</span><i style="margin-left: 5px"></i>
          </div>
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card style="color: #8c939d">
          <div><i class="el-icon-user-solid" /> 散户</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            <span>{{amountList[4]}}人</span><i style="margin-left: 5px"></i>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height: 450px"></div>
      </el-col>

      <el-col :span="12">
        <div id="pie" style="width: 500px; height: 400px"></div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: "DashBord",

  data() {
    return {
      amountList: [],


    }
  },

  created() {
    this.load()
  },



  methods: {


    load() {
      this.request.get("/echarts/getAmountList").then(res => {
        this.amountList=res.data

      })

    },

  },

  mounted() {  // 页面元素渲染之后再触发

    //折线图
    var option = {
      title: {
        text: '各季度师生注册人员数量统计',
        subtext: '趋势图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      xAxis: {
        type: 'category',
        data: ["第一季度", "第二季度", "第三季度", "第四季度"]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: "学生",
          data: [],
          type: 'bar'
        },
        {
          name: "学生",
          data: [],
          type: 'line'
        },
        {
          name: "老师",
          data: [],
          type: 'bar'
        },
        {
          name: "老师",
          data: [],
          type: 'line'
        }
      ]
    };

    // 饼图

    var pieOption = {
      title: {
        text: '各季度师生注册人员数量统计',
        subtext: '比例图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      //第一个饼图
      series: [
        {
          name: "学生",
          type: 'pie',
          radius: '55%',
          center: ['25%', '70%'],
          label:{            //饼图图形上的文本标签
            normal:{
              show:true,
              position:'inner', //标签的位置
              textStyle : {
                fontWeight : 300 ,
                fontSize : 14,    //文字的字体大小
                color: "#fff"
              },
              formatter:'{c}({d}%)'
            }
          },
          data: [],  // 填空
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        },

              //第二个饼图
        {
          name: "老师",
          type: 'pie',
          radius: '50%',
          center: ['75%', '50%'],
          label:{            //饼图图形上的文本标签
            normal:{
              show:true,
              position:'inner', //标签的位置
              textStyle : {
                fontWeight : 300 ,
                fontSize : 14,    //文字的字体大小
                color: "#fff"
              },
              formatter:'{d}%'
            }
          },
          data: [],  // 填空
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ],

    };


    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);

    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);

    this.request.get("/echarts/student").then(res => {
      // 填空
      //数据库查的学生人数
      option.series[0].data = res.data
      option.series[1].data = res.data

      myChart.setOption(option);


      //饼状图，学生人数
      pieOption.series[0].data = [
        {name: "第一季度", value: res.data[0]},
        {name: "第二季度", value: res.data[1]},
        {name: "第三季度", value: res.data[2]},
        {name: "第四季度", value: res.data[3]},
      ]
      pieChart.setOption(pieOption)
    })

    this.request.get("/echarts/teacher").then(res => {
      //数据库查的老师人数
      option.series[2].data = res.data
      option.series[3].data = res.data
      // 数据准备完毕之后再set
      myChart.setOption(option);


      //饼状图，学生人数
      pieOption.series[1].data = [
        {name: "第一季度", value: res.data[0]},
        {name: "第二季度", value: res.data[1]},
        {name: "第三季度", value: res.data[2]},
        {name: "第四季度", value: res.data[3]},
      ]
      pieChart.setOption(pieOption)
    })




  }
}
</script>

<style scoped>

</style>
