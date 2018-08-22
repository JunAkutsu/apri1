package com.apri.iframe;

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

import com.apri.sample2.TantousyaService;
import com.apri.sample3.TantousyaDomain3;

//iframeの実装

@Controller
@RequestMapping(value="/iframe")
public class RegistContainerLogic {

	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(Model model){
		return "iframe/index";
	}
	
	@RequestMapping(value="index2", method=RequestMethod.GET)
	public String index2(Model model){
		return "iframe/index2";
	}
	
	@RequestMapping(value="index3", method=RequestMethod.GET)
	public String index3(Model model){
		return "iframe/index3";
	}
	
	@RequestMapping(value="index4", method=RequestMethod.GET)
	public String index4(Model model){
		return "iframe/index4";
	}
	
	@RequestMapping(value="menu", method=RequestMethod.GET)
	public String menu(Model model){
		return "iframe/menu";
	}
	
	@RequestMapping(value="menu3", method=RequestMethod.GET)
	public String menu3(Model model){
		return "iframe/menu3";
	}
	
	@RequestMapping(value="menu4", method=RequestMethod.GET)
	public String menu4(Model model){
		return "iframe/menu4";
	}
}
