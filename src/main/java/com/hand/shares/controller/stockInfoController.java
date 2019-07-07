package com.hand.shares.controller;

import com.hand.shares.entity.StockInfo;
import com.hand.shares.service.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class stockInfoController {

	@Autowired
	private StockInfoService stockInfoService;
	@RequestMapping("/save")
	@ResponseBody
	public String save(){
		stockInfoService.cloneStockInfo();
		return "success";
	}

	@GetMapping("/brand")
	public String brand() {
		return "price";
	}


	@GetMapping("/404")
	public String notFoundPage() {
		return "404";
	}



	@GetMapping("/index")
	public String list(Model model,@RequestParam(value = "page", defaultValue = "0") int page,
					   @RequestParam(value = "size", defaultValue = "10") int size,@RequestParam(value="code",defaultValue = "0000001") String code) {
		page = page<0?0:page;
		Sort sort = new Sort(Sort.Direction.ASC, "id");
		Pageable pageable = new PageRequest(page, size, sort);
		Map<String,Object> map =stockInfoService.findAll(code,pageable);
		model.addAttribute("stockInfoPage", map.get("page"));
		model.addAttribute("indexPage", page);
		model.addAttribute("totalPage", map.get("count"));
		model.addAttribute("code", code);
		return "index";
	}

	@GetMapping("/price")
	public String price(Model model,@RequestParam(value = "page", defaultValue = "0") int page,@RequestParam(value = "size", defaultValue = "10") int size,String code) {
		page = page<0?0:page;
		Sort sort = new Sort(Sort.Direction.ASC, "id");
		Pageable pageable = new PageRequest(page, size, sort);
		Map<String,Object> map =stockInfoService.findAll(code,pageable);
		model.addAttribute("stockInfoPage", map.get("page"));
		model.addAttribute("indexPage", page);
		model.addAttribute("totalPage", map.get("count"));
		return "price";
	}
}
