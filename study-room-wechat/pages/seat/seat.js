// pages/seat/seat.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  
  data:{
    seat:[
      {
        num:1,
        status:"null"
      },
      {
        num:2,
        status:"chance"
      },
      {
        num:3,
        status:"null"
      },
      {
        num:4,
        status:"chance"
      },
      {
        num:5,
        status:"null"
      },
      {
        num:6,
        status:"null"
      },
      {
        num:7,
        status:"now"
      },{
        num:8,
        status:"null"
      },{
        num:9,
        status:"null"
      },{
        num:10,
        status:"null"
      },{
        num:11,
        status:"null"
      },{
        num:12,
        status:"null"
      },
      {
        num:13,
        status:"chance"
      },
      {
        num:14,
        status:"null"
      },
      {
        num:15,
        status:"chance"
      },
      {
        num:16,
        status:"null"
      },
      {
        num:17,
        status:"null"
      },
      {
        num:18,
        status:"now"
      },{
        num:19,
        status:"null"
      },{
        num:20,
        status:"null"
      },{
        num:21,
        status:"null"
      },{
        num:22,
        status:"null"
      },{
        num:23,
        status:"null"
      },
      {
        num:24,
        status:"chance"
      },
      {
        num:25,
        status:"null"
      },
      {
        num:26,
        status:"chance"
      },
      {
        num:27,
        status:"null"
      },
      {
        num:28,
        status:"null"
      },
      {
        num:29,
        status:"now"
      },{
        num:30,
        status:"null"
      },{
        num:31,
        status:"null"
      },{
        num:32,
        status:"null"
      },{
        num:33,
        status:"null"
      },{
        num:34,
        status:"null"
      },
      {
        num:35,
        status:"chance"
      },
      {
        num:36,
        status:"null"
      },
      {
        num:37,
        status:"chance"
      },
      {
        num:38,
        status:"null"
      },
      {
        num:39,
        status:"null"
      },
      {
        num:40,
        status:"now"
      },{
        num:41,
        status:"null"
      },{
        num:42,
        status:"null"
      },{
        num:43,
        status:"null"
      },{
        num:44,
        status:"null"
      }
    ],
    status:{
      "true":"../../static/photo/座位0.png",
      "now":"../../static/photo/座位1.png",
      "false":"../../static/photo/座位2.png"
    },
    now:-1,
    index:-1,
    startTime:wx.getStorageSync('starttime')
  },

  onShow(){
    let that = this;
    console.log(wx.getStorageSync("starttime"))
    console.log(wx.getStorageSync("endtime"))
    wx.request({
      url: app.globalData.api_url + '/getSeatsByTime',
      method:"POST",
      header:{
        'content-type':"application/x-www-form-urlencoded"
      },
      data:{
        ssRoomID:Number(wx.getStorageSync('roomID')),
        // ssRoomID:1,
        beginTime:wx.getStorageSync('starttime'),
        endTime:wx.getStorageSync('endtime')
        
      },
      success(res){
        console.log(res);
        that.setData({
          seat:res.data
        })
        console.log(that.data.seat[0].useful.toString())
      },
      fail(res){
        console.log(res)
      }
    })
  },

  choose(e){ 
    console.log(e.target.dataset.item);
    if(this.data.seat[e.target.dataset.item].useful.toString() == 'false'){
      wx.showModal({
        title:"冲突订单时间",
        content: this.data.seat[e.target.dataset.item].useTime,
      })
      return;
    }else if(this.data.seat[e.target.dataset.item].useful == 'now'){
      let up = "seat[" + e.target.dataset.item + "].useful";
      this.setData({
        now:-1,
        index:-1,
        [up]:"true"
      })
      return
    }
    let up = "seat[" + this.data.index + "].useful";
    if(this.data.index != -1){
      this.setData({
        [up]:"true"
      })
    }
    this.data.index = e.target.dataset.item ;
    up = "seat[" + this.data.index + "].useful";
    this.setData({
      [up]:"now",
      now:this.data.seat[this.data.index].seatID
    })
  },

  nextStep(){
    if(this.data.now == -1){
      wx.showToast({
        title: '请选择座位',    
        icon: 'error',    
        duration: 1000//持续的时间
      })
      return;
    }
    let that = this;
    wx.showModal({
      title:"确认订单",
      content:"自习室："+ wx.getStorageSync('roomName') +"\n座位号" + that.data.now +  "\n开始时间:" + wx.getStorageSync('starttime') + "\n结束时间:" + wx.getStorageSync('endtime'),
      success:function(res){
        console.log("11111111111111" + wx.getStorageSync('studyTime'))
        if(res.confirm){
          //调用增加预约路径
          wx.request({
            url: app.globalData.api_url + "/AddRecord",
            method:"POST",
            header:{
              'content-type':"application/x-www-form-urlencoded"
            },
            data:{
              customerID:wx.getStorageSync('customerID'),
           
              ssRoomID:Number(wx.getStorageSync('roomID')),
              seatID:that.data.now,
              startTime:wx.getStorageSync('starttime'),
              studyLen:Number(wx.getStorageSync('studytime') * 60)
            },
            success(res){
              console.log(res)
              //返回成功后弹出提示
              if(res.data.stateCode == 137){
                console.log(wx.getStorageSync('studytime'))

                console.log(-wx.getStorageSync('studytime'))
                wx.request({
                  url: app.globalData.api_url+'/chargeTime',
                  method: "POST",
                  data:{
                    length:-wx.getStorageSync('studytime'),
                    customerID:wx.getStorageSync('customerID'),
                  },
                  header: {
                   'content-type':'application/x-www-form-urlencoded'
                  //  'content-type':'application/json'//GET请求
                  },
                  success: function (resp) {
                   console.log("修改成功")
                  },
                })
                wx.showToast({
                  title: '预定成功',    
                  icon: 'success',    
                  duration: 1000//持续的时间
                }),

                //1s后返回首页
                setTimeout(function(params) {
                    wx.navigateBack({
                      delta: 2
                    })
                },1000)
              
              
              
              }else{
                //后台返回不成功提示
                wx.showToast({
                  title: '预定失败',    
                  icon: 'error',    
                  duration: 1000//持续的时间
                })
              }
            },
            //后台返回不成功提示
            fail(){
              wx.showToast({
                title: '预定失败',    
                icon: 'error',    
                duration: 1000//持续的时间
              })
            }
          })
        }
      }
      
    })
  }
})