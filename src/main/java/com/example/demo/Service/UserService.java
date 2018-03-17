package com.example.demo.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.Administrator;
import com.example.demo.model.User;


@Service
public class UserService {
	@Autowired
	UserDao userDao;
	
	public Map<String,Object> userlist(String admin,Integer page,Integer rows){
		Map<String,Object> map = new HashMap<>();
		Pageable pageable = new PageRequest(page-1,rows);
		if(admin == null||"".equals(admin)) {
			map.put("total", 0);
			map.put("pageNumber", 0);
			map.put("pageSize", 0);
			map.put("rows", null);
		}else {

			Page<User> list = userDao.findByState(1,pageable);
			map.put("total",list.getTotalElements()); //总条数
		    map.put("pageNumber", list.getSize()); //10 20 ...
		    map.put("pageSize", list.getTotalPages()); //总页数
		    map.put("rows",list.getContent()); //内容
		} 
	    return map;
	}
	
	
	public boolean deleteUser(String phone) {
		String[] p = phone.split(",");
		for(int i = 0 ; i < p.length; i++) {
			userDao.delete(p[i]);
		}
		return true;
	}

	public boolean editUser(User user) {
		User u = userDao.findOne(user.getPhone());
//		u.setAddress(user.getAddress());
//		u.setAge(user.getAge());
//		u.setEdu(user.getEdu());
//		u.setFuzhai(user.getFuzhai());
//		u.setHuabei(user.getHuabei());
//		u.setJiedaibao(user.getJiedaibao());
//		u.setName(user.getName());
//		u.setPassword(user.getPassword());
//		u.setPhone(user.getPhone());
//		u.setPhoto(user.getPhoto());
//		u.setQq(user.getQq());
//		u.setQrcode(user.getQrcode());
//		u.setState(1);
//		u.setZhima(user.getZhima());
		u.setShenhestate(user.getShenhestate());
		if(userDao.save(u)!= null)
			return true;
		return false;
	}


	public Map<String, Object> findByPhone(String phone,Integer page,Integer rows) {
		Map<String,Object> map = new HashMap<>();
		System.out.println("---------------"+phone+"----------");
		if(phone == null || "".equals(phone)) {
			Pageable pageable = new PageRequest(page-1,rows);
			Page<User> list = userDao.findByState(1,pageable);
			map.put("total",list.getTotalElements()); //总条数
		    map.put("pageNumber", list.getSize()); //10 20 ...
		    map.put("pageSize", list.getTotalPages()); //总页数
		    map.put("rows",list.getContent()); //内容
		}else {
		    map.put("total",1);
		    map.put("pageNumber", 1);
		    map.put("pageSize", 10);
		    map.put("rows",userDao.findByPhone(phone));
		}		
		return map;
	}
}
