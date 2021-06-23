<template>
 <!-- <main class="lyear-layout-content"> -->
      
      <div class="container-fluid">
        
        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-header"><h4>基本样式</h4></div>
              <div class="card-body">
                
                <form class="form-horizontal" action="#" method="post" enctype="multipart/form-data" onsubmit="return false;">
                  <div class="form-group">
                    <label class="col-xs-12" for="roomID">自习室编号</label>
                    <div class="col-xs-12">
                      <input disabled v-model="oldssRoomID" required class="form-control" type="text" id="roomID" name="roomID">
                    </div>
                  </div>                 
                  <div class="form-group">
                    <label class="col-xs-12" for="roomID">自习室编号</label>
                    <div class="col-xs-12">
                      <input v-model="ssRoomID" required class="form-control" type="text" id="roomID" name="roomID">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="roomtype">自习室类型</label>
                    <div class="col-xs-12">
                      <input v-model="ssRoomType" class="form-control" type="text" id="roomtype" name="roomtype" placeholder="文本..">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="roomname">自习室名称</label>
                    <div class="col-xs-12">
                      <input v-model="ssRoomName" required class="form-control" type="text" id="roomname" name="roomname" placeholder="文本..">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="cover">自习室封面图</label>
                    <div class="col-xs-12">
                      <img :src="`${coverMap}?x-oss-process=image/resize,h_100,m_lfit`" />
                       <input @change="uploadImage" name="coverMap" type="file" value="" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="introduce">自习室介绍</label>
                    <div class="col-xs-12">
                      <input v-model="introduce" class="form-control" type="text" id="introduce" name="introduce" placeholder="文本..">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="address">自习室地址</label>
                    <div class="col-xs-12">
                      <input v-model="address" required class="form-control" type="text" id="address" name="address" placeholder="文本..">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="seat-all-num">自习室座位总数量</label>
                    <div class="col-xs-12">
                      <input v-model="locNum" required class="form-control" type="text" id="seat-all-num" name="seat-all-num" placeholder="文本..">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-xs-12" for="seat-use-num">自习室座位使用数量</label>
                    <div class="col-xs-12">
                      <input v-model="useNum" required class="form-control" type="text" id="seat-use-num" name="seat-use-num" placeholder="文本..">
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
      
    <!-- </main> -->
</template>

<script>
import axios from 'axios'
import qs from 'qs'
import $ from 'jquery'

export default {
  name: 'UpdateStudyroomInfo',
  data(){
    return{
      //与input中的v-model绑定
      oldssRoomID:this.$route.query.ssRoomID,
      address:this.$route.query.address,
      coverMap:this.$route.query.coverMap,
      introduce:this.$route.query.introduce,
      locNum:this.$route.query.locNum,
      ssRoomID:this.$route.query.ssRoomID,
      ssRoomName:this.$route.query.ssRoomName,
      ssRoomType:this.$route.query.ssRoomType,
      useNum:this.$route.query.useNum,
    }
  },
  
  methods:{
    // test:function(){
    //   var that = this;
    //   ssRoomID=that.address
    // },
    update:function(){
      var that = this;
      // console.log();
      axios.post(that.GLOBAL.API_ROOT + "/change_roomsinfo",qs.stringify({
        oldssRoomID:that.oldssRoomID,
        address:that.address,
        coverMap:that.coverMap,
        introduce:that.introduce,
        locNum:that.locNum,
        ssRoomID:that.ssRoomID,
        ssRoomName:that.ssRoomName,
        ssRoomType:that.ssRoomType,
        useNum:that.useNum
        })).then(function(result){
          console.log(result.data);
          if(result.data.status == "修改自习室信息成功"){
            $(".alert-success").css("display","block")
          }
      })
    },
    uploadImage: function (event) {
        var that = this;
        console.log("传图片");
        //获取文件对象
        let imageFile = event.target.files[0];
        console.log(event.target.files);
        // let objName = event.target.name;
        //   组装一个form数据
        let formData = new FormData();
        formData.append("imageFile", imageFile);
        let config = {
            headers: { "Content-Type": "multipart/form-data" }, //指定传的数据类型
        };
        //   调接口
        axios
        .post(that.GLOBAL.API_ROOT + "/upload_image", formData, config)
        .then((response) => {
            let cover = response.data.imageUrl
            // console.log(imageUrl);
            that.coverMap = cover;//将data中的coverMap赋值
        });
    },
  }
}
</script>