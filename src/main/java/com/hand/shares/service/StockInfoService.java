package com.hand.shares.service;

import com.hand.shares.entity.StockInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StockInfoService {
	public void save(StockInfo stockInfo);
	public void cloneStockInfo();
	public Map<String,Object> findAll(String code, Pageable pageable);
	public Optional<StockInfo> findOne();
	public List<StockInfo> selectHistoryByCode(String coed);
}
