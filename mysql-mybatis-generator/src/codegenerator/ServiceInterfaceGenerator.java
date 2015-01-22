package codegenerator;


public class ServiceInterfaceGenerator {
	public static String generateServiceInterface(String packageName, String entityName) {
		StringBuilder sb = new StringBuilder();
		sb.append("package "+packageName+".service;\r\n\r\n");
		sb.append("import "+packageName+".entity."+entityName+";\r\n");
		sb.append("import com.system.prg.util.BaseService;\r\n\r\n");
		sb.append("public interface " + entityName + "Service extends BaseService<"+ entityName +">{\r\n\r\n");
		sb.append("}\r\n");
		return sb.toString();
	}
}
