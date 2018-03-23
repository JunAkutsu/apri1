package com.apri.xlsx;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.apri.csv4.TantousyaDomain;
import com.apri.csv4.TantousyaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.handlers.CsvEntityListHandler;


//CSVファイル出力(OrangeSignal CSV)

@Controller
@RequestMapping(value="/xlsx")
public class SelectContainerLogic {
	
	@Autowired
	TantousyaService tantousyaService;

	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		return "xlsx/index";
	}
	
	@RequestMapping(value="/downloadXLSX",method=RequestMethod.POST)
    public ModelAndView downloadCSV() {
		XLSXHolder holder = new XLSXHolder();
		holder.setFilename("test2");
		
		// DB検索
		List<TantousyaDomain> list = tantousyaService.getTantousya_list();
		List<String[]>xlsx_list = new ArrayList();
		for(int i=0;i<list.size();i++){
			TantousyaDomain data = (TantousyaDomain)list.get(i);
			String[] value = new String[3];
			value[0] = data.getTantousya_renban().toString();
			value[1] = data.getTantousya_id().toString();
			value[2] = data.getSimei();
			xlsx_list.add(value);
		}
		
		holder.setDataFields(xlsx_list);
		Map<String, Object> map = new HashMap<>();
		map.put("holder", holder);
		ModelAndView mav = new ModelAndView(new ExcelBuilder(), map);	
	    return mav;

    }	
}
