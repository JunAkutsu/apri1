package com.apri.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apri.sample2.TantousyaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


//commit、rollbackによるトランザクション管理

@Controller
@RequestMapping(value="/ajax")
public class RegistContainerLogic {

	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		return "ajax/index";
	}
	
	@RequestMapping(value="/json",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public String ajax_regist(@RequestBody JsonBean jsonBean){
		// new GsonBuilderを使用すると値がNULLの場合もJSONに出力される。
		// new GSONを使用すると値がNULLの場合は、JSONから外される。
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		HashMap map = new HashMap();
		map.put("input_value", jsonBean.getV_a1());
		map.put("tantousya_renban", "102");
		map.put("tantousya_mei", "山田一郎");
		map.put("tantousya_age", null);
		// ログ出力
		Logger logger = LoggerFactory.getLogger(RegistContainerLogic.class);
		logger.info("KITA1");
		return gson.toJson(map);
	}
}
