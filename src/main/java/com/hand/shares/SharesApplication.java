package com.hand.shares;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@EnableScheduling
@SpringBootApplication
public class SharesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharesApplication.class, args);
	}


}
