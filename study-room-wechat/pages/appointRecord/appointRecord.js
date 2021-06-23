const app=getApp()
Page({
  data: {
    navState: 0,//导航状态
    //历史订单
    listDate:[
      //测试数据
      // {"appointID":"01","customerID":"text1","ssRoomID":"1","seatID":1,"startTime":"2020-01-02 19:00","studyLen":1},
      // {"appointID":"02","customerID":"text2","ssRoomID":"1","seatID":2,"startTime":"2020-01-02 19:00","studyLen":1},
      // {"appointID":"03","customerID":"text3","ssRoomID":1,"seatID":3,"startTime":"2020-01-02 19:00","studyLen":1},
      // {"appointID":"04","customerID":"text4","ssRoomID":1,"seatID":4,"startTime":"2020-01-02 19:00","studyLen":1}
    ]
  },
  //点击导航
  navSwitch: function(e) {
    let index = e.currentTarget.dataset.index;
    this.setData({
      navState:index
    })
  },
 
  /**
  * 生命周期函数--监听页面加载
  */
  onShow: function(options) {
    console.log("l"+this.data.listDate)
    var that=this
    var customer_id=wx.getStorageSync('customerID')
    console.log(customer_id+"llll")
    //调用获取订单的接口
    wx.request({
      url: app.globalData.api_url+'/appointRecord',
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
        that.setData({
          listDate:resp.data//预约记录
        })
        console.log(that.data.listDate)
        // console.log(that.data.listDate)
        console.log("kk")
      }
    })
  },
 })