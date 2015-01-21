package com.system.prg.log.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.prg.log.entity.SLog;
import com.system.prg.log.service.SLogService;
import com.system.prg.util.AjaxUtils;
import com.system.prg.util.BusinessException;
import com.system.prg.util.DateUtils;
import com.system.util.BaseController;
import com.system.util.ComUtil;

@Controller
@Scope("session")
@RequestMapping(value = "/system/prg/log")
public class LogController extends BaseController {
	Logger log = LoggerFactory.getLogger(LogController.class);
	@Autowired
	private SLogService sLogService;

	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		return "system/prg/log/init";
	}
	
	@RequestMapping(value = "list")
	public void list(HttpServletRequest request, HttpServletResponse response,@ModelAttribute SLog sLog){
		log.debug("method: list() ");
		try {
			po = getPageObject(request,"LOG_TIME desc");
			if (po.isExeQuery()) {
				po.getCondition().put("userName",sLog.getUserName());
				po.getCondition().put("userIp",sLog.getUserIp());
				po.getCondition().put("createTimeStart",request.getParameter("createTimeStart"));
				po.getCondition().put("createTimeEnd",request.getParameter("createTimeEnd"));
				List<SLog> modelList = sLogService.pageList(po);
				Integer totalCount = sLogService.countByCondition(po.getCondition());
				po.setTotalCount(totalCount);
				super.list(modelList, po, response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			writeToPage(AjaxUtils.makeJsonResponse(false, "数据错误"), response);
		}
	}
	
	@RequestMapping(value = "exportExcel")
	public void listExcel(HttpServletRequest request, HttpServletResponse response) {
	    HSSFWorkbook workbook = new HSSFWorkbook();// 产生工作簿对象
		po.setPageSize(5000);
		List<SLog>list=sLogService.pageList(po); 
		String col[][]={ //表头和列宽
				{"操作人","3000"},
				{"操作URL","8000"},
				{"操作时间","5000"},
				{"访问IP","8000"}
		};
		HSSFSheet sheet = ComUtil.listExcel(workbook,col);
		HSSFCellStyle style = workbook.createCellStyle();//定义工作薄样式
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		for(int i=0;i<list.size();i++){
			HSSFRow row = sheet.createRow(i+1);
			row.setHeightInPoints(15);
			HSSFCell cell[] = new HSSFCell[col.length];
			for(int j =0;j<cell.length;j++){
				cell[j] = row.createCell(j);
				cell[j].setCellStyle(style);
			}
			cell[0].setCellValue(new HSSFRichTextString(list.get(i).getUserName()));
			cell[1].setCellValue(new HSSFRichTextString(list.get(i).getActionUrl()));
			cell[2].setCellValue(new HSSFRichTextString(list.get(i).getLogTime()));
			cell[3].setCellValue(new HSSFRichTextString(list.get(i).getUserIp()));
		}
        OutputStream fOut = null;
        try {
    		String fileName = "Log"+DateUtils.getDateTimeString();
    		String codedFileName = java.net.URLEncoder.encode(fileName, "utf-8");// 进行转码，使其支持中文文件名
			response.setHeader("content-disposition", "attachment;filename="+ codedFileName + ".xls");
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fOut!=null){fOut.flush();fOut.close();}
			}catch (IOException e) { }
	   }
	}
}
