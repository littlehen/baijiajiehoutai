package com.example.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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

import com.example.demo.Service.DanService;
import com.example.demo.model.Business;
import com.example.demo.model.Dan;
import com.example.demo.model.User;

@RestController
public class DanController {
	@Autowired
	DanService danService;
	
	@RequestMapping("/ifhuobi")
	public Map<String,Object> ifhuobi(String code,String phone) {
		return danService.ifhuobi(code,phone);
	}
	
	@PostMapping("/finddanuser")
	public Map<String,Object> finddanuser(String code,Integer page,Integer rows) {
		return danService.finddanuser(code,page,rows);
	}
	
	@PostMapping(value="/dan/phone/{phone}")
	public Map<String,Object> findByPhone(@PathVariable("phone") String phone,String code,Integer page,Integer rows){
		return danService.findByPhone(phone,code,page,rows);
	}
	
	@RequestMapping("/buyInfo")
	public Map<String,Object> buyInfo(String code,String phone){
		return danService.buyInfo(code, phone);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/exportDan/{code}")
	public void exportDan(@PathVariable("code")String code,HttpServletRequest req,HttpServletResponse resp) throws IOException {
		 HSSFWorkbook workbook = new HSSFWorkbook();
	     HSSFSheet sheet = workbook.createSheet("用户信息表");
	     
	     String fileName="用户信息表";//设置要导出的文件的名字
	     //新增数据行，并且设置单元格数据
	     
	     int rowNum = 1;
	     
	     String[] headers= {"编号","手机号","姓名","地址","年龄","借款金额","负债","是否有花呗","是否有借贷宝","QQ","芝麻信用分","来源","申请时间"};
		   //headers表示excel表中第一行的表头
	     
	     HSSFRow row = sheet.createRow(0);
	        //在excel表中添加表头
	     
	     for(int i=0;i<headers.length;i++){
	            HSSFCell cell = row.createCell(i);
	            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
	            cell.setCellValue(text);
	     }
	     
	     List<Dan> danlist = danService.Danlist(code);
	     for(int i=0;i<danlist.size();i++) {
	        	HSSFRow row1 = sheet.createRow(rowNum);
	        	Dan dan = danlist.get(i);
	        	if(dan.getCode()!=null && !"".equals(dan.getCode()))
	        		row1.createCell(0).setCellValue(dan.getCode());
	        	else
	        		row1.createCell(0).setCellValue("");
	        	if(dan.getPhone()!=null &&!"".equals(dan.getPhone()))
	        		row1.createCell(1).setCellValue(dan.getPhone());
	        	else
	        		row1.createCell(1).setCellValue("");
	        	if(dan.getName()!=null && !"".equals(dan.getName())) {
	        		//String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(dan.getBorrow_date());
	        		row1.createCell(2).setCellValue(dan.getName());
	        	}
	        	else
	        		row1.createCell(2).setCellValue("");
	        	if(dan.getAddress()!=null && !"".equals(dan.getAddress())) {
	        		//String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(dan.getPay_data());
	        		row1.createCell(3).setCellValue(dan.getAddress());
	        	}
	        	else
	        		row1.createCell(3).setCellValue("");
	        	if(dan.getAge()!=null && !"".equals(dan.getAge())) {
	        		row1.createCell(4).setCellValue(dan.getAge());
	        	}
	        	else
	        		row1.createCell(4).setCellValue("");
	        	if(dan.getEdu()!=null && !"".equals(dan.getEdu())) {
	        		row1.createCell(5).setCellValue(dan.getEdu());
	        	}
	        	else
	        		row1.createCell(5).setCellValue("");
	        	if(dan.getFuzhai()!=null && !"".equals(dan.getFuzhai())) {
	        		row1.createCell(6).setCellValue(dan.getFuzhai());
	        	}
	        	else
	        		row1.createCell(6).setCellValue("");
	        	if(dan.getHuabei()!=null && !"".equals(dan.getHuabei())) {
	        		row1.createCell(7).setCellValue(dan.getHuabei());
	        	}
	        	else
	        		row1.createCell(7).setCellValue("");
	        	if(dan.getJiedaibao()!=null && !"".equals(dan.getJiedaibao())) {
	        		row1.createCell(8).setCellValue(dan.getJiedaibao());
	        	}
	        	else
	        		row1.createCell(8).setCellValue("");
	        	if(dan.getQq()!=null && !"".equals(dan.getQq())) {
	        		row1.createCell(9).setCellValue(dan.getQq());
	        	}
	        	else
	        		row1.createCell(9).setCellValue("");
	        	if(dan.getZhima()!=null && !"".equals(dan.getZhima())) {
	        		row1.createCell(10).setCellValue(dan.getZhima());
	        	}
	        	else
	        		row1.createCell(10).setCellValue("");
	        	if(dan.getSource()!=null && !"".equals(dan.getSource())) {
	        		row1.createCell(11).setCellValue(dan.getSource());
	        	}
	        	else
	        		row1.createCell(11).setCellValue("");
	        	if(dan.getShenqingshijian()!=null && !"".equals(dan.getShenqingshijian())) {
	        		row1.createCell(12).setCellValue(dan.getShenqingshijian());
	        	}
	        	else
	        		row1.createCell(12).setCellValue("");
	        	
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
