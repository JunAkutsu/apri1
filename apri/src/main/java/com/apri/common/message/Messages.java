package com.apri.common.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class Messages implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	MessageSource message_source;
	
	// エラーメッセージ
	private final ArrayList messages = new ArrayList();
	
	// エラーメッセージ(ヘッダー用)
	private final ArrayList messages_header = new ArrayList();
	
	// エラーメッセージ(明細用)
	private final ArrayList messages_detail = new ArrayList();
	
	
	/**
	 * エラーメッセージのサイズを取得する。
	 */
	public int size() {
		return messages.size();
	}
	
	/**
	 * エラーメッセージ(ヘッダー用)のサイズを取得する。
	 */
	public int size_header() {
		return messages_header.size();
	}
	
	/**
	 * エラーメッセージ(明細用)のサイズを取得する。
	 */
	public int size_detail() {
		return messages_detail.size();
	}
	
	/**
	 * 全てのエラーメッセージのサイズを取得する。
	 */
	public int size_all() {
		return messages.size() + messages_header.size() + messages_detail.size();
	}

	/**
	 * 指定されたメッセージキーを追加します。
	 * @param key メッセージを識別するキー
	 */
	public void addMessage(String key) {
		String meassge = message_source.getMessage(key, null, LocaleContextHolder.getLocale());
		messages.add(meassge);
	}
	
	/**
	 * 指定されたメッセージキーを追加します。
	 * @param key この例外のメッセージキーです。
	 * @param params メッセージの置換パラメータの配列です。
	 */
	public void addMessage(String key, Object params[]) {
		String meassge = message_source.getMessage(key, params, LocaleContextHolder.getLocale());
		messages.add(meassge);
	}
	
	/**
	 * 指定されたメッセージキーを追加します。
	 * @param key この例外のメッセージキーです。
	 * @param param0 メッセージの置換パラメータ{0}です。
	 */
	public void addMessage(String key, Object param0) {
		addMessage(key, new Object[] { param0 });
	}	
	
	/**
	 * 指定されたメッセージキーを追加します。
	 * @param key メッセージを識別するキー
	 * @param locale ロケール
	 */
	public void addMessage(String key,Locale locale) {
		String meassge = message_source.getMessage(key, null, locale);
		messages.add(meassge);
	}
	
	/**
	 * 指定されたメッセージキーを追加します。
	 * @param key この例外のメッセージキーです。
	 * @param params メッセージの置換パラメータの配列です。
	 * @param locale ロケール
	 */
	public void addMessage(String key, Object params[],Locale locale) {
		String meassge = message_source.getMessage(key, params, locale);
		messages.add(meassge);
	}
	
	/**
	 * 指定されたメッセージキーを追加します。
	 * @param key この例外のメッセージキーです。
	 * @param param0 メッセージの置換パラメータ{0}です。
	 * @param locale ロケール
	 */
	public void addMessage(String key, Object param0,Locale locale) {
		addMessage(key, new Object[] { param0 },locale);
	}	
	
	/**
	 * 指定されたメッセージキーを追加します。(ヘッダー用)
	 * @param key メッセージを識別するキー
	 */
	public void addMessage_header(String key) {
		String meassge = message_source.getMessage(key, null, LocaleContextHolder.getLocale());
		messages_header.add(meassge);
	}
	
	/**
	 * 指定されたメッセージキーを追加します。(ヘッダー用)
	 * @param key この例外のメッセージキーです。
	 * @param params メッセージの置換パラメータの配列です。
	 */
	public void addMessage_header(String key, Object params[]) {
		String meassge = message_source.getMessage(key, params, LocaleContextHolder.getLocale());
		messages_header.add(meassge);
	}
	
	/**
	 * 指定されたメッセージキーを追加します。(ヘッダー用)
	 * @param key この例外のメッセージキーです。
	 * @param param0 メッセージの置換パラメータ{0}です。
	 */
	public void addMessage_header(String key, Object param0) {
		addMessage_header(key, new Object[] { param0 });
	}	
	
	/**
	 * 指定されたメッセージキーを追加します。(ヘッダー用)
	 * @param key メッセージを識別するキー
	 * @param locale ロケール
	 */
	public void addMessage_header(String key,Locale locale) {
		String meassge = message_source.getMessage(key, null, locale);
		messages_header.add(meassge);
	}
	
	/**
	 * 指定されたメッセージキーを追加します。(ヘッダー用)
	 * @param key この例外のメッセージキーです。
	 * @param params メッセージの置換パラメータの配列です。
	 * @param locale ロケール
	 */
	public void addMessage_header(String key, Object params[],Locale locale) {
		String meassge = message_source.getMessage(key, params, locale);
		messages_header.add(meassge);
	}
	
	/**
	 * 指定されたメッセージキーを追加します。(ヘッダー用)
	 * @param key この例外のメッセージキーです。
	 * @param param0 メッセージの置換パラメータ{0}です。
	 * @param locale ロケール
	 */
	public void addMessage_header(String key, Object param0,Locale locale) {
		addMessage_header(key, new Object[] { param0 },locale);
	}	
	
	/**
	 * 指定されたメッセージキーを追加します。(明細用)
	 * @param key メッセージを識別するキー
	 */
	public void addMessage_detail(String key) {
		String meassge = message_source.getMessage(key, null, LocaleContextHolder.getLocale());
		messages_detail.add(meassge);
	}
	
	/**
	 * 指定されたメッセージキーを追加します。(明細用)
	 * @param key この例外のメッセージキーです。
	 * @param params メッセージの置換パラメータの配列です。
	 */
	public void addMessage_detail(String key, Object params[]) {
		String meassge = message_source.getMessage(key, params, LocaleContextHolder.getLocale());
		messages_detail.add(meassge);
	}
	
	/**
	 * 指定されたメッセージキーを追加します。(明細用)
	 * @param key この例外のメッセージキーです。
	 * @param param0 メッセージの置換パラメータ{0}です。
	 */
	public void addMessage_detail(String key, Object param0) {
		addMessage_detail(key, new Object[] { param0 });
	}	
	
	/**
	 * 指定されたメッセージキーを追加します。(明細用)
	 * @param key メッセージを識別するキー
	 * @param locale ロケール
	 */
	public void addMessage_detail(String key,Locale locale) {
		String meassge = message_source.getMessage(key, null, locale);
		messages_detail.add(meassge);
	}
	
	/**
	 * 指定されたメッセージキーを追加します。(明細用)
	 * @param key この例外のメッセージキーです。
	 * @param params メッセージの置換パラメータの配列です。
	 * @param locale ロケール
	 */
	public void addMessage_detail(String key, Object params[],Locale locale) {
		String meassge = message_source.getMessage(key, params, locale);
		messages_detail.add(meassge);
	}
	
	/**
	 * 指定されたメッセージキーを追加します。(明細用)
	 * @param key この例外のメッセージキーです。
	 * @param param0 メッセージの置換パラメータ{0}です。
	 * @param locale ロケール
	 */
	public void addMessage_detail(String key, Object param0,Locale locale) {
		addMessage_detail(key, new Object[] { param0 },locale);
	}
	
	/**
	 * メッセージを取得する。
	 */
	public String get_messages(){
		
		StringBuffer value = new StringBuffer();
		
		for(int i=0; i<size(); i++){
			if(i!=0){
				value.append('\n');
			}
			value.append(messages.get(i));
		}

		return value.toString();
	}
	
	/**
	 * メッセージを取得する。(ヘッダー用)
	 */
	public String get_messages_header(){
		
		StringBuffer value = new StringBuffer();
		
		for(int i=0; i<size_header(); i++){
			if(i!=0){
				value.append('\n');
			}
			value.append(messages_header.get(i));
		}

		return value.toString();
	}
	
	/**
	 * メッセージを取得する。(明細用)
	 */
	public String get_messages_detail(){
		
		StringBuffer value = new StringBuffer();
		
		for(int i=0; i<size_detail(); i++){
			if(i!=0){
				value.append('\n');
			}
			value.append(messages_detail.get(i));
		}

		return value.toString();
	}	
	
	/**
	 * メッセージを取得する。(リスト形式)
	 */
	public List get_messages_list(){
		return (List)messages;
	}
	
	/**
	 * メッセージを取得する。(リスト形式、ヘッダー用)
	 */
	public List get_messages_header_list(){
		return (List)messages_header;
	}
	
	/**
	 * メッセージを取得する。(リスト形式、明細用)
	 */
	public List get_messages_detail_list(){
		return (List)messages_detail;
	}
}
