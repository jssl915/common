package codegenerator;

public class PrimaryKeyDto {
	private String constraintName;
	private int position;	
	private String columnName;
	private String dataType;
	private int dataLength;
	
	public String getConstraintName() {
		return constraintName;
	}
	public int getPosition() {
		return position;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getDataLength() {
		return dataLength;
	}
	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}
	
	
}
