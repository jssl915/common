package codegenerator;

import java.util.List;

public class MapperXmlGenerator {
	private String  packageName;
	private String entityName;	
	private String tableName;	
	private List<PrimaryKeyDto> primaryKeyList;
	
	public MapperXmlGenerator() {
		super();		
	}
	
	public MapperXmlGenerator(String packageName, String entityName,
			String tableName, List<PrimaryKeyDto> primaryKeyList) {
		super();
		this.packageName = packageName;
		this.entityName = entityName;
		this.tableName = tableName;
		this.primaryKeyList = primaryKeyList;
	}


	public  String generateMapperXML(List<ColumnDto> list){
		StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
		sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
		sb.append(" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\r\n");
		sb.append("<mapper namespace=\"").append(this.packageName).append(".mapper.").append(this.entityName);
		sb.append("Mapper\" >\r\n");		
		resultMap(list,sb);
		exampleWhereClause(list,sb);
		insertSQL(list,sb);
		deleteByConditionSQL(sb);
		updateSQL(list,sb);
		countByConditionSQL(sb);
		selectByConditionSQL(list,sb);
		deleteByPrimaryKeySQL(sb);
		findByPrimaryKeySQL(list,sb);
		sb.append("</mapper>");
		
		return sb.toString();
	}
	
	
	
	public  void  resultMap(List<ColumnDto> list,StringBuilder sb){
		sb.append("\t<resultMap id=\"BaseResultMap\" type=\""+this.packageName+
				    ".entity."+this.entityName+"\" >\r\n");
		PrimaryKeyDto singlePKColumn = findSinglePrimaryKeyColumn();
		for(ColumnDto dto : list){
			String columnName = dto.getColName();
			String jdbcType = CommonUtil.getJdbcType(dto.getColType());
			String property = CommonUtil.columnName2Property(columnName);
			if(singlePKColumn!=null && columnName.equalsIgnoreCase(singlePKColumn.getColumnName())){
				sb.append("\t\t<id column=\""+columnName+ "\" property=\""+property+
						"\" jdbcType=\""+jdbcType+"\" />\r\n");
			}
			else{
				sb.append("\t\t<result column=\""+columnName+ "\" property=\""+property+
						"\" jdbcType=\""+jdbcType+"\" />\r\n");
			}
		}
		sb.append("\t</resultMap>\r\n");		
	}
	
	
	public void exampleWhereClause(List<ColumnDto> list,StringBuilder sb){
		sb.append("\t<sql id=\"Example_Where_Clause\" >\r\n");
		sb.append("\t\t<where>\r\n");
		     for(ColumnDto dto : list){
		    	 String columnName = dto.getColName();
				 String property = CommonUtil.columnName2Property(columnName);
		    	 sb.append("\t\t\t<if test=\""+property+" != null and "+property+" !=''\">\r\n");
		    	 sb.append("\t\t\t\t  AND " +columnName+ " = #{" +property+ "}\r\n");
		    	 sb.append("\t\t\t</if>\r\n");
		     }
		sb.append("\t\t</where>\r\n");
		sb.append("\t</sql>\r\n");
	}
	
	
    public void insertSQL(List<ColumnDto> list,StringBuilder sb){
			sb.append("\t<insert id=\"insert\"  parameterType=\""+
	                     this.packageName+ ".entity."+this.entityName+"\" >\r\n");	
			sb.append("\t\tinsert into ").append(this.tableName.toUpperCase()).append("(");
		    for(int i=0;i<list.size();i++){
			    ColumnDto dto = list.get(i);
			    String columnName = dto.getColName();
			    if(i==list.size()-1){
			    	sb.append(" " +columnName);
			    }
			    else{
			    	sb.append(columnName+",");
			    }
			    if(i%3==0 && i!=0){
			    	sb.append("\r\n \t\t\t ");
			    }			    	
			}
			sb.append(") \r\n \t\t values( ");
			for(int i=0;i<list.size();i++){
			    ColumnDto dto = list.get(i);
			    String columnName = dto.getColName();
			    String property = CommonUtil.columnName2Property(columnName);
			    String jdbcType = CommonUtil.getJdbcType(dto.getColType());
			    String value = "#{"+property+",jdbcType="+jdbcType+"}";
			    if("N".equalsIgnoreCase(dto.getColNullable())){  //不能为空
			    	value = "#{" +property+ "}";
			    }
			    if(i==list.size()-1){
			    	sb.append(" " +value);
			    }
			    else{
			    	sb.append(value+",");
			    }
			    if(i%3==0 && i!=0){
			    	sb.append("\r\n \t\t\t ");
			    }			    	
			}
			sb.append(") \r\n \t</insert> \r\n");
    }

    public void deleteByConditionSQL(StringBuilder sb){
		sb.append("\t<delete id=\"deleteByCondition\"  parameterType=\"java.util.Map\" >\r\n");	
		sb.append("\t\t delete from  ").append(this.tableName.toUpperCase()).append("\r\n");
		sb.append("\t\t <include refid=\"Example_Where_Clause\" />\r\n");
		sb.append("\t</delete> \r\n");
    }
    
    public void updateSQL(List<ColumnDto> list,StringBuilder sb){
		sb.append("\t<update id=\"updateByCondition\"  parameterType=\""+
	                     this.packageName+ ".entity."+this.entityName+"\"  >\r\n");	
		sb.append("\t\t update ").append(this.tableName.toUpperCase()).append("\r\n");
		sb.append("\t\t\t<trim prefix=\"SET\" suffixOverrides=\",\" > ").append("\r\n");
		for(ColumnDto dto : list){
	    	 String columnName = dto.getColName();
			 String property = CommonUtil.columnName2Property(columnName);
	    	 sb.append("\t\t\t\t<if  test=\""+property+" != null and "+property+" !=''\">\r\n");
	    	 sb.append("\t\t\t\t\t" +columnName+ " = #{" +property+ "} , \r\n");
	    	 sb.append("\t\t\t\t</if>\r\n");
	     }
		sb.append("\t\t\t</trim>\r\n");
		//有主键，以主键作为条件
		if(this.primaryKeyList!=null || this.primaryKeyList.size()>0){
			sb.append("\t\t\t where ").append(getWhereConditionByPrimaryKeyList() ).append("\r\n");
		}
		else if(list.size()>0){ //无主键，取第一个字段作为条件
			ColumnDto dto = list.get(0);
			String property = CommonUtil.columnName2Property(dto.getColName());
			String condition = dto.getColName()+" = #{" +property+"} " ; 
			sb.append("\t\t\t where ").append( condition ).append("\r\n");
		}
		sb.append("\t</update> \r\n");
    }
    
    
    public void countByConditionSQL(StringBuilder sb){
		sb.append("\t<select id=\"countByCondition\"  parameterType=\"java.util.Map\"" +
				                              "  resultType=\"java.lang.Integer\">\r\n");	
		sb.append("\t\t select  count(1)  from ").append(this.tableName.toUpperCase()).append("\r\n");		
		sb.append("\t\t <include refid=\"Example_Where_Clause\" />\r\n");
		sb.append("\t</select> \r\n");
    }
    
    public void selectByConditionSQL(List<ColumnDto> list,StringBuilder sb){
		sb.append("\t<select id=\"selectByCondition\"  parameterType=\"java.util.Map\"" +
				                              "  resultMap=\"BaseResultMap\">\r\n");	
		sb.append("\t\t select ").append(getColumnList(list,5) ).append("\r\n\t\t  from ");
		sb.append(this.tableName.toUpperCase()).append("\r\n");				
		sb.append("\t\t <include refid=\"Example_Where_Clause\" />\r\n");
		sb.append("\t\t <if  test=\"orderByClause != null and orderByClause !=''\">\r\n");
		sb.append("\t\t\t order by ${orderByClause}\r\n");
		sb.append("\t\t </if>\r\n");		
		sb.append("\t</select> \r\n");
    }
    
    public void deleteByPrimaryKeySQL(StringBuilder sb){
    	if(this.primaryKeyList==null && this.primaryKeyList.size()==0){
    		return;
		}
    	sb.append("\t<delete id=\"deleteByPrimaryKey\"  >\r\n");	
		sb.append("\t\t delete from  ").append(this.tableName.toUpperCase()).append("\r\n");
		sb.append("\t\t\t  where ").append( getWhereConditionByPrimaryKeyList() );	
		sb.append("\r\n");
		sb.append("\t</delete> \r\n");
    }
    
    
    public void findByPrimaryKeySQL(List<ColumnDto> list,StringBuilder sb){
    	if(this.primaryKeyList==null && this.primaryKeyList.size()==0){
    		return;
		}
    	sb.append("\t<select id=\"findByPrimaryKey\"   resultMap=\"BaseResultMap\">\r\n");	
    	sb.append("\t\t select ").append(getColumnList(list,5) ).append("\r\n\t\t   from ");
    	sb.append(this.tableName.toUpperCase()).append("\r\n");	
		sb.append("\t\t\t  where ").append( getWhereConditionByPrimaryKeyList() );	
		sb.append("\r\n");
		sb.append("\t</select> \r\n");
    }
    
    
    private String getWhereConditionByPrimaryKeyList(){
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<this.primaryKeyList.size();i++){
    		PrimaryKeyDto  key  = this.primaryKeyList.get(i);
    		String property = CommonUtil.columnName2Property(key.getColumnName());
    		if(i==0){
    			sb.append(key.getColumnName()).append(" = #{").append(property).append("}");
    		}
    		else{
    			sb.append("  AND  ").append(key.getColumnName()).append(" = #{").append(property).append("}");
    		}    		
    	}
    	return sb.toString();
    }
    
    private PrimaryKeyDto  findSinglePrimaryKeyColumn(){
		if(this.primaryKeyList==null || this.primaryKeyList.size()!=1){
			return null;
		}
		else{
			return this.primaryKeyList.get(0);
		}
	}
	
    private String getColumnList(List<ColumnDto> list,int rowMaxColumn){
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<list.size();i++){
		    ColumnDto dto = list.get(i);
		    String columnName = dto.getColName();		   
		    if(i==list.size()-1){
		    	sb.append("  " +columnName);
		    }
		    else{
		    	sb.append(columnName+", ");
		    }
		    if(i%rowMaxColumn==0 && i!=0){
		    	sb.append("\r\n \t\t\t ");
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<PrimaryKeyDto> getPrimaryKeyList() {
		return primaryKeyList;
	}

	public void setPrimaryKeyList(List<PrimaryKeyDto> primaryKeyList) {
		this.primaryKeyList = primaryKeyList;
	}
    
    
}
