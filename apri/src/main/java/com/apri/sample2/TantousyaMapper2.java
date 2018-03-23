package com.apri.sample2;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.apri.sample.SelectTantousyaContent;

@Mapper
public interface TantousyaMapper2 {
	List<TantousyaDomain> selectAll();
	void insert(TantousyaDomain data);
}
