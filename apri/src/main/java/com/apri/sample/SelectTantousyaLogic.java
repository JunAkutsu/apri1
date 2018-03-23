package com.apri.sample;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/sample")
public class SelectTantousyaLogic {

	@Autowired
	TantousyaMapper tantousyaMapper;
	
	@RequestMapping(value="/tantousya", method=RequestMethod.GET)
	public String index(Model model){
		List<SelectTantousyaContent> list = tantousyaMapper.selectAll();
		model.addAttribute("list", list);
		return "index1";
	}
	@RequestMapping(value="/tantousya/insert", method=RequestMethod.GET)
	public String index2(Model model){
		return "index2";
	}
	@RequestMapping(value="/tantousya/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("formModel") SelectTantousyaContent input,Model model){
        tantousyaMapper.insert(input);
		List<SelectTantousyaContent> list = tantousyaMapper.selectAll();
		model.addAttribute("list", list);
		return "index2";
	}
	
}
