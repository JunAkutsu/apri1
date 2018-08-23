package com.apri.iframe2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



//iframeの実装

@Controller
@RequestMapping(value="/iframe2")
public class RegistContainerLogic {

	@Autowired
	MenuService menuService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		return "iframe2/index";
	}
	
	@RequestMapping(value="menu", method=RequestMethod.GET)
	public String menu(Model model){
		// 初期化
		int v_row_no=0;
		List<GyoumuDomain> gyoumu_list = new ArrayList<>();
		List<MenuDomain> menu_list = new ArrayList<>();
		
		// メニューデータの取得
		List<MenuDomain> list = menuService.getMenu_list();
		
		for(int i=0;i<list.size();i++){
			// メニューデータの取得
			MenuDomain data = list.get(i);
			// 未処理のデータだけ、以下の処理を実施する。
			if(!data.isSyori_zumi_flg()){
				// GyoumuDomainの生成　
				GyoumuDomain gyoumu_data = new GyoumuDomain();
				
				// Noの設定
				gyoumu_data.setNo(++v_row_no);
				// 業務名称の設定
				gyoumu_data.setGyoumu_meisyou(data.getGyoumu_meisyou());
				// メニューリストへ追加
				menu_list.add(data);
				
				// 他のメニューデータから、メニューリストへ追加する
				for(int j=i+1;j<list.size();j++){
					MenuDomain data2 = list.get(j);
					// 業務名称が一致する場合、メニューリストへ追加する。
					if(data.getGyoumu_meisyou().equals(data2.getGyoumu_meisyou())){
						// メニューリストへ追加
						menu_list.add(data2);
						// 処理済FLGを設定する。
						data2.setSyori_zumi_flg(true);
					}
				}
				// メニューリストの設定
				gyoumu_data.setMenu_list(menu_list);
				// 業務リストへ追加
				gyoumu_list.add(gyoumu_data);
				// メニューリストの初期化
				menu_list = new ArrayList<>();
			}
		}
		model.addAttribute("menu_list", gyoumu_list);
		return "iframe2/menu";
	}
	
}
