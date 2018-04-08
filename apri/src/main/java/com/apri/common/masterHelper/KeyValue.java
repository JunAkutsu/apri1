package com.apri.common.masterHelper;

import java.util.Map;
import java.io.Serializable;

/**
 * マップに追加する際のキーと値を保持するオブジェクトです。
 */
public class KeyValue implements Serializable {

  static final long serialVersionUID = 1L;

  // このオブジェクトのKey
  private Object key;

  // このオブジェクトのValue
  private Object value;

  /**
   * 空のインスタンスを構築します。
   */
  public KeyValue() {
  }

  /**
   * キーと値を指定してインスタンスを構築します。
   * @param key このオブジェクトのキー
   * @param value このオブジェクトの値
   */
  public KeyValue(Object key, Object value) {
    this.key = key;
    this.value = value;
  }

  /**
   * マップのエントリからインスタンスを構築します。
   * @param entry マップのエントリ
   */
  public KeyValue(Map.Entry<Object,Object> entry) {
    this.key = entry.getKey();
    this.value = entry.getValue();
  }

  /**
   * このオブジェクトのキーを返します。
   * @return このオブジェクトのキー
   */
  public Object getKey() {
    return this.key;
  }

  /**
   * このオブジェクトのキーを設定します。
   * @param key このオブジェクトのキー
   */
  public void setKey(Object key) {
    this.key = key;
  }

  /**
   * このオブジェクトの値を返します。
   * @return このオブジェクトの値
   */
  public Object getValue() {
    return this.value;
  }

  /**
   * このオブジェクトの値を設定します。
   * @param value このオブジェクトの値
   */
  public void setValue(Object value) {
    this.value = value;
  }

  /**
   * このオブジェクトと指定されたオブジェクトが等しいか判定します。
   * @param object このオブジェクトと比較するオブジェクト
   * @return 等しい場合はtrue、それ以外はfalse
   */
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    } else if (!(object instanceof KeyValue)) {
      return false;
    }
    KeyValue p = (KeyValue)object;
    if (key != p.key) {
      if ((key == null) || (p.key == null)) {
        return false;
      } else if (!key.equals(p.key)) {
        return false;
      }
    }
    if (value != p.value) {
      if ((value == null) || (p.value == null)) {
        return false;
      } else if (!value.equals(p.value)) {
        return false;
      }
    }
    return true;
  }

  /**
   * このオブジェクトのハッシュ値を計算して返します。
   * @return このオブジェクトのハッシュ値
   */
  public int hashCode() {
    int hash = 0;
    if (key != null) {
      hash ^= key.hashCode();
    }
    if (value != null) {
      hash ^= value.hashCode();
    }
    return hash;
  }

  /**
   * このオブジェクトの文字列表現を返します。
   * @return このオブジェクトの文字列表現
   */
  public String toString() {
    return "KeyValue[key=" + key + ",value=" + value + ']';
  }

}