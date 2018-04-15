package com.apri.common.controller;

import com.apri.common.exception.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * 例外共通ハンドラ。
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    /** ロガー */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * AppExceptionのハンドリングを行ないます。
     * @param request リクエスト
     * @param ex      AppException
     * @return 例外レスポンス
     */
    @ExceptionHandler(value = ApplicationException.class)
    public String handleAppException(HttpServletRequest request, ApplicationException ex,Model model) {
        String message = ex.getMessage();
        logger.error(message, ex);
//        if (error.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR) {
//            logger.error(message, ex);
//        } else {
//            logger.debug(message, ex);
//        }

        model.addAttribute("error_message",ex.getCause() !=null ? ex.getCause().toString():message);
        model.addAttribute("error_details",ex.getStackTrace());
        
        return "error/error";
    }
    
    /**
     * RuntimeExceptionのハンドリングを行ないます。
     * @param request リクエスト
     * @param ex      RuntimeException
     * @return 例外レスポンス
     */
    @ExceptionHandler(value = RuntimeException.class)
    public String handleRuntimeException(HttpServletRequest request, RuntimeException ex,Model model) {
        String message = ex.getMessage();
        logger.error(ex.getMessage(), ex);
//        if (error.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR) {
//            logger.error(message, ex);
//        } else {
//            logger.debug(message, ex);
//        }

        model.addAttribute("error_message",ex.getCause() !=null ? ex.getCause().toString():message);
        model.addAttribute("error_details",ex.getStackTrace());
        
        return "error/error";
    }
    
}
