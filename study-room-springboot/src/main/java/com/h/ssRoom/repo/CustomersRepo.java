package com.h.ssRoom.repo;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.h.ssRoom.model.Customers;

public interface CustomersRepo extends JpaRepository<Customers, Integer>{
	@Query(value = "select * from Customers",nativeQuery = true)
    public List<Customers> findAllCustomers();

    //根据顾客主键查询剩余时长
    @Query(value = "select mem_length from customers where customerID=?1", nativeQuery = true)
    public Integer getMemLenByID(String customerID);  
    
    //根据用户编号获取用户信息
    @Query(value = "select * from customers where customerid=?1",nativeQuery = true)
    public List<Customers> getCustomersByCustomerID(String customerID);
    
    //更新用户信息
    @Transactional
    @Modifying
    @Query(value = "update customers set mem_length=?2,res_status=?3,customer_name=?4 where customerID=?1",nativeQuery = true)
    public void updateCustomers(String customerID, Integer memLength, Integer resStatus,String customerName);
    
    //删除用户
    @Transactional
    @Modifying
    @Query(value = "delete from customers where customerID=?1",nativeQuery = true)
    public void deleteCustomers(String customerID);

    //获取用户学习总时长
    @Query(value = "SELECT SUM(appoints.study_len) AS total_study_len FROM customers JOIN appoints ON customers.customerid=appoints.customerid WHERE customers.customerid=?1",nativeQuery=true)
    public Integer getSumStudyLength(String customerID);
    
    //获取用户每次学习时长和学习时间
    @Query(value = "SELECT" + 
    		"	DATE( start_time ) AS date," + 
    		"	SUM( study_len ) AS perDay " + 
    		"FROM" + 
    		"	`appoints` " + 
    		"WHERE" + 
    		"	DATE_SUB( CURDATE( ), INTERVAL 7 DAY ) <= DATE( start_time ) " + 
    		"	AND DATE( start_time ) <> CURDATE( ) " + 
    		"	AND customerid = ?1 " + 
    		"GROUP BY" + 
    		"	DATE( start_time ) " + 
    		"ORDER BY" + 
    		"	start_time;" + 
    		"",nativeQuery=true)
    public List<Map<String,Object>> getPerStudyRecord(String customerID);

}
