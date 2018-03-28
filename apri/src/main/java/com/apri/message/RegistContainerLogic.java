package com.apri.message;

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
import org.springframework.context.MessageSource;
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

import com.apri.common.message.Messages;
import com.apri.common.message.MessagesFactory;



@Controller
@RequestMapping(value="/message")
public class RegistContainerLogic {

	@Autowired
	MessagesFactory messagesFactory;
	
    // この記述がないとｈｔｍｌ側でth:object="${registForm}"、th:value="*{name}"を記述しているとエラーが発生する。
	@ModelAttribute
    RegistForm setUpForm() {
        return new RegistForm();
    }
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){

		Logger logger = LoggerFactory.getLogger(RegistContainerLogic.class);
		
		Messages messages = messagesFactory.newInstance();
		
		messages.addMessage("error.required");
		messages.addMessage("error.age.seven",new Object[]{"7","10"});
		
        // 結果の確認
        logger.info("message = " + messages.get_messages());
        
        model.addAttribute("messages", messages.get_messages_list());

        return "message/index";
	}
	
}
