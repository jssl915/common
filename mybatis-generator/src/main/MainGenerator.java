package main;

import java.util.List;

import codegenerator.*;

public class MainGenerator {
	public static void main(String[] args) {
		TableInfoReader reader = new TableInfoReader();
		ConfigInfo config = reader.loadConfigInfo();
		String packageName = config.getPackageName(); //包名
		String[] tableNameList = config.getTableNameList();//表名
		String[] entityNameList = config.getEntityNameList();//实体类名
		EntityGenerator entityGen = new EntityGenerator();//获取生成实体类实例
		for(int i=0;i<tableNameList.length;i++){
			String tableName = tableNameList[i];
        	String entityName = entityNameList[i];//得到实体类名      
        	List<ColumnDto>list = reader.readTableColumnInfo(config, tableName);//得到列集合
        	if(list==null || list.size()==0){
        		System.out.println("===================load table info  fail , error !");
        		break;
        	}
        	//生成entity.java    
        	String entityCode = entityGen.generateEntityCode(list,packageName,entityName);
        	createFile(packageName+".entity",entityName+".java", entityCode);
        	
        	//生成mapper.java        	
        	String mapperinterfaceCode = MapperInterfaceGenerator.generateMapperInterface(packageName, entityName);
        	createFile(packageName+".mapper",entityName+"Mapper.java", mapperinterfaceCode);
        	
        	List<PrimaryKeyDto> keyList = reader.getPrimaryKey(config, tableName);//得到主键集合
        	MapperXmlGenerator mapperXMLGen = new MapperXmlGenerator(packageName, entityName,tableName,keyList);        			
        	//生成mapper.xml     
        	String mapperinXMLCode = mapperXMLGen.generateMapperXML(list);
        	createFile(packageName+".mapper",entityName+"Mapper.xml", mapperinXMLCode); 
        	
        	//生成service.java        	
        	String serviceinterfaceCode = ServiceInterfaceGenerator.generateServiceInterface(packageName, entityName);
        	createFile(packageName+".service",entityName+"Service.java", serviceinterfaceCode);
        	
        	//生成serviceImpl.java        	
        	String serviceImplinterfaceCode = ServiceImplInterfaceGenerator.generateServiceImplInterface(packageName, entityName);
        	createFile(packageName+".service",entityName+"ServiceImpl.java", serviceImplinterfaceCode);
        	
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
