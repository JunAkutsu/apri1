package com.apri.xlsx3;

import java.io.Serializable;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;

public class XLSXTemplateReportData2 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// ヘッダー部分の置換変数
	private Map header;
	
	// 明細部分の置換変数
	private Map detailList;
	
	// 明細行のリスト数
	private int numOfDetailList=0;
	
	// true:明細行より下にある行を移動する、false：移動処理はない。
	boolean row_move_flg = false;
	
	// 移動行の開始位置
	// 開始行と終了行を同じ値にすると、作成したxlxsを開こうとするとエラーが発生する。
	// 1行しか移動しない場合は、終了行には開始行の+1した値を設定する。
	int start_row = 0;

	// 移動行の終了位置
	int end_row = 0;
	
	// 移動数
	int move_num = 0;
	
	// 明細の最終行の線を手動で設定する/設定しないを制御する
	// true:設定する、false：設定しない
	boolean final_row_border_flg = false;

	// 最終行に設定する線の種類
	BorderStyle final_border_top;
	BorderStyle final_border_bottom;
	BorderStyle final_border_left;
	BorderStyle final_border_right;

	// 明細の高さ設定FLG
	// true:設定する、false：設定しない
	boolean height_flg = false;

	// 明細の高さを設定する。
	// xlsxの高さが30.00の場合は、600と入力する。
	// xlsx値の20倍した値を設定する。
	short height_num;

	// 明細(奇数行)のセルの色を設定FLG
	// true:設定する、false：設定しない
	boolean cell_color_even_flg = false;

	// 明細(奇数行)の色を設定する。
	short cell_color_even_num;

	// 明細(偶数行)のセルの色を設定FLG
	// true:設定する、false：設定しない
	boolean cell_color_odd_flg = false;

	// 明細(偶数行)の色を設定する。
	short cell_color_odd_num;
	
	
	
	public boolean isRow_move_flg() {
		return row_move_flg;
	}
	public void setRow_move_flg(boolean row_move_flg) {
		this.row_move_flg = row_move_flg;
	}
	public int getStart_row() {
		return start_row;
	}
	public void setStart_row(int start_row) {
		this.start_row = start_row;
	}
	public int getEnd_row() {
		return end_row;
	}
	public void setEnd_row(int end_row) {
		this.end_row = end_row;
	}
	public int getMove_num() {
		return move_num;
	}
	public void setMove_num(int move_num) {
		this.move_num = move_num;
	}
	public boolean isFinal_row_border_flg() {
		return final_row_border_flg;
	}
	public void setFinal_row_border_flg(boolean final_row_border_flg) {
		this.final_row_border_flg = final_row_border_flg;
	}
	public BorderStyle getFinal_border_top() {
		return final_border_top;
	}
	public void setFinal_border_top(BorderStyle final_border_top) {
		this.final_border_top = final_border_top;
	}
	public BorderStyle getFinal_border_bottom() {
		return final_border_bottom;
	}
	public void setFinal_border_bottom(BorderStyle final_border_bottom) {
		this.final_border_bottom = final_border_bottom;
	}
	public BorderStyle getFinal_border_left() {
		return final_border_left;
	}
	public void setFinal_border_left(BorderStyle final_border_left) {
		this.final_border_left = final_border_left;
	}
	public BorderStyle getFinal_border_right() {
		return final_border_right;
	}
	public void setFinal_border_right(BorderStyle final_border_right) {
		this.final_border_right = final_border_right;
	}
	public boolean isHeight_flg() {
		return height_flg;
	}
	public void setHeight_flg(boolean height_flg) {
		this.height_flg = height_flg;
	}
	public short getHeight_num() {
		return height_num;
	}
	public void setHeight_num(short height_num) {
		this.height_num = height_num;
	}
	public boolean isCell_color_even_flg() {
		return cell_color_even_flg;
	}
	public void setCell_color_even_flg(boolean cell_color_even_flg) {
		this.cell_color_even_flg = cell_color_even_flg;
	}
	public short getCell_color_even_num() {
		return cell_color_even_num;
	}
	public void setCell_color_even_num(short cell_color_even_num) {
		this.cell_color_even_num = cell_color_even_num;
	}
	public boolean isCell_color_odd_flg() {
		return cell_color_odd_flg;
	}
	public void setCell_color_odd_flg(boolean cell_color_odd_flg) {
		this.cell_color_odd_flg = cell_color_odd_flg;
	}
	public short getCell_color_odd_num() {
		return cell_color_odd_num;
	}
	public void setCell_color_odd_num(short cell_color_odd_num) {
		this.cell_color_odd_num = cell_color_odd_num;
	}
	public Map getHeader() {
		return header;
	}
	public void setHeader(Map header) {
		this.header = header;
	}
	public Map getDetailList() {
		return detailList;
	}
	public void setDetailList(Map detailList) {
		this.detailList = detailList;
	}
	public int getNumOfDetailList() {
		return numOfDetailList;
	}
	public void setNumOfDetailList(int numOfDetailList) {
		this.numOfDetailList = numOfDetailList;
	}
	

}
