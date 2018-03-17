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

import com.example.demo.dao.AdministratorDao;
import com.example.demo.dao.BusinessDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Administrator;
import com.example.demo.model.Business;
import com.example.demo.model.User;

@Service
public class BusinessService {
	
	@Autowired
	BusinessDao businessDao;
	
	@Autowired
	AdministratorDao administratorDao;
	
	@Autowired
	UserDao userDao;
	
	public Map<String,Object> businessAll(String qq,Integer rows,Integer page){
		Map<String,Object> map = new HashMap<>();
		Pageable pageable = new PageRequest(page-1,rows);
		
		if("".equals(qq) || qq == null) {
			Page<Business> business = businessDao.findAll(pageable);
			map.put("total", business.getTotalElements());
			map.put("pageNumber", business.getSize());
			map.put("pageSize", business.getTotalPages());
			map.put("rows", business.getContent());
		}else {
			Page<Business> business = businessDao.findByQq(qq, pageable);
			map.put("total", business.getTotalElements());
			map.put("pageNumber", business.getSize());
			map.put("pageSize", business.getTotalPages());
			map.put("rows", business.getContent());
		}
		return map;
	}
	
	
	public boolean addBusiness(Business business) {
		if(businessDao.save(business) != null)
			return true;
		return false;
	}
	
//	public boolean deleteBusiness(String bid) {
//		String[] b = bid.split(",");
//		for(int i = 0; i < b.length ; i++) {
//			businessDao.delete((long) Integer.parseInt(b[i]));
//		}
//		return true;
//	}
//	
//	public boolean editBusiness(Business business ,Long bid) {
//		Business b = businessDao.findOne(bid);
//		b.setAmount(business.getAmount());
//		b.setMaterials(business.getMaterials());
//		b.setMatters(business.getMatters());
//		b.setName(business.getName());
//		b.setPeriod(business.getPeriod());
//		b.setQq(business.getQq());
//		b.setQualification(business.getQualification());
//		if(businessDao.save(b) != null)
//			return true;
//		return false;
//	}


	public Map<String, Object> businessRuzhu(String code, String password, String bname, String qQ, Float amount,
			Integer period, String qualification, String materials, String matters) {
		Map<String,Object> map = new HashMap<>();
		Business business = new Business();
		business.setAmount(amount);
		business.setMaterials(materials);
		business.setMatters(matters);
		business.setName(bname);
		business.setPassword(password);
		business.setPeriod(period);
		business.setQq(qQ);
		business.setQualification(qualification);
		business.setCode(code);
		business.setshenhestate("审核进行中");
		if(businessDao.save(business) != null)
			map.put("state", true);
		else
			map.put("state", false);
		return map;
	}


	public Map<String, Object> login(String code, String bpassword) {
		Map<String,Object> map = new HashMap<>();
		Business business = businessDao.findByCode(code);
		if(business != null) {
			if(business.getPassword().equals(bpassword)) {
				map.put("state", true);
				map.put("business", business);
			}
			else {
				map.put("state", false);
			}
		}
		else
			map.put("state", false);
		return map;
	}


	public Map<String, Object> editBusiness(String aname,Business business) {
		Map<String,Object> map = new HashMap<>();
		Integer houbi = 0;
		Business b = businessDao.findOne(business.getCode());
		String shenhestate = business.getshenhestate();
		if(shenhestate.equals("审核通过")) {
			map.put("state", "审核通过");
			Administrator a = new Administrator();
			a = administratorDao.findByAname(aname);
//			if(b.getDangshu().equals("一档"))
//				houbi = a.getYidangohuobi();
//			if(b.getDangshu().equals("二档"))
//				houbi = a.getErdangohuobi();
//			if(b.getDangshu().equals("三档"))
//				houbi = a.getSandangohuobi();
			b.setHuobi(0);
			b.setOhuobi(0);
		}
		else if(shenhestate.equals("审核进行中")){
			map.put("state", "审核进行中");
			houbi = 0;
			b.setHuobi(houbi);
			b.setOhuobi(houbi);
		}else if(shenhestate.equals("审核不通过")){
			map.put("state", "审核不通过");
			houbi = 0;
			b.setHuobi(houbi);
			b.setOhuobi(houbi);
		}
		
		b.setshenhestate(shenhestate);		
		if(businessDao.save(b)!= null)
			map.put("iftrue", true);
		else
			map.put("iftrue", false);
		return map;
	}


	public Map<String, Object> shenhestate(String code) {
		Map<String,Object> map = new HashMap<>();
		Business business = businessDao.findByCode(code);
		map.put("state", business.getshenhestate());
		return map;
	}


	public Map<String, Object> buserlist(String code,Integer page, Integer rows) {
		Map<String,Object> map = new HashMap<>();
		Pageable pageable = new PageRequest(page-1,rows);
		if(code == null||"".equals(code)) {
			map.put("total", 0);
			map.put("pageNumber", 0);
			map.put("pageSize", 0);
			map.put("rows", null);
		}else {
			Page<User> list = userDao.findByShenhestate("审核通过",pageable);
			map.put("total",list.getTotalElements()); //总条数
		    map.put("pageNumber", list.getSize()); //10 20 ...
		    map.put("pageSize", list.getTotalPages()); //总页数
		    map.put("rows",list.getContent()); //内容
		} 
	    return map;
	}


	public Map<String, Object> findhuobivalue(String code) {
		Map<String,Object> map = new HashMap<>();
		int huobi = 0;
		int lefthuobi = 0;
		Business business = new Business();
		business = businessDao.findOne(code);
		huobi = business.getOhuobi();
		lefthuobi = business.getHuobi();
		map.put("huobi", huobi);
		map.put("lefthuobi", lefthuobi);
		return map;
	}


	public Map<String, Object> Searchshen(String code, Integer rows, Integer page) {
		Map<String,Object> map = new HashMap<>();
		
		if("".equals(code) || code == null) {

			List<Business> list = businessDao.findByShenhestate("审核通过");
			map.put("total",list.size()); //总条数
		    map.put("pageNumber",rows); //10 20 ...
		    map.put("pageSize",page); //当前页数
		    List<Business> li = new LinkedList<Business>();
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
		}else {
			List<Business> list = (List<Business>) businessDao.findOne(code);
			map.put("total",list.size()); //总条数
		    map.put("pageNumber",rows); //10 20 ...
		    map.put("pageSize",page); //当前页数
		    List<Business> li = new LinkedList<Business>();
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
		
		
//		Map<String,Object> map = new HashMap<>();
//		if(admin == null||"".equals(admin)) {
//			map.put("total", 0);
//			map.put("pageNumber", 0);
//			map.put("pageSize", 0);
//			map.put("rows", null);
//		}else {
////			Pageable pageable = new PageRequest(page-1,rows);
//			List<Business> list = businessDao.findByShenhestate("审核通过");
//			map.put("total",list.size()); //总条数
//		    map.put("pageNumber",rows); //10 20 ...
//		    map.put("pageSize",page); //当前页数
//		    List<Business> li = new LinkedList<Business>();
//		    if(rows*page >= list.size()) {
//		    	if(list.size()-rows*(page-1) > 0) {
//			    	 for(int i = 0; i < list.size()-rows*(page-1); i++) {
//					    	li.add(list.get(rows*(page-1)+i));
//					    }
//		    	}else {
//		    		for(int i = 0; i < list.size(); i++) {
//				    	li.add(list.get(rows*(page-1)+i));
//				    }
//		    	}
//		    }else {
//		    	for(int i = 0; i < rows; i++) {
//			    	li.add(list.get(rows*(page-1)+i));
//			    }
//				    
//		    }
//		    map.put("rows",li); //内容
//		}
//	    return map;
	}


	public Map<String, Object> edithuobi(String aname, Business business) {
		Map<String,Object> map = new HashMap<>();
		Integer houbi = 0;
		Administrator a = new Administrator();
		a = administratorDao.findByAname(aname);
		Business b = businessDao.findOne(business.getCode());
		if(a!=null) {
			houbi = business.getOhuobi();
			b.setOhuobi(houbi);;
			b.setHuobi(houbi);
		}
		if(businessDao.save(b)!= null) {
			map.put("state", "货币发放成功");
		}
		else
			map.put("state", "货币发放失败");
		
		return map;
	}
}
