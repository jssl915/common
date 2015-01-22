package codegenerator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EntityGenerator {	
	/**
	 * 生成实体类主体代码
	 */
	public String generateEntityCode(List<ColumnDto> list,String packageName,String entityName) {
		StringBuilder sb = new StringBuilder();
		sb.append("package "+packageName+".entity;\r\n\r\n");
		sb.append("import java.io.Serializable;\r\n");
		int len = sb.length();
		importPackage(list, sb, len);
		sb.append("\r\n");
		sb.append("public class " + entityName + " implements Serializable{\r\n");
		generateProperty(sb, list);
		generateSetterAndGetter(sb, list);
		sb.append("}\r\n");
		return sb.toString();
	}
	//添加包
	public void importPackage(List<ColumnDto> list, StringBuilder sb, int len) {
		Set<String> set = new HashSet<String>();
		for (Iterator<ColumnDto> iterator = list.iterator(); iterator.hasNext();) {
			ColumnDto dto = iterator.next();
			String type = CommonUtil.sqlType2JavaType(dto.getColType(),dto.getColSize());
			if (!set.contains("Date") && "Date".equals(type) ) {
				sb.insert(len, "import java.util.Date;\r\n");
				set.add("Date");
			} 
		}
	}
	//添加实体申明
	private void generateProperty(StringBuilder sb, List<ColumnDto> list) {
		for (Iterator<ColumnDto> iterator = list.iterator(); iterator.hasNext();) {
			ColumnDto dto = iterator.next();
			String type = CommonUtil.sqlType2JavaType(dto.getColType(),dto.getColSize());
			String property =  CommonUtil.columnName2Property(dto.getColName());
			if(dto.getColComment()!=null&&dto.getColComment()!=""){
				sb.append("\r\n\t//").append(dto.getColComment()+ "\r\n");
			}
			sb.append("\tprivate " + type + " " + property + "; \r\n");
		}
	}
	
	//添加get和set方法
	private void generateSetterAndGetter(StringBuilder sb, List<ColumnDto> list) {
		for (Iterator<ColumnDto> iterator = list.iterator(); iterator.hasNext();) {
			ColumnDto dto = iterator.next();	
			String property =  CommonUtil.columnName2Property(dto.getColName());
			String type = CommonUtil.sqlType2JavaType(dto.getColType(),dto.getColSize());
			sb.append("\r\n\tpublic " +type  ); 
			sb.append("  get" + CommonUtil.firstChar2UpperCase(property));		
			sb.append("(){\r\n\t\treturn this." + property+";\r\n");
			sb.append("\t}\r\n");
			
			sb.append("\r\n\tpublic void set" + CommonUtil.firstChar2UpperCase(property)) ;
			sb.append("("+type+" "+property+"){\r\n");					
			if (!"String".equals(type) ) {
				sb.append("\t\tthis." + property + "=" + property + ";\r\n");
			}else{
				sb.append("\t\tthis." + property + " = " + property + " == null ? null : " + property + ".trim();\r\n");
			}
			sb.append("\t}\r\n");
		}
	}
	
	


	
}
