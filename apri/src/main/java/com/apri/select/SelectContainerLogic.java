package com.apri.select;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apri.common.customTag.LabelValue;


//DBからのプルダウンを作成
//labelタグを作成
//MasterFinderBaseもどきを作成

@Controller
@RequestMapping(value="/select")
public class SelectContainerLogic {

	@Autowired
	TantousyaService tantousyaService;
	
	@ModelAttribute
	SelectForm setUpForm() {
		SelectForm form = new SelectForm();
		form.setTantousya_renban(new Long(6));
        return form;
    }
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model){
		List<TantousyaDomain> list = tantousyaService.getTantousya_list();
		List<LabelValue> find_list = tantousyaService.getFind_tantousya_list();
		Map<Long,String> find_map = tantousyaService.getFind_Map_selectAll();
		model.addAttribute("tantousya_list", list);
		model.addAttribute("find_tantousya_list", find_list);
		model.addAttribute("find_map", find_map);
		return "select/index";
	}
	
}
