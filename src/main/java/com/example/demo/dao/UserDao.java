package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.User;

public interface UserDao extends CrudRepository<User,Integer>{
	Page<User> findAll(Pageable pageable);
	
	List<User> findAll();
	
	Page<User> findByState(Integer state,Pageable pageable);
	
	List<User> findByState(Integer state);
	
	Page<User> findByPhoneAndState(String phone,Integer state,Pageable pageable);

	List<User> findByPhone(String phone);

	Page<User> findByShenhestate(String string, Pageable pageable);
	
	@Modifying
	@Query(nativeQuery = true,value = "delete  from dan where phone = ?1")
	void delete1(String phone);

}
