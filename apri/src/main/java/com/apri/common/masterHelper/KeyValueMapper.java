package com.apri.common.masterHelper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.apri.common.customTag.LabelValue;


@Mapper
@Component("common.masterHelper.KeyValueMapper")
public interface KeyValueMapper {
	List<KeyValue> queryTantousya();
}
