package com.example.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserService;
import com.example.demo.model.Business;
import com.example.demo.model.User;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	List<User> userlist  = new ArrayList<>();
	@PostMapping(value="/user/phone/{phone}")
	public Map<String,Object> userById(@PathVariable("phone") String phone,Integer page,Integer rows){
		userlist = userService.findByPhon(phone);
		return userService.findByPhone(phone,page,rows);
	}
	
	@PostMapping(value="userlist")
	public Map<String,Object> users(String admin,Integer page,Integer rows){
		userlist = userService.userlis(admin);
		return userService.userlist(admin,page,rows);
	}
	
	@RequestMapping("/deleteUser")
	public boolean deleteUser(String phone) {
		return userService.deleteUser(phone);
	}
	
	@RequestMapping("/editUser")
	public boolean editUser(User user) {
		return userService.editUser(user);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/exportUser")
	public void exportUser(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		 HSSFWorkbook workbook = new HSSFWorkbook();
	     HSSFSheet sheet = workbook.createSheet("用户信息表");
	     
	     String fileName="用户信息表";//设置要导出的文件的名字
	     //新增数据行，并且设置单元格数据
	     
	     int rowNum = 1;
	     
	     String[] headers= {"用户姓名","手机号","地址","年龄","负债","是否有花呗","是否有借贷宝","借款额度","QQ","芝麻信用","审核状态"};
		   //headers表示excel表中第一行的表头
	     
	     HSSFRow row = sheet.createRow(0);
	        //在excel表中添加表头
	     
	     for(int i=0;i<headers.length;i++){
	            HSSFCell cell = row.createCell(i);
	            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
	            cell.setCellValue(text);
	     }
	     if(userlist.size() > 0) {
		     for(int i=0;i<userlist.size();i++) {
		        	HSSFRow row1 = sheet.createRow(rowNum);
		        	User dan = userlist.get(i);
		        	if(dan.getName()!=null && !"".equals(dan.getName()))
		        		row1.createCell(0).setCellValue(dan.getName());
		        	else
		        		row1.createCell(0).setCellValue("");
		        	if(dan.getPhone()!=null &&!"".equals(dan.getPhone()))
		        		row1.createCell(1).setCellValue(dan.getPhone());
		        	else
		        		row1.createCell(1).setCellValue("");
		        	if(dan.getAddress()!=null && "".equals(dan.getAddress())) {
		        		//String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(dan.getBorrow_date());
		        		row1.createCell(2).setCellValue(dan.getAddress());
		        	}
		        	else
		        		row1.createCell(2).setCellValue("");
		        	if(dan.getAge()!=null && "".equals(dan.getAge())) {
		        		//String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(dan.getPay_data());
		        		row1.createCell(3).setCellValue(dan.getAge());
		        	} 
		        	else
		        		row1.createCell(3).setCellValue("");
		        	if(dan.getFuzhai()!=null && "".equals(dan.getFuzhai())) {
		        		row1.createCell(4).setCellValue(dan.getFuzhai());
		        	}else
		        		row1.createCell(4).setCellValue("");
		        	if(dan.getHuabei()!=null && "".equals(dan.getHuabei())) {
		        		row1.createCell(5).setCellValue(dan.getHuabei());
		        	}else
		        		row1.createCell(5).setCellValue("");
		        	if(dan.getJiedaibao()!=null && "".equals(dan.getJiedaibao())) {
		        		row1.createCell(6).setCellValue(dan.getJiedaibao());
		        	}else
		        		row1.createCell(6).setCellValue("");
		        	if(dan.getEdu()!=null && "".equals(dan.getEdu())) {
		        		row1.createCell(7).setCellValue(dan.getEdu());
		        	}else
		        		row1.createCell(7).setCellValue("");
		        	if(dan.getQq()!=null && "".equals(dan.getQq())) {
		        		row1.createCell(8).setCellValue(dan.getQq());
		        	}else
		        		row1.createCell(8).setCellValue("");
		        	if(dan.getZhima()!=null && "".equals(dan.getZhima())) {
		        		row1.createCell(9).setCellValue(dan.getZhima());
		        	}else
		        		row1.createCell(9).setCellValue("");
		        	if(dan.getState()!=null && "".equals(dan.getState())) {
		        		row1.createCell(10).setCellValue(dan.getState());
		        	}else
		        		row1.createCell(10).setCellValue("");
		        }
	     }
	     
	  // 通过Response把数据以Excel格式保存
	        resp.reset();
	        resp.setContentType("application/msexcel;charset=UTF-8");
	        resp.addHeader("Content-Disposition",
	                "attachment;filename=" + new String((fileName + ".xls").getBytes("GBK"), "ISO8859_1"));
	        // 定义输出类型
	        OutputStream out = resp.getOutputStream();
	        workbook.write(out);
	        out.flush();
	        out.close();
	     
	}
	
}
