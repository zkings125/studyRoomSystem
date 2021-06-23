// pages/recharge/recharge.js
const app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //充值记录
    listData:[
      //测试数据
      // {"customerID":"01","time":"2021-01-02 12:00","length":"30"},
      // {"customerID":"02","time":"2021-02-03 13:00","length":"100"},
      // {"customerID":"01","time":"2021-01-02 12:00","length":"30"},
      // {"customerID":"02","time":"2021-02-03 13:00","length":"100"}
    ]
  },

  onLoad: function () {
    console.log('onLoad') 
  },
  
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that=this
    var customer_id=wx.getStorageSync('customerID')
    // console.log(that.data)
    //调用获取时长接口/getTimeLen获取当前剩余时长
    wx.request({
      url: app.globalData.api_url+'/recharge',
      method: "POST",
      data:{
        customerID:customer_id,
      },
      header: {
        'content-type':'application/x-www-form-urlencoded'
       //  'content-type':'application/json'//GET请求
       },
      success: function (resp) {
        console.log(resp.data)
        for (var x in resp.data) {
          resp.data[x].time =  resp.data[x].time.substring(0,19).replace('T',' ')
          console.log(resp.data[x].time)
        }
        that.setData({
          listData:resp.data//充值记录
        })
        console.log(that.data.listData)
        console.log("kk")
      }
    })
  }
})