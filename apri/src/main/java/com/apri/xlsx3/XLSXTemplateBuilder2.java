package com.apri.xlsx3;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class XLSXTemplateBuilder2 {

	// シートの置換処理をするメソッド
	public void createReplaceSheet(Sheet v_sheet,XLSXTemplateReportData2 reportData,Workbook wb,boolean meisai_kotei_flg) {

		// 1)結合情報マップの取得
        HashMap v_regionMap = getRegionMap(v_sheet);
		Map header_Map = reportData.getHeader();
		Map detail_Map = reportData.getDetailList();

		// 2)isRow_move_flgがtrueの場合、指定した行を指定した分だけ移動する。
		if(reportData.isRow_move_flg()){
			v_sheet.shiftRows(reportData.getStart_row(), reportData.getEnd_row(), reportData.getMove_num());
		}

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
						// 9-1)結合情報の取得
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
						if(meisai_kotei_flg) {
							setMeisaiCellValueNotStyle(v_cell, v_sheet, (Object[])detail_Map.get(v_key.toString()),reportData,i,v_cellStyle,v_region,wb);
						}
						else {
							setMeisaiCellValue(v_cell, v_sheet, (Object[])detail_Map.get(v_key.toString()),reportData,i,v_cellStyle,v_region,wb);
						}
					}
				}
			}
		}
	}

	// 結合情報を取得するメソッド
	public HashMap getRegionMap(Sheet sheet) {
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
	public void setCellValue(Cell cell, Object value, CellStyle cellStyle,Workbook wb) {

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
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
			cell.setCellStyle(cellStyle);
		}
	}

	// セルに値を設定するメソッド
	public void setCellValueNotStyle(Cell cell, Object value, CellStyle cellStyle,Workbook wb) {

		CreationHelper createHelper = wb.getCreationHelper();

		if (value != null) {
			if (value instanceof String) {
				cell.setCellValue(createHelper.createRichTextString(value.toString()));
			}
			else if (value instanceof Number) {
				Number numValue = (Number) value;
				if (numValue instanceof Float) {
					Float floatValue = (Float) numValue;
					numValue = new Double(String.valueOf(floatValue));
				}
				cell.setCellValue(numValue.doubleValue());
			}
			else if (value instanceof Date) {
				Date dateValue = (Date) value;
				cell.setCellValue(dateValue);
			}
			else if (value instanceof Boolean) {
				Boolean boolValue = (Boolean) value;
				cell.setCellValue(boolValue.booleanValue());
			}
		}
		else {
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
		}
	}

	// 明細行のセルに値を設定するメソッド
	public void setMeisaiCellValue(Cell baseCell, Sheet sheet, Object[] values,XLSXTemplateReportData2 reportData ,int startRowPosition,
											CellStyle cellStyle,CellRangeAddress region,Workbook wb) {

		// 行数の取得
		int detailMaxNum = reportData.getNumOfDetailList();

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

			// 行の高さを調整する。
			if(reportData.isHeight_flg()) {
				row.setHeight(reportData.getHeight_num());
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

			// セルの色を設定する。koko
			if(reportData.isCell_color_even_flg() || reportData.isCell_color_odd_flg()) {
				// 色を設定する場合
				if( i % 2 ==1) {
					// 奇数の場合
					if(reportData.isCell_color_even_flg()) {
						// 値の設定
						CellStyle newStyle = wb.createCellStyle();
						newStyle.cloneStyleFrom(cellStyle);
						newStyle.setFillForegroundColor(reportData.getCell_color_even_num());
						newStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

						// セルに値を設定
						setCellValue(cell, values[i],newStyle,wb);
					}
					else {
						setCellValue(cell, values[i],cellStyle,wb);
					}
				}
				else {
					// 偶数の場合
					if(reportData.isCell_color_odd_flg()) {
						// 値の設定
						CellStyle newStyle = wb.createCellStyle();
						newStyle.cloneStyleFrom(cellStyle);
						newStyle.setFillForegroundColor(reportData.getCell_color_odd_num());
						newStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

						// セルに値を設定
						setCellValue(cell, values[i],newStyle,wb);
					}
					else {
						setCellValue(cell, values[i],cellStyle,wb);
					}
				}

			}
			else {
				// 色を設定しない場合
				// セルに値を設定
				setCellValue(cell, values[i],cellStyle,wb);
			}

			// 最終行の枠の線を設定
			if(reportData.isFinal_row_border_flg()) {
				// 最終行が判定
				if(i == detailMaxNum-1) {
					CellStyle v_cellStyle = wb.createCellStyle();
					// 上の設定
					if(reportData.getFinal_border_top() == null) {
						v_cellStyle.setBorderTop(cellStyle.getBorderTopEnum());
					}
					else {
						v_cellStyle.setBorderTop(reportData.getFinal_border_top());
					}
					// 下の設定
					if(reportData.getFinal_border_bottom() == null) {
						v_cellStyle.setBorderBottom(cellStyle.getBorderBottomEnum());
					}
					else {
						v_cellStyle.setBorderBottom(reportData.getFinal_border_bottom());
					}
					// 左の設定
					if(reportData.getFinal_border_left() == null) {
						v_cellStyle.setBorderLeft(cellStyle.getBorderLeftEnum());
					}
					else {
						v_cellStyle.setBorderLeft(reportData.getFinal_border_left());
					}
					// 右の設定
					if(reportData.getFinal_border_right() == null) {
						v_cellStyle.setBorderRight(cellStyle.getBorderRightEnum());
					}
					else {
						v_cellStyle.setBorderRight(reportData.getFinal_border_right());
					}
					cell.setCellStyle(v_cellStyle);
				}
			}

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
						// 最終行の枠の線を設定
						if(reportData.isFinal_row_border_flg()) {
							// 最終行が判定
							if(i == detailMaxNum-1) {
								CellStyle v_style2 = wb.createCellStyle();
								// 上の設定
								if(reportData.getFinal_border_top() == null) {
									v_style2.setBorderTop(cellStyle.getBorderTopEnum());
								}
								else {
									v_style2.setBorderTop(reportData.getFinal_border_top());
								}
								// 下の設定
								if(reportData.getFinal_border_bottom() == null) {
									v_style2.setBorderBottom(cellStyle.getBorderBottomEnum());
								}
								else {
									v_style2.setBorderBottom(reportData.getFinal_border_bottom());
								}
								// 左の設定
								if(reportData.getFinal_border_left() == null) {
									v_style2.setBorderLeft(cellStyle.getBorderLeftEnum());
								}
								else {
									v_style2.setBorderLeft(reportData.getFinal_border_left());
								}
								// 右の設定
								if(reportData.getFinal_border_right() == null) {
									v_style2.setBorderRight(cellStyle.getBorderRightEnum());
								}
								else {
									v_style2.setBorderRight(reportData.getFinal_border_right());
								}
								cell2.setCellStyle(v_style2);
							}
						}
					}
				}

//				CellRangeAddress new_region = new CellRangeAddress(startRowPosition + i,startRowPosition + i,columnPosition,columnPosition+(area-1));

				CellRangeAddress new_region = new CellRangeAddress(startRowPosition + i,startRowPosition + i + (rowTo - rowFrom),columnFrom,columnTo);

//				new_region.setFirstRow(startRowPosition + i);
//				new_region.setLastRow(startRowPosition + i);
//				new_region.setFirstColumn(columnPosition);
//				new_region.setLastColumn((columnPosition+(area-1)));// areaは、自分のセルも含んでいるので1つ減らす。
				sheet.addMergedRegion(new_region);
			}
		}
	}

	// 明細行のセルに値を設定するメソッド(セルのタイプはコピーしない。)
	public void setMeisaiCellValueNotStyle(Cell baseCell, Sheet sheet, Object[] values,XLSXTemplateReportData2 reportData ,int startRowPosition,
											CellStyle cellStyle,CellRangeAddress region,Workbook wb) {

		// 行数の取得
		int detailMaxNum = reportData.getNumOfDetailList();

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
//				copyCell(baseCell, cell);
				// 新たに行(セル)を追加した所だけ、結合情報を設定する必要があるので、FLGをtrueにする。
				// 行の追加の基となる行は、結合されているので、その箇所に新たに結合情報を設定するとエラーとなる。
				v_copy_ari_flg = true;
			}

			// セルに値を設定
			setCellValueNotStyle(cell, values[i],cellStyle,wb);

			// 最終行の枠の線を設定
			// 枠は固定に用意しているものを使うので
			// 枠の線を設定する必要がないはず。
/**
			if(reportData.isFinal_row_border_flg()) {
				// 最終行が判定
				if(i == detailMaxNum-1) {
					CellStyle v_cellStyle = wb.createCellStyle();
					// 上の設定
					if(reportData.getFinal_border_top() == null) {
						v_cellStyle.setBorderTop(cellStyle.getBorderTopEnum());
					}
					else {
						v_cellStyle.setBorderTop(reportData.getFinal_border_top());
					}
					// 下の設定
					if(reportData.getFinal_border_bottom() == null) {
						v_cellStyle.setBorderBottom(cellStyle.getBorderBottomEnum());
					}
					else {
						v_cellStyle.setBorderBottom(reportData.getFinal_border_bottom());
					}
					// 左の設定
					if(reportData.getFinal_border_left() == null) {
						v_cellStyle.setBorderLeft(cellStyle.getBorderLeftEnum());
					}
					else {
						v_cellStyle.setBorderLeft(reportData.getFinal_border_left());
					}
					// 右の設定
					if(reportData.getFinal_border_right() == null) {
						v_cellStyle.setBorderRight(cellStyle.getBorderRightEnum());
					}
					else {
						v_cellStyle.setBorderRight(reportData.getFinal_border_right());
					}
					cell.setCellStyle(v_cellStyle);
				}
			}
**/
			// 結合情報の設定
			// 枠は固定に用意しているものを使うので
			// 結合情報を設定する必要はないはず。
/**
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
						// 最終行の枠の線を設定
						if(reportData.isFinal_row_border_flg()) {
							// 最終行が判定
							if(i == detailMaxNum-1) {
								CellStyle v_style2 = wb.createCellStyle();
								// 上の設定
								if(reportData.getFinal_border_top() == null) {
									v_style2.setBorderTop(cellStyle.getBorderTopEnum());
								}
								else {
									v_style2.setBorderTop(reportData.getFinal_border_top());
								}
								// 下の設定
								if(reportData.getFinal_border_bottom() == null) {
									v_style2.setBorderBottom(cellStyle.getBorderBottomEnum());
								}
								else {
									v_style2.setBorderBottom(reportData.getFinal_border_bottom());
								}
								// 左の設定
								if(reportData.getFinal_border_left() == null) {
									v_style2.setBorderLeft(cellStyle.getBorderLeftEnum());
								}
								else {
									v_style2.setBorderLeft(reportData.getFinal_border_left());
								}
								// 右の設定
								if(reportData.getFinal_border_right() == null) {
									v_style2.setBorderRight(cellStyle.getBorderRightEnum());
								}
								else {
									v_style2.setBorderRight(reportData.getFinal_border_right());
								}
								cell2.setCellStyle(v_style2);
							}
						}
					}
				}

				CellRangeAddress new_region = new CellRangeAddress(startRowPosition + i,startRowPosition + i,columnPosition,columnPosition+(area-1));
//				new_region.setFirstRow(startRowPosition + i);
//				new_region.setLastRow(startRowPosition + i);
//				new_region.setFirstColumn(columnPosition);
//				new_region.setLastColumn((columnPosition+(area-1)));// areaは、自分のセルも含んでいるので1つ減らす。
				sheet.addMergedRegion(new_region);
			}
**/
		}
	}

	// セルの値を別セルにコピーする
	public void copyCell(Cell fromCell, Cell toCell) {

		if (fromCell != null) {

			int cellType = fromCell.getCellType();

			switch (cellType) {
				case HSSFCell.CELL_TYPE_BLANK:
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
