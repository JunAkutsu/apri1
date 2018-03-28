package com.apri.form5;

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



@Controller
@RequestMapping(value="/form5")
public class RegistContainerLogic {

    // この記述がないとｈｔｍｌ側でth:object="${registForm}"、th:value="*{name}"を記述しているとエラーが発生する。
	@ModelAttribute
    RegistForm setUpForm() {
		DetailContentLogic content= null;
		RegistForm form = new RegistForm();
		content = new DetailContentLogic();
		form.addContents(content);
		content = new DetailContentLogic();
		form.addContents(content);
        return form;
    }
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
        return "form5/index";
	}
	
	//@Validatedがないと、RegistFormにある@MAXなどのアノテーションが動かない)
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public ModelAndView commit(@ModelAttribute @Validated RegistForm form, BindingResult result,ModelAndView mav){
		Logger logger = LoggerFactory.getLogger(RegistContainerLogic.class);
		// 画面の項目を表示する。
		logger.info("name="+form.getName());
		logger.info("age="+form.getAge());
		for(int i=0;i<form.getContents().size();i++){
			DetailContentLogic content = (DetailContentLogic)form.getContents().get(i);
			logger.info("itemName["+i+"]="+content.getItemName());
			logger.info("price["+i+"]="+content.getPrice());
		}
		
		// formに存在しないプロパティを指定すると画面表示の際にエラーが表示される。
//		if(result.hasErrors()){
//			result.rejectValue("header", "error.header.exists", new Object[]{}, "error.header.exists");
//		}
		
		mav.setViewName("form5/index");
		return mav;
	}
}
