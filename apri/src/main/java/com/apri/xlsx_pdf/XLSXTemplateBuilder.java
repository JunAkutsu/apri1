package com.apri.xlsx_pdf;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class XLSXTemplateBuilder extends AbstractXlsxView  {
	@Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

		// XLSX情報の取得
		XLSXTemplateHolder holder = (XLSXTemplateHolder)model.get("holder");
		
		// ファイル名の設定
		response.setHeader("Content-Disposition", "attachment; filename=" + holder.getFile_name());
		
        try{
    	    OutputStream out = new BufferedOutputStream(response.getOutputStream());
    	    holder.getWb().write(out);
    	    out.flush();
        } finally {
        	if (holder.getWb() != null) {
        		// 2017/03/30 J.Akutsu XLSXファイル作成時にメモリ不足エラーが発生する対応
        		// 一時ファイルを削除する。
        		((Workbook) holder.getWb()).close();
        	}
        }
    }
}
