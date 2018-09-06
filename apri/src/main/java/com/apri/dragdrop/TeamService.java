package com.apri.dragdrop;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apri.common.customTag.LabelValue;
import com.apri.common.exception.ApplicationException;


@Service
@Transactional
public class TeamService {

	@Autowired
	TeamMapper teamMapper;
	
	@Transactional(readOnly=true)
	public List<TeamDomain> getTeamList(TeamDomain input){
		return teamMapper.getTeamList(input);
	}
	
	@Transactional(readOnly=true)
	public List<YoteiDomain> getTeamYoteiList(YoteiDomain input){
		return teamMapper.getTeamYoteiList(input);
	}
	
	@Transactional(readOnly=true)
	public YoteiDomain getTeamYotei(Long id){
		return teamMapper.getTeamYotei(id);
	}
	
	@Transactional
	public YoteiDomain getId(){
		return teamMapper.getId();
	}
	
	// このメソッド内でExceptionが発生するとrollbackとなる。
	@Transactional(rollbackForClassName = "Exception") 
	public int executeCall(YoteiDomain input) {
		int result = 0;
		try {
			if(input.getAction_type() == 1){
				// 新規の場合
				teamMapper.insert(input);
			}
			else{
				// 修正の場合
				teamMapper.update(input);
			}
		} catch (RuntimeException e) {
			// TODO 自動生成された catch ブロック
			throw e;
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			throw new ApplicationException(e);
		}
		return result;
	}
	
	// このメソッド内でExceptionが発生するとrollbackとなる。
	@Transactional(rollbackForClassName = "Exception") 
	public int updateDays(YoteiDomain input) {
		int result = 0;
		try {
			teamMapper.updateDays(input);
		} catch (RuntimeException e) {
			// TODO 自動生成された catch ブロック
			throw e;
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			throw new ApplicationException(e);
		}
		return result;
	}
	
	// このメソッド内でExceptionが発生するとrollbackとなる。
	@Transactional(rollbackForClassName = "Exception") 
	public int delete(Long id) {
		int result = 0;
		try {
			teamMapper.delete(id);
		} catch (RuntimeException e) {
			// TODO 自動生成された catch ブロック
			throw e;
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			throw new ApplicationException(e);
		}
		return result;
	}
}
