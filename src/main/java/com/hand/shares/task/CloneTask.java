package com.hand.shares.task;

import com.hand.shares.service.StockInfoService;
import com.hand.shares.utils.Crawler;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CloneTask {
	@Autowired
	private StockInfoService stockInfoService;

//	@Scheduled(cron = "0 0 3 * * ?")
//	@Scheduled(cron = "0 */1 * * * ?")
	public void clonetask() {
		System.out.println("++=================");

		stockInfoService.cloneStockInfo();
	}

	public List<Elements> getTrsList(){

	Crawler crawler = new Crawler();

	List<Elements> trsList=new ArrayList<>();
	for (int p=3;p<5;p++){
		if(p%2==0){
			try {
				Thread.sleep(1000*6);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(p);
		for(int m=2009;m<=2018;m++) {
			System.out.println(m);
			for (int n = 1; n <= 4; n++) {
				Elements trs = crawler.getMovieList("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/00000"+p+"/type/S.phtml?year=" + m + "&jidu=" + n);

				trsList.add(trs);
				System.out.println(n);

			}
		}
	}

		return trsList;
	}

	public static void main(String[] args) {
		Map<String,Object> map=new HashMap<>();
		map.put("11","qw");
		map.put("22","bb");
		System.out.println(4863/10);
		System.out.println(map.get("22"));


//		for (int i=1;i<10;i++){
//			if(i%2==0) {
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println(i);
//			}
//		}
	}
}
