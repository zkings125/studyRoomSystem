import * as echarts from '../../ec-canvas/echarts';

//页外页面指针
let that = null;
const app = getApp()

function initChart(canvas, width, height) {
  let perDays = [];
  let date = [];
  const chart = echarts.init(canvas, null, {
    width: width,
    height: height,
  });
  canvas.setChart(chart);
  //调用接口
  let preDay = new Promise(function(resolve,reject) {
    wx.request({
      url: app.globalData.api_url + '/get_per_study',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        customerID: wx.getStorageSync('customerID'),
      },
      success: function (resp) {
        //使用增强for循环访问
        for (var x in resp.data) {
          date.push(resp.data[x].date)
          perDays.push(resp.data[x].perDay)

        }
        console.log(resp)
        resolve()
      }
    })
  })
  preDay.then(function resolve() {
    var option = {
      xAxis: [{
        type: 'category',
        name: '日期',
        show: true, //这里呢，就是每个柱的name了，根据实际情况下就好了，我就先写这三个
        data: [...date],
        // data:["2021-01-02","2021-01-02","2021-01-02","2021-01-02","2021-01-02","2021-01-02","2021-01-02"]
      }],
      yAxis: [{
        type: 'value',
        name: '数量', //下面的就很简单了，最小是多少，最大是多少，默认一次增加多少
      }],
      series: [{
        name: '数量',
        type: 'bar',
        itemStyle: {
          normal: {
            color: function (params) {
              // build a color map as your need.
              var colorList = [
                '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
              ];
              return colorList[params.dataIndex % 15]
            },
            //以下为是否显示，显示位置和显示格式的设置了
            label: {
              show: true,
              position: 'top',
              formatter: '{c}'
            }
          }
        },
        //设置柱的宽度
        barWidth: 10,
        data: [...perDays]
        // data:[10,20,15,20,10,20,10]
      }]
    };
    chart.setOption(option);
    return chart;
  })
}
var page = Page({
  data: {
    studyTime: 0,
    ec: {
      onInit: initChart
    },
  },
  onLoad() {
    that = this; //页外保存page指针
  },
  onUnload() {
    that = null; //记得释放
  },
  onShow: function () {
    //调用接口获取总学习时间
    var _this = this
    // var customerID=wx.getStorageSync('customerID')
    var customerID = "100"
    wx.request({
      url: app.globalData.api_url + '/get_sum_stu_length',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        customerID: wx.getStorageSync('customerID')
      },
      success: function (resp) {
        _this.setData({
          studyTime: resp.data
        })
        console.log(resp)
      }
    })
  }
})

