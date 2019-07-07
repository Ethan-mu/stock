package com.hand.shares.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crawler {
	
	/**
	 * 传入url，将要访问的页面转换成String字符串
	 * 
	 * @param url
	 * @return htmlString
	 */
	public String getHtmlString(String url){
		
		String htmlString = "";
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			if(response != null && 
					response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				htmlString = EntityUtils.toString(entity);
//				htmlToFile(htmlString);
				return htmlString;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	/**
	 * 将访问的html页面保存为文件
	 * 
	 * @param htmlString
	 * @throws ParseException 、IOException
	 * @throws 
	 */
	public void htmlToFile(String htmlString) throws ParseException, IOException{
		String dirPath = "E:\\douban-html\\";
		File dirFile = new File(dirPath);
		if(dirFile == null || !dirFile.exists()){
			dirFile.mkdir();
		}
		String filePath = dirPath+"douban.html";
		File htmlFile = new File(filePath);
		if(htmlFile == null || !htmlFile.exists()){
			try {
				htmlFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream out = new FileOutputStream(htmlFile);
		out.write(htmlString.getBytes("UTF-8"));
		if (out != null) {
            out.close();
        }
	}

	/**
	 * 处理response，Jsoup解析页面，获取所需数据
	 * 
	 * @param url
	 */
	public  Elements getMovieList(String url){
		List<String> movieType = null;
		String html = getHtmlString(url);
		//将String类型的html转换为Document
		Document doc = Jsoup.parse(html);
//		System.out.println(doc);
		//获取所有class为pl2的元素，即包含所有<a>的div
//		Elements nodes = doc.getElementsByClass("p12");
		Elements nodes1 = doc.getElementsByTag("tbody");

		Element elemen=nodes1.get(4);
		Elements trs=elemen.getElementsByTag("tr");
		//删除多余的tr
		trs.remove(0);
		return trs;
		
	}
	
	/**
	 * 测试用main方法
	 * 
	 * @param
	 */
	public  List<Elements> dump(){
		List<Elements> trsList=new ArrayList<>();
		//2019没有34季度
		for(int i=2015;i<=2018;i++){
			System.out.println(i);
			for(int j=1;j<=4;j++){
				Elements trs=this.getMovieList("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/000001/type/S.phtml?year="+i+"&jidu="+j);
				trsList.add(trs);
				System.out.println(j);

			}
		}


		return trsList;
	}

}
