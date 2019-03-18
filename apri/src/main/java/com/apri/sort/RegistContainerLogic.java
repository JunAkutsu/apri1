package com.apri.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

import com.apri.sample2.TantousyaService;

//commit、rollbackによるトランザクション管理

@Controller
@RequestMapping(value="/sort")
public class RegistContainerLogic {

	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		// 初期化
		String line;
		int hyouji_num=0;
		List<String> inputList = new ArrayList<String>();
		List<String> outputList = new ArrayList<String>();
		boolean first_flg=true;
		
		// データの取得
		File file = new File("C:\\work\\test2.txt");
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			while ((line = in.readLine()) != null) {
				if(first_flg) {
					hyouji_num = Integer.parseInt(line);
					first_flg=false;
				}
				else {
					if(line != null && line != "") {
						outputList.add(line);
					}
				}
			}
		} catch (NumberFormatException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// ソート
		outputList.sort(Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.naturalOrder()));

		// 値の出力
		for(int i=0;i<hyouji_num;i++) {
			System.out.println(outputList.get(i));
		}
		return "sort/index";
	}
}
