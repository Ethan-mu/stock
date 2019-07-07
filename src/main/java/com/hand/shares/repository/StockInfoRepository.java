package com.hand.shares.repository;

import com.hand.shares.entity.StockInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface StockInfoRepository extends PagingAndSortingRepository<StockInfo, Long>,CrudRepository<StockInfo,Long> {
	Page<StockInfo> findAllStockInfoByCode(String code, Pageable pageable);

//	@Query("SELECT closingprice,time FROM stock_info s where s.code = :code")
//	List<StockInfo> selectHistoryByCode(@Param("code") String code);

}
