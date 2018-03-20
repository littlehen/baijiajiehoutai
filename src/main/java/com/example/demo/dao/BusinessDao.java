package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Business;
import com.example.demo.model.User;

public interface BusinessDao extends CrudRepository<Business,String>{
	Page<Business> findAll(Pageable pageable);
	
	List<Business> findAll();
	
	Page<Business> findByQq(String qq,Pageable pageable);
	
	List<Business>  findByQq(String qq);

	Business findByCode(String code);

	@Modifying
	@Transactional
	@Query(nativeQuery = true,value = "update business set huobi = ohuobi")  
	void updatahuobi();

	@Modifying
	@Query(nativeQuery = true,value = "SELECT   *   FROM   USER   LIMIT  ?1")  
	List<User> findByCount(Integer i);

	@Modifying
	@Transactional
	@Query(nativeQuery = true,value = "UPDATE dan SET DAY = DAY-1")
	void updataday();

	@Modifying
	@Transactional
	@Query(nativeQuery = true,value = "DELETE FROM dan WHERE DAY <= 0")
	void deletedan();

	List<Business> findByShenhestate(String string);
}
