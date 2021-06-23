<template>
<div class="card">
<div class="card-body">
<div class="table-responsive">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>账号</th>
          <th>昵称</th>
          <th>会员时长</th>
          <th>订座状态</th>
          <th>操作</th>
          
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in customerList" :key="item.customerID">
          <td>{{item.customerID}}</td>
          <td>{{item.customerName}}</td>
          <td>{{item.memLength}}</td>
          <td>{{item.resStatus}}</td>
          <td>
            <div class="btn-group">
              <router-link @click.native="edit(item)" to="./update_customer_info" class="btn btn-xs btn-default" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></router-link>
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

export default {
    name:'CustomerTable',
    data(){
      return{
        customerList:[]
      }
    },
    created(){
      var that = this;
      axios.get(that.GLOBAL.API_ROOT + "/all_customers").then(function(result){
        console.log(result);
        that.customerList = result.data
      })
    },
    methods:{
      edit:function(item){
        var that = this;
        // 将参数传递到修改页面
          that.$router.push({
            query:{
              "customerID":item.customerID,
              "customerName":item.customerName,
              "memLength":item.memLength,
              "resStatus":item.resStatus
            }
          })
      },
      del:function(item){
        var that = this;
        axios.post(that.GLOBAL.API_ROOT + "/delete_customers",qs.stringify({
          "customerID":item.customerID
        })).then(function(result){
          console.log(result);
          //刷新
         location.reload();
        })
      }
    }

}
</script>