package codegenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableInfoReader {
	public static String database = "oracle";
	public ConfigInfo loadConfigInfo(){
		ConfigInfo config = new ConfigInfo();		
    	String confPath = CommonUtil.getRootDirPath()+"/conf";        	
    	System.out.println("===============confPath="+confPath);
    	try {
			PropertiesUtil.loadProperties(confPath+"/config.properties");
			String driver = PropertiesUtil.getProperty("jdbc.driver");
			if(driver.contains("mysql")){
				database = "mysql";
			}else{
				database = "oracle";
			}
			if(driver!=null){
				config.setDriver(driver.trim());
			}
			String url = PropertiesUtil.getProperty("jdbc.url");
			if(url!=null){
				config.setUrl(url.trim());
			}
			String userName = PropertiesUtil.getProperty("jdbc.userName");
			if(userName!=null){
				config.setUserName(userName.trim());
			}
			String pwd = PropertiesUtil.getProperty("jdbc.password");
			if(pwd!=null){
				config.setPassword(pwd.trim());
			}
			String packageName = PropertiesUtil.getProperty("module.packageName");
			if(packageName!=null){
				config.setPackageName(packageName.trim());
			}
			String entityNames = PropertiesUtil.getProperty("entityNameList");
			String tableNames = PropertiesUtil.getProperty("tableNameList");
			if(tableNames!=null){
				tableNames = tableNames.trim();
				String[] tableList = tableNames.split(",");
				config.setTableNameList(tableList);
				entityNames = entityNames.trim();
				String[] entityList = entityNames.split(",");
				config.setEntityNameList(entityList);
			}
			return config;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("============【error】读取配置参数出错");
			return null;
		}
	}
	
	
	public List<ColumnDto>readTableColumnInfo(ConfigInfo config, String tableName){
		List<ColumnDto> list = new ArrayList<ColumnDto>();
		Connection conn = null;
		try {
			Class.forName(config.getDriver());
			conn = DriverManager.getConnection(config.getUrl(), config.getUserName(), config.getPassword());
			String strsql ="";
			if(database=="oracle"){
				strsql= "select c.column_name,c.comments,t.data_type,t.data_length, " + 
			                    "t.data_precision,t.nullable from  user_col_comments c,user_tab_columns t "
					           + "where c.column_name = t.column_name " + "and c.table_name = t.table_name " 
			                   + "and t.table_name = '" + tableName.toUpperCase() + "' ";
			}else{
				strsql= "SELECT COLUMN_NAME, COLUMN_COMMENT as comments, DATA_TYPE,CHARACTER_MAXIMUM_LENGTH as data_length,NUMERIC_PRECISION as data_precision,"
						+ "IS_NULLABLE as nullable FROM information_schema.columns "
						+ "WHERE table_name = '" + tableName.toUpperCase() + "'";
			}
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(strsql);
			while (rs.next()) {
				ColumnDto dto = new ColumnDto();				
				dto.setColName(rs.getString("column_name"));
				dto.setColType(rs.getString("data_type"));
				dto.setColComment(rs.getString("comments"));
				dto.setColSize(rs.getInt("data_length"));
				dto.setColPrecision(rs.getInt("data_precision"));
				dto.setColNullable(rs.getString("nullable"));
				list.add(dto);
			}
		 } catch (Exception e) {
			 e.printStackTrace();
		  } finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return list;
	}
	
	public List<PrimaryKeyDto>getPrimaryKey(ConfigInfo config, String tableName ){
		List<PrimaryKeyDto> list = new ArrayList<PrimaryKeyDto>();
		Connection conn = null;
		try {
			Class.forName(config.getDriver());
			conn = DriverManager.getConnection(config.getUrl(), config.getUserName(), config.getPassword());
			String sqlStr ="";
			if(database=="oracle"){
				sqlStr= "select a.constraint_name,  a.column_name, a.position, t.data_type, t.data_length " +
			   " from user_cons_columns a, user_constraints b, user_tab_columns t  " +
		         " where a.constraint_name = b.constraint_name  and a.column_name = t.column_name " +
			     " and a.table_name = t.table_name and b.constraint_type = 'P' and a.table_name = '" +
		            tableName.toUpperCase() + "'  order by a.position" ;
			}else{
				sqlStr= "SELECT COLUMN_NAME, COLUMN_COMMENT as comments, DATA_TYPE,CHARACTER_MAXIMUM_LENGTH as data_length,NUMERIC_PRECISION as data_precision,"
						+ "IS_NULLABLE as nullable,count(1) as position FROM information_schema.columns "
						+ "WHERE table_name = '" + tableName.toUpperCase() + "'and column_key !='' " ;
			}
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sqlStr);		
			while (rs.next()) {
				PrimaryKeyDto dto = new PrimaryKeyDto();
				dto.setConstraintName(tableName.toUpperCase());	
				dto.setColumnName(rs.getString("column_name"));
				dto.setDataType(rs.getString("data_type"));	
				dto.setDataLength(rs.getInt("data_length"));	
				dto.setPosition(rs.getInt("position"));
				list.add(dto);
			}			
		 } 
		 catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return list;
	}
}

