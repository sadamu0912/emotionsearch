package com.xjxspace.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.xjxspace.mapper.system.UserMapper;
import com.xjxspace.model.frame.RestResult;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserMapper userMapper;
	
	@ResponseBody
	@RequestMapping("/list/all")
	public Object listAll() {
		return userMapper.getAllUser();
	}

	@RequestMapping("/index")
	public Object index(Model model) {
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("admin","admin");
		
		list.add(map);
		model.addAttribute("list", list);
		return "user/list";
	}
	
	
	@RequestMapping(value="/upload" ,method = RequestMethod.POST)
	@ResponseBody
	public RestResult uplaodFile(@RequestParam MultipartFile[] fileImage,HttpServletRequest req) throws Exception{
	if(fileImage!=null&&fileImage.length!=0){
	}
	
	return  RestResult.ofSuccess(null,"上传成功");
	}
	
}
