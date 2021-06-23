<template>
<div class="card">
<div class="card-body">
  <div class="table-responsive">
    <div class="toolbar-btn-action">
      <router-link class="btn btn-primary m-r-5" to="./add_admin"><i class="mdi mdi-plus"></i> 新增管理员</router-link>
    </div>
    <table ref="admin_table" class="table table-bordered">
      <thead>
        <tr>
          <th>管理员账号</th>
          <th>管理员密码</th>
          <th>管理员权限</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in adminList" :key="item.adminID">
          <td>{{item.adminID}}</td>
          <td>{{item.adminPwd}}</td>
          <td>{{item.adminPower == 1?'普通管理员':'超级管理员'}}</td>
          <td>
             <div class="btn-group">
              <router-link @click.native="edit(item)" to="./update_admin" class="btn btn-xs btn-default" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></router-link>
              <a @click="del(item)"  class="btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-close"></i></a>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</div>
</div>
</template>

<script>
import axios from 'axios'
import qs from 'qs'
// import $ from 'jquery'

export default {
    name:'AdminTable',
    data(){
      return{
        adminList:[],
      }
    },
    methods:{ 
      //点击编辑时跳转页面并传递参数 item:传入的当前行信息
      edit:function(item){
        console.log(item);
        var that = this;
        // 将参数传递到UpdateAdmin
          that.$router.push({
            query:{
              adminID:item.adminID,
              adminPwd:item.adminPwd,
              adminPower:item.adminPower
            }
          })
      },
      del:function(item){
        var that = this;
        // $.ajax({
        //   type:"post",
        //   dataType:"JSON",
        //   data:{
        //     "adminID":item.adminID
        //   },
        //   url:that.GLOBAL.API_ROOT + "/delete_admins",
        //   success:function(result){
        //     console.log(result);
        //   }
        // })
        axios.post(that.GLOBAL.API_ROOT + "/delete_admins",qs.stringify({
          "adminID":item.adminID
        })).then(function(result){
          //刷新
          
         location.reload();
          console.log(result);
        })
      }
    },
    created(){
      console.log(12);
      var that = this;
      axios.get(that.GLOBAL.API_ROOT + "/all_admins",{
      //  params: e.ssRoomID
      }).then(function(result){
        that.adminList = result.data;
        console.log(result);
      })
    },
   
    
    
}
// this.$router.push({
//   name: "login",
//   params: {
//     uname: "this.userName"
//   }
// });

</script>