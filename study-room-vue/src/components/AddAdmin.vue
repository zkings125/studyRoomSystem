<template>
      <div class="container-fluid">
        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-header"><h4>基本样式</h4></div>
              <div class="card-body">
                <form  class="form-horizontal form" action="#" method="post" enctype="multipart/form-data" onsubmit="return false;">
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
                    <label class="col-xs-12" for="status">管理员权限</label>
                    <div class="col-xs-12">
                      <label class="lyear-radio radio-inline radio-primary">
                      <input value="0" v-model="adminPower" type="radio" name="e"><span>超级管理员</span>
                    </label>
                    <label class="lyear-radio radio-inline radio-primary">
                      <input value="1" v-model="adminPower" type="radio" name="e"><span>普通管理员</span>
                    </label>
                    </div>
                  </div>
                  <div class="form-group">
                    <button class="btn btn-primary" type="submit" @click="addAdmin">新增</button>
                  </div>
                  <div class="alert alert-success" style="display: none;">新增成功</div>

                </form>
                
              </div>
            </div>
          </div>
        </div>
      </div>
      
    <!-- </main> -->
</template>


<script>
import $ from 'jquery'

export default {
  name: 'AddAdmin',
  data(){
    return{
      // v-model
      adminID:"",
      adminPwd:"",
      adminPower:"",//单选框的值
      // power:""
      // addform:""//表单DOM
    }
  },
  mounted() {
    var that = this;
    that.addform = this.$refs.addform
    // var id = this.$route.query.id;//获取上个页面传递的id,在下面获取数据的时候先提交id
    // console.log(this.$route.query.id);
    
  },
  methods:{
    addAdmin:function(){
      var that = this;
      // var form = that.$refs.addform
      console.log(that.$refs.addform);
      console.log(that.adminPower);
      $.ajax({
        type:"post",
        dataType:"JSON",
        data:{
          "adminID":that.adminID,//获取v-mdel的值并赋值，传参到后端
          "adminPwd":that.adminPwd,
          "adminPower":that.adminPower
        },
        url:that.GLOBAL.API_ROOT + "/create_admins",
        success:function(result){
          console.log(result);
          // $(".form").reset();//清空表单
          // console.log(that.$refs.addform);
          $(".alert-success").css("display","block")
          
        }
      })
    }
  }
}
</script>