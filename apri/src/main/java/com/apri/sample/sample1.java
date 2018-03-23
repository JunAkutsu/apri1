package com.apri.sample;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
@Controller
public class sample1 {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="/sample1", method=RequestMethod.GET)
	public String index(Model model){
		List<Map<String,Object>> list;
		list = jdbcTemplate.queryForList("select * from tantousya");
		model.addAttribute("list", list);
		return "index1";
	}
	
}
