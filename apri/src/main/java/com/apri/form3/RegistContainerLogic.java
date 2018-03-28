package com.apri.form3;

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
@RequestMapping(value="/form3")
public class RegistContainerLogic {

    // この記述がないとｈｔｍｌ側でth:object="${registForm}"、th:value="*{name}"を記述しているとエラーが発生する。
	@ModelAttribute
    RegistForm setUpForm() {
        return new RegistForm();
    }
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){

		Logger logger = LoggerFactory.getLogger(RegistContainerLogic.class);
		
		// Validator を取得
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        // Bean を作成
        RegistForm form = new RegistForm();

        // バリデーションを実行
        form.setAge(1);
        form.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Set<ConstraintViolation<RegistForm>> result = validator.validate(form);

        // 結果の確認
        logger.info("size = " + result.size());
        logger.info("message = " + result.iterator().next().getMessage());

        return "form3/index";
	}
	
}
