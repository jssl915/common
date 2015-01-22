package codegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class CommonUtil {
	public static String sqlType2JavaType(String sqlType,int colLength) {
		if (sqlType.equalsIgnoreCase("varchar2") || sqlType.equalsIgnoreCase("nvarchar2")
			 || sqlType.equalsIgnoreCase("CHAR") || sqlType.equalsIgnoreCase("CLOB")|| sqlType.equalsIgnoreCase("varchar")) {
			return "String";
		}else if (sqlType.equalsIgnoreCase("number")|| sqlType.equalsIgnoreCase("int")) {
			if(colLength>=10){
				return "Long";
			}else{
				return "Integer";
			}
		}else if (sqlType.equalsIgnoreCase("DATE")) {
			return "Date";
		}else if (sqlType.equalsIgnoreCase("blob")) {
			return "byte[]";
		}
		return null;
	}


	public static String getJdbcType(String sqlType){
		if("varchar2".equalsIgnoreCase(sqlType)){
			return "VARCHAR";
		}else if("nvarchar2".equalsIgnoreCase(sqlType)||"varchar".equalsIgnoreCase(sqlType)){
			return "NVARCHAR";
		}else if("char".equalsIgnoreCase(sqlType)){
			return "CHAR";
		}else if("number".equalsIgnoreCase(sqlType)||"int".equalsIgnoreCase(sqlType)){
			return "DECIMAL";
		}else if("DATE".equalsIgnoreCase(sqlType)){
			return "TIMESTAMP";
		}else if("CLOB".equalsIgnoreCase(sqlType)){
			return "CLOB";
		}else if("BLOB".equalsIgnoreCase(sqlType)){
			return "BLOB";
		}
        return null;
	}
	
	public static String getEntityName(String tableName){
		StringBuilder sb = new StringBuilder();
	    String strNum [] = tableName.split("_");
	    for (int i = 0; i < strNum.length; i++) {
	    	sb.append(strNum[i].substring(0, 1).toUpperCase()).append(strNum[i].substring(1).toLowerCase());
		}
		String name = sb.toString() ;
		if(name.startsWith("T")){
			name = name.substring(1);
		}
		return name;
	}
	
	public static String columnName2Property(String columnName) {
		StringBuilder sb = new StringBuilder();
	    String strNum [] = columnName.split("_");
	    for (int i = 0; i < strNum.length; i++) {
	    	if(i==0){
	    	    sb.append(strNum[i].toLowerCase());
	    	}else{
	    		sb.append(strNum[i].substring(0, 1).toUpperCase()).append(strNum[i].substring(1).toLowerCase());	
	    	}
		}
		return sb.toString() ;
	}
	
	public static String firstChar2UpperCase(String property) {
		return property.substring(0,1).toUpperCase()+property.substring(1);
	}
	
	
	public static String getRootDirPath(){
		String binPath = CommonUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		try { //解决中文路径的问题
			binPath = java.net.URLDecoder.decode(binPath, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if(binPath.startsWith("/")){
    		binPath=binPath.substring(1);
    	}
    	if(binPath.endsWith("/")){
    		binPath=binPath.substring(0,binPath.length()-1);
    	}
    	String rootPath = binPath.substring(0,binPath.lastIndexOf("/"));
    	if(binPath.endsWith(".jar")){
    		int index = binPath.lastIndexOf("/lib/");
    		rootPath = binPath.substring(0,index);
    	}
    	return rootPath;
	}
	
	public static boolean generateFile(String dir,String fileName,String content){
		BufferedWriter bw = null;
		try {
			File directory = new File(dir);
			if(!directory.exists()){
				directory.mkdirs();
			}
			File file = new File(dir+'/'+fileName);
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			bw.write(content);
			bw.flush();	
			return true;
	   } 
	   catch (Exception e) {
		   e.printStackTrace();
		   return false;
	   }
	   finally {
			try {
				if(bw!=null){
					bw.close();
				}				
			} catch (IOException e) {				
				e.printStackTrace();
			}
	    }		
	}
}
