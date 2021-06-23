// pages/chargetime/chargetime.js
const app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    balance: 0,//余额
    activeIndex: 0, //默认选中第一个
    numArray: [10, 20, 30, 40, 50,60,70,80,'m'],
    money: 0,//充值金额
  },
  activethis: function (event) { //点击选中事件
    var thisindex = event.currentTarget.dataset.thisindex; //当前index
    this.setData({
      activeIndex: thisindex
    })
  },
  //获取自定义金额
  mInput:function(e) {
    this.setData({
      money: e.detail.value
    })
    console.log("自定义设置的金额为："+e.detail.value)
  },
  //时间格式转化
  getdate:function(now) {
  　　var y = now.getFullYear()
  　　var m = now.getMonth() + 1
  　　var d = now.getDate()
  　　return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
  },
  //选择充值金额
  recharge:function () {
    var that=this
    var activeIndex=that.data.activeIndex//当前选中的编号
    if(that.data.numArray[activeIndex]!='m'){
      that.setData({
        money: that.data.numArray[activeIndex]//充值的金额 
      }) 
      console.log("目前选中的金额"+that.data.money)
    }
    //检查是否允许充值（充值时长为10的整数倍）
    if(that.data.money%10==0){
      var that=this
      var customer_id=wx.getStorageSync('customerID')//用户账号
      //允许充值，询问
      wx.showModal({
        title: '提示',
        content: '您的充值时长为'+that.data.money+'小时，是否确认充值？',
        success: function (res) {
            if (res.confirm) {
                console.log('用户点击确定')
                //调用充值接口/chargeTime进行充值
                wx.request({
                  url: app.globalData.api_url+'/chargeTime',
                  method: "POST",
                  data:{
                    length:that.data.money,
                    customerID:customer_id,
                  },
                  header: {
                   'content-type':'application/x-www-form-urlencoded'
                  //  'content-type':'application/json'//GET请求
                  },
                  success: function (resp) {
                   console.log("充值成功")
                   that.onShow()
                   var nowTime=new Date()
                   var time=that.getdate(nowTime)//获取当前充值时间
                   console.log("当前充值时间:"+time)
                   //将充值记录插入到表中，调用接口/insertRecharge
                   wx.request({
                    url: app.globalData.api_url+'/insertRecharge',
                    method: "POST",
                    data:{
                      customerID:customer_id,
                      time:time,
                      length:that.data.money
                    },
                    header: {
                      'content-type':'application/x-www-form-urlencoded'
                     //  'content-type':'application/json'//GET请求
                     },
                    success: function (resp) {
                      console.log(resp)
                      // console.log(that.data)
                    }
                  })
                  },
                })
            }else{
              console.log('用户点击取消')
            }
        }
      })
    } else {
      wx.showModal({
        title: '提示',
        content: '请输入10的整数倍哦！',
        // cancelText:'不是',
        showCancel: false,
        success (res) {
          if (res.confirm) {
            console.log('用户点击')
          } else if (res.cancel) {
            console.log('用户点击取消')
          }
        }
      })   
    }
  },
  //历史充值
  getRecharge: function () {
    wx.navigateTo({
      url: '../recharge/recharge'
    })
  },
  //获取剩余时长
  onShow:function() {
    var that=this
    // console.log(that.data)
    //调用获取时长接口/getTimeLen获取当前剩余时长
    wx.request({
      url: app.globalData.api_url+'/getTimeLen',
      method: "GET",
      data:{
        customerID:wx.getStorageSync('customerID'),
      },
      success: function (resp) {
        that.setData({
          balance:resp.data//设置当前剩余时长
        })
        // console.log(that.data)
      }
    })
  }
})