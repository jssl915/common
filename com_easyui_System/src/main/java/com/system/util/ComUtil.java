package com.system.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ComUtil {
	public static List<String> toCombo(Map<String,String>map){
		List<String>list = new ArrayList<String>();
		for (String key : map.keySet()) {  
			String str="{\"text\":\""+map.get(key)+"\",\"value\":\""+key+"\"}";
			list.add(str);
		}  
		return list;
	}
	
	public static HSSFSheet listExcel(HSSFWorkbook workbook, String col[][]){
		HSSFCellStyle style = workbook.createCellStyle();//定义工作薄样式
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		style.setFillForegroundColor((short) 13);// 设置背景色  
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
		HSSFSheet sheet = workbook.createSheet();// 产生工作表对象
		HSSFRow row = sheet.createRow(0);
		row.setHeightInPoints(20);
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//设置表头为粗体
		font.setFontName("宋体");  
		style.setFont(font);
		for(int i =0;i<col.length;i++){
			sheet.setColumnWidth(i,Integer.valueOf(col[i][1]));
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(col[i][0]));
		}
		return sheet;
	}
}
