package com.apri.common.customTag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.io.Serializable;

public class LabelValue implements Serializable {

  static final long serialVersionUID = -4805521822621898691L;

  /** このラベルの表示です。 */
  private Object label;

  /** このラベルの値です。 */
  private Object value;

  /**
   * ラベルを取得する反復子から値をキーに持ったマップに変換します。
   * @param iterator ラベルを取得する反復子
   * @return 値をキーに持ったマップ
   */
  public static HashMap toHashMap(Iterator iterator) {
    HashMap result = new HashMap();
    while (iterator.hasNext()) {
      LabelValue label = (LabelValue)iterator.next();
      result.put(label.getValue(), label.getLabel());
    }
    return result;
  }

  /**
   * ラベルを取得する列挙子から値をキーに持ったマップに変換します。
   * @param iterator ラベルを取得する列挙子
   * @return 値をキーに持ったマップ
   */
  public static HashMap toHashMap(Enumeration enume) {
    HashMap result = new HashMap();
    while (enume.hasMoreElements()) {
      LabelValue label = (LabelValue)enume.nextElement();
      result.put(label.getValue(), label.getLabel());
    }
    return result;
  }

  /**
   * ラベルを取得するコレクションから値をキーに持ったマップに変換します。
   * @param iterator ラベルを取得するコレクション
   * @return 値をキーに持ったマップ
   */
  public static HashMap toHashMap(Collection collection) {
    if (collection instanceof ArrayList) {
      int size = collection.size();
      HashMap result = new HashMap(size);
      for (int i = 0; i < size; i++) {
        LabelValue label = (LabelValue)((ArrayList)collection).get(i);
        result.put(label.getValue(), label.getLabel());
      }
    }
    return toHashMap(collection.iterator());
  }

  /**
   * 空のラベルのインスタンスを構築します。
   */
  public LabelValue() {
  }

  /**
   * ラベルの表示と値からインスタンスを構築します。
   * @param label ラベルの表示
   * @param value ラベルの値
   */
  public LabelValue(Object label, Object value) {
    this.label = label;
    this.value = value;
  }

  /**
   * このラベルの表示を返します。
   * @return このラベルの表示
   */
  public Object getLabel() {
    return this.label;
  }

  /**
   * このラベルの表示を設定します。
   * @param label このラベルの表示
   */
  public void setLabel(Object label) {
    this.label = label;
  }

  /**
   * このラベルの値を返します。
   * @return このラベルの値
   */
  public Object getValue() {
    return this.value;
  }

  /**
   * このラベルの値を設定します。
   * @param value このラベルの値
   */
  public void setValue(Object value) {
    this.value = value;
  }

  /**
   * このオブジェクトの文字列表現を返します。
   * @return このオブジェクトの文字列表現
   */
  public String toString() {
    return "LabelValue[label=" + label + ", value=" + value + "]";
  }

}