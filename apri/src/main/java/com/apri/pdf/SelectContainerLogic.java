package com.apri.pdf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
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

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

//CSVファイル出力(OrangeSignal CSV)

@Controller
@RequestMapping(value="/pdf")
public class SelectContainerLogic {
	
	@Autowired
	TantousyaService tantousyaService;
	
	@Autowired
	ApplicationContext context;

	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		return "pdf/index";
	}
	
	@RequestMapping(value="/downloadPDF",method=RequestMethod.POST)
	@ResponseBody
    public void downloadPDF(HttpServletResponse response) throws Exception {
		
 		// 帳票テンプレートの読み込み
 		Resource resource = context.getResource("classpath:jasperreports/sample2.jrxml"); 
        InputStream inputStream = resource.getInputStream(); 
        JasperReport report=JasperCompileManager.compileReport(inputStream);		 
 		
		// DB検索
		List<TantousyaDomain> list = tantousyaService.getTantousya_list();

        // データ作成（パラメータ）
        HashMap<String,  Object> params = new HashMap<String,  Object>();
        params.put("test1", "テスト1");

        // データの設定
        JasperPrint jasperPrint = JasperFillManager.fillReport(report,  params,  new JRBeanCollectionDataSource(list));
        
        // ContentTypeの設定
//        response.setContentType(MediaType.APPLICATION_PDF_VALUE); 
		response.setHeader("Content-Disposition", "attachment; filename=test1.pdf");
        
        // PDFの作成
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream()); 
    }	
}
