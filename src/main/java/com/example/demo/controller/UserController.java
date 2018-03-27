package com.example.demo.controller;

import java.io.File;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.UserService;
import com.example.demo.model.User;
import com.example.demo.util.ExcelImportUtils;

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
	
	@PostMapping(value="/userlist")
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
	@RequestMapping("/exportUs")
	public void exportUser(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		 HSSFWorkbook workbook = new HSSFWorkbook();
	     HSSFSheet sheet = workbook.createSheet("用户信息表");
	     
	     String fileName="用户信息表";//设置要导出的文件的名字
	     //新增数据行，并且设置单元格数据
	     
	     int rowNum = 1;
	     
	     String[] headers= {"来源","姓名","手机号","芝麻信用分","地区","申请时间"};
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
		        	row1.createCell(0).setCellValue("享来介");
		        	if(dan.getName()!=null && !"".equals(dan.getName()))
		        		row1.createCell(1).setCellValue(dan.getName());
		        	else
		        		row1.createCell(1).setCellValue("");
		        	if(dan.getPhone()!=null && !"".equals(dan.getPhone())) {
		        		//String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(dan.getBorrow_date());
		        		row1.createCell(2).setCellValue(dan.getPhone());
		        	}
		        	else
		        		row1.createCell(2).setCellValue("");
		        	if(dan.getZhima()!=null && !"".equals(dan.getZhima())) {
		        		//String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(dan.getPay_data());
		        		row1.createCell(3).setCellValue(dan.getZhima());
		        	} 
		        	else
		        		row1.createCell(3).setCellValue("");
		        	if(dan.getAddress()!=null && !"".equals(dan.getAddress())) {
		        		row1.createCell(4).setCellValue(dan.getAddress());
		        	}else
		        		row1.createCell(4).setCellValue("");
		        
		        	row1.createCell(5).setCellValue("");
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
	
	
	@RequestMapping("/batchImport")
	public void batchImport(@RequestParam(value="filename") MultipartFile file,HttpServletRequest request,
			HttpServletResponse response) throws IOException   {
		//判断文件是否为空  
		if(file == null)
			System.out.println("导入文件为空！");
		//获取文件名 
		String fileName = file.getOriginalFilename();
		//验证文件名是否合格  
		if(!ExcelImportUtils.validateExcel(fileName)) {
			System.out.println("文件格式不对！");
		}
		//进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size = file.getSize();
		if(StringUtils.isEmpty(fileName) || size == 0) {
			System.out.println("文件内容不能为空！");
		}
		System.out.println("ok");
		String rootPath = request.getSession().getServletContext().getRealPath(File.separator);
		userService.importFile(file,rootPath);
		response.setContentType("text/html;charset=utf-8");
		response.sendRedirect("/admin/index.html");
	}
	
	
	
	
	//houtaiadd  -- h5
	@RequestMapping("/Laiqian")
	public Map<String,Object> Laiqian(String name,String IdCard,String money,String phone,String qq,String 	wxnumber,String zhima){
		
		return userService.Laiqian(name, IdCard, money, phone, qq, wxnumber, zhima);
	}
	
	@RequestMapping("/yanzheng")
	public Map<String,Object> yanzheng(String mobile){
		return userService.yanzhengma(mobile);
	}
}
