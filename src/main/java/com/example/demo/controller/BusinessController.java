package com.example.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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

import com.example.demo.Service.BusinessService;
import com.example.demo.model.Business;
import com.example.demo.model.Dan;

@RestController
public class BusinessController {
	
	@Autowired
	BusinessService businessService;
	
	List<Business> Businesslist = new ArrayList<>();
	@RequestMapping("/blogin")
	public Map<String,Object> login(String code,String bpassword) {
		Businesslist = businessService.businessAl();
		return businessService.login(code, bpassword);
	}
	
	@RequestMapping("/Search")
	public Map<String,Object> businessAll(String qq,Integer rows,Integer page){
		Businesslist = businessService.businessAl(qq);
		return businessService.businessAll(qq,rows, page);
	}
	
	@RequestMapping("/Searchshen")
	public Map<String,Object> Searchshen(String code,Integer rows,Integer page){
		return businessService.Searchshen(code,rows, page);
	}
	
	@RequestMapping("/ruzhu")
	public Map<String,Object> businessRuzhu(String code,String password,String bname,String QQ,
			Float amount,Integer period,String qualification,String materials,String matters){
		return businessService.businessRuzhu(code,password,bname,QQ,amount,period,qualification,
				materials,matters);
	}
	
	@RequestMapping("/addBusiness")
	public boolean addBusiness(Business business) {
		return businessService.addBusiness(business);
	}
	
//	@RequestMapping("/delete")
//	public boolean deleteBusiness(String bid) {
//		return businessService.deleteBusiness(bid);
//		
//	}
//	
//	@RequestMapping("/edit")
//	public boolean editBusiness(Business business,Long bid) {
//		return businessService.editBusiness(business,bid);
//	}
	
	@RequestMapping("/editBusiness/{aname}")
	public Map<String, Object> editBusiness(@PathVariable("aname") String aname,Business business) {
		return businessService.editBusiness(aname,business);
	}
	
	@RequestMapping("/edithuobi/{aname}")
	public Map<String, Object> edithuobi(@PathVariable("aname") String aname,Business business) {
		return businessService.edithuobi(aname,business);
	}
	
	@RequestMapping("/shenhestate")
	public Map<String, Object> shenhestate(String code) {
		return businessService.shenhestate(code);
	}
	
	@PostMapping("/buserlist")
	public Map<String,Object> buserlist(String code,Integer page,Integer rows){
		return businessService.buserlist(code,page,rows);
	}
	
	@PostMapping("/findhuobivalue")
	public Map<String,Object> findhuobivalue(String code){
		return businessService.findhuobivalue(code);
	}
	
	@RequestMapping("/exportBus")
	public void exportBus(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		 HSSFWorkbook workbook = new HSSFWorkbook();
	     HSSFSheet sheet = workbook.createSheet("商家信息表");
	     
	     String fileName="商家信息表";//设置要导出的文件的名字
	     //新增数据行，并且设置单元格数据
	     
	     int rowNum = 1;
	     
	     String[] headers= {"账号","商家","借款额度","QQ","审核状态"};
		   //headers表示excel表中第一行的表头
	     
	     HSSFRow row = sheet.createRow(0);
	        //在excel表中添加表头
	     
	     for(int i=0;i<headers.length;i++){
	            HSSFCell cell = row.createCell(i);
	            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
	            cell.setCellValue(text);
	     }
	     for(int i=0;i<Businesslist.size();i++) {
	        	HSSFRow row1 = sheet.createRow(rowNum);
	        	Business dan = Businesslist.get(i);
	        	if(dan.getCode()!=null && !"".equals(dan.getCode()))
	        		row1.createCell(0).setCellValue(dan.getCode());
	        	else
	        		row1.createCell(0).setCellValue("");
	        	if(dan.getName()!=null &&!"".equals(dan.getName()))
	        		row1.createCell(1).setCellValue(dan.getName());
	        	else
	        		row1.createCell(1).setCellValue("");
	        	if(dan.getAmount()!=null) {
	        		//String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(dan.getBorrow_date());
	        		row1.createCell(2).setCellValue(dan.getAmount());
	        	}
	        	else
	        		row1.createCell(2).setCellValue("");
	        	if(dan.getQq()!=null && "".equals(dan.getQq())) {
	        		//String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(dan.getPay_data());
	        		row1.createCell(3).setCellValue(dan.getQq());
	        	}
	        	else
	        		row1.createCell(3).setCellValue("");
	        	if(dan.getshenhestate()!=null && "".equals(dan.getshenhestate())) {
	        		row1.createCell(4).setCellValue(dan.getshenhestate());
	        	}
	        	else
	        		row1.createCell(4).setCellValue("");
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
