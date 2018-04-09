package com.fengmap.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.postgresql.ssl.NonValidatingFactory.NonValidatingTM;

/**
 *  <p>Title: HttpClientUtils </p>
 *  <p>Description: </p>
 *  <p>Company: www.fengmap.com </p> 
 *	@author aaron
 *  @date 2018年3月7日上午11:04:14
 *  @version 1.0
 */
public final class HttpClientUtils {
	
	/**
	 * 获取httpsClient 对象
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	private static CloseableHttpClient getHttpsClient() throws KeyManagementException, NoSuchAlgorithmException{
		X509TrustManager tm=new NonValidatingTM();
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, new TrustManager[]{tm}, null);
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,NoopHostnameVerifier.INSTANCE);
		
		/**
         * 通过setSSLSocketFactory(sslsf)保证httpclient实例能发送Https请求
         */
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).setMaxConnTotal(50)
                .setMaxConnPerRoute(50).setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectionRequestTimeout(60000).setConnectTimeout(60000).setSocketTimeout(60000).build())
                .build();
		return httpclient;
	}
	
	/**
	 * 执行httpGet请求
	 * @param url
	 * @return
	 */
	public  static String executeHttpGet(String url) {
		String result="";
		try (CloseableHttpClient httpClient=HttpClients.createDefault();
				){
			HttpGet httpGet=new HttpGet(url);
			CloseableHttpResponse response=null;
			InputStream input=null;
			BufferedReader bufferedReader=null;
			Reader reader=null;
			response=httpClient.execute(httpGet);
			HttpEntity entity=response.getEntity();
			input=entity.getContent();
			reader=new InputStreamReader(input);
			bufferedReader=new BufferedReader(reader);
			String line="";
			while((line=bufferedReader.readLine())!=null){
				result=line;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 执行https Get请求
	 * @param url
	 * @return
	 */
	public static String executeHttpsGet(String url) {
		String response="";
		try (CloseableHttpClient httpclient =getHttpsClient();){
			HttpGet httpget = new HttpGet(url);
			BasicResponseHandler responseHandler=new BasicResponseHandler();
			response=httpclient.execute(httpget, responseHandler);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	private static String testHttpGet(String url) {
		return executeHttpGet(url);
	}
	
	private static String testHttpsGet(String url) {
		return executeHttpsGet(url);
	}
	
	public static void main(String[] args) {
		//WeixinInterfaceParamInfo code=null;
		/*CloseableHttpClient httpclient;
		String url=WeixinSmallProgramInterfaceUtils.buildWeixinInterfaceURL(code);
		try {
			httpclient = getHttpsClient();
			HttpGet httpget = new HttpGet("https://www.fengmap.com/FMCloud/user/dk?_=1489043917308");
			BasicResponseHandler responseHandler=new BasicResponseHandler();
			//HttpPost httpPost=new HttpPost("https://www.fengmap.com/FMCloud/user/login?timestamp=1489043917525");
			String response=httpclient.execute(httpget, responseHandler);
			System.out.println("---------------------"+response);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		String response=testHttpsGet("https://www.fengmap.com/FMCloud/user/dk?_=1489043917308");
		testHttpGet("http://www.fengmap.com/FMCloud/user/dk?_=1489043917308");
		System.out.println(response);
	}
}

