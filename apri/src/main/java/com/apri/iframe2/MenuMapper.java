package com.apri.iframe2;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.apri.common.customTag.LabelValue;


@Mapper
@Component("iframe2.MenuMapper")
public interface MenuMapper {
	List<MenuDomain> selectAll();
}
