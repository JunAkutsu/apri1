package com.apri.trans;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.apri.sample.SelectTantousyaContent;

@Mapper
@Component("com.apri.trans.TantousyaMapper")
public interface TantousyaMapper {
	List<RegistContent> selectAll();
	void insert(RegistContent data);
	int update(RegistContent data);
}
