package com.apri.light;

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
@RequestMapping(value="/light")
public class RegistContainerLogic {

	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		// 初期化
	    String line="";
		int hyouji_num=0;
		boolean first_flg = true;
		String n_m_list[]=null;
		String light_list[]=null;
		int n_size=0;
		int m_size=0;
		int row_no=0;
		int[][] kairo_kiban=null;
		int[][] kairo_kiban_original=null;
	    boolean all_zero_flg=true;
	    int push_num=0;
	    int min_push_num=-1;
	    String binary_value=null;
	    String[] bit_su=null;
	    int row_m_num=0;

		
		// データの取得
		File file = new File("C:\\work\\test11.txt");
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			while ((line = in.readLine()) != null) {
				if(first_flg) {
					// サイズを取得する
					first_flg=false;
					n_m_list = line.split(" ");
					n_size = Integer.parseInt(n_m_list[0]);
					m_size = Integer.parseInt(n_m_list[1]);
				}
				else {
					// 回路基盤を生成する
					if(row_no==0) {
						kairo_kiban = new int[n_size][m_size];
						kairo_kiban_original = new int[n_size][m_size];
					}

					// ライト状態を取得する
					light_list = line.split("");
					for(int i=0;i<m_size;i++) {
						if(light_list[i].equals("0")) {
							kairo_kiban[row_no][i] = 1;
							kairo_kiban_original[row_no][i] = 1;
						}
						else {
							kairo_kiban[row_no][i] = 0;
							kairo_kiban_original[row_no][i] = 0;
						}
					}
					// 次の行へ
					row_no++;
				}
			}
		} catch (NumberFormatException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	    // 1行目を左上から順々に押して確認する
	    for(int i=0;i<Math.pow(2, m_size);i++) {
	    	// 初期化
	    	all_zero_flg=true;
	    	push_num=0;

	    	// オリジナル回路基盤で元に戻す
	    	if(i!=0) {
	    		copy_kairo_kiban(kairo_kiban,kairo_kiban_original,n_size,m_size);
	    	}

	    	// 1行目を左上から順々に押す。
	    	// 押す度に回数を加算する。
		    binary_value = Integer.toBinaryString(i);
		    bit_su = binary_value.split("");
		    row_m_num=0;

		    for(int loop_cnt=bit_su.length-1;loop_cnt>=0;loop_cnt--) {
			    if(bit_su[loop_cnt].equals("1")) {
				    auto_hanten(kairo_kiban,0,row_m_num,n_size,m_size);
				    push_num++;
			    };
			    row_m_num++;
		    }

		    // 2行目から最終行までの左端から右へ行く。
		    // 自分の上のマスが1の場合は、自分自身を押す。
		    for(int j=1;j<n_size;j++){
			    for(int k=0;k<m_size;k++){
			    	// 自分の上のマスが1かチェック
			    	if(kairo_kiban[j-1][k] ==1) {
			    		// 1の場合は、自分自身を押す。
			    		auto_hanten(kairo_kiban,j,k,n_size,m_size);
					    push_num++;
			    	}
			    }
		    }

		    // 回路基盤の全てのマスが0かチェック
		    for(int l=0;l<n_size;l++) {
			    for(int m=0;m<m_size;m++) {
					if(kairo_kiban[l][m]==1) {
						all_zero_flg=false;
						break;
					};
			    }
		    }

		    // 最小回数を設定する。
		    if(all_zero_flg) {
		    	if(min_push_num == -1) {
		    		min_push_num = push_num;
		    	}
		    	else {
		    		if(push_num < min_push_num) {
			    		min_push_num = push_num;
		    		}
		    	}
		    }
	    }
	    // 結果の出力
	    System.out.println(min_push_num);
	    
		return "light/index";
	}
	
	// ライトの灯りを上下左右変換する
	// p_kairo_kiban 回路基盤
	// p_now_n_size n(行)の現在位置
	// p_now_m_size m(列)の現在位置
	// p_n_size ｎサイズ
	// p_m_size mサイズ
	public void auto_hanten(int[][] p_kairo_kiban,int p_now_n_size,int p_now_m_size,int p_n_size,int p_m_size) {

		// 自分自身の反転
		if(p_kairo_kiban[p_now_n_size][p_now_m_size] == 1) {
			p_kairo_kiban[p_now_n_size][p_now_m_size] = 0;
		}
		else {
			p_kairo_kiban[p_now_n_size][p_now_m_size] = 1;
		}
		
		// 自分自身の上を反転
		// 自分自身が現在いる場所を考慮する
		if(p_now_n_size != 0) {
			if(p_kairo_kiban[p_now_n_size-1][p_now_m_size] == 1) {
				p_kairo_kiban[p_now_n_size-1][p_now_m_size] = 0;
			}
			else {
				p_kairo_kiban[p_now_n_size-1][p_now_m_size] = 1;
			}
		}

		// 自分自身の下を反転
		// 自分自身が現在いる場所を考慮する
		if(p_now_n_size+1 != p_n_size) {
			if(p_kairo_kiban[p_now_n_size+1][p_now_m_size] == 1) {
				p_kairo_kiban[p_now_n_size+1][p_now_m_size] = 0;
			}
			else {
				p_kairo_kiban[p_now_n_size+1][p_now_m_size] = 1;
			}
		}


		// 自分自身の左を反転
		// 自分自身が現在いる場所を考慮する
		if(p_now_m_size != 0) {
			if(p_kairo_kiban[p_now_n_size][p_now_m_size-1] == 1) {
				p_kairo_kiban[p_now_n_size][p_now_m_size-1] = 0;
			}
			else {
				p_kairo_kiban[p_now_n_size][p_now_m_size-1] = 1;
			}
		}
		
		// 自分自身の右を反転
		// 自分自身が現在いる場所を考慮する
		if(p_now_m_size+1 != p_m_size) {
			if(p_kairo_kiban[p_now_n_size][p_now_m_size+1] == 1) {
				p_kairo_kiban[p_now_n_size][p_now_m_size+1] = 0;
			}
			else {
				p_kairo_kiban[p_now_n_size][p_now_m_size+1] = 1;
			}
		}
	}

	// 回路基盤をコピーする
	// p_dist_kairo_kiban 回路基盤(コピー先)
	// p_src_kairo_kiban 回路基盤(コピー元)
	// p_n_size ｎサイズ
	// p_m_size mサイズ
	public void copy_kairo_kiban(int[][] p_dist_kairo_kiban,int[][] p_src_kairo_kiban,int p_n_size,int p_m_size) {

	    for(int i=0;i<p_n_size;i++) {
		    for(int j=0;j<p_m_size;j++) {
		    	p_dist_kairo_kiban[i][j] = p_src_kairo_kiban[i][j];
		    }
	    }
	}

	// 画面に結果を出力する
	// p_kairo_kiban 回路基盤
	// p_n_size ｎサイズ
	// p_m_size mサイズ
	public static void print_log(int[][] p_kairo_kiban,int p_n_size,int p_m_size) {

	    for(int i=0;i<p_n_size;i++) {
		    for(int j=0;j<p_m_size;j++) {
				System.out.print(p_kairo_kiban[i][j]);
		    }
		    System.out.println("");
	    }
	}
}
