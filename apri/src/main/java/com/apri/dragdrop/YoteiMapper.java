package com.apri.dragdrop;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.apri.common.customTag.LabelValue;
import com.apri.sample3.TantousyaDomain3;


@Mapper
public interface YoteiMapper {
	void insert(YoteiDomain data);
}
