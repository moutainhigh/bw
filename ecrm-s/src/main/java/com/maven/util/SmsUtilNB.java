package com.maven.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.hy.pull.common.util.api.Enum_MSG;

/***
 * 发送短信接口工具类
 * 
 * 
 * 接入商：互亿无线
 * 客户：牛博娱乐提供的
 * 
 * @author Administrator
 *
 */
public class SmsUtilNB {

	/**
	 * 国内验证码短信
	 * 
	 * 以GET方式推送短信，只适合验证码模式
	 * 
	 * @param account	API账号
	 * @param password	API密码
	 * @param mobile	目标手机号码
	 * @param content	验证码，数字，小于10位数
	 * 
	 * @return Map<String, String> code参数为1时表示成功
	 */
	public static Map<String, String> sendSmsByGet1(String mobile,String content){
		
		//短信内容（请注意，此格式是固定的，需要在短信平台先行预设，不得随意私自编辑内容，否则无法发送）
		//模板：您的验证码是：1234。请不要把验证码泄露给其他人。
		
		StringBuffer urls = new StringBuffer();
		urls.append("http://106.ihuyi.com/webservice/sms.php?method=Submit");
		urls.append("&account=").append("C81704747");
		urls.append("&password=").append("38ac9353a803d496bdba51350ecf6a4d");
		urls.append("&mobile=").append(mobile);
		urls.append("&content=").append("您的验证码是：").append(content).append("。请不要把验证码泄露给其他人。");
		
		String result = getUrl(urls.toString());
//		System.out.println(result);
		Map<String, String> map = getQueryParams(result);
		if(map.get("code").equals("2")) {
			map.put("code", "1");
		}
		return map; 
	}
	
	
	/**
	 * 国外短信
	 * 
	 * 以GET方式推送短信
	 * 
	 * @param account	API账号
	 * @param password	API密码
	 * @param mobile	目标手机号码
	 * @param content	验证码，数字，小于10位数
	 * 
	 * @return Map<String, String> code参数为1时表示成功
	 */
	public static Map<String, String> sendSmsByGet2(String mobile,String content){
		//http://api.isms.ihuyi.com/webservice/isms.php?method=Submit&account=APIID&password=APIKEY&mobile=手机号码&content=Your verification code is 1125
		
		StringBuffer urls = new StringBuffer();
		urls.append("http://106.ihuyi.cn/webservice/sms.php?method=Submit");
		urls.append("&account=").append("");//API账号
		urls.append("&password=").append("");//API密码
		urls.append("&mobile=").append(mobile);
		urls.append("&content=").append("Your verification code is 1125").append(content).append("");
		
		String result = getUrl(urls.toString());
		Map<String, String> map = getQueryParams(result);
		if(map.get("code").equals("2")) {
			map.put("code", "1");
		}
		return map; 
	}
	
	private static Map<String, String> getQueryParams(String xmlStr) {
		if(xmlStr==null || xmlStr.equals("")) {
			return null;
		}
		Document doc = null;
		Map<String, String> data = new HashMap<String, String>();
		try {
			doc = DocumentHelper.parseText(xmlStr); // 将字符串转为XML    
			Element root = doc.getRootElement();
			
			List<Element> list2 = root.selectNodes("//SubmitResult");
			
			for (Element element : list2) {
				
				List<Element> list = element.elements();
				
				for (Element element2 : list) {
					data.put(element2.getName(), element2.getStringValue());
					//System.out.println(element2.getName() +"-"+ element2.getStringValue());
				}
				
				
			}
			return data;
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getUrl(String urls) {
		StringBuilder sb = new StringBuilder();
		try {
			InputStream in = new URL(urls).openStream();
			InputStreamReader inputStreamReader = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			reader.close();
			inputStreamReader.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return Enum_MSG.接口调用错误.message(e.getMessage());
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(sendSmsByGet1("15369050199", RandomStringNum.createRandomString(4)));;
	}
}
