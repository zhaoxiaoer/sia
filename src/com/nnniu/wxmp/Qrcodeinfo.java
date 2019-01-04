package com.nnniu.wxmp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Qrcodeinfo {
	@NotBlank(message="二维码类型不能为空")
	private String qrtype;
//	@Min(300)
	@Max(2592000)
	private int expiration;
	@NotNull
	@Min(1)
	@Max(100000)
	private int scene;

	public String getQrtype() {
		return qrtype;
	}
	public void setQrtype(String qrtype) {
		this.qrtype = qrtype;
	}
	public int getExpiration() {
		return expiration;
	}
	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}
	public int getScene() {
		return scene;
	}
	public void setScene(int scene) {
		this.scene = scene;
	}
}
