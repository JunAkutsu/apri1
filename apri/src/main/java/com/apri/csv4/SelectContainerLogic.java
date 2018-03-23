package com.apri.csv4;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.handlers.CsvEntityListHandler;


//CSVファイル出力(OrangeSignal CSV/DB検索バージョン、ブラウザに出力)

@Controller
@RequestMapping(value="/csv4")
public class SelectContainerLogic {

	@Autowired
	TantousyaService tantousyaService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		return "csv4/index";
	}
	
	@RequestMapping(value="/downloadCSV",method=RequestMethod.POST)
    public Object downloadCSV(HttpServletResponse response) throws IOException {
		// DB検索
		List<TantousyaDomain> list = tantousyaService.getTantousya_list();
        // CSV出力の為のConfigファイルの生成
		CsvConfig cfg = new CsvConfig();
		// CSVファイル出力
		response.addHeader("Content-Type", "application/octet-stream");
		response.addHeader("Content-Disposition", "attachment; filename=list4.csv");
		try{
			Csv.save(list, response.getOutputStream(),cfg, new CsvEntityListHandler<TantousyaDomain>(TantousyaDomain.class));
		} catch (IOException e) { 
		}
		return null;
    }	
}
