package com.apri.dragdrop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apri.sample2.TantousyaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@Controller
@RequestMapping(value="/drag")
public class RegistContainerLogic {
	
	@Autowired
	YoteiService yoteiService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
        return "dragdrop/index";
	}
	
	@RequestMapping(value="/regist",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public String ajax_regist(@RequestBody YoteiBean yoteiBean){
		// 初期化
		// new GsonBuilderを使用すると値がNULLの場合もJSONに出力される。
		// new GSONを使用すると値がNULLの場合は、JSONから外される。
		Gson gson = new GsonBuilder().serializeNulls().create();
		LocalDate v_event_day_to = null;
		HashMap v_result = new HashMap();
		
		// date型の取得
		LocalDate v_event_day_from = LocalDate.parse(yoteiBean.getEvent_day_from(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//		LocalDateTime v_event_time_from = LocalDateTime.parse(yoteiBean.getEvent_day_from()+' '+yoteiBean.getEvent_time_from(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		if(yoteiBean.getEvent_day_to()!=null && yoteiBean.getEvent_day_to()!=""){
			v_event_day_to = LocalDate.parse(yoteiBean.getEvent_day_to(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		}
		
		// 予定の登録
		YoteiDomain input = new YoteiDomain();
		input.setEvent_name(yoteiBean.getEvent_name());
		input.setEvent_day_from(v_event_day_from);
		input.setEvent_time_from(yoteiBean.getEvent_time_from());
		input.setEvent_day_to(v_event_day_to);
		input.setEvent_time_to(yoteiBean.getEvent_time_to());
		input.setDetail_naiyou(yoteiBean.getDetail_naiyou());
		yoteiService.executeCall(input);
		
		// ログ出力
		Logger logger = LoggerFactory.getLogger(RegistContainerLogic.class);
		logger.info("event_name="+yoteiBean.getEvent_name());
		logger.info("event_day_from="+v_event_day_from);
//		logger.info("event_time_from="+v_event_time_from);
		v_result.put("event_name", input.getEvent_name());

		return gson.toJson(v_result);
	}
	
}
