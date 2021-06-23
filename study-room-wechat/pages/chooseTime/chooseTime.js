// index.js
// 获取应用实例
const app = getApp()
var datePicker = require('../../utils/dateSetting.js')
 
//设定当前的时间，将其设定为常量
var date = new Date();
var year = date.getFullYear();
var month = date.getMonth() + 1;
Page({
  data: {
    currentId: '1',
    section: [{
      name: '马上入座',
      typeId: '1'
    }, {
      name: '订座',
      typeId: '2'
    }],
    date:'',
    starttime: '',
    endtime:'',
    multiArray: [],
    multiIndex: [0, 0, 0, 0],
    choose_year: "",
    remainHour:[],
    bgcolor:'',
    colornext:'',
    isDisabled:'',
    key:'',
    start:'',
    end:'',
    studyHour:'',
    address:'',
  },
  handleTap: function(e) {
    let id = e.currentTarget.id;
    if(id){
      this.setData({
        currentId:id
      })
    }
    if(id==1){
      this.setData({
        starttime:'',
        endtime:'',
        isDisabled:'true',
        colornext:' background-color:rgb(250, 249, 245);',
      })
    }else{
      this.setData({
        key:'',
        isDisabled:'true',
        colornext:' background-color:rgb(250, 249, 245);',
      })
    }
  },
  initial:function(){
    this.setData({
      bgcolor:' background-color:rgb(255, 255, 255);',
      isDisabled:'true',
      colornext:' background-color:rgb(250, 249, 245);',
      starttime:'',
      endtime:'',
      key:'',
      studyHour:''
    })
    date = new Date
  },
  onShow:function(){
    let remainHour = [];
    let hour = 24-(date.getHours()+1);
    for (let i = 1; i <= hour; i++) {
      remainHour.push(i+"h")
  }
      remainHour.push('到打烊')
    this.setData({
      remainHour:remainHour,
       //获取自习室名称
      address:wx.getStorageSync('roomName'),
    })
    this.initial();
   
  },
  //与wxml中的数据一一对应，在具体函数中修改time、multiArray、multiIndex等值，进行页面的呈现
  //Page原始的加载函数，设定multiArray，其中datePicker中的函数，会在下面的js中呈现。
  onLoad: function () {
    this.setData({
      multiArray:
        [
          [year + "年", year + 1 + "年"],
          datePicker.determineMonth(),
          datePicker.determineDay(year, month),
          datePicker.determineHour()
        ],    
    })
  },
  //最后呈现时间的函数。
  bindMultiPickerChange: function (e) {
    var startTime = this.data.multiArray[0][this.data.multiIndex[0]] +
      this.data.multiArray[1][this.data.multiIndex[1]] +
      this.data.multiArray[2][this.data.multiIndex[2]] +
      this.data.multiArray[3][this.data.multiIndex[3]];
    this.setData({
      starttime: startTime,
    })
    this.chooseTime();
  },
  bindMultiPickerChange2: function (e) {
    var endTime = this.data.multiArray[0][this.data.multiIndex[0]] +
      this.data.multiArray[1][this.data.multiIndex[1]] +
      this.data.multiArray[2][this.data.multiIndex[2]] +
      this.data.multiArray[3][this.data.multiIndex[3]];
    this.setData({
     endtime: endTime,
    })
    this.chooseTime();
},
  chooseTime:function(){
    if (this.data.starttime!=""&&this.data.endtime!=""){
      var startdate = new Date();
      var enddate = new Date();
      var reg = /(\d+)年(\d+)月(\d+)日(\d+)时/;
      reg.test(this.data.starttime)
      startdate.setFullYear(parseInt(RegExp.$1));  
      startdate.setMonth(parseInt(RegExp.$2)-1);
      startdate.setDate(parseInt(RegExp.$3));
      startdate.setHours(parseInt(RegExp.$4));
      reg.test(this.data.endtime)
      enddate.setFullYear(parseInt(RegExp.$1));    
      enddate.setMonth(parseInt(RegExp.$2)-1);
      enddate.setDate(parseInt(RegExp.$3));
      enddate.setHours(parseInt(RegExp.$4));
      var memLenth = (enddate.getTime()-startdate.getTime())/1000/60/60;
      if(startdate.getTime()<enddate.getTime()){
        this.setData({
          isDisabled:'false', 
          colornext:'background-color:rgb(255, 127, 89)',
          studyHour:memLenth,
          start:startdate,
          end:enddate
        })
      }else{
        this.initial()
        wx.showToast({
          title: '结束时间不能早于开始时间!',
          icon: 'none',  
          duration: 2000//持续的时间  
        })

      }
      if((startdate.getTime()+60000)<date.getTime()){
        this.initial()
        wx.showToast({
          title: '开始时间不能早于当前时间!',
          icon: 'none',  
          duration: 2000//持续的时间  
        })
      }
    }
  },
  //当时间选择器呈现并进行滚动选择时间时调用该函数。
  bindMultiPickerColumnChange: function (e) {
    //e.detail.column记录哪一行发生改变，e.detail.value记录改变的值（相当于multiIndex）
    switch (e.detail.column) {
      //这里case的值有0/1/2/3/4,但除了需要记录年和月来确定具体的天数外，其他的都可以暂不在switch中处理。
      case 0:
        //记录改变的年的值
        let year = this.data.multiArray[0][e.detail.value];
        this.setData({
          choose_year: year.substring(0, year.length - 1)
        })
        break;
      case 1:
        //根据选择的年与月，确定天数，并改变multiArray中天的具体值
        let month = this.data.multiArray[1][e.detail.value];
        let dayDates = datePicker.determineDay(this.data.choose_year, month.substring(0, month.length - 1));
        //这里需要额外注意，改变page中设定的data，且只要改变data中某一个值，可以采用下面这种方法
        this.setData({
          ['multiArray[2]']: dayDates,
        })
        break;
    }
    //同上，上面改变的是二维数组中的某一个一维数组，这个是改变一个一维数组中某一个值。
    this.setData({
      ["multiIndex[" + e.detail.column + "]"]: e.detail.value
    })
  },
  changetime:function(now) {
    　　var y = now.getFullYear()
    　　var m = now.getMonth() + 1
    　　var d = now.getDate()
    　　return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
    },
  chooseChange:function(e){
        this.setData({
          key:e.currentTarget.dataset.item,
          isDisabled:'false', 
          colornext:'background-color:rgb(255, 127, 89)',     
        })    
        var reg = /(\d+)小时/;
        console.log(reg.test(this.data.key))
        var memLenth= parseInt(RegExp.$1)
        this.setData({
            studyHour:memLenth
        })
        // wx.setStorageSync('menLenth', memLenth) 
        // console.log(wx.getStorageSync('menLenth'))           
  },
  goTohome:function(){
    wx.navigateBack({
      delta: 0,
    })
  },
  goToNext:function(e){
    if(this.data.isDisabled=='false'){
      wx.showToast({
        title: '选择时间成功',    
        icon: 'success',    
        duration: 1000//持续的时间
      })
      if(isNaN(this.data.studyHour)){
        console.log("ininininini")
        wx.setStorageSync('studytime',24-date.getHours()-1)
        wx.setStorageSync('starttime', this.changetime(date))
        var endtime = new Date(date.setHours(24,0,0))
        wx.setStorageSync('endtime', this.changetime(endtime))
      
      }else if(this.data.start!=""&&this.data.endt!=""){
        wx.setStorageSync('studytime', this.data.studyHour)
        wx.setStorageSync('starttime', this.changetime(this.data.start))
        wx.setStorageSync('endtime', this.changetime(this.data.end))
        console.log(wx.getStorageSync('studytime'))
      }else{
        wx.setStorageSync('studytime', this.data.studyHour)
        wx.setStorageSync('starttime', this.changetime(date))   
        var endtime = new Date(date.setHours(date.getHours()+this.data.studyHour))
        wx.setStorageSync('endtime', this.changetime(endtime))
      }
     this.initial();
     wx.navigateTo({
       url: '../seat/seat',
     })
    }else{
          console.log("错误")
          wx.showToast({
            title: '请选择时间！',
            icon: 'none',  
            duration: 2000//持续的时间  
          })
    }  
  },
})
