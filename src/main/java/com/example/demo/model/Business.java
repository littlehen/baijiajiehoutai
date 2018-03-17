package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Business {
	

	private String name;
	
	@Id
	private String code;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private Float amount; //最高额度

	private Integer period; //周期

	private String qualification; //资格

	private String materials; //材料

	private String matters;//注意事项
	
	private String qq;
	
	private String password;
	
	private Integer huobi;
	
	private Integer ohuobi;
	
	private String shenhestate;
	
	public String getshenhestate() {
		return shenhestate;
	}
	public void setshenhestate(String shenhestate) {
		this.shenhestate = shenhestate;
	}
	public Integer getOhuobi() {
		return ohuobi;
	}
	public void setOhuobi(Integer ohuobi) {
		this.ohuobi = ohuobi;
	}
	public Integer getHuobi() {
		return huobi;
	}
	public void setHuobi(Integer huobi) {
		this.huobi = huobi;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Business() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getMaterials() {
		return materials;
	}
	public void setMaterials(String materials) {
		this.materials = materials;
	}
	public String getMatters() {
		return matters;
	}
	public void setMatters(String matters) {
		this.matters = matters;
	}
}