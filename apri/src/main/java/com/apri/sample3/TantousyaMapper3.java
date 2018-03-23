package com.apri.sample3;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.apri.sample.SelectTantousyaContent;

@Mapper
public interface TantousyaMapper3 {
	List<TantousyaDomain3> selectAll();
	void insert(TantousyaDomain3 data);
}
