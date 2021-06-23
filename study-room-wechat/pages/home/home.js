// home.js
// 获取应用实例
const app = getApp()
const obsPath = "https://study-room.obs.cn-north-4.myhuaweicloud.com"
//此处的Key为在腾讯地图开发平台中申请的对应自身APPID的Key，视情况更换
const ljqMapKey = "IG3BZ-M4CES-N2ROJ-6NXOU-UPV6J-36BOP"
const tjyMapKey = "DFCBZ-42BRF-UX5JL-NTJQS-O6DWO-DTB4X"
const wxfMapKey = "C2KBZ-2BJ3J-IGVFR-K26XQ-53GK3-7QFXZ"
const MapKey = ljqMapKey

// 引入SDK核心类
var QQMapWX = require('../../libs/qqmap-wx-jssdk.js');

// 实例化API核心类
var qqmapsdk = new QQMapWX({
  key: MapKey // 必填
});

Page({
  data: {
    navigation_icon: obsPath + "/navigate_icon.jpg",
    ssRoomList: []
  },
  onLoad() {
    if (wx.getUserProfile) {
      this.setData({
        canIUseGetUserProfile: true
      })
    }
  },
  onShow: function () {
    var that = this;
    wx.request({
      url: app.globalData.api_url + '/getRoom',
      method: "GET",
      success(res) {
        that.setData({
          ssRoomList: res.data
        })
        console.log(res)
        wx.setStorageSync('location', res.data)
      }
    })
  },
  // 事件处理函数
  navigate: function (event) {
    var _this = this;
    wx.setStorageSync('address', event.currentTarget.dataset.address)
    wx.setStorageSync('roomName', event.currentTarget.dataset.roomname)
    wx.request({
      url: 'https://apis.map.qq.com/ws/geocoder/v1/',
      method: 'GET',
      data: {
        key: MapKey,
        address: wx.getStorageSync('address')
      },
      success: function (resp) {
        wx.setStorageSync('location', resp.data.result.location)
        // var loc = wx.getStorageSync('location')
        _this.onGuideTap()
      },
      //定位失败回调
      fail: function (err) {
        console.log(err)
        wx.showModal({
          title: '提示',
          content: '定位失败，请检查您的设置',
          duration: 2000,
          success: function () {
            wx.navigateBack({
              delta: 0,
            })
          }
        })
      }
    })
  },
  //导航
  onGuideTap: function () {
    var _this = this;
    var location = wx.getStorageSync('location')
    wx.getLocation({
      type: 'gcj02',
      success: (res) => {
        _this.setData({
          startLat: Number(res.latitude),
          startLng: Number(res.longitude),
        })
        var desLat = Number(location.lat)
        var desLng = Number(location.lng)
        wx.openLocation({
          title: _this.data.roomName,
          latitude: desLat,
          longitude: desLng,
          name: _this.data.roomName,
          address: wx.getStorageSync('address'),
          scale: 18
        })
      }
    })
  },
  //订座
  appoint: function (event) {
    console.log(wx.getStorageSync('customerID'))
    if (wx.getStorageSync('customerID') != '') {
      wx.setStorageSync('roomName', event.currentTarget.dataset.roomname)
      wx.setStorageSync('roomID', event.currentTarget.dataset.roomid)
      wx.navigateTo({
        url: '../chooseTime/chooseTime',
      })
    }else{
      wx.showToast({
        title: '请先登录',    
        icon: 'error',    
        duration: 1000//持续的时间
      })
    }

    // console.log(wx.getStorageSync('roomName'))
  }


})