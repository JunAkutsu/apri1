package com.apri.common.masterHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class KeyValueHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	KeyValueService key_value_service;
	
	// マスタの内容を保存するマップ
	private final HashMap masterMap = new HashMap();
	
	public HashMap getMasterMap() {
		return masterMap;
	}
	
	/** マスターのデータを使用してKeyValueリストを作成する際は、
	 * ここにあるひな形を使用して作成して下さい。
	 * 
  	    public Map getXXXXX(){
			String cacheKey = "XXXXX:N";
			Map result = (Map)masterMap.get(cacheKey);
			if(result == null){
				result = key_value_service.getTantousya();
        		masterMap.put(cacheKey, result);
			}
			return result;
		}
	 **/
	
	public Map getTantousya(){
		// キャッシュキー名を取得するデータ毎に設定する。
		String cacheKey = "tantousya:N";
		Map result = (Map)masterMap.get(cacheKey);
		if(result == null){
			result = key_value_service.getTantousya();
	        masterMap.put(cacheKey, result);
		}
		return result;
	}
	
	public Map getTantousyaWithNULL(){
		// キャッシュキー名を取得するデータ毎に設定する。
		String cacheKey = "tantousya:NULL";
		Map result = (Map)masterMap.get(cacheKey);
		if(result == null){
	        result = new HashMap();
	        result.put(new Integer(-1), "(指定なし)");
			result.putAll(key_value_service.getTantousya());
	        masterMap.put(cacheKey, result);
		}
		return result;
	}
	
	
}
