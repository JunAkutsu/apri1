package com.apri.type;

import java.io.UnsupportedEncodingException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apri.form.RegistContainerLogic;

public class BytesLengthValidator implements ConstraintValidator<BytesLength, String> {
	private int length = 10;
	private String encoding = "MS932";
	private static final Logger logger = LoggerFactory.getLogger(BytesLengthValidator.class);
	
	@Override
	public void initialize(BytesLength bytesLength){
		this.length=bytesLength.length();
		this.encoding=bytesLength.encoding();
	}
	
	@Override
	public boolean isValid(String input,ConstraintValidatorContext cxt){
		boolean result = true;
		if(input == null){
			return false;
		}
		int v_length=0;
        try { 
            // 一度バイト配列に変換してから長さを取得する 
        	v_length = input.getBytes(encoding).length; 
        } catch (UnsupportedEncodingException uee) { 
        	// 指定された文字エンコーディングを使用できない 
        	logger.error(uee.getMessage());
        }
        
        if(v_length > this.length){
        	result=false;
		}
		return result;
	}

}
