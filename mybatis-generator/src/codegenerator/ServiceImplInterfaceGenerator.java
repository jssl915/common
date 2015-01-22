package codegenerator;




public class ServiceImplInterfaceGenerator {
	public static String generateServiceImplInterface(String packageName, String entityName) {
		StringBuilder sb = new StringBuilder();
		sb.append("package "+packageName+".service;\r\n\r\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");	
		sb.append("import org.springframework.stereotype.Service;\r\n\r\n");	
		sb.append("import org.springframework.transaction.annotation.Transactional;\r\n");	
		sb.append("import "+packageName+".mapper."+entityName+"Mapper;\r\n");
		sb.append("import com.system.prg.util.BaseServiceImpl;\r\n");
		sb.append("import com.system.prg.util.BusinessException;\r\n");
		
		sb.append("import "+packageName+".entity."+entityName+";\r\n\r\n");
		sb.append("@Service\r\n");
		sb.append("@Transactional(rollbackFor = BusinessException.class)\r\n");
		sb.append("public class " + entityName + "ServiceImpl extends BaseServiceImpl<"+ entityName +"> implements "+entityName+"Service{\r\n\r\n");
		sb.append("\t@Autowired\r\n");
		sb.append("\t"+entityName+"Mapper mapper;\r\n");
		sb.append("\t"+"@Autowired\r\n");
		sb.append("\t"+"public void setMapper("+entityName+"Mapper mapper) {\r\n");
		sb.append("\t\t"+"super.setMapper(mapper);\r\n");
		sb.append("\t"+"}\r\n");
		sb.append("}\r\n");
		return sb.toString();
	}
}
