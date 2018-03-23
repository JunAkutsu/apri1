package com.apri.sample3;

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

//commit、rollbackを手動によるトランザクション管理にしたバージョン

@Controller
@RequestMapping(value="/sample3")
public class SelectContainerLogic3 {

	@Autowired
	TantousyaService3 tantousyaService3;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@RequestMapping(value="/tantousya/insert", method=RequestMethod.GET)
	public String index2(Model model){
		List<TantousyaDomain3> list = tantousyaService3.getTantousya_list();
		model.addAttribute("list", list);
		return "sample3/index2";
	}
	@RequestMapping(value="/tantousya/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("formModel") TantousyaDomain3 input,Model model){
	    TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);		
	    
		try{
			tantousyaService3.executeCall(input);
			transactionManager.commit(status);
		}
		catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(status);
        }
		List<TantousyaDomain3> list = tantousyaService3.getTantousya_list();
		model.addAttribute("list", list);
		return "sample3/index2";
	}
	
}
