package com.hand.shares.entity;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "StockInfo")
public class StockInfo {
	/**
	 * 2006-03-31
	 * 1291.518
	 * 1298.751
	 * 1298.295
	 * 1285.735
	 * 2663685800
	 * 13957859443
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;

	private Date time;

	private Double openingprice;

	private Double maximumpprice;

	private Double closingprice;

	private Double minimumprice;

	private long tradingvolume;

	private long tradingprice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getOpeningprice() {
		return openingprice;
	}

	public void setOpeningprice(Double openingprice) {
		this.openingprice = openingprice;
	}

	public Double getMaximumpprice() {
		return maximumpprice;
	}

	public void setMaximumpprice(Double maximumpprice) {
		this.maximumpprice = maximumpprice;
	}

	public Double getClosingprice() {
		return closingprice;
	}

	public void setClosingprice(Double closingprice) {
		this.closingprice = closingprice;
	}

	public Double getMinimumprice() {
		return minimumprice;
	}

	public void setMinimumprice(Double minimumprice) {
		this.minimumprice = minimumprice;
	}

	public long getTradingvolume() {
		return tradingvolume;
	}

	public void setTradingvolume(long tradingvolume) {
		this.tradingvolume = tradingvolume;
	}

	public long getTradingprice() {
		return tradingprice;
	}

	public void setTradingprice(long tradingprice) {
		this.tradingprice = tradingprice;
	}
}
