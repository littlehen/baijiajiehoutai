package com.example.demo.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdministratorDao;
import com.example.demo.model.Administrator;

@Service
public class AdministratorService {
	
	@Autowired
	AdministratorDao administratorDao;
	
	
	public Map<String,Object> login(String aname,String password) {
		Map<String,Object> map = new HashMap<>();
		Administrator admin = administratorDao.findByAname(aname);
		if(admin != null) {
			if(admin.getPassword().equals(password)) {
				map.put("state", true);
				map.put("admin", admin);
			}
			else {
				map.put("state", false);
			}
		}
		else
			map.put("state", false);
		return map;
	}


	public boolean updatahoubi(String aname,Integer yidangohuobi, Integer erdangohuobi, Integer sandangohuobi) {
		Administrator admin = administratorDao.findByAname(aname);
		admin.setYidangohuobi(yidangohuobi);
		admin.setErdangohuobi(erdangohuobi);
		admin.setSandangohuobi(sandangohuobi);
		if(administratorDao.save(admin)!=null)
			return true;
		else
			return false;
	}
}
