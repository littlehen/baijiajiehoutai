package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer did;
	
	private String code;
	
	private String phone;
	
	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	private String name;
	
	private Integer age;
	
	private String qq;
	
	private String address;
	
	private Float zhima;
	
	private String huabei;
	
	private String jiedaibao;
	
	private Float fuzhai;
	
	private Float edu;
	
	private Integer day;
	
	private String shenqingshijian;
	
	private String source;

	public String getShenqingshijian() {
		return shenqingshijian;
	}

	public void setShenqingshijian(String shenqingshijian) {
		this.shenqingshijian = shenqingshijian;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Float getZhima() {
		return zhima;
	}

	public void setZhima(Float zhima) {
		this.zhima = zhima;
	}

	public String getHuabei() {
		return huabei;
	}

	public void setHuabei(String huabei) {
		this.huabei = huabei;
	}

	public String getJiedaibao() {
		return jiedaibao;
	}

	public void setJiedaibao(String jiedaibao) {
		this.jiedaibao = jiedaibao;
	}

	public Float getFuzhai() {
		return fuzhai;
	}

	public void setFuzhai(Float fuzhai) {
		this.fuzhai = fuzhai;
	}

	public Float getEdu() {
		return edu;
	}

	public void setEdu(Float edu) {
		this.edu = edu;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

}
