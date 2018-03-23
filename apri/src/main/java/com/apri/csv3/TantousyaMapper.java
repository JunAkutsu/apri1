package com.apri.csv3;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.apri.sample.SelectTantousyaContent;

@Mapper
// DI注入でクラス名が同じであるファイルが複数存在する場合
// 実際にそのファイルを@Autowiredでしようとするとエラーが発生する(同じ名前のファイルが複数存在するというエラー)
// この為、Beanとして登録されるファイルにComponentをつける(引数は登録のBean名)
// 使用側で登録した名前を選択(@Qualifier)すれば、エラーにはならない。
@Component("csv3.TantousyaMapper")
public interface TantousyaMapper {
	List<TantousyaDomain> selectAll();
}
