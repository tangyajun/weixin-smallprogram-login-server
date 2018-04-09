package com.fengmap.weixin;

/**
 * 微信小程序接口工具类
 *  <p>Title: WeixinInterfaceUtils </p>
 *  <p>Description: </p>
 *  <p>Company: www.fengmap.com </p> 
 *	@author aaron
 *  @date 2018年3月7日下午3:49:51
 *  @version 1.0
 */
public final class WeixinSmallProgramInterfaceUtils {

	// 微信小程序接口地址
	private static final String WEIXIN_API_URL="https://api.weixin.qq.com/sns/jscode2session?";
	
	/**
	 * 构建微信小程序session_key接口地址
	 * @param code 微信小程序接口参数
	 * 包含appid、secret、js_code、grant_type
	 * @return
	 */
	private static String buildWeixinInterfaceURL(WeixinInterfaceParamInfo code) {
		StringBuilder code_url=new StringBuilder(WEIXIN_API_URL);
		code_url.append("appid=");
		code_url.append(code.getAppid());
		code_url.append("&secret=");
		code_url.append(code.getSecret());
		code_url.append("&js_code=");
		code_url.append(code.getJs_code());
		code_url.append("&grant_type=authorization_code");
		return code_url.toString();
	}
	
	/**
	 * 使用httpclient 通过appid、secret、code、grant_type等参数 请求微信小程序接口获取session_key和openid
	 * @param code 
	 * @return
	 */
	public static String weixinCode2sessionKey(WeixinInterfaceParamInfo code) {
		String url=buildWeixinInterfaceURL(code);
		return HttpClientUtils.executeHttpsGet(url);
	}
}

