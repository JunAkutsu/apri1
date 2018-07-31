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
		LocalDate v_event_day_from = null;
		LocalDate v_event_day_to = null;
		HashMap v_result = new HashMap();
		YoteiDomain input = null;
		
		// date型の取得
		v_event_day_from = LocalDate.parse(yoteiBean.getEvent_day_from(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		if(yoteiBean.getEvent_day_to()!=null && yoteiBean.getEvent_day_to()!=""){
			v_event_day_to = LocalDate.parse(yoteiBean.getEvent_day_to(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		}
		
		// 新規/修正の判定
		if(yoteiBean.getAction_type() == 1){
			// 新規の場合
			// IDの取得
			YoteiDomain idInfo = yoteiService.getId();
			
			// 予定の登録
			input = new YoteiDomain();
			input.setAction_type(yoteiBean.getAction_type());
			input.setId(idInfo.getId());
			input.setEvent_name(yoteiBean.getEvent_name());
			input.setEvent_day_from(v_event_day_from);
			input.setEvent_time_from(yoteiBean.getEvent_time_from());
			input.setEvent_day_to(v_event_day_to);
			input.setEvent_time_to(yoteiBean.getEvent_time_to());
			input.setDetail_naiyou(yoteiBean.getDetail_naiyou());
			yoteiService.executeCall(input);
		}
		else{
			// 修正の場合
			input = new YoteiDomain();
			input.setAction_type(yoteiBean.getAction_type());
			input.setId(yoteiBean.getId());
			input.setEvent_name(yoteiBean.getEvent_name());
			input.setEvent_day_from(v_event_day_from);
			input.setEvent_time_from(yoteiBean.getEvent_time_from());
			input.setEvent_day_to(v_event_day_to);
			input.setEvent_time_to(yoteiBean.getEvent_time_to());
			input.setDetail_naiyou(yoteiBean.getDetail_naiyou());
			yoteiService.executeCall(input);
		}
		
		// ログ出力
		Logger logger = LoggerFactory.getLogger(RegistContainerLogic.class);

		//戻り値の設定
		v_result.put("id", input.getId());

		return gson.toJson(v_result);
	}
	
	@RequestMapping(value="/modify",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public String ajax_modify(@RequestBody YoteiBean yoteiBean){
		// 初期化
		// new GsonBuilderを使用すると値がNULLの場合もJSONに出力される。
		// new GSONを使用すると値がNULLの場合は、JSONから外される。
		Gson gson = new GsonBuilder().serializeNulls().create();
		HashMap v_result = new HashMap();
		
		// 予定の取得
		YoteiDomain yoteiInfo = yoteiService.getYotei(yoteiBean.getId());
		
		// ログ出力
		Logger logger = LoggerFactory.getLogger(RegistContainerLogic.class);
		
		v_result.put("id", yoteiInfo.getId());
		v_result.put("event_name", yoteiInfo.getEvent_name());
		
		String v_event_day_from=null;
		String v_event_day_to=null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		if(yoteiInfo.getEvent_day_from() != null){
			v_event_day_from = yoteiInfo.getEvent_day_from().format(formatter);
		}
		if(yoteiInfo.getEvent_day_from() != null){
			v_event_day_to = yoteiInfo.getEvent_day_to().format(formatter);
		}
		v_result.put("event_day_from", v_event_day_from);
		v_result.put("event_time_from", yoteiInfo.getEvent_time_from());
		v_result.put("event_day_to", v_event_day_to);
		v_result.put("event_time_to", yoteiInfo.getEvent_time_to());
		v_result.put("detail_naiyou", yoteiInfo.getDetail_naiyou());

		return gson.toJson(v_result);
	}
	
	@RequestMapping(value="/changeDays",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public String ajax_changeEvent(@RequestBody YoteiBean yoteiBean){
		// 初期化
		// new GsonBuilderを使用すると値がNULLの場合もJSONに出力される。
		// new GSONを使用すると値がNULLの場合は、JSONから外される。
		Gson gson = new GsonBuilder().serializeNulls().create();
		LocalDate v_event_day_from = null;
		LocalDate v_event_day_to = null;
		HashMap v_result = new HashMap();
		YoteiDomain input = null;
		
		// date型の取得
		v_event_day_from = LocalDate.parse(yoteiBean.getEvent_day_from(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		if(yoteiBean.getEvent_day_to()!=null && yoteiBean.getEvent_day_to()!=""){
			v_event_day_to = LocalDate.parse(yoteiBean.getEvent_day_to(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		}

		// 日付の更新
		input = new YoteiDomain();
		input.setId(yoteiBean.getId());
		input.setEvent_day_from(v_event_day_from);
		input.setEvent_day_to(v_event_day_to);
		yoteiService.updateDays(input);
		
		// ログ出力
		Logger logger = LoggerFactory.getLogger(RegistContainerLogic.class);

		//戻り値の設定
		v_result.put("id", input.getId());

		return gson.toJson(v_result);
	}
	
	@RequestMapping(value="/delete",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public String ajax_delete(@RequestBody YoteiBean yoteiBean){
		// 初期化
		// new GsonBuilderを使用すると値がNULLの場合もJSONに出力される。
		// new GSONを使用すると値がNULLの場合は、JSONから外される。
		Gson gson = new GsonBuilder().serializeNulls().create();
		HashMap v_result = new HashMap();
		YoteiDomain input = null;

		// 予定の削除
		yoteiService.delete(yoteiBean.getId());
		
		// ログ出力
		Logger logger = LoggerFactory.getLogger(RegistContainerLogic.class);

		//戻り値の設定
		v_result.put("id", yoteiBean.getId());

		return gson.toJson(v_result);
	}
}
