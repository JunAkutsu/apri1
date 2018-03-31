package com.apri.select;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.apri.common.customTag.LabelValue;


@Mapper
@Component("select.TantousyaMapper")
public interface TantousyaMapper {
	List<TantousyaDomain> selectAll();
	List<LabelValue> find_selectAll();
	@MapKey("tantousya_renban")
	Map<Long,String> find_Map_selectAll();
}
