package codegenerator;

public class ColumnDto {

	private String colName; //列名
	private String colType; //列类型
	private String colComment; //列注释
	private int colSize; //列大小
	private int colPrecision; //列主键编号
	private String colNullable; //列是否可以为null N不可 Y可以

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColType() {
		return colType;
	}

	public void setColType(String colType) {
		this.colType = colType;
	}

	public String getColComment() {
		return colComment;
	}

	public void setColComment(String colComment) {
		this.colComment = colComment;
	}

	public int getColSize() {
		return colSize;
	}

	public void setColSize(int colSize) {
		this.colSize = colSize;
	}

	public int getColPrecision() {
		return colPrecision;
	}

	public void setColPrecision(int colPrecision) {
		this.colPrecision = colPrecision;
	}

	public String getColNullable() {
		return colNullable;
	}

	public void setColNullable(String colNullable) {
		this.colNullable = colNullable;
	}

}
