package com.example.demo.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.example.demo.dao.UserDao;
import com.example.demo.model.Administrator;
import com.example.demo.model.User;
import com.example.demo.util.ExcelImportUtils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;


@Service
public class UserService {
	@Autowired
	UserDao userDao;
	
	
	List<User> userlist  = new ArrayList<>();
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
	
	
	public List<User>  userlis(String admin){
		if(admin == null||"".equals(admin)) {
			userlist = null;
		}else
			userlist = userDao.findAll();
		return userlist;
			
	}
	
	public boolean deleteUser(String phone) {
		String[] p = phone.split(",");
		for(int i = 0 ; i < p.length; i++) {
			userDao.delete1(p[i]);
		}
		return true;
	}

	public boolean editUser(User user) {
		List<User> u = userDao.findByPhone(user.getPhone());
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
		u.get(0).setShenhestate(user.getShenhestate());
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
	
	public List<User> findByPhon(String phone){
		if(phone == null || "".equals(phone)) {
			userlist = userDao.findByState(1);
		}else
			userlist = userDao.findByPhone(phone);
		
		return userlist;
	}


	public void importFile(MultipartFile mFile, String rootPath) {
		
		String filename = mFile.getOriginalFilename();
		
		
		String filePath =  filename;
		try {
			File file = new File(rootPath+filePath);
			if(!file.exists()){
			    //先得到文件的上级目录，并创建上级目录，在创建文件
			    file.getParentFile().mkdir();
			    try {
			        //创建文件
			        file.createNewFile();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
			
			mFile.transferTo(file);
			if (ExcelImportUtils.isExcel2003(filename)) {
				importXls(file);
			}else if (ExcelImportUtils.isExcel2007(filename)) {
				importXlsx(file);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	private void importXlsx(File file) {
		InputStream is = null;
		XSSFWorkbook xWorkbook = null;
		try {
			 is = new FileInputStream(file);
			 xWorkbook = new XSSFWorkbook(is);
			 XSSFSheet xSheet = xWorkbook.getSheetAt(0);
			 
			 if (null != xSheet) {
				 for (int i = 1; i < xSheet.getPhysicalNumberOfRows(); i++){  
						User su = new User();
						XSSFRow hRow = xSheet.getRow(i);
						//这里
						if(hRow.getCell(0).toString() != null &&!"".equals(hRow.getCell(0).toString()))
							su.setSource(hRow.getCell(0).toString());
						if(hRow.getCell(1).toString() != null &&!"".equals(hRow.getCell(1).toString()))
							su.setName(hRow.getCell(1).toString());
						if(hRow.getCell(2).toString() != null &&!"".equals(hRow.getCell(2).toString()))
							su.setPhone(hRow.getCell(2).toString());
						if(hRow.getCell(3).toString() != null &&!"".equals(hRow.getCell(3).toString()))
							su.setZhima(Float.parseFloat(hRow.getCell(3).toString()));
						if(hRow.getCell(4).toString() != null &&!"".equals(hRow.getCell(4).toString()))
							su.setAddress(hRow.getCell(4).toString());
						if(hRow.getCell(5).toString() != null &&!"".equals(hRow.getCell(5).toString()))
							su.setShenqingshijian(hRow.getCell(5).toString());
						userDao.save(su);
					}
			 }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(null != is) {
				try {
					is.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


	private void importXls(File file) {
		InputStream is = null;
		HSSFWorkbook hWorkbook = null;
		
		try {
			is = new FileInputStream(file);
			hWorkbook = new HSSFWorkbook(is);
			HSSFSheet hSheet = hWorkbook.getSheetAt(0);
			if(null != hSheet) {
				for (int i = 1; i < hSheet.getPhysicalNumberOfRows(); i++){  
					User su = new User();
					
					//这里
					HSSFRow hRow = hSheet.getRow(i);
					if(hRow.getCell(0).toString() != null &&!"".equals(hRow.getCell(0).toString()))
						su.setSource(hRow.getCell(0).toString());
					if(hRow.getCell(1).toString() != null &&!"".equals(hRow.getCell(1).toString()))
						su.setName(hRow.getCell(1).toString());
					if(hRow.getCell(2).toString() != null &&!"".equals(hRow.getCell(2).toString()))
						su.setPhone(hRow.getCell(2).toString());
					if(hRow.getCell(3).toString() != null &&!"".equals(hRow.getCell(3).toString()))
						su.setZhima(Float.parseFloat(hRow.getCell(3).toString()));
					if(hRow.getCell(4).toString() != null &&!"".equals(hRow.getCell(4).toString()))
						su.setAddress(hRow.getCell(4).toString());
					if(hRow.getCell(5).toString() != null &&!"".equals(hRow.getCell(5).toString()))
						su.setShenqingshijian(hRow.getCell(5).toString());
					userDao.save(su);
				}
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(null != is) {
				try {
					is.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
