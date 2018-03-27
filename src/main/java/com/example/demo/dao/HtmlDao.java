package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Html;
import com.example.demo.model.User;

public interface HtmlDao extends CrudRepository<Html,Integer>{
	Page<Html> findAll(Pageable pageable);
}
