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
public class YoteiService {

	@Autowired
	YoteiMapper yoteiMapper;
	
//	@Transactional(readOnly=true)
//	public List<TantousyaDomain> getTantousya_list(){
//		return tantousyaMapper.selectAll();
//	}
	
	// このメソッド内でExceptionが発生するとrollbackとなる。
	@Transactional(rollbackForClassName = "Exception") 
	public int executeCall(YoteiDomain input) {
		int result = 0;
		try {
			yoteiMapper.insert(input);
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