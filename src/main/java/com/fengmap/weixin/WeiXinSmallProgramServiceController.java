package com.fengmap.weixin;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序登录服务
 *  <p>Title: WeiXinServiceController </p>
 *  <p>Description: </p>
 *  <p>Company: www.fengmap.com </p> 
 *	@author aaron
 *  @date 2018年3月7日上午10:51:12
 *  @version 1.0
 */

@RestController
@RequestMapping(value="/weixin")
public class WeiXinSmallProgramServiceController {

	/**
	 * 获取微信小程序session_key及openid
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/onLogin",method=RequestMethod.POST)
	public String prepareLogin(@RequestBody WeixinInterfaceParamInfo param) {
		return WeixinSmallProgramInterfaceUtils.weixinCode2sessionKey(param);
	}
	
	/**
	 * 获取微信小程序session_key及openid
	 * @param appid 小程序唯一标识
	 * @param secret 小程序app的secret
	 * @param js_code 登录时获取的code
	 * @param grant_type 填写为 authorization_code
	 * @return
	 */
	@RequestMapping(value="/onLogin",method=RequestMethod.GET)
	public String getOpenIdAndSessionKey(@RequestParam String appid,
			@RequestParam String secret,@RequestParam String js_code,
			@RequestParam String grant_type) {
		WeixinInterfaceParamInfo param=new WeixinInterfaceParamInfo(appid, secret, js_code, grant_type);
		return WeixinSmallProgramInterfaceUtils.weixinCode2sessionKey(param);
	}
}

