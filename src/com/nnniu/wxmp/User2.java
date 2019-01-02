package com.nnniu.wxmp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class User2 {
	@NotBlank(message="微信id不能为空")
	private String openId;
	@NotBlank(message="名字不能为空")
	@Length(min=1, max=5)
	private String name;
	@NotBlank(message="电话不能为空")
	@Pattern(regexp="^1(3|4|5|7|8)\\d{9}$", message="手机号码格式错误")
	private String phone;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "openId: " + openId + ", name: " + name + ", phone: " + phone;
	}
}
