package com.apri.common.masterHelper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component("common.masterHelper.KeyValueMapper")
public interface KeyValueMapper {
	List<KeyValue> queryTantousya();
	List<KeyValue> querykeyValue(Integer renban);
	List<KeyValue> querykeyValueInteger(Integer renban);
	List<KeyValue> querykeyValueLong(Integer renban);
}
