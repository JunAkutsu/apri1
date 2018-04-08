package com.apri.common.sqlHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apri.common.exception.ApplicationException;
import com.apri.common.maintenance.ConfigService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SQLHelperService {

	private static final Logger logger = LoggerFactory.getLogger("sql_logger");

	@Autowired
	ConfigService configService;
    
	public void logger_sql(String path_file_name,String mapper_id,Object params)
	throws ApplicationException {
		try{
			if(!configService.isSql_log_hyouji_flg()){
				// 現在ディレクトリーの取得
				String absolute_path = new File(".").getAbsoluteFile().getParent();
				
				// Configuration をインスタンス化
				Configuration config = new Configuration();
				
				// Configuration と Mapper XML で XMLMapperBuilder をインスタンス化
				InputStream fileStream = new FileInputStream(absolute_path+path_file_name);
				XMLMapperBuilder parser = new XMLMapperBuilder(fileStream, config, "", config.getSqlFragments());
				
				// Mapper XML をパース
				parser.parse();
				
				// Configurationから指定の SQL に対応した MappedStatement を取得
				MappedStatement st = config.getMappedStatement(mapper_id);
				
				// SQLの取得
				BoundSql sql = st.getBoundSql(params);

				// SQLの出力
				logger.info("[SQL]");
				logger.info(sql.getSql());
				logger.info("[Parameters]");
				logger.info(sql.getParameterObject().toString());
			}
		}
		catch (FileNotFoundException e) {
			throw new ApplicationException(e);
		}
	}
}
