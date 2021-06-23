package com.h.ssRoom.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

//阿里云帮助类
public class AliyunOssHelper {
    public static String getUUID() {
        String id = UUID.randomUUID().toString();
        String uid = id.replaceAll("-", "");
        return uid;
    }


    public static String uploadImage(MultipartFile mFile) {
        String imageUrl = "";

        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tS2rN1SDhnignbrVZEf";
        String accessKeySecret = "eSg0KeizD8H2oUgfOaJPtwx9Ks038S";

        String urlPrefix = "https://jia-yao.oss-cn-hangzhou.aliyuncs.com/";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        File file = new File("E:\\desktop\\实习\\tmp\\temp.jpg");
//        File file = new File("/User/jy/images/temp.jpg");
//        File file = new File("/User/jy/images/temp.jpg");
        try {
            mFile.transferTo(file);
        }catch (Exception e){
            System.out.println(e);
        }

        String fileName = getUUID() + ".jpg";
        //设置header类型，这样图片才能在浏览器显示出来
        ObjectMetadata omd = new ObjectMetadata();
        omd.setContentType("image/jpg");

// 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("jia-yao", fileName, file,omd);

// 关闭OSSClient。
        ossClient.shutdown();

        imageUrl = urlPrefix + fileName;
        System.out.println(imageUrl);
        return imageUrl;
    }
}
