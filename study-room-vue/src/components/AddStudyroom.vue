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
                      <input v-model="ssRoomID" required class="form-control" type="text" id="roomID" name="roomID" placeholder="文本..">
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
                  <!-- <div class="form-group">
                    <label class="col-xs-12" for="cover">自习室封面图</label>
                    <div class="col-xs-12">
                      <img  />
                       <input @change="uploadImage" name="coverMap" type="file" value="" />
                    </div>
                  </div> -->
                  <div class="form-group">
                    <label class="col-xs-12" for="cover">自习室封面图</label>
                    <div class="col-xs-12">
                      <img :src="`${coverMap}?x-oss-process=image/resize,h_100,m_lfit`" />
                      <input @change="uploadImage"  type="file" id="cover" name="cover" placeholder="文本..">
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
                    <button class="btn btn-primary" type="submit" @click="saveRoom">新增</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    <!-- </main> -->
</template>

<script>
import axios from 'axios';
import qs from 'qs'

export default {

  name: 'AddStudyroom',
  data(){
    return{
      // v-model中的值
          address:"",
          coverMap:"https://jia-yao.oss-cn-hangzhou.aliyuncs.com/default.jpg",
          introduce:"",
          locNum:"",
          ssRoomID:"",
          ssRoomName:"",
          ssRoomType:"",
          useNum:""
      // coverImageUrl:""
    }
  },
  methods:{
    saveRoom:function(){
      // console.log(this.address);
        var that = this;
        axios.post(
          that.GLOBAL.API_ROOT + "/create_rooms",
          // 传递参数到后端
          qs.stringify({
            "address":that.address,
            "coverMap":that.coverMap,
            "introduce":that.introduce,
            "locNum":that.locNum,
            "ssRoomID":that.ssRoomID,
            "ssRoomName":that.ssRoomName,
            "ssRoomType":that.ssRoomType,
            "useNum":that.useNum
          })
        ).then(
          function (result) {
            console.log(result);
            // that.loadProducts();
          },
          function (err) {
            console.log(err);
          }
        );
    },
    uploadImage: function (event) {
      console.log(this.ssRoomID);
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
        axios.post(that.GLOBAL.API_ROOT + "/upload_image", formData, config)
        .then((response) => {
            console.log(response.data);
            let cover = response.data.imageUrl
            // console.log(imageUrl);
            that.coverMap = cover;//将data中的coverMap赋值
        });
    },
  }
}
</script>