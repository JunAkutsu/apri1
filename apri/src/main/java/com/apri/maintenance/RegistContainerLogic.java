package com.apri.maintenance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.apri.common.maintenance.ConfigService;
import com.apri.common.masterHelper.KeyValueFactory;



@Controller
@RequestMapping(value="/config")
public class RegistContainerLogic {
	
	@Autowired
	ConfigService configService;

    // この記述がないとｈｔｍｌ側でth:object="${registForm}"、th:value="*{name}"を記述しているとエラーが発生する。
	@ModelAttribute
    RegistForm setUpForm() {
		RegistForm form = new RegistForm();
        return form;
    }
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("sql_log_hyouji_flg", configService.isSql_log_hyouji_flg());
        return "maintenance/index";
	}
	
	//@Validatedがないと、RegistFormにある@MAXなどのアノテーションが動かない)
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public ModelAndView commit(@ModelAttribute @Validated RegistForm form, BindingResult result,ModelAndView mav){
		
		Logger logger = LoggerFactory.getLogger(RegistContainerLogic.class);
		
		// config情報を設定する。
		configService.setSql_log_hyouji_flg(form.isSql_log_hyouji_flg());
		
		// 画面の項目を表示する
		logger.info("sql_log_hyouji_flg="+configService.isSql_log_hyouji_flg());
		mav.addObject("sql_log_hyouji_flg", configService.isSql_log_hyouji_flg());
		mav.setViewName("maintenance/index");
		return mav;
	}
}
