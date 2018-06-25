package com.apri.xlsx_pdf;

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
		try{
			// ファイル名の設定
			holder.setFile_name("nyukin_yotei_hyou.xlsx");

			// 入力ファイル名の取得
	 		Resource resource = context.getResource("classpath:xlsx_templates/hingata.xlsx");
	 		File file = new File(resource.getURI()); 
			FileInputStream fileIn = new FileInputStream(file);
			
			// 入力ファイルを表すワークブックオブジェクトの生成
			Workbook wb = new XSSFWorkbook(fileIn);

			// xlsxに表示するデータの作成
			XLSXTemplateReportData reportData = new XLSXTemplateReportData();

			Map header_Map = new HashMap();
			Map detail_Map = new HashMap();

			// データの加工
            Long[]   v_tantousya_renban = new Long[content_list.size()];
			String[] v_tantousya_id = new String[content_list.size()];
			String[] v_simei = new String[content_list.size()];
			
			for(int i=0;i<content_list.size();i++){
				String[] content = (String[])content_list.get(i);
				
				v_tantousya_renban[i] = Long.parseLong(content[0]);
				v_tantousya_id[i] = content[1];
				v_simei[i] = content[2];
			}

			// ヘッダの設定
			header_Map.put("$$_NAME","担当者一覧表");

			// 明細の設定
			detail_Map.put("$$_TANTOUSYA_RENBAN[]",v_tantousya_renban);
			detail_Map.put("$$_TANTOUSYA_ID[]",v_tantousya_id);
			detail_Map.put("$$_SIMEI[]",v_simei);

			reportData.setHeader(header_Map);
			reportData.setDetailList(detail_Map);
			reportData.setNumOfDetailList(content_list.size());

			int v_sheet_cnt = 1;

			// xlsxの作成
			// 1)テンプレートシートの取得
			Sheet v_sheet = wb.cloneSheet(0);

			// シート名の設定
			wb.setSheetName(v_sheet_cnt++, "list");

			// 2)結合情報マップの取得
			//      HashMap v_regionMap = getRegionMap(v_sheet);
			HashMap v_regionMap = new HashMap();

			// 3)行数分繰り返す
			int v_lastRow = v_sheet.getLastRowNum();
			for (int i = 0; i <= v_lastRow; i++) {
				// 4)行データの取得
				Row v_row = v_sheet.getRow(i);
				if (v_row == null) {
					continue;
				}

				// 5)セル数分繰り返す
				int v_lastCell = v_row.getLastCellNum();
				for (int j = 0; j < v_lastCell; j++) {
					// 6)セルの取得
					Cell v_cell = v_row.getCell(j);
					if (v_cell == null) {
						continue;
					}

					if (v_cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						// 7)値を置換える為に準備したセルであればデータを設定
						RichTextString v_key = v_cell.getRichStringCellValue();
						CellStyle v_cellStyle = v_cell.getCellStyle();

						// 8)ヘッダー分の置換文字列チェック
						if (header_Map.containsKey(v_key.toString())) {
							// 8-1)ヘッダー部の値の置換
							setCellValue(v_cell, header_Map.get(v_key.toString()), v_cellStyle,wb);
						}
						// 9)明細分の置換文字列チェック
						else if (detail_Map.containsKey(v_key.toString())) {
							// 9-1)明細部の値の置換
//							if(v_key.toString().equals("$$_HINMEI[]")){
//								v_cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//							}
							// 9-2)結合情報の取得
							CellRangeAddress v_region = null;
							ArrayList keys = new ArrayList();
							Integer v_rowFrom = new Integer(i);
							Integer v_columnFrom = new Integer(j);
							keys.add(v_rowFrom);
							keys.add(v_columnFrom);
							if(v_regionMap.containsKey(keys)){
								v_region = (CellRangeAddress)v_regionMap.get(keys);
							}
							// 9-2)明細部の値の置換
							setMeisaiCellValue(v_cell, v_sheet, (Object[])detail_Map.get(v_key.toString()),reportData.getNumOfDetailList(),i,v_cellStyle,v_region,wb);
						}
					}
				}
			}

			// コピー基のシートの削除
			wb.removeSheetAt(0);

			holder.setWb(wb);
			

		  }catch(Exception e){
		      e.printStackTrace();
		  } finally {
		  }
		  return holder;
	}

	// 結合情報を取得するメソッド
	public static HashMap getRegionMap(Sheet sheet) {
		// 初期化
		HashMap regionMap = new HashMap();

		for (int i=0;i<sheet.getNumMergedRegions();i++){
			// 結合情報(Region)を取得する
			CellRangeAddress v_region = sheet.getMergedRegion(i);
			//　結合開始行を取得する
			Integer v_rowFrom = new Integer(v_region.getFirstRow());
			//　結合開始列を取得する
			Integer v_columnFrom = new Integer(v_region.getFirstColumn());
			// Keyの作成
			ArrayList keys = new ArrayList();
			keys.add(v_rowFrom);
			keys.add(v_columnFrom);

			// マップの作成
			regionMap.put(keys,v_region);
		}
		return regionMap;
	}

	// セルに値を設定するメソッド
	public static void setCellValue(Cell cell, Object value, CellStyle cellStyle,Workbook wb) {

		CreationHelper createHelper = wb.getCreationHelper();

		if (value != null) {
			if (value instanceof String) {
				cell.setCellValue(createHelper.createRichTextString(value.toString()));
				cell.setCellStyle(cellStyle);
			}
			else if (value instanceof Number) {
				Number numValue = (Number) value;
				if (numValue instanceof Float) {
					Float floatValue = (Float) numValue;
					numValue = new Double(String.valueOf(floatValue));
				}
				cell.setCellValue(numValue.doubleValue());
				cell.setCellStyle(cellStyle);
			}
			else if (value instanceof Date) {
				Date dateValue = (Date) value;
				cell.setCellValue(dateValue);
				cell.setCellStyle(cellStyle);
			}
			else if (value instanceof Boolean) {
				Boolean boolValue = (Boolean) value;
				cell.setCellValue(boolValue.booleanValue());
				cell.setCellStyle(cellStyle);
			}
		}
		else {
//			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
//			cell.setCellStyle(cellStyle);
		}
	}

	// 明細行のセルに値を設定するメソッド
	public static void setMeisaiCellValue(Cell baseCell, Sheet sheet, Object[] values,int detailMaxNum ,int startRowPosition,
											CellStyle cellStyle,CellRangeAddress region,Workbook wb) {

		// 繰返し処理開始セルの位置情報を保持
		int columnPosition = baseCell.getColumnIndex();

		// 行数分繰り返す
		for (int i = 0; i < detailMaxNum; i++) {

			// 行を取得または生成
			Row row = sheet.getRow(startRowPosition + i);
			if (row == null) {
				row = sheet.createRow(startRowPosition + i);
				// 繰返し処理開始行と同じ高さを設定
//				row.setHeight(sheet.getRow(startRowPosition).getHeight());
			}

			// セルを取得または生成
			Cell cell = row.getCell(columnPosition);
			boolean v_copy_ari_flg=false;
			if (cell == null) {
				cell = row.createCell(columnPosition);
				// 繰返し処理開始セルの情報をコピー
				copyCell(baseCell, cell);
				// 新たに行(セル)を追加した所だけ、結合情報を設定する必要があるので、FLGをtrueにする。
				// 行の追加の基となる行は、結合されているので、その箇所に新たに結合情報を設定するとエラーとなる。
				v_copy_ari_flg = true;
			}

			// セルに値を設定
			setCellValue(cell, values[i],cellStyle,wb);

			// 結合情報の設定
			if(region != null && v_copy_ari_flg){
				// 結合されたセルの数を取得する
				int area = region.getNumberOfCells();
				// 結合開始列を取得する
				int columnFrom = region.getFirstColumn();
				// 結合終了列を取得する
				int columnTo = region.getLastColumn();
				// 結合開始行を取得する
				int rowFrom = region.getFirstRow();
				// 結合終了行を取得する
				int rowTo = region.getLastRow();

				// 結合セルのタイプをコピー基と合わせる
				if(area - 1 > 0){
					for(int j = 1; j < area; j++){
						// 結合対象のセルを取得または生成
						Cell cell2 = row.getCell(columnPosition + j);
						if (cell2 == null) {
							cell2 = row.createCell((columnPosition + j));
							// 繰返し処理開始セルの情報をコピー
							copyCell(baseCell, cell2);
						}
						// 線を設定する
						// コピー基のタイプを使用すると右側の線が引けないので、
						// 強制的に上下左右の線を設定する
						CellStyle style2 = wb.createCellStyle();
//						style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//						style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//						style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//						style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
						cell2.setCellStyle(style2);
					}
				}

				CellRangeAddress new_region = new CellRangeAddress(startRowPosition + i,startRowPosition + i,columnPosition,columnPosition+(area-1));
//				new_region.setFirstRow(startRowPosition + i);
//				new_region.setLastRow(startRowPosition + i);
//				new_region.setFirstColumn(columnPosition);
//				new_region.setLastColumn((columnPosition+(area-1)));// areaは、自分のセルも含んでいるので1つ減らす。
				sheet.addMergedRegion(new_region);
			}
		}
	}

	// セルの値を別セルにコピーする
	public static void copyCell(Cell fromCell, Cell toCell) {

		if (fromCell != null) {

			int cellType = fromCell.getCellType();

			switch (cellType) {
				case Cell.CELL_TYPE_BLANK:
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					toCell.setCellFormula(fromCell.getCellFormula());
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					toCell.setCellValue(fromCell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					toCell.setCellErrorValue(fromCell.getErrorCellValue());
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					toCell.setCellValue(fromCell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					toCell.setCellValue(fromCell.getRichStringCellValue());
					break;
				default:
			}

			if (fromCell.getCellStyle() != null) {
				toCell.setCellStyle(fromCell.getCellStyle());
			}
		}
	}


}
