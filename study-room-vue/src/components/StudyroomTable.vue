<template>
<div class="card">
<div class="card-body">
  <div class="toolbar-btn-action">
    <router-link class="btn btn-primary m-r-5" to="add_studyroom"><i class="mdi mdi-plus"></i> 新增自习室</router-link>
  </div>
  <div class="table-responsive">
    <table class="table table-bordered">
      <thead>
        <tr>
          <!-- <th>#</th> -->
          <th>自习室编号</th>
          <th>自习室类型</th>
          <th>自习室名称</th>
          <th>自习室封面图</th>
          <th>自习室介绍</th>
          <th>自习室地址</th>
          <th>自习室座位总数量</th>
          <th>自习室座位使用数量</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in customerList" :key="item.ssRoomID">
          <td>{{item.ssRoomID}}</td>
          <td>{{item.ssRoomType}}</td>
          <td>{{item.ssRoomName}}</td>
          <td>
            <img :src="`${item.coverMap}?x-oss-process=image/resize,h_100,m_lfit`"/>
          </td>
          <td>{{item.introduce}}</td>
          <td>{{item.address}}</td>
          <td>{{item.locNum}}</td>
          <td>{{item.useNum}}</td>
          <td>
            <!-- <router-link @click.native="edit(item.ssRoomID)" to="./update_studyroom_info">111</router-link> -->
            <div class="btn-group">
              <router-link @click.native="edit(item)" to="./update_studyroom_info" class="btn btn-xs btn-default" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></router-link>
              <a @click="del(item)" class="btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-close"></i></a>
            </div>
          </td>
        </tr>
        <!-- <tr>
          <th scope="row">1</th>
          <td>钦差大臣</td>
          <td>果戈理</td>
          <td>俄国</td>
          <td>国外名族</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>
            <div class="btn-group">
              <router-link to="./update_studyroom_info" class="btn btn-xs btn-default" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></router-link>
              <router-link to="./update_studyroom_info" class="btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-close"></i></router-link>
            </div>
          </td>
        </tr>
        <tr>
          <th scope="row">2</th>
          <td>西厢记</td>
          <td>王实甫</td>
          <td>中国</td>
          <td>古文典籍</td>
        </tr>
        <tr>
          <th scope="row">3</th>
          <td>儒林外史</td>
          <td>吴敬梓</td>
          <td>中国</td>
          <td>古文典籍</td>
        </tr> -->
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
  name: "StudyroomTable",
  data(){
    return{
      customerList:[],
    }
  },
  created(){
    // console.log(e);
    var that = this;
      axios.get(that.GLOBAL.API_ROOT + "/getRoom",{
      //  params: e.ssRoomID
      }).then(function(ret){
      //  console.log(ret.data);
        that.customerList = ret.data
        that.customerList.coverMap = ret.data.coverMap
        console.log(that.customerList);
      })
    
  },
  methods:{
    //点击编辑时传递值到自习室修改页面
    edit:function(item){
      console.log(item);
      this.$router.push({
        query:{
          // info:item
          address:item.address,
          coverMap:item.coverMap,
          introduce:item.introduce,
          locNum:item.locNum,
          ssRoomID:item.ssRoomID,
          ssRoomName:item.ssRoomName,
          ssRoomType:item.ssRoomType,
          useNum:item.useNum
        }
      })
    },
    del:function(item){
      var that = this;
      axios.post(that.GLOBAL.API_ROOT + "/delete_rooms",qs.stringify({
        "ssRoomID":item.ssRoomID
      })).then(function(){
        //刷新
         location.reload();
      })
    }
  }
};
</script>