package com.match.springmvc.tools;

public class WDWUtil {
	
	//@描述：是否是2003的Excel，返回true 是2003版本
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}
	
	//@描述：是否是2007的Excel，返回true 是2007版本
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
}
