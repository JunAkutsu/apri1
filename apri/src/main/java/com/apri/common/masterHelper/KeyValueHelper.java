package com.apri.common.masterHelper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.apri.common.exception.ApplicationException;
import com.apri.common.sqlHelper.SQLHelperService;

/**
 * SQLを実行し、その結果を加工するクラス
 */
public class KeyValueHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	KeyValueService key_value_service;
	
	@Autowired
	SQLHelperService SQLHelperService;
	
	private static final String KEY_VALUE_MAPPER = "\\src\\main\\java\\com\\apri\\common\\masterHelper\\KeyValueMapper.xml";
	
	// マスタの内容を保存するマップ
	private final HashMap<String,Map<Object,Object>> masterMap = new HashMap<>();

	public HashMap<String,Map<Object,Object>> getMasterMap() {
		return masterMap;
	}
	
	public Map<Object,Object> getTantousya()
	throws ApplicationException {
		// キャッシュキー名を取得するデータ毎に設定する。
		String cacheKey = "tantousya:N";
		Map<Object,Object> result = masterMap.get(cacheKey);
		if(result == null){
			result = key_value_service.getTantousya();
			Object params = null;
			// SQLの出力
			SQLHelperService.logger_sql(KEY_VALUE_MAPPER,"queryTantousya", params);
	        masterMap.put(cacheKey, result);
		}
		return result;
	}
	
	public Map<Object,Object> getTantousyaWithNULL()
	throws ApplicationException {
		// キャッシュキー名を取得するデータ毎に設定する。
		String cacheKey = "tantousya:NULL";
		Map<Object,Object> result = masterMap.get(cacheKey);
		if(result == null){
	        result = new HashMap<>();
	        result.put(new Integer(-1), "(指定なし)");
			result.putAll(key_value_service.getTantousya());
			Object params = null;
			// SQLの出力
			SQLHelperService.logger_sql(KEY_VALUE_MAPPER,"queryTantousya", params);
	        masterMap.put(cacheKey, result);
		}
		return result;
	}
	
	/**
	 * key_value_tblから値を取得する。
	 * 取得したKeyはStirng型である。
	 * @param renban key_value_tblの連番
	 * @return ラベルの値をキーとして保持しているマップ
	 */	
	public Map<Object,Object> getKeyValue(Integer renban)
	throws ApplicationException {
		// キャッシュキー名を取得するデータ毎に設定する。
		String cacheKey = "keyValue:N"+renban;
		Map<Object,Object> result = masterMap.get(cacheKey);
		if(result == null){
			result = key_value_service.getQuerykeyValue(renban);
			Object params = (Object)renban;
			// SQLの出力
			SQLHelperService.logger_sql(KEY_VALUE_MAPPER,"querykeyValue", params);
		}
		return result;
	}
	
	/**
	 * key_value_tblから値を取得する。
	 * 取得したKeyはInteger型である。
	 * @param renban key_value_tblの連番
	 * @return ラベルの値をキーとして保持しているマップ
	 */	
	public Map<Object,Object> getKeyValueInteger(Integer renban)
	throws ApplicationException {
	
		// キャッシュキー名を取得するデータ毎に設定する。
		String cacheKey = "keyValueInteger:N"+renban;
		Map<Object,Object> result = masterMap.get(cacheKey);
		if(result == null){
			result = key_value_service.getQuerykeyValueInteger(renban);
			Object params = (Object)renban;
			// SQLの出力
			SQLHelperService.logger_sql(KEY_VALUE_MAPPER,"querykeyValueInteger", params);
	        masterMap.put(cacheKey, result);
		}
		return result;
	}
	
	/**
	 * key_value_tblから値を取得する。
	 * 取得したKeyはLong型である。
	 * @param renban key_value_tblの連番
	 * @return ラベルの値をキーとして保持しているマップ
	 */	
	public Map<Object,Object> getKeyValueLong(Integer renban)
	throws ApplicationException {
	
		// キャッシュキー名を取得するデータ毎に設定する。
		String cacheKey = "keyValueLong:N"+renban;
		Map<Object,Object> result = masterMap.get(cacheKey);
		if(result == null){
			result = key_value_service.getQuerykeyValueLong(renban);
			Object params = (Object)renban;
			// SQLの出力
			SQLHelperService.logger_sql(KEY_VALUE_MAPPER,"querykeyValueLong", params);
	        masterMap.put(cacheKey, result);
		}
		return result;
	}
}
