// pages/appoint/appoint.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl:'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132',
    nickName:'欢迎',
    pointerEvents:"auto",
    number:"点击登录",
    type:"黄金会员",
    flag:true,
    isLogin:false,
    menLength:''
  },
  onShow(){
    if(this.data.isLogin){
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
            menLength:resp.data//设置当前剩余时长
          })
          // console.log(that.data)
        }
      })
    }
  },

  Login:function(){
    let that = this;
    console.log("登录")
    wx.login({
      success(resp){
        console.log(resp);
         wx.setStorageSync('code', resp.code)
      }
    })
    var getUserPro = new Promise(function(resolve,reject){
      wx.getUserProfile({
        desc: '获取用户信息',
        success(resp){
          wx.setStorageSync('nickName', resp.userInfo.nickName);
          that.setData({
            avatarUrl:resp.userInfo.avatarUrl,
            nickName:resp.userInfo.nickName,
            isLogin:true
          })
          resolve();
        }
      })

    })
    getUserPro.then(function(){
       wx.request({
      url: app.globalData.api_url + '/loginWx',
      method:"POST",
      header:{
        'content-type':"application/x-www-form-urlencoded"
      },
      data:{
        js_code:wx.getStorageSync('code'),
        nickName:wx.getStorageSync('nickName')
      },
      success(resp){
        console.log(resp)
        wx.setStorageSync('customerID', resp.data[0].customerID),
        wx.setStorageSync('menLength', resp.data[0].memLength)
        that.setData({
          nickName:resp.data[0].customerName,
          menLength:resp.data[0].memLength
        })
        resp.data[0]
      },
      fail(Res){
        console.log(Res)
      }
    })}
    )
  },
  //跳转至充值
  goChargeTime:function(){
    wx.navigateTo({
      url: '../chargetime/chargetime',
    })
  },

  //跳转学习记录
  goLearnReport:function(){
    wx.navigateTo({
      url: '../learnReport/learnReport',
    })
  },
  
  //跳转我的订单
  goAppoinRecord:function(){
    wx.navigateTo({
      url: '../appointRecord/appointRecord',
    })
  }

})