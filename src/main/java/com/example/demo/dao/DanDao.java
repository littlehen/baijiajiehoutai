package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Dan;
import com.example.demo.model.User;


public interface DanDao extends CrudRepository<Dan,Integer>{

	Dan findByCodeAndPhone(String code, String phone);

	List<Dan> findByCode(String code);

	List<Dan> findByPhone(String phone);

	@Modifying
	@Query(nativeQuery = true,value = "SELECT   *   FROM   DAN   WHERE  code=?1 and phone=?2")  
	List<Dan> findByMyCodeAndPhone(String code, String phone);

}
