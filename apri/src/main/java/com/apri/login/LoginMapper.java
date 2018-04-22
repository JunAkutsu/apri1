package com.apri.login;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.apri.sample.SelectTantousyaContent;

@Mapper
@Component("com.apri.login.LoginMapper")
public interface LoginMapper {
	UserValue findById(
			@Param("tantousya_id") String tantousya_id);
}
