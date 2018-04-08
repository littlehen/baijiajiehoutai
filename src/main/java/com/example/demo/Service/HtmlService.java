package com.example.demo.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.BusinessDao;
import com.example.demo.dao.HtmlDao;
import com.example.demo.model.Html;

@Service
public class HtmlService {
	
	@Autowired
	HtmlDao htmlDao;
	
	@Autowired
	BusinessDao businessDao;
	
	public  Map<String,Object> urllist(String admin,Integer page,Integer rows){
		Map<String,Object> map = new HashMap<>();
		Pageable pageable = new PageRequest(page-1,rows);
		if(admin == null||"".equals(admin)) {
			map.put("total", 0);
			map.put("pageNumber", 0);
			map.put("pageSize", 0);
			map.put("rows", null);
		}else {

			Page<Html> list = htmlDao.findAll(pageable);
			map.put("total",list.getTotalElements()); //总条数
		    map.put("pageNumber", list.getSize()); //10 20 ...
		    map.put("pageSize", list.getTotalPages()); //总页数
		    map.put("rows",list.getContent()); //内容
		} 
	    return map;
	}
	
	public String newHtml(String filePath,String disrPath,String fileName,MultipartFile file) {
		Map<String,Object> map = new HashMap<>();
		Html html = new Html();
		String url = "";
		String imagePath = "";
		
		String img = "";
		//上传照片,保存
		if(!file.isEmpty()) {
			try {
				Resource resource = new ClassPathResource("static/new_html/assets/images"); //选择保存
				if(resource.exists()) {
					String im = URLDecoder.decode(resource.getURL().getPath(), "UTF-8");
					imagePath = URLDecoder.decode(file.getOriginalFilename(), "UTF-8");
					img = file.getOriginalFilename();
					System.out.println(imagePath);
					File f =new File(im,imagePath);
					BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(f));//输入输出流
					outputStream.write(file.getBytes());
					outputStream.flush();
					outputStream.close();
				}
			}catch(Exception e) {
				System.out.print(e.toString());
			}
		}
		
		//新建一个登录页面
		try {
			String templateContent = "";
			FileInputStream fileinputstream = new FileInputStream(filePath);// 读取模板文件
			InputStreamReader oStreamReader = new InputStreamReader(fileinputstream, "utf-8");
			int len = fileinputstream.read(); 
		    char []cha = new char[20240];
            oStreamReader.read(cha);
            oStreamReader.close();
            templateContent = new String(cha);
  //          int lenght = fileinputstream.available();
 //           byte bytes[] = new byte[lenght];
 //           fileinputstream.read(bytes);
 //           fileinputstream.close();
			  
		    
//            templateContent = new String(bytes);
            
            
  //          String fn = new String(fileName.getBytes("gbk"),"utf-8");
            templateContent = templateContent.replaceAll("<title>借款申请</title>", "<title>"+fileName+"</title>"); 
            templateContent = templateContent.replaceAll("shenqing.htm", fileName + ".htm");
            templateContent = templateContent.replaceAll("shenqingneirong.htm", fileName+"-2.htm").replace("借款申请", fileName);
           
            if(img != null && !"".equals(img)) {
            	templateContent = templateContent.replaceAll("/firstPic.png", "/"+img);
            }
            
            String filename = fileName + ".htm";
            filename = disrPath+"/" + filename;// 生成的html文件保存路径。
            
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(filename), "utf-8");
            oStreamWriter.append(templateContent);
            oStreamWriter.close();
//            FileOutputStream fileoutputstream = new FileOutputStream(filename);// 建立文件输出流
//            byte tag_bytes[] = templateContent.getBytes();
//            fileoutputstream.write(tag_bytes);
//            fileoutputstream.close();
            
            html.setTitle(fileName);
            html.setUrl("http:/www.juhuaihua.cn:8082/new_html/"+fileName+".htm");
            int x=(int)(Math.random()*1000000)+100000;
            int y=(int)(Math.random()*100000)+100000;
            while(htmlDao.findByCount(x+"") != null && businessDao.findOne(x+"") != null) {
            	x=(int)(Math.random()*1000000)+100000;
            }
            html.setCount(x+"");
            html.setPassword(y+"");
            htmlDao.save(html);
            url = "/new_html/html.html";
        } catch (Exception e) {
            System.out.print(e.toString());
            return "/new_html/html.html";
        }
		
		//新建一个注册页面
		
		try {
			String templateContent = "";
			filePath = filePath.replace("shenqing.htm", "shenqingneirong.htm");
			FileInputStream fileinputstream = new FileInputStream(filePath);// 读取模板文件
			
			InputStreamReader oStreamReader = new InputStreamReader(fileinputstream, "utf-8");
//			int len = fileinputstream.read(); 
		    char []cha = new char[20240];
            oStreamReader.read(cha);
            oStreamReader.close();
            templateContent = new String(cha);
//            int lenght = fileinputstream.available();
//            byte bytes[] = new byte[lenght];
//            fileinputstream.read(bytes);
//            fileinputstream.close();
//            templateContent = new String(bytes);
			
 //           String fn = new String(fileName.getBytes("gbk"),"utf-8");
            templateContent = templateContent.replaceAll("<title>借款申请</title>", "<title>"+fileName+"</title>");
            if(img != null && !"".equals(img)) {											
            	templateContent = templateContent.replaceAll("/firstPic.png", "/"+img).replaceAll("借款申请", fileName);
            }
            
            String filename = fileName + "-2.htm";
            filename = disrPath+"/" + filename;// 生成的html文件保存路径。
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(filename), "utf-8");
            oStreamWriter.append(templateContent);
            oStreamWriter.close();
//            FileOutputStream fileoutputstream = new FileOutputStream(filename);// 建立文件输出流
//            byte tag_bytes[] = templateContent.getBytes();
//            fileoutputstream.write(tag_bytes);
//            fileoutputstream.close();

        } catch (Exception e) {
        	System.out.println(e.getLocalizedMessage());
        }
		return url;
	}
}
