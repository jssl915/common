package codegenerator;

import java.util.List;

public class MapperInterfaceGenerator {
	private String  packageName;
	private String entityName;	
	private List<PrimaryKeyDto> primaryKeyList;
	
	public MapperInterfaceGenerator() {
		super();		
	}


	public MapperInterfaceGenerator(String packageName, String entityName,
			List<PrimaryKeyDto> primaryKeyList) {
		super();
		this.packageName = packageName;
		this.entityName = entityName;
		this.primaryKeyList = primaryKeyList;
	}

	public String generateMapperInterface() {
		StringBuilder sb = new StringBuilder();
		sb.append("package "+this.packageName+".mapper;\r\n\r\n");
		sb.append("import java.util.List;\r\n");
		sb.append("import java.util.Map;\r\n");
		//sb.append("import org.apache.ibatis.annotations.Param;\r\n\r\n");
		sb.append("import org.apache.ibatis.session.RowBounds;\r\n\r\n");
		sb.append("import "+this.packageName+".entity."+this.entityName+";\r\n\r\n");
		
		sb.append("public interface " + this.entityName + "Mapper {\r\n\r\n");
		sb.append("\tpublic void insert(" +this.entityName+ "  record" + ") ;\r\n\r\n");
		sb.append("\tpublic int deleteByCondition( Map<String,Object> condition) ;\r\n\r\n");		
		sb.append("\tpublic int updateByCondition(" +this.entityName+ "  record" +") ;\r\n\r\n");
		sb.append("\tpublic  Integer  countByCondition(Map<String,Object> condititon) ;\r\n\r\n");
		sb.append("\tpublic  List<" +this.entityName+ "> selectByCondition(Map<String,Object> condititon ) ;\r\n\r\n");
		sb.append("\tpublic  List<" +this.entityName+ "> selectByCondition(Map<String,Object> condititon, RowBounds rowBounds ) ;\r\n\r\n");
        if(this.primaryKeyList!=null && this.primaryKeyList.size()>0){
        	sb.append("\tpublic int deleteByPrimaryKey(");	
        	sb.append(getParameterListByPrimaryKey());
        	sb.append(");\r\n\r\n");	
        	sb.append("\tpublic "+this.entityName+" findByPrimaryKey(");	
        	sb.append(getParameterListByPrimaryKey());
        	sb.append(");\r\n\r\n");
		}
		sb.append("}\r\n");

		return sb.toString();
	}
	
	private String getParameterListByPrimaryKey(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<this.primaryKeyList.size();i++ ){
    		PrimaryKeyDto key = this.primaryKeyList.get(i);
    		String type = CommonUtil.sqlType2JavaType(key.getDataType(), key.getDataLength());
    		String property = CommonUtil.columnName2Property(key.getColumnName());
    		String tmp = type+"  "+property ;
    		if(i==0){
    			sb.append(tmp);
    		}
    		else{
    			sb.append(", "+tmp);
    		}        		
    	}
		return sb.toString();
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<PrimaryKeyDto> getPrimaryKeyList() {
		return primaryKeyList;
	}

	public void setPrimaryKeyList(List<PrimaryKeyDto> primaryKeyList) {
		this.primaryKeyList = primaryKeyList;
	}

   
}
