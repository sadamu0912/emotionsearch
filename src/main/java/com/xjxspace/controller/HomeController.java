package com.xjxspace.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xjxspace.util.Common;

@Controller
public class HomeController {
	
	
	@RequestMapping("/login")
	public String index(Model model,HttpServletRequest req) {
		if(Common.isLogin(req)){
			return "index";
		}
		return "login";
	}
	
	@RequestMapping("/")
	public String login(Model model,HttpServletRequest req) {
		return "index";
	}

	@RequestMapping("/upload")
	public String uploadPage(Model model,HttpServletRequest req) {
		return "upload";
	}


	@RequestMapping("/tabs")
	public String tabs(Model model,HttpServletRequest req) {
		return "tabs";
	}
	
	/**
	 * 首页
	 */
	@RequestMapping(value="/indexFrame",method=RequestMethod.GET)
	public String getIndexFrame(@RequestParam Map<String,Object> params,Model model){
		return "default";
	}
}
