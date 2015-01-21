package codegenerator;

public class ConfigInfo {
	private String driver;
	private String url;
	private String userName;
	private String password;
	private String[] tableNameList;
	private String packageName;
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String[] getTableNameList() {
		return tableNameList;
	}
	public void setTableNameList(String[] tableNameList) {
		this.tableNameList = tableNameList;
	}
}
