package com.apri.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.apri.common.exception.ApplicationException;
import com.apri.sample2.TantousyaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping(value="/error1")
public class RegistContainerLogic {

    // この記述がないとｈｔｍｌ側でth:object="${registForm}"、th:value="*{name}"を記述しているとエラーが発生する。
	@ModelAttribute
    RegistForm setUpForm() {
        return new RegistForm();
    }
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		return "error/index";
	}
	
	//@Validatedがないと、RegistFormにある@MAXなどのアノテーションが動かない)
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public ModelAndView commit(@ModelAttribute RegistForm form,ModelAndView mav){
		Logger logger = LoggerFactory.getLogger(RegistContainerLogic.class);

		if(form.getAge()==1){
			throw new ApplicationException("ApplicationExceptionエラー");
		}
		if(form.getAge()==2){
			throw new RuntimeException("RuntimeExceptionエラー");
		}
		mav.setViewName("error/index");
		return mav;
	}
}
