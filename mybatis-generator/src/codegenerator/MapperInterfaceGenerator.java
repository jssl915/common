package codegenerator;


public class MapperInterfaceGenerator {
	public static String generateMapperInterface(String packageName, String entityName) {
		StringBuilder sb = new StringBuilder();
		sb.append("package "+packageName+".mapper;\r\n\r\n");
		sb.append("import "+packageName+".entity."+entityName+";\r\n");
		sb.append("import com.system.prg.util.BaseMapper;\r\n\r\n");
		sb.append("public interface " + entityName + "Mapper extends BaseMapper<"+ entityName +">{\r\n\r\n");
		sb.append("}\r\n");
		return sb.toString();
	}
}
