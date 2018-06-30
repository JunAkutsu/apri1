package com.apri.login;

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
import org.springframework.context.annotation.Scope;
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
@Scope("request")
public class LoginContainerLogic {

	@Autowired
	MessagesFactory messagesFactory;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String index(Model model){
        return "login/login";
	}

	@RequestMapping(value="/menu", method=RequestMethod.POST)
	public String menu(@ModelAttribute("formModel") LoginForm input,Model model){
		model.addAttribute("tantousya_id", input.getTantousya_id());
		model.addAttribute("language", input.getLanguage());
		return "login/menu";
	}

	@RequestMapping(value="/login_failure", method=RequestMethod.POST)
	public String login_failure(@ModelAttribute("formModel") @Validated LoginForm input,BindingResult result,Model model){
		if(!result.hasErrors()) {
			// メッセージインスタンスの生成
			Messages messages = messagesFactory.newInstance();
			// メッセージの追加
			messages.addMessage("error.login.auth");
			model.addAttribute("error_messages", messages.get_messages_list());
        }
		return "login/login";
	}
	
}
