package com.match.springmvc.tools;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportExcel {
	
	public static Workbook createWorkBook(List<Map<String,Object>> list,String []keys,String columnNames[]) {
		// 创建Excel 工作簿
		Workbook wb = new HSSFWorkbook();
		// 创建第一个sheet（页），并命名
		Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		for(int i=0;i<keys.length;i++){
			sheet.setColumnWidth((short)i,(short)(40 * 225));
		}
		// 创建第一行
		Row row = sheet.createRow((short) 0);
		// 创建两种单元格格式
		CellStyle cs1 = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();
		// 创建两种字体
		Font f1 = wb.createFont();
		Font f2 = wb.createFont();
		// 创建第一种字体样式（用于列名）
		f1.setFontHeightInPoints((short) 10);
		f1.setColor(IndexedColors.BLACK.getIndex());
		f1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// 创建第二种字体样式（用于值）
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());
		// 设置第一种单元格的样式（用于列名）
		cs1.setFont(f1);
		cs1.setBorderLeft(CellStyle.BORDER_THIN);
		cs1.setBorderRight(CellStyle.BORDER_THIN);
		cs1.setBorderTop(CellStyle.BORDER_THIN);
		cs1.setBorderBottom(CellStyle.BORDER_THIN);
		cs1.setAlignment(CellStyle.ALIGN_CENTER);
		
		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		cs2.setAlignment(CellStyle.ALIGN_CENTER);
		
		//设置列名
		for(int i=0;i<columnNames.length;i++){
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(cs1);
		}
		
		//设置每行每列的值
		for (short i = 1; i < list.size(); i++) {
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
			
			// 创建一行，在页sheet上
			Row row1 = sheet.createRow((short) i);
			
			// 在row行上创建一个方格
			for(short j=0;j<keys.length;j++){
				Cell cell = row1.createCell(j);
				cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
				cell.setCellStyle(cs2);
			}
		}
		return wb;
	}
}
