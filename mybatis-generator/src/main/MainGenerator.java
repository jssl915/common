package main;

import java.util.List;

import codegenerator.*;

public class MainGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TableInfoReader  reader = new TableInfoReader();
		EntityGenerator entityGen = new EntityGenerator();
		ConfigInfo config = reader.loadConfigInfo();
		String packageName = config.getPackageName();
		String[] tableNameList = config.getTableNameList();
        for(String tableName : tableNameList){
        	String entityName = CommonUtil.getEntityName(tableName);        	
        	List<ColumnDto>  list = reader.readTableColumnInfo(config, tableName);
        	if(list==null || list.size()==0){
        		System.out.println("===================load table info  fail , error !");
        		break;
        	}
        	List<PrimaryKeyDto>  keyList = reader.getPrimaryKey(config, tableName);
        	String entityCode = entityGen.generateEntityCode(list, packageName , entityName);
        	createFile(packageName+".entity",entityName+".java", entityCode);
        	        	
        	MapperInterfaceGenerator mapperGen = new MapperInterfaceGenerator(packageName, entityName,keyList);        			
        	String mapperinterfaceCode = mapperGen.generateMapperInterface();
        	createFile(packageName+".mapper",entityName+"Mapper.java", mapperinterfaceCode);
        	
        	MapperXmlGenerator mapperXMLGen = new MapperXmlGenerator(packageName, entityName,
        			 tableName,keyList);        			
        	String mapperinXMLCode = mapperXMLGen.generateMapperXML(list);
        	createFile(packageName+".mapper",entityName+"Mapper.xml", mapperinXMLCode);     
        	System.out.println("===================generate  all , done !");
        }
	}
	
	private static void createFile(String packageName,String fileName,String codeContent){
		String  codePath = CommonUtil.getRootDirPath()+"/code"; 
		String packagePath = packageName.replaceAll("[.]", "/");
		String dir = codePath+'/'+packagePath;
		System.out.println("========================生成文件："+dir+"/"+fileName);
		CommonUtil.generateFile(dir, fileName, codeContent);
		System.out.println("===================generate  successfully");
	}

}
