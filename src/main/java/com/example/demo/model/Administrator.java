package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Administrator {
	
	@Id
	private int aid;
	
	private String aname;
	
	private String password;
	
	private int state;
	
	private Integer yidangohuobi;
	
	private Integer erdangohuobi;
	
	private Integer sandangohuobi;

	public int getAid() {
		return aid;
	}

	public Integer getYidangohuobi() {
		return yidangohuobi;
	}

	public void setYidangohuobi(Integer yidangohuobi) {
		this.yidangohuobi = yidangohuobi;
	}

	public Integer getErdangohuobi() {
		return erdangohuobi;
	}

	public void setErdangohuobi(Integer erdangohuobi) {
		this.erdangohuobi = erdangohuobi;
	}

	public Integer getSandangohuobi() {
		return sandangohuobi;
	}

	public void setSandangohuobi(Integer sandangohuobi) {
		this.sandangohuobi = sandangohuobi;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Administrator() {
		super();
	}
	
}
