package com.h.ssRoom.controller;

import com.h.ssRoom.model.Customers;
import com.h.ssRoom.repo.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api", produces = "application/json;charset=utf-8")
public class CustomersController {
    @Autowired
    private CustomersRepo customersRepo;

    // 获取用户信息接口
    @PostMapping(value = "/customers")
    public List<Customers> getCustomers(@RequestParam(value = "customerID") String customerID) {
        List<Customers> result = customersRepo.getCustomersByCustomerID(customerID);
        return result;
    }

    // 修改用户会员时长，订座状态
    @CrossOrigin // 解决跨域问题
    @PostMapping(value = "/change_customers")
    public Map<String, Object> changeCustomer(@RequestParam(value = "customerID") String customerID,
            @RequestParam(value = "memLength") Integer memLength, @RequestParam(value = "resStatus") Integer resStatus,
            @RequestParam(value = "customerName") String customerName) {
        Map<String, Object> result = new HashMap<>();
        List<Customers> customersList = customersRepo.getCustomersByCustomerID(customerID);
        if (customersList.size() == 0) {
            result.put("faild", "customerID is not exists");
            return result;
        } else {
            customersRepo.updateCustomers(customerID, memLength, resStatus, customerName);
            result.put("status", "修改用户会员时长，订座状态成功");
            return result;
        }
    }

    // 删除用户信息接口
    @PostMapping(value = "/delete_customers")
    public Map<String, Object> deleteCustomer(@RequestParam(value = "customerID") String customerID) {
        Map<String, Object> result = new HashMap<>();
        List<Customers> customersList = customersRepo.getCustomersByCustomerID(customerID);
        if (customersList.size() == 0) {
            result.put("faild", "customerID is not exists");
            return result;
        } else {
            customersRepo.deleteCustomers(customerID);
            result.put("status", "删除成功");
            return result;
        }
    }

    // 获取用户学习总时长
    @PostMapping(value = "/get_sum_stu_length")
    public Integer getSumStudy(@RequestParam(value = "customerID") String customerID) {
        return customersRepo.getSumStudyLength(customerID);
    }

    // 获取用户每次学习时长和学习时间
    @PostMapping(value = "/get_per_study")
    public List<Map<String, Object>> getPerStudy(@RequestParam(value = "customerID") String customerID) {
        return customersRepo.getPerStudyRecord(customerID);
    }

    // 登录
    @RequestMapping(value = "/loginWx")
    public Object LoginWX(@RequestParam(value = "js_code", required = true) String jsCode,
            @RequestParam(value = "nickName", required = true) String nickName) {
        RestTemplate restTemplate = new RestTemplate();
        // 请求地址
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxe679002be5158fc9&secret=5f0cc99ec08b0485d13691fd2b04270f&js_code={jsCode}&grant_type=authorization_code";
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 设置请求参数
        Map<String, String> map = new HashMap<>();
        map.put("jsCode", jsCode);
        restTemplate.setDefaultUriVariables(map);
        // 发送请求

        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String body = exchange.getBody();
        System.out.println(exchange);
        // 获取openid
        Integer a = body.indexOf("\"openid\"") + 10;
        if (a == 9) {
            System.out.println("用户信息查询失败");
            return false;
        }
        Integer b = body.indexOf("\"", a);
        String openid = body.substring(a, b);
        // 对openid进行md5加密
        String md5Openid = DigestUtils.md5DigestAsHex(openid.getBytes());

        List<Customers> customers = customersRepo.getCustomersByCustomerID(md5Openid);

        if (customers.size() < 1) {
            System.out.println("新建用户");
            Customers customer = new Customers();
            customer.setCustomerID(md5Openid);
            customer.setCustomerName(nickName);
            customer.setResStatus(0);
            customer.setMemLength(0);
            customersRepo.save(customer);
            customers.add(customer);
        }

        return customers;
    }

    @CrossOrigin
    @GetMapping("/all_customers")
    public List<Customers> findAllCustomers(){
        return customersRepo.findAllCustomers();
    }
}
