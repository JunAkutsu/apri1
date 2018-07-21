package com.apri.session2;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.apri.csv4.TantousyaDomain;

// Http Sessionを使用せずに異なるコントローラ間でセッションデータを使用する方法
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS,value = WebApplicationContext.SCOPE_SESSION)
public class AccountLogic implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private  List<TantousyaDomain> list;

	public List<TantousyaDomain> getList() {
		return list;
	}

	public void setList(List<TantousyaDomain> list) {
		this.list = list;
	}
	
}
