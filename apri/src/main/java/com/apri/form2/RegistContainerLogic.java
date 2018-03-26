package com.apri.form2;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.apri.sample2.TantousyaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping(value="/form2")
public class RegistContainerLogic {

    // この記述がないとｈｔｍｌ側でth:object="${registForm}"、th:value="*{name}"を記述しているとエラーが発生する。
	@ModelAttribute
    RegistForm setUpForm() {
        return new RegistForm();
    }
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		return "form2/index";
	}
	
	//@Validatedがないと、RegistFormにある@MAXなどのアノテーションが動かない)
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public ModelAndView commit(@ModelAttribute @Validated RegistForm form, BindingResult result,ModelAndView mav){
		Logger logger = LoggerFactory.getLogger(RegistContainerLogic.class);
		
		if(result.hasErrors()){
			logger.info("error="+result.toString());
		}
		else{
			if(form.getAge() == 7 || form.getAge() == 10){
				result.rejectValue("age", "error.age.seven", new Object[]{"7","10"}, "7は入力出来ません。");
			}
		}
		
		mav.setViewName("form2/index");
		return mav;
	}
}
