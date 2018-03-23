package com.apri.csv;

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

import com.apri.sample2.TantousyaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


//CSVファイル出力

@Controller
@RequestMapping(value="/csv")
public class SelectContainerLogic {

	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		return "csv/index";
	}
	
	@RequestMapping(value="/downloadCSV",method=RequestMethod.POST)
	@GetMapping(value = "data.csv",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=Shift_JIS; Content-Disposition: attachment")
	@ResponseBody
    public Object downloadCSV() throws JsonProcessingException {
        List<CSVContent> list = new ArrayList<CSVContent>();
        list.add(new CSVContent(1, "山田一", 10, "男"));
        list.add(new CSVContent(2, "山田二", 15, "女"));
        list.add(new CSVContent(3, "山田三", 20, "男"));

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(CSVContent.class).withHeader();
        return mapper.writer(schema).writeValueAsString(list);
    }	
}
