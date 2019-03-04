package com.apri.xlsx3;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import com.apri.common.exception.ApplicationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStream;


public class XLSXTemplateWriter {

	// XLSXデータを作成する。
	public XLSXTemplateHolder createXlsxData(List content_list,ApplicationContext context)
	  throws ApplicationException
	{
		XLSXTemplateHolder holder = new XLSXTemplateHolder();
		XLSXTemplateBuilder2 builder = new XLSXTemplateBuilder2();

		try{
			// ファイル名の設定
			holder.setFile_name("nyukin_yotei_hyou.xlsx");

			// 入力ファイル名の取得
	 		Resource resource = context.getResource("classpath:xlsx_templates/hingata2.xlsx");
	 		File file = new File(resource.getURI()); 
			FileInputStream fileIn = new FileInputStream(file);
			
			// 入力ファイルを表すワークブックオブジェクトの生成
			Workbook wb = new XSSFWorkbook(fileIn);
			
			// テンプレートシートの取得
			Sheet v_sheet = wb.getSheetAt(0);

			// xlsxに表示するデータの作成
			XLSXTemplateReportData2 reportData = new XLSXTemplateReportData2();

			// 初期化
			// 担当者情報の移動開始行(templateのxlsxの開始行)
			int v_tantousya_move_start = 4;

			// 担当者情報の明細移動数
			// -1にしないと余計に行の移動が発生する。
			int v_tantousya_move_num = content_list.size() -1;

			// 氏名情報の明細移動数
			// -1にしないと余計に行の移動が発生する。
//			int v_simebi_move_num = list4.size() -1;
			
			// xlsxの枠最終行
			int v_xlsx_end_num = 12 + v_tantousya_move_num ;
			
			////////////////////////
			// 担当者情報の行を移動する。
			////////////////////////
			if(v_tantousya_move_num != 0){
				// 行を移動する。
				v_sheet.shiftRows(v_tantousya_move_start, v_xlsx_end_num, v_tantousya_move_num);
			}
			
			/////////////////////////
			// 担当者情報部分のデータ設定
			/////////////////////////
            Long[]   v_tantousya_renban = new Long[content_list.size()];
			String[] v_tantousya_id = new String[content_list.size()];
			String[] v_simei = new String[content_list.size()];
			
			for(int i=0;i<content_list.size();i++){
				String[] content = (String[])content_list.get(i);
				v_tantousya_renban[i] = Long.parseLong(content[0]);
				v_tantousya_id[i] = content[1];
				v_simei[i] = content[2];
			}

			Map header_Map = new HashMap();
			Map detail_Map = new HashMap();
			
			// ヘッダの設定

			// 明細(担当者情報)の設定
			detail_Map.put("$$_TANTOUSYA_RENBAN[]",v_tantousya_renban);
			detail_Map.put("$$_TANTOUSYA_ID[]",v_tantousya_id);

			// ヘッダー、明細、件数の設定
			reportData.setHeader(header_Map);
			reportData.setDetailList(detail_Map);
			reportData.setNumOfDetailList(content_list.size());
			reportData.setHeight_flg(true);
			reportData.setHeight_num((short)645);
			reportData.setCell_color_even_flg(true);
			reportData.setCell_color_even_num(IndexedColors.BLUE.index);

			// xlsxデータの作成(担当者情報)
			builder.createReplaceSheet(v_sheet,reportData,wb,false);

			header_Map = new HashMap();
			detail_Map = new HashMap();
			
			// 明細(氏名情報)の設定
			detail_Map.put("$$_TANTOUSYA_RENBAN2[]",v_tantousya_renban);
			detail_Map.put("$$_SIMEI[]",v_simei);
			
			// ヘッダー、明細、件数の設定
			reportData.setHeader(header_Map);
			reportData.setDetailList(detail_Map);
			reportData.setNumOfDetailList(content_list.size());
			reportData.setHeight_flg(true);
			reportData.setHeight_num((short)645);
			reportData.setCell_color_even_flg(true);
			reportData.setCell_color_even_num(IndexedColors.AQUA.index);
			
			// xlsxデータの作成(氏名情報)
			builder.createReplaceSheet(v_sheet,reportData,wb,false);
			
			holder.setWb(wb);
			

		  }catch(Exception e){
		      e.printStackTrace();
		  } finally {
		  }
		  return holder;
	}

}
