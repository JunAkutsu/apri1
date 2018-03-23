package com.apri.csv4;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class TantousyaService {

	@Autowired
	@Qualifier("csv4.TantousyaMapper")
	TantousyaMapper tantousyaMapper;
	
	public List<TantousyaDomain> getTantousya_list(){
		return tantousyaMapper.selectAll();
	}
	
}
