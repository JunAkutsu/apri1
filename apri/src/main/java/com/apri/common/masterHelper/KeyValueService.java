package com.apri.common.masterHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class KeyValueService {

	@Autowired
	@Qualifier("common.masterHelper.KeyValueMapper")
	KeyValueMapper keyValueMapper;
	
	public Map<Object,Object> getTantousya(){
		List<KeyValue> list = keyValueMapper.queryTantousya();
		Map<Object,Object> result = new HashMap<>();
		for(int i=0;i<list.size();i++){
			KeyValue keyvalue = (KeyValue)list.get(i);
			result.put(keyvalue.getKey(), keyvalue.getValue());
		}
		return result;
	}
	
	/**
	 * key_value_tblから値を取得する。
	 * 取得したKeyはString型である。
	 * @param renban key_value_tblの連番
	 * @return ラベルの値をキーとして保持しているマップ
	 */	
	public Map<Object,Object> getQuerykeyValue(Integer renban){
		List<KeyValue> list = keyValueMapper.querykeyValue(renban);
		Map<Object,Object> result = new HashMap<>();
		for(int i=0;i<list.size();i++){
			KeyValue keyvalue = (KeyValue)list.get(i);
			result.put(keyvalue.getKey(), keyvalue.getValue());
		}
		return result;
	}
	
	/**
	 * key_value_tblから値を取得する。
	 * 取得したKeyはInteger型である。
	 * @param renban key_value_tblの連番
	 * @return ラベルの値をキーとして保持しているマップ
	 */	
	public Map<Object,Object> getQuerykeyValueInteger(Integer renban){
		List<KeyValue> list = keyValueMapper.querykeyValueInteger(renban);
		Map<Object,Object> result = new HashMap<>();
		for(int i=0;i<list.size();i++){
			KeyValue keyvalue = (KeyValue)list.get(i);
			result.put(keyvalue.getKey(), keyvalue.getValue());
		}
		return result;
	}
	
	/**
	 * key_value_tblから値を取得する。
	 * 取得したKeyはLong型である。
	 * @param renban key_value_tblの連番
	 * @return ラベルの値をキーとして保持しているマップ
	 */	
	public Map<Object,Object> getQuerykeyValueLong(Integer renban){
		List<KeyValue> list = keyValueMapper.querykeyValueLong(renban);
		Map<Object,Object> result = new HashMap<>();
		for(int i=0;i<list.size();i++){
			KeyValue keyvalue = (KeyValue)list.get(i);
			result.put(keyvalue.getKey(), keyvalue.getValue());
		}
		return result;
	}
}
