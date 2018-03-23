package com.apri.sample3;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Service
public class TantousyaService3 {

	@Autowired
	TantousyaMapper3 tantousyaMapper;
	
	public List<TantousyaDomain3> getTantousya_list(){
		return tantousyaMapper.selectAll();
	}
	
	public void executeCall(TantousyaDomain3 input) throws Exception {
		tantousyaMapper.insert(input);
		throw new Exception();
	}
	
}
