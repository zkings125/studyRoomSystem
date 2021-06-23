<template>
<div class="lyear-layout-web" id="app">
  <div class="lyear-layout-container">
    <!--左侧导航-->
    <aside class="lyear-layout-sidebar">
       
      <!-- logo -->
      <div id="logo" class="sidebar-header">
        <a href="index.html"><img src="static/images/logo.png" title="LightYear" alt="LightYear" /></a>
      </div>
      <div class="lyear-layout-sidebar-scroll">
        
        <nav class="sidebar-main">
          <ul class="nav nav-drawer">
            <!-- <li class="nav-item"> <a href="index.html"><i class="mdi mdi-home"></i> 后台首页</a> </li> -->
            <li class="nav-item nav-item-has-subnav active">
              <router-link to="./customer_table"><i class="mdi mdi-format-align-justify"></i>顾客管理</router-link>
              <!-- <a href="./customer_table"><i class="mdi mdi-format-align-justify"></i>顾客管理</a> -->
              <!-- <a href="./customer_table"><i class="mdi mdi-format-align-justify"></i>顾客管理</a> -->
            </li>
            <li class="nav-item nav-item-has-subnav">
              <router-link to="./studyroom_table"><i class="mdi mdi-format-align-justify"></i>自习室管理</router-link>
              <!-- <a href="javascript:void(0)"><i class="mdi mdi-file-outline"></i> 自习室管理</a> -->
            </li>
            <li class="nav-item nav-item-has-subnav">
              <!-- <a href="javascript:void(0)"><i class="mdi mdi-palette"></i>权限管理</a> -->
              <!-- <a href="./admin_table" ref="power"><i class="mdi mdi-format-align-justify"></i>权限管理</a> -->
              <router-link to="./admin_table" ><i class="mdi mdi-format-align-justify"></i><span class="power" ref="power">管理员管理</span></router-link>
            </li>
          </ul>
        </nav>
      </div>
      
    </aside>
    <!--End 左侧导航-->
     <header class="lyear-layout-header">
      
      <nav class="navbar navbar-default">
        <div class="topbar">
          
          <div class="topbar-left">
            <div class="lyear-aside-toggler">
              <span class="lyear-toggler-bar" ref="test"></span>
              <span class="lyear-toggler-bar"></span>
              <span class="lyear-toggler-bar"></span>
            </div>
            <span class="navbar-page-title" v-text="title"> 示例页面 - 文档列表 </span>
          </div>
          
          <ul class="topbar-right">
            <li class="dropdown dropdown-profile">
              <a href="javascript:void(0)" data-toggle="dropdown">
                <img class="img-avatar img-avatar-48 m-r-10" src="static/images/users/avatar.jpg" alt="笔下光年" />
                <span>{{adminID}} <span class="caret"></span></span>
              </a>
              <ul class="dropdown-menu dropdown-menu-right">
                <!-- <li> <router-link to="./userinfo"><i class="mdi mdi-account"></i> 个人信息</router-link> </li> -->
                <li> <router-link class="nav-item" :to="{path:'./update_password',query:{'adminID':adminID}}"><i class="mdi mdi-lock-outline"></i> 修改密码</router-link> </li>
                <!-- <li> <a href="javascript:void(0)"><i class="mdi mdi-delete"></i> 清空缓存</a></li> -->
                <li class="divider"></li>
                <li> <a href="/login.html"><i class="mdi mdi-logout-variant"></i> 退出登录</a> </li>
              </ul>
            </li>
            <!--切换主题配色-->

            <!--切换主题配色-->
          </ul>
          
        </div>
      </nav>
      
    </header>
    
    <!--页面主要内容-->
    <main class="lyear-layout-content">
        <router-view :key="$route.fullPath" name="content"/>
    </main>
    <!--End 页面主要内容-->
  </div>
</div>

</template>


<script>
import $ from 'jquery'

export default {
  name: 'Layout',
  data(){
    return{
      adminID:"",
      title:"顾客管理" //v-text
    }
  },
  mounted:function(){
    var that = this;
    $(".nav-item").click(function(e){
      var title = e.target.innerText;
      that.title = title //页面切换时的标题
      // console.log($(this));
      // console.log(this.$refs.title);
      // $(".nav-item")[0].removeClass("active")
      $(this).addClass("active").siblings().removeClass("active")
    })
  },
  created(){
    var that = this;
    $.ajax({
    type:"get",
    dataType:"JSON",
    // async:false,
    // contentType : "application/json",
    url: that.GLOBAL.API_ROOT + "/admininfo",
    success:function (result) {
        console.log(result);
        that.adminID = result.adminID
        var power = that.$refs.power;
        // console.log(that.$refs.power);
        // 判断权限
        if(result.power == "1"){
          // console.log(power.parentElement);
          power.parentElement.style.display = "none"
        }else{
          power.parentElement.style.display = "block"
        }
    },
    error:function (result) {
        console.log(result)
    }
    }) 
  }
}
</script>
