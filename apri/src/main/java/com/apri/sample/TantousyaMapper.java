package com.apri.sample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.apri.sample.SelectTantousyaContent;

@Mapper
public interface TantousyaMapper {
	List<SelectTantousyaContent> selectAll();
	void insert(SelectTantousyaContent data);
}
