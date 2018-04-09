package com.fengmap.weixin;

/**
 * 微信小程序接口参数信息
 *  <p>Title: WeixinCode </p>
 *  <p>Description: </p>
 *  <p>Company: www.fengmap.com </p> 
 *	@author aaron
 *  @date 2018年3月7日上午10:56:06
 *  @version 1.0
 */
public class WeixinInterfaceParamInfo {
    //  微信小程序唯一标识
	private String appid;
	//  微信小程序 secret
	private String secret;
	//  登录后的code
	private String js_code;
	//  授权类型，值为 authorization_code
	private String grant_type;
	
	public WeixinInterfaceParamInfo() {
		
	}
	
	public WeixinInterfaceParamInfo(String appid,String secret,String js_code,String grant_type) {
		this.appid=appid;
		this.secret=secret;
		this.js_code=js_code;
		this.grant_type=grant_type;
	}

	public String getJs_code() {
		return js_code;
	}

	public void setJs_code(String js_code) {
		this.js_code = js_code;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	
}

