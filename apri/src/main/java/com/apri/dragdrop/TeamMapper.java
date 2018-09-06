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
public interface TeamMapper {
	List<TeamDomain> getTeamList(TeamDomain input);
	List<YoteiDomain> getTeamYoteiList(YoteiDomain input);
	YoteiDomain getTeamYotei(Long id);
	YoteiDomain getId();
	void insert(YoteiDomain data);
	void update(YoteiDomain data);
	void updateDays(YoteiDomain data);
	void delete(Long id);
}
