package com.apri.trans;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apri.common.exception.ApplicationException;


@Service
@Transactional(readOnly = true,timeout = 60,rollbackForClassName = "Exception",isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
public class TantousyaService {

	@Autowired
	TantousyaMapper tantousyaMapper;
	
	int i = 0;
	
	public List<RegistContent> getTantousya_list()
	throws ApplicationException {
		return tantousyaMapper.selectAll();
	}
	
	// このメソッド内でExceptionが発生するとrollbackとなる。
	@Transactional(readOnly = false) 
	public int executeCall(RegistContent input) 
	throws ApplicationException {
		int result = 0;
		try {
			tantousyaMapper.insert(input);
			if(input.getTantousya_id().equals("100")){
				throw new Exception("Exception Insert SQLエラー");
			}
			if(input.getTantousya_id().equals("200")){
				throw new RuntimeException("RuntimeException Insert SQLエラー");
			}
			if(input.getTantousya_id().equals("300")){
				throw new ApplicationException("ApplicationException Insert SQLエラー");
			}
			if(input.getTantousya_id().equals("400")){
				Thread.sleep(10000);
			}
		} catch (ApplicationException e) {
			// TODO 自動生成された catch ブロック
			throw e;
		} catch (RuntimeException e) {
			// TODO 自動生成された catch ブロック
			throw e;
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			throw new ApplicationException(e);
		}
		return result;
	}
	
	@Transactional(readOnly = false) 
	public int executeUpdate(RegistContent input) 
	throws ApplicationException {
		int result = 0;
		try {
			tantousyaMapper.update(input);
			if(input.getTantousya_id().equals("100")){
				throw new Exception("Exception Insert SQLエラー");
			}
			if(input.getTantousya_id().equals("200")){
				throw new RuntimeException("RuntimeException Insert SQLエラー");
			}
			if(input.getTantousya_id().equals("300")){
				throw new ApplicationException("ApplicationException Insert SQLエラー");
			}
			if(input.getTantousya_id().equals("400") && i==0){
				System.out.println("kita:"+i);
				i++;
				Thread.sleep(10000);
			}
		} catch (ApplicationException e) {
			// TODO 自動生成された catch ブロック
			throw e;
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
