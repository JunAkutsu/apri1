package com.apri.xlsx;

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

public class ExcelBuilder extends AbstractXlsxView  {
	@Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

		// XLSX情報の取得
		XLSXHolder holder = (XLSXHolder)model.get("holder");
		
		// ファイル名の設定
		response.setHeader("Content-Disposition", "attachment; filename=" + holder.getFilename()+holder.getExtension());
		
        Writer out = new OutputStreamWriter(response.getOutputStream());
        
        // XLSXWriterを使用してレスポンスに出力する
        XLSXWriter writer = new XLSXWriter(out, holder.getPropertyNames(),holder.getFilename());
        
        try{
            int[] numindex = null;
            if (holder.getHeaderFields() != null) {
            	// ヘッダーがある場合
                numindex =new  int[holder.getHeaderFields().length];
                String value = null;
                String[] header = new String[holder.getHeaderFields().length];
                for(int i= 0;i<holder.getHeaderFields().length;i++){
                	value = holder.getHeaderFields()[i].toString();

                	if(holder.getHeaderFields()[i].endsWith(" ")){
                		//数値型のケース
                		numindex[i] = 1;
                		header[i] = (value.substring(0, value.length()-1));
                	}
                	else if(holder.getHeaderFields()[i].endsWith("_")){
                		//日付型のケース
                		numindex[i] = 2;
                		header[i] = (value.substring(0, value.length()-1));
                	}
                	else{
                		numindex[i] = 0;
                		header[i] = value;
                	}
                }
                // ヘッダラベルに余計なものを付けて判断していたので、余計なものを取ったヘッダを設定
                holder.setHeaderFields(header) ;
                writer.writeHeader(holder.getHeaderFields());
            }
            if (holder.getDataFields() != null) {
                List list = holder.getDataFields();
                Iterator it = list.iterator();
                short rowCount = 0;
                if(holder.getHeaderFields() != null){
                	rowCount = 1;
                }
                while(it.hasNext()){
                  Object[] data = (Object[])it.next();
                  writer.writeDeatil(data,rowCount++,numindex);
                }
            }
            OutputStream fos= response.getOutputStream();
            writer.workBook.write(fos);
            fos.close();
            writer.flush();
    	} finally {
    		if (writer.workBook != null) {
    			// 2017/03/30 J.Akutsu XLSXファイル作成時にメモリ不足エラーが発生する対応
    			// 一時ファイルを削除する。
    			((SXSSFWorkbook) writer.workBook).dispose();
    		}
    	}
        
    }

}
