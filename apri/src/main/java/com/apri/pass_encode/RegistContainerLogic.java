package com.apri.pass_encode;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Scope("request")
@RequestMapping(value="/pass")
public class RegistContainerLogic {
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("password", "");
		model.addAttribute("password_encode", "");
		model.addAttribute("password_result", "");
        return "pass_encode/index";
	}
	
	@RequestMapping(value="/encode", method=RequestMethod.POST)
	public String encode(@ModelAttribute("formModel") RegistForm input,Model model){
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password_encode = passwordEncoder.encode(input.getPassword());
		if (passwordEncoder.matches(input.getPassword(), password_encode)) {
			model.addAttribute("password_result", "一致した");
        }
		else{
			model.addAttribute("password_result", "一致しない");
		}
		model.addAttribute("password", input.getPassword());
		model.addAttribute("password_encode", password_encode);
		return "pass_encode/index";
	}
}
