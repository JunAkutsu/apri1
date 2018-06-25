package com.apri.xlsx_pdf;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.jodconverter.DocumentConverter;
import org.jodconverter.LocalConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.document.DocumentFormat;
import org.jodconverter.document.DocumentFormatRegistry;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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


//xlsxファイル出力(ひな形ファイル取り込)

@Controller
@RequestMapping(value="/xlsx_pdf")
public class SelectContainerLogic {
	
	@Autowired
	TantousyaService tantousyaService;

	@Autowired
	ApplicationContext context;
	
	public static final String SRC_FILE_PATH = "C:\\work\\sample1.xlsx";
	public static final String DEST_FILE_PATH = "C:\\work\\test1.pdf";
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		return "xlsx_pdf/index";
	}
	
	@RequestMapping(value="/convert",method=RequestMethod.POST)
	@ResponseBody
    public void convert(HttpServletResponse response) {
		
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
		
		// xlsxファイル作成
		XLSXTemplateWriter writer = new XLSXTemplateWriter();
		XLSXTemplateHolder holder = writer.createXlsxData(xlsx_list,context);
		
		// バッファ内にxlsxファイルの内容を書き出す
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        // PDF変換時に渡すInputStreamを用意
        ByteArrayInputStream in=null;
        
        try {       
            // バッファ内xlsxのデータを書き出し
            holder.getWb().write(buff);
            // PDF変換時に渡すInputStreamデータを作成
            in = new ByteArrayInputStream(buff.toByteArray());
            // PDFの出力先を開く
//            FileOutputStream out=null;
            BufferedOutputStream out=null;          
            try{
                // ContentTypeの設定
        		response.setHeader("Content-Disposition", "attachment; filename=list.pdf");
                out = new BufferedOutputStream(response.getOutputStream());
                
        		// LibreOfficeを管理するクラス生成
        		OfficeManager officeManager = LocalOfficeManager.make();
        		// conveterを管理するクラス生成
        		DocumentConverter converter = LocalConverter.make(officeManager);
        		
        		DocumentFormatRegistry registry = converter.getFormatRegistry();
        		DocumentFormat xlsxFormat = registry.getFormatByExtension("xlsx");
        		DocumentFormat pdfFormat = registry.getFormatByExtension("pdf");
        		try{
        			// LibreOfficeの起動
        			officeManager.start();
        			// xlsx -> PDF変換
        			converter.convert(in).as(xlsxFormat).to(out).as(pdfFormat).execute();

        		}catch(OfficeException e){
        		      e.printStackTrace();
        		} finally {
            		try{
            			// LibreOfficeの終了
            			officeManager.stop();
            		}catch(OfficeException e){
          		      e.printStackTrace();
            		}
        		}
            	
            } catch (FileNotFoundException fnfe) {
                throw new RuntimeException(fnfe);//開けない場合はここでアウト
            } finally {
                if(null != out){
                    try {
                        out.flush();
                        out.close();
                    } catch (IOException ioe) {
                        // ignore
                        ioe.printStackTrace();
                    }
                }
 
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } finally {
            if(in != null){
                try {
                	in.close();
                } catch (IOException ioe) {
                    // ignore
                    ioe.printStackTrace();
                }
            }
            try {
                buff.close(); // OpenOfficeから切断
            } catch (IOException e) {
                // ignore
                e.printStackTrace();
            }
        }
    }	
}
