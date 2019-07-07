package com.hand.shares.service;

import com.hand.shares.entity.StockInfo;
import com.hand.shares.repository.StockInfoRepository;
import com.hand.shares.task.CloneTask;
import com.hand.shares.utils.Crawler;
import com.hand.shares.utils.DateUtil;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockInfoServiceImpl implements StockInfoService {




	@Autowired
	private StockInfoRepository stockInfoRepository;


	@Override
	public void save(StockInfo stockInfo) {
		stockInfoRepository.save(stockInfo);
	}

	@Override
	public void cloneStockInfo() {
		CloneTask cloneTask=new CloneTask();
		List<Elements> trsList=cloneTask.getTrsList();

		for (Elements trs:trsList) {
			for (int i = 0; i < trs.size(); i++) {

				StockInfo stockInfo = new StockInfo();
				stockInfo.setCode("0000002");

				Elements tds = trs.get(i).select("td");
				for (int j = 0; j < tds.size(); j++) {
					Date date = DateUtil.str2Date(tds.get(0).text());
					stockInfo.setTime(date);
					stockInfo.setOpeningprice(Double.valueOf(tds.get(1).text()));
					stockInfo.setMaximumpprice(Double.valueOf(tds.get(2).text()));
					stockInfo.setClosingprice(Double.valueOf(tds.get(3).text()));
					stockInfo.setMinimumprice(Double.valueOf(tds.get(4).text()));
					stockInfo.setTradingvolume(Long.valueOf(tds.get(5).text()));
					stockInfo.setTradingprice(Long.valueOf(tds.get(6).text()));
				}
				this.save(stockInfo);
				System.out.println("===");
			}
		}




//		for(int m=2015;m<=2018;m++) {
//			System.out.println(m);
//			for (int n = 1; n <= 4; n++) {
//				Elements trs = crawler.getMovieList("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/000001/type/S.phtml?year=" + m + "&jidu=" + n);
//
//				System.out.println(n);
//				for (int i = 0; i < trs.size(); i++) {
//
//					StockInfo stockInfo = new StockInfo();
//					stockInfo.setCode("0000001");
//
//					Elements tds = trs.get(i).select("td");
//					for (int j = 0; j < tds.size(); j++) {
//						Date date = DateUtil.str2Date(tds.get(0).text());
//						stockInfo.setTime(date);
//						stockInfo.setOpeningprice(Double.valueOf(tds.get(1).text()));
//						stockInfo.setMaximumpprice(Double.valueOf(tds.get(2).text()));
//						stockInfo.setClosingprice(Double.valueOf(tds.get(3).text()));
//						stockInfo.setMinimumprice(Double.valueOf(tds.get(4).text()));
//						stockInfo.setTradingvolume(Long.valueOf(tds.get(5).text()));
//						stockInfo.setTradingprice(Long.valueOf(tds.get(6).text()));
//					}
//					this.save(stockInfo);
//					System.out.println("===");
//				}
//			}
//		}
	}

	@Override
	public Map<String,Object> findAll(String code, Pageable pageable) {
		Map<String,Object> map=new HashMap<String,Object>();
		Long count=stockInfoRepository.count();
//		Page<StockInfo> page=stockInfoRepository.findAll(pageable);
		Page<StockInfo> page=stockInfoRepository.findAllStockInfoByCode(code,pageable);
		map.put("count", count/pageable.getPageSize());
		map.put("page", page);
		return  map;

	}

	@Override
	public Optional<StockInfo> findOne() {

		return stockInfoRepository.findById(13L);
	}

	@Override
	public List<StockInfo> selectHistoryByCode(String code) {
		return null;
	}
}
