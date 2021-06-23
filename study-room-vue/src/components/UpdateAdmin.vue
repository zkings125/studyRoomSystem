<template>
      <div class="container-fluid">
        
        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-header"><h4>基本样式</h4></div>
              <div class="card-body">
                
                <form class="form-horizontal" action="#" method="post" enctype="multipart/form-data" onsubmit="return false;">
                  <div class="form-group">
                    <label class="col-xs-12" for="admin-id">管理员账号</label>
                    <div class="col-xs-12">
                      <input v-model="oldAdminID" required class="form-control" type="text" id="admin-id" name="admin-id" placeholder="文本..">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="admin-id">管理员账号</label>
                    <div class="col-xs-12">
                      <input v-model="adminID" required class="form-control" type="text" id="admin-id" name="admin-id" placeholder="文本..">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="password">管理员密码</label>
                    <div class="col-xs-12">
                      <input v-model="adminPwd" required class="form-control" type="text" id="password" name="password" placeholder="文本..">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="adminPower">管理员权限</label>
                    <div class="col-xs-12">
                      <select v-model="selected" class="form-control">
                        <option value="1">普通管理员</option>
                        <option value="0">超级管理员</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <button class="btn btn-primary" type="submit" @click="update">修改</button>
                  </div>
                  <div class="alert alert-success" style="display: none;">修改成功</div>
                </form>
              </div>
            </div>
          </div> 
        </div>
      </div>
</template>

<script>
import $ from "jquery"

export default {
  name: 'UpdateAdmin',
  data(){
    return{
      //接收传过来的参数
      oldAdminID:this.$route.query.adminID,
      adminID:this.$route.query.adminID,
      adminPwd:this.$route.query.adminPwd,
      adminPower:this.$route.query.adminPower,
      //-----
      selected0:"",
      selected1:""
    }
  },
  created(){
    var that = this
    console.log(that.adminPower);
    if (that.adminPower == 1) {
      that.selected = "1"
    }else{
      that.selected = "0"
    }
  },
  methods:{
    update:function(){
      var that = this;
      $.ajax({
        type:"post",
        dataType:"JSON",
        data:{
          //获取参数并传递
          "adminID":that.adminID,
          "adminPwd":that.adminPwd,
          "oldAdminID":that.oldAdminID,
          "adminPower":that.selected
        },
        url:that.GLOBAL.API_ROOT + "/change_admins",
        success:function(result){
          
        //   //刷新页面
        //   location.reload()
          console.log(result);
          console.log(that.adminPower);
          // if(result == "修改成功"){
            // console.log(13241324);
            $(".alert-success").css("display","block")
          // }
        }
      })
    }
  }
}

</script>