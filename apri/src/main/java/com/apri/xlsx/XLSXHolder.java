package com.apri.xlsx;

import java.util.List;
import java.io.Serializable;

public class XLSXHolder implements Serializable {

  /** XLSXのファイル名です。 */
  private String filename = "data";
  
  /** XLSXの拡張子です。 */
  private String extension = ".xlsx";

  /** XLSXへ出力するプロパティの出力順を格納する配列です。 */
  private String[] propertyNames;

  /** XLSXのヘッダーを保持する配列です。 */
  private String[] headerFields;

  /** XLSXの内容を保持するリストです。 */
  private List dataFields;


  public String getExtension() {
	return extension;
}

public void setExtension(String extension) {
	this.extension = extension;
}

/**
   * XLSXファイル名を取得します。
   * @return XLSXファイル名
   */
  public String getFilename() {
    return filename;
  }

  /**
   * XLSXファイル名を設定します。
   * @param filename XLSXファイル名
   */
  public void setFilename(String filename) {
    this.filename = filename;
  }

  /**
   * XLSXへ出力するプロパティの出力順を格納した配列を取得します。
   * @return XLSXへ出力するプロパティの出力順配列
   */
  public String[] getPropertyNames() {
    return propertyNames;
  }

  /**
   * XLSXへ出力するプロパティの出力順を格納した配列を設定します。
   * dataFieldsプロパティの各要素に格納されているオブジェクトがJavaBeansかマップの場合には
   * 設定する必要がありますが、オブジェクトが配列の場合には設定する必要はありません。
   * @param propertyNames XLSXへ出力するプロパティの出力順配列
   */
  public void setPropertyNames(String[] propertyNames) {
    this.propertyNames = propertyNames;
  }

  /**
   * XLSXヘッダー配列を取得します。
   * @return XLSXのヘッダー
   */
  public String[] getHeaderFields() {
    return headerFields;
  }

  /**
   * XLSXヘッダー配列を設定します。
   * @param headerFields XLSXのヘッダー
   */
  public void setHeaderFields(String[] headerFields) {
    this.headerFields = headerFields;
  }

  /**
   * XLSXの内容を取得します。
   * @return XLSXの内容
   */
  public List getDataFields() {
    return dataFields;
  }

  /**
   * XLSXの内容を設定します。
   * @param dataFields XLSXの内容
   */
  public void setDataFields(List dataFields) {
    this.dataFields = dataFields;
  }

}