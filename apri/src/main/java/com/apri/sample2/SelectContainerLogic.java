package com.apri.sample2;

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

// @Transactional(Service内でCommit/rollback)によるトランザクション管理

@Controller
@RequestMapping(value="/sample2")
public class SelectContainerLogic {

	@Autowired
	TantousyaService tantousyaService;
	
	@RequestMapping(value="/tantousya", method=RequestMethod.GET)
	public String index(Model model){
		List<TantousyaDomain> list = tantousyaService.getTantousya_list();
		model.addAttribute("list", list);
		return "index1";
	}
	@RequestMapping(value="/tantousya/insert", method=RequestMethod.GET)
	public String index2(Model model){
		List<TantousyaDomain> list = tantousyaService.getTantousya_list();
		model.addAttribute("list", list);
		return "sample2/index2";
	}
	@RequestMapping(value="/tantousya/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("formModel") TantousyaDomain input,Model model){
//		try{
			int result = tantousyaService.executeCall(input);
			System.out.println("result="+result);
			List<TantousyaDomain> list = tantousyaService.getTantousya_list();
			model.addAttribute("list", list);
//		}
//		catch (Exception e) {
//            e.printStackTrace();
//        }
		return "sample2/index2";
	}
	
}
