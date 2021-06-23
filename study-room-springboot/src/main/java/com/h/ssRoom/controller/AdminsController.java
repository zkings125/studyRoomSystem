package com.h.ssRoom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import com.h.ssRoom.model.Admins;
import com.h.ssRoom.repo.AdminsRepo;
import com.h.ssRoom.util.JWTHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "/api",produces = "application/json; charset=utf-8")
public class AdminsController {
    @Autowired
    private AdminsRepo adminsRepo;

    @Resource
    private HttpSession session;

    public Admins admin;

    //查询管理员
//    @CrossOrigin
    @GetMapping("/all_admins")
    public List<Admins> findAllAdmins(){
        return adminsRepo.findAllAdmins();
    }

    //添加管理员
    @PostMapping(value = "/create_admins")
    public Map<String,Object> createAdmins(@RequestParam(value = "adminID") String adminID,
                                           @RequestParam(value = "adminPwd") String adminPwd,
                                           @RequestParam(value = "adminPower") Integer adminPower){
        Map<String,Object> result = new HashMap<>();
        List<Admins> adminsList = adminsRepo.findByadminID(adminID);
        Admins admins = new Admins();
        if(adminsList.size()==0){
            admins.setAdminID(adminID);
            String md5Pwd = DigestUtils.md5DigestAsHex(adminPwd.getBytes());
            admins.setAdminPwd(md5Pwd);
            admins.setAdminPower(adminPower);
            result.put("status","创建成功");
            result.put("adminId",admins.getAdminID());
            result.put("adminPwd",admins.getAdminPwd());
            result.put("adminPower",admins.getAdminPower());
            adminsRepo.save(admins);
            return result;
        }else{
            result.put("status","创建失败，管理员账号已存在");
            return result;
        }

    }

    //修改管理员信息
    @PostMapping(value = "/change_admins")
    public Map<String,Object> changeAdmins(@RequestParam(value = "adminID") String adminID,
                                           @RequestParam(value = "adminPwd") String adminPwd,
                                           @RequestParam(value = "adminPower") Integer adminPower,
                                           @RequestParam(value = "oldAdminID") String oldAdminID){
        Map<String,Object> result = new HashMap<>();
        List<Admins> adminsList = adminsRepo.findByadminID(oldAdminID);
        if(adminsList.size()==0){
            result.put("failed","adminID is not exists");
            return result;
        }
        else{
            adminPwd = DigestUtils.md5DigestAsHex(adminPwd.getBytes());
            System.out.println(adminPwd);
            adminsRepo.updateAdmins(adminID,adminPwd,adminPower,oldAdminID);
            result.put("status", "修改成功");
            return result;
        }

    }

    //删除管理员
    @PostMapping(value ="/delete_admins")
    public Map<String,Object> deleteAdmins(@RequestParam(value = "adminID") String adminID) {
        Map<String,Object> result = new HashMap<>();
        List<Admins> adminsList = adminsRepo.findByadminID(adminID);
        if(adminsList.size()==0){
            result.put("failed","adminID is not exists");
            return result;
        }else{
            adminsRepo.deleteAdmins(adminID);
            result.put("status", "删除成功");
            return result;
        }

    }

    //登录接口
    @CrossOrigin
    @PostMapping(value = "/login")
    public Map<String,Object> login(@RequestParam(value = "adminID") String adminID,
                                    @RequestParam(value = "adminPwd") String adminPwd){
        Map<String,Object> result = new HashMap<>();
        List<Admins> adminsList = adminsRepo.findByadminID(adminID);
        if(adminsList.size()==0){
            result.put("status","username or password error1");
            return result;
        }
        Admins admins = adminsList.get(0);
        String oldPwd = admins.getAdminPwd();
        String md5Pwd = DigestUtils.md5DigestAsHex(adminPwd.getBytes());
        if(md5Pwd.equals(oldPwd)){
           admin = adminsRepo.findByAdminID(adminID);
            session.setAttribute("admin", admin);

            String token = JWTHelper.createJWTToken(admins);
            result.put("adminID",adminID);
            result.put("status","登录成功");
            result.put("token",token);
            return  result;
        }else{
            result.put("status","username or password error");
            return result;
        }
    }

    //获取用户信息
    // @CrossOrigin
    @GetMapping("/admininfo")
    public Map<String,Object> adminInfo(){
        Map<String, Object> result = new HashMap<String, Object>();
//        System.out.println(admin);
        result.put("adminID",admin.getAdminID());
        return result;
    }

    //修改密码
    @CrossOrigin
    @PostMapping("/update_password")
    public Map<String,Object> updatePassword(@RequestParam(value = "oldPassword") String oldPassword,
                                             @RequestParam(value = "password") String password,
                                             @RequestParam(value = "rePassword") String rePassword,
                                             @RequestParam(value = "adminID") String adminID){
        Map<String,Object> result = new HashMap<>();

        //获取该用户
        List<Admins> adminsList = adminsRepo.findByadminID(adminID);
        Admins admin = adminsList.get(0);
        //用户输入的原始密码转为md5
        String md5Pwd = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        //用户输入的原始密码与数据库存储密码比较
        if (md5Pwd.equals(admin.getAdminPwd())){
            //如果两次密码一致则允许修改
            if (rePassword.equals(password)){
                adminsRepo.updatePassword(DigestUtils.md5DigestAsHex(password.getBytes()),adminID);
                result.put("status",true);
            }else {
                result.put("status","密码不一致");
            }
        }else{
           result.put("status","原登录密码有误");
        }
//        System.out.println(result);
        return  result;
    }
     
}

