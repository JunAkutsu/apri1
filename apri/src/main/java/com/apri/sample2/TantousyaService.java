package com.apri.sample2;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apri.common.exception.ApplicationException;


@Service
@Transactional
public class TantousyaService {

	@Autowired
	TantousyaMapper2 tantousyaMapper;
	
	@Transactional(readOnly=true)
	public List<TantousyaDomain> getTantousya_list(){
		return tantousyaMapper.selectAll();
	}
	
	// このメソッド内でExceptionが発生するとrollbackとなる。
	@Transactional(rollbackForClassName = "Exception") 
	public int executeCall(TantousyaDomain input) {
		int result = 0;
		try {
			tantousyaMapper.insert(input);
			if(input.getTantousya_id().equals("100")){
				throw new Exception("Exception Insert SQLエラー");
			}
			if(input.getTantousya_id().equals("200")){
				throw new RuntimeException("RuntimeException Insert SQLエラー");
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
	
}
