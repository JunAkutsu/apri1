package com.apri.trans;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// @Transactional(Service内でCommit/rollback)によるトランザクション管理

@Controller
@Scope("request")
@RequestMapping(value="/trans")
public class RegistContainerLogic {

	@Autowired
	TantousyaService tantousyaService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model){
		List<RegistContent> list = tantousyaService.getTantousya_list();
		model.addAttribute("list", list);
		return "trans/index";
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("formModel") RegistForm input,Model model){
			RegistContent data = new RegistContent();
			data.setTantousya_renban(input.getTantousya_renban());
			data.setSimei(input.getSimei());
			data.setTantousya_id(input.getTantousya_id());
			int result = tantousyaService.executeCall(data);
			List<RegistContent> list = tantousyaService.getTantousya_list();
			model.addAttribute("list", list);
		return "trans/index";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String index2(Model model){
		List<RegistContent> list = tantousyaService.getTantousya_list();
		model.addAttribute("list", list);
		return "trans/index2";
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute("formModel") RegistForm input,Model model){
			RegistContent data = new RegistContent();
			data.setTantousya_renban(input.getTantousya_renban());
			data.setSimei(input.getSimei());
			data.setTantousya_id(input.getTantousya_id());
			int result = tantousyaService.executeUpdate(data);
			List<RegistContent> list = tantousyaService.getTantousya_list();
			model.addAttribute("list", list);
		return "trans/index2";
	}
}
