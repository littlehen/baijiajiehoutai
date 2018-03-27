package com.example.demo.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BusinessDao;
import com.example.demo.dao.DanDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Business;
import com.example.demo.model.Dan;
import com.example.demo.model.User;

@Service
public class DanService {
	@Autowired
	DanDao danDao;
	
	@Autowired
	BusinessDao businessDao;
	
	@Autowired
	UserDao userDao;
	
	@SuppressWarnings({ "null", "unlikely-arg-type" })
	public Map<String,Object> buyInfo(String code,String phone){
		Map<String,Object> map = new HashMap<>();
		Business business = businessDao.findByCode(code);
		String[] Ph = phone.split(",");
		int huobi = business.getHuobi();
		if(business.getHuobi() >= Ph.length) {
			for(int i = 0;i < Ph.length;i++) {
				Dan dan = danDao.findByCodeAndPhone(code,Ph[i]);
				List<User> user = userDao.findByPhone(Ph[i]);
				if(dan == null) {
					huobi--;
					business.setHuobi(huobi);
					businessDao.save(business);
					dan.setCode(business.getCode());
					if(user.get(0).getAddress() !=null && !"".equals(user.get(0).getAddress()))
						dan.setAddress(user.get(0).getAddress());
					if(user.get(0).getAge() !=null && !"".equals(user.get(0).getAge()))
						dan.setAge(user.get(0).getAge());
					dan.setDay(3);
					if(user.get(0).getEdu() !=null && !"".equals(user.get(0).getEdu()))
						dan.setEdu(user.get(0).getEdu());
					if(user.get(0).getFuzhai()!=null && !"".equals(user.get(0).getFuzhai()))
						dan.setFuzhai(user.get(0).getFuzhai());
					if(user.get(0).getHuabei() !=null && !"".equals(user.get(0).getHuabei()))
						dan.setHuabei(user.get(0).getHuabei());
					if(user.get(0).getJiedaibao() !=null && !"".equals(user.get(0).getJiedaibao()))
						dan.setJiedaibao(user.get(0).getJiedaibao());
					if(user.get(0).getName() !=null && !"".equals(user.get(0).getName()))
						dan.setName(user.get(0).getName());
					if(user.get(0).getPhone()!=null && !"".equals(user.get(0).getPhone()))
						dan.setPhone(user.get(0).getPhone());
					if(user.get(0).getZhima()!=null && !"".equals(user.get(0).getZhima()))
						dan.setZhima(user.get(0).getZhima());
					if(user.get(0).getQq() !=null && !"".equals(user.get(0).getQq()))
						dan.setQq(user.get(0).getQq());
					if(user.get(0).getSource()!=null && !"".equals(user.get(0).getSource()))
						dan.setSource(user.get(0).getSource());
					if(user.get(0).getShenqingshijian()!=null && !"".equals(user.get(0).getShenqingshijian()))
						dan.setSource(user.get(0).getShenqingshijian());
					danDao.save(dan);
				}
			}
			map.put("state", 1);//此操作将使用一个币
		}else {
			map.put("state", 0);//币不足
		}
		return map;
	}

	@SuppressWarnings("unlikely-arg-type")
	public Map<String,Object> ifhuobi(String code,String phone) {
		Map<String,Object> map = new HashMap<>();
		Business business = businessDao.findOne(code);
		List<User> user = userDao.findByPhone(phone);
		Dan dan = new Dan();
		int huobi = business.getHuobi();
		Dan dan1 = danDao.findByCodeAndPhone(code,phone);
		if(dan1 == null) {
			if(huobi > 0 ) {
				huobi--;
				business.setHuobi(huobi);
				businessDao.save(business);
				dan.setCode(business.getCode());
				if(user.get(0).getAddress() !=null && !"".equals(user.get(0).getAddress()))
					dan.setAddress(user.get(0).getAddress());
				if(user.get(0).getAge() !=null && !"".equals(user.get(0).getAge()))
					dan.setAge(user.get(0).getAge());
				dan.setDay(3);
				if(user.get(0).getEdu() !=null && !"".equals(user.get(0).getEdu()))
					dan.setEdu(user.get(0).getEdu());
				if(user.get(0).getFuzhai()!=null && !"".equals(user.get(0).getFuzhai()))
					dan.setFuzhai(user.get(0).getFuzhai());
				if(user.get(0).getHuabei() !=null && !"".equals(user.get(0).getHuabei()))
					dan.setHuabei(user.get(0).getHuabei());
				if(user.get(0).getJiedaibao() !=null && !"".equals(user.get(0).getJiedaibao()))
					dan.setJiedaibao(user.get(0).getJiedaibao());
				if(user.get(0).getName() !=null && !"".equals(user.get(0).getName()))
					dan.setName(user.get(0).getName());
				if(user.get(0).getPhone()!=null && !"".equals(user.get(0).getPhone()))
					dan.setPhone(user.get(0).getPhone());
				if(user.get(0).getZhima()!=null && !"".equals(user.get(0).getZhima()))
					dan.setZhima(user.get(0).getZhima());
				if(user.get(0).getQq() !=null && !"".equals(user.get(0).getQq()))
					dan.setQq(user.get(0).getQq());
				if(user.get(0).getSource()!=null && !"".equals(user.get(0).getSource()))
					dan.setSource(user.get(0).getSource());
				if(user.get(0).getShenqingshijian()!=null && !"".equals(user.get(0).getShenqingshijian()))
					dan.setShenqingshijian(user.get(0).getShenqingshijian());
				danDao.save(dan);
				map.put("state", 1);//此操作将使用一个币
			}
			else
				map.put("state", 0);//币不足
		}
		else {
//			if(dan1.getDay() > 0) {
				map.put("state", 2);//已经购买过此用户
//			}
//			else {
//				if(huobi > 0 ) {
//					huobi--;
//					business.setHuobi(huobi);
//					businessDao.save(business);
//					dan1.setDay(3);
//					danDao.save(dan1);
//					map.put("state", 1);
//				}
//				else
//					map.put("state", 0);
//			}
		}

		return map;	
	}

	public Map<String, Object> finddanuser(String code,Integer page,Integer rows) {
		Map<String,Object> map = new HashMap<>();
		if(code == null||"".equals(code)) {
			map.put("total", 0);
			map.put("pageNumber", 0);
			map.put("pageSize", 0);
			map.put("rows", null);
		}else {
			List<Dan> list = danDao.findByCode(code);
			map.put("total",list.size()); //总条数
		    map.put("pageNumber",rows); //10 20 ...
		    map.put("pageSize",page); //当前页数
		    List<Dan> li = new LinkedList<Dan>();
		    if(rows*page >= list.size()) {
		    	if(list.size()-rows*(page-1) > 0) {
			    	 for(int i = 0; i < list.size()-rows*(page-1); i++) {
					    	li.add(list.get(rows*(page-1)+i));
					    }
		    	}else {
		    		for(int i = 0; i < list.size(); i++) {
				    	li.add(list.get(rows*(page-1)+i));
				    }
		    	}
		    }else {
		    	for(int i = 0; i < rows; i++) {
			    	li.add(list.get(rows*(page-1)+i));
			    }
				    
		    }
		    map.put("rows",li); //内容

		}
	    return map;
	}

	public Map<String, Object> findByPhone(String phone, String code,Integer page, Integer rows) {
		Map<String,Object> map = new HashMap<>();
		List<Dan> list= danDao.findByMyCodeAndPhone(code,phone);
		    map.put("total",1);
		    map.put("pageNumber", 1);
		    map.put("pageSize", 10);
		    map.put("rows",list);
		return map;
	}
	
	public List<Dan> Danlist(String code){
		return danDao.findByMyCode(code);
	}

}
