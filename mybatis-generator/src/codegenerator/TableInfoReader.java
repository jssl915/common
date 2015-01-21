package codegenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableInfoReader {
	
	public ConfigInfo loadConfigInfo(){
		ConfigInfo config = new ConfigInfo();		
    	String confPath = CommonUtil.getRootDirPath()+"/conf";        	
    	System.out.println("===============confPath="+confPath);
    	try {
			PropertiesUtil.loadProperties(confPath+"/config.properties");
			String driver = PropertiesUtil.getProperty("jdbc.driver");
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
			String tableNames = PropertiesUtil.getProperty("tableNameList");
			if(tableNames!=null){
				tableNames = tableNames.trim();
				String[] tableList = tableNames.split(",");
				config.setTableNameList(tableList);
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
			String strsql = "select c.column_name,c.comments,t.data_type,t.data_length, " + 
			                    "t.data_precision,t.nullable from  user_col_comments c,user_tab_columns t "
					           + "where c.column_name = t.column_name " + "and c.table_name = t.table_name " 
			                   + "and t.table_name = '" + tableName.toUpperCase() + "' ";
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
			String sqlStr = "select a.constraint_name,  a.column_name, a.position, t.data_type, t.data_length " +
			   " from user_cons_columns a, user_constraints b, user_tab_columns t  " +
		         " where a.constraint_name = b.constraint_name  and a.column_name = t.column_name " +
			     " and a.table_name = t.table_name and b.constraint_type = 'P' and a.table_name = '" +
		            tableName.toUpperCase() + "'  order by a.position" ;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sqlStr);		
			while (rs.next()) {
				PrimaryKeyDto dto = new PrimaryKeyDto();
				dto.setConstraintName(rs.getString("constraint_name"));	
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

