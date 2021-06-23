<template>   
      <div class="container-fluid">
        
        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-header"><h4>基本样式</h4></div>
              <div class="card-body">
                
                <form  class="form-horizontal" action="#" method="post" enctype="multipart/form-data" onsubmit="return false;">
                  <div class="form-group">
                    <label class="col-xs-12" for="id">顾客账号</label>
                    <div class="col-xs-12">
                      <input v-model="customerID" disabled class="form-control" type="text" id="id" name="id" placeholder="文本..">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="nickname">顾客昵称</label>
                    <div class="col-xs-12">
                      <input ref="form" v-model="customerName" class="form-control" type="text" id="nickname" name="nickname" placeholder="文本..">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="time">会员时长</label>
                    <div class="col-xs-12">
                      <input v-model="memLength" required class="form-control" type="text" id="time" name="time" placeholder="文本..">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="status">状态</label>
                    <div class="col-xs-12">
                      <input  v-model="resStatus" required class="form-control" type="text" id="status" name="status" placeholder="文本..">
                    </div>
                  </div>
                  <div class="form-group">
                    <button @click="update" class="btn btn-primary" type="submit">修改</button>
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
import axios from 'axios'
import qs from 'qs'
import $ from 'jquery'

export default {
  name: 'UpdateCustomerInfo',
  data(){
    return{
      customerID:this.$route.query.customerID,
      customerName:this.$route.query.customerName,
      memLength:this.$route.query.memLength,
      resStatus:this.$route.query.resStatus
    }
  },
  methods:{
    update:function(){
      $(".alert-success").css("display","none")
      var that = this;
      axios.post(that.GLOBAL.API_ROOT + "/change_customers",qs.stringify({
        "customerID":that.customerID,
        "customerName":that.customerName,
        "memLength":that.memLength,
        "resStatus":that.resStatus
      })).then(function(result){
        console.log(result);
        // console.log(that.$refs.form);
        if(result.data.status == "修改用户会员时长，订座状态成功"){
          $(".alert-success").css("display","block")
        }
      })
    }
  }
}
</script>