package com.hand.shares.utils;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hand.shares.entity.StockInfo;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;

import static com.hand.shares.utils.DateUtil.str2Date;

public class HttpClientUtil {
	/**
	 * 通过httpclient发送json数据
	 *
	 * @param url
	 * @param json
	 * @throws IOException
	 */

	public static String sendJsonByPost(String url, String json) throws IOException {
		//构建httpClient对象
		CloseableHttpClient client = HttpClientBuilder.create().build();

		//构建post请求
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type", "application/json");
		//设置请求体的格式类型
		StringEntity stringEntity = new StringEntity(json, "utf-8");
		//设置请求体的内容
		post.setEntity(stringEntity);

		//发送post请求
		CloseableHttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		//从响应体中解析响应结果
		String result = EntityUtils.toString(entity);

		//关闭httplicent
		client.close();
		return result;
	}

	public static String sendJsonByGet(String url, String json) throws IOException {
		//构建httpClient对象
		CloseableHttpClient client = HttpClientBuilder.create().build();

		//构建一个get请求
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Content-Type", "application/json");
		//发送请求
		CloseableHttpResponse response = client.execute(httpGet);
		//获得响应体
		HttpEntity entity = response.getEntity();
		//从响应体中解析响应结果
		String result = EntityUtils.toString(entity);
		//JSONPObject jsonpObject=JSONPObject.fromObject(result);
		//System.out.println("响应结果:" + entity);
		return result;
	}

	public static String result(String s) throws IOException {
		//构建httpClient对象
		CloseableHttpClient client = HttpClientBuilder.create().build();

		//构建一个get请求
		//"http://data.gtimg.cn/flashdata/hushen/daily/19/sz000002.js?maxage=43201"
		HttpGet httpGet = new HttpGet(s);
		httpGet.addHeader("Content-Type", "application/json");
		//发送请求
		CloseableHttpResponse response = client.execute(httpGet);
		//获得响应体
		HttpEntity entity = response.getEntity();
		//从响应体中解析响应结果
		String result = EntityUtils.toString(entity);
		//JSONPObject jsonpObject=JSONPObject.fromObject(result);

		return result;
	}
}
