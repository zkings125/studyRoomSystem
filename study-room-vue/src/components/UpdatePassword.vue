<template>
    <div class="container-fluid">
        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-body">
                <form ref="form" method="post" class="site-form">
                  <div  class="form-group">
                    <label for="old-password">管理员账号</label>
                    <input v-model="adminID" type="text" class="form-control" name="id" id="id" disabled>
                  </div>
                  <div class="form-group">
                    <label for="old-password">旧密码</label>
                    <input required v-model="oldPassword" type="password" class="form-control" id="old-password" placeholder="输入账号的原登录密码">
                  </div>
                  <div class="form-group">
                    <label for="new-password">新密码</label>
                    <input required v-model="password" type="password" class="form-control" name="newpwd" id="new-password" placeholder="输入新的密码">
                  </div>
                  <div class="form-group">
                    <label for="confirm-password">确认新密码</label>
                    <input required v-model="rePassword" type="password" class="form-control" name="confirmpwd" id="confirm-password" placeholder="再次输入新密码">
                  </div>
                  <div class="form-group">
                    <input @click="update" class="btn btn-primary" type="button" value="修改">
                  </div>
                  <div class="alert alert-danger error1" style="display: none;">原登录密码有误</div>
                  <div class="alert alert-danger error2" style="display: none;">两次密码输入不一致</div>
                  <div class="alert alert-success error3" style="display: none;">修改成功</div>
                  <!-- <button @click="update" type="submit" class="btn btn-primary">修改密码</button> -->
                </form>
              </div>
            </div>
          </div>
          
        </div>
        
      </div>
</template>

<script>
import $ from 'jquery'
import axios from 'axios'
import qs from 'qs'

export default {
  name:"UpdatePassword",
  data(){
    return{
      adminID:"",
      oldPassword:"",
      password:"",
      rePassword:""
    }
  },
  created(){
    var that = this;
    $.ajax({
    type:"get",
    dataType:"JSON",
    url: that.GLOBAL.API_ROOT + "/admininfo",
    success:function (result) {
        console.log(result);
        that.adminID = result.adminID
    },
    error:function (result) {
        console.log(result)
    }
    }) 
  },
  methods:{
    update:function(){
      var that = this;
      axios.post(that.GLOBAL.API_ROOT + "/update_password",qs.stringify({
        "oldPassword":that.oldPassword,
        "password":that.password,
        "rePassword":that.rePassword,
        "adminID":that.adminID
      })).then(function(result){
        console.log();
        if(result.data.status == "原登录密码有误"){
          $(".error1").css("display","block")
          $(".error2").css("display","none")
          $(".error3").css("display","none")
          // $(".error")[0].css("display","block").siblings().css("display","block");
        }else if(result.data.status == "密码不一致"){
          // $(".error")[1].css("display","block").siblings().css("display","block");
           $(".error1").css("display","none")
          $(".error2").css("display","block")
          $(".error3").css("display","none")        
        }else if(result.data.status){
          // $(".error")[2].css("display","block").siblings().css("display","block");
           $(".error1").css("display","none")
          $(".error2").css("display","none")
          $(".error3").css("display","block")
          console.log(result);
          //修改成功，清空表单
          that.$refs.form.reset()
        }
      })
      // $.ajax({
      //   type:"post",
      //   dataType:"JSON",
      //   url:that.GLOBAL.API_ROOT + "/update_password",
      //   data:{
      //     "oldPwd":that.oldPwd,
      //     "adminPwd":that.adminPwd,
      //     "reAdminPwd":that.rePassword,
      //     "adminID":that.adminID
      //   },
      //   success:function(result){
      //     console.log(result);
      //   }
      // })
    }
  }
}
</script>