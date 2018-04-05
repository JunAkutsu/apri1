package com.apri.select2;

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
import com.apri.common.masterHelper.KeyValueFactory;
import com.apri.common.masterHelper.KeyValueHelper;


//DBからのプルダウンを作成
//labelタグを作成
//MasterFinderBaseもどきを作成

@Controller
@RequestMapping(value="/select2")
public class SelectContainerLogic {

	@Autowired
	KeyValueFactory keyValueFactory;
	
	@ModelAttribute
	SelectForm setUpForm() {
		SelectForm form = new SelectForm();
		form.setTantousya_renban(new Long(1));
		form.setMode("red");
        return form;
    }
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model){
		KeyValueHelper helper = keyValueFactory.newInstance();
//		Map<Object,Object> result = helper.getTantousya();
		Map<Object,Object> result = helper.getTantousyaWithNULL();
		Map<Object,Object> result2 = helper.getKeyValueLong(new Integer(1));
		Map<Object,Object> result3 = helper.getKeyValue(new Integer(2));
		model.addAttribute("find_map", result);
		model.addAttribute("find_key_value", result2);
		model.addAttribute("find_key_value_string", result3);
		return "select2/index";
	}
	
}
