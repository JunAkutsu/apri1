package com.apri.dragdrop;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.apri.common.customTag.LabelValue;
import com.apri.sample2.TantousyaDomain;
import com.apri.sample3.TantousyaDomain3;


@Mapper
public interface YoteiMapper {
	void insert(YoteiDomain data);
	void update(YoteiDomain data);
	void updateDays(YoteiDomain data);
	void delete(Long id);
	YoteiDomain getId();
	YoteiDomain getYotei(Long id);
	List<YoteiDomain> getYoteiList(YoteiDomain input);
}
