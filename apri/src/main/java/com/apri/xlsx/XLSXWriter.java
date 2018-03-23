package com.apri.xlsx;

import java.io.Writer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FilterWriter;
import java.io.IOException;


public class XLSXWriter extends FilterWriter {

  /** 書き込むプロパティのマッピングです。 */
  private String propertyNames[];

  /** 文字列をフィルタする時に使用するバッファです。 */
  private StringBuffer buffer;

  /** XLS用オブジェクト */
  public Workbook workBook;

  private Sheet sheet;

  /**
   * 出力先と出力プロパティを指定してインスタンスを構築します。
   * @param out CSVを書き込む<code>Writer</code>
   * @param propertyNames プロパティのマッピング
   */
  public XLSXWriter(Writer out, String propertyNames[], String fileName) {
    super(out);
    this.propertyNames = propertyNames;
    // 2017/03/30 J.Akutsu XLSXファイル作成時にメモリ不足エラーが発生する対応
//    workBook = new XSSFWorkbook();
    workBook = new SXSSFWorkbook(100);
    ((SXSSFWorkbook)workBook).setCompressTempFiles(true);
    sheet = workBook.createSheet(fileName); // シート名を指定する
  }

  /**
   * HEADの情報を保存します。
   * @param header 書き込む配列
   * @throws IOException 処理に失敗した場合
   */
  public void writeHeader(String[] header)
    throws IOException {
    Row row = sheet.createRow(0);
    for (short i = 0; i < header.length; i++) {
        Cell cell = row.createCell(i);
        cell.setCellValue(header[i]);
    }
  }

  /**
   * 指定されたコレクションの全要素をCSV形式に変換して書き込みます
   * @param collection 書き込むオブジェクトを格納したコレクション
   * @param autoFlush 書き込み後に自動的にフラッシュする場合はtrue
   * @throws ApplicationException 処理に失敗した場合
   */
  public void writeDeatil(Object[] data,short rowCount,int[] numIndex)
    throws IOException {

        Row row = sheet.createRow(rowCount);
        DataFormat format  = workBook.createDataFormat();

        CellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setDataFormat(format.getFormat("text"));

        CellStyle cellStyle_d = workBook.createCellStyle();
        cellStyle_d.setDataFormat(format.getFormat("m/d/yy"));


        for (short j = 0; j < data.length; j++) {
            Cell cell = row.createCell(j);
            if(numIndex != null){
            	// ヘッダーがある場合
                if(numIndex[j]==1){
                	if(data[j]!=null){
              	  		String value = data[j].toString();
              	  		cell.setCellValue(new Double(value.replaceAll(",", "")).doubleValue());
                	}
                }
                else if(numIndex[j]==2){
                	// 2014/04/03 J.Akutsu xls2010を使用すると日付が途中から数値に表示されてしまう
                	// 文字列ならば問題なく表示されるので、セルのTYPEをテキストに変更する。
                	if (data[j] != null){
                		cell.setCellValue(data[j].toString());
                    	cell.setCellStyle(cellStyle);
                	}
                }
                else{
                	if (data[j] != null){
                		cell.setCellValue(data[j].toString());
                		cell.setCellStyle(cellStyle);
                	}
                }
            }
            else{
            	// ヘッダーがない場合
              	if (data[j] != null){
                	cell.setCellValue(data[j].toString());
                	cell.setCellStyle(cellStyle);
                }
            }
        }
  }

}
