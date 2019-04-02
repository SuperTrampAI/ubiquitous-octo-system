/** 
 * Project Name:admin 
 * File Name:OutputExcelUtil.java 
 * Package Name:com.xmlyart.admin.common.util 
 * Date:2019年4月1日下午5:02:25 
 * Copyright (c) 2019, 交艺网 All Rights Reserved. 
 * 
*/

package com.github.supertrampai.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class OutputExcelUtil {

	private static final Logger log = LoggerFactory.getLogger(OutputExcelUtil.class);

	/**
	 * 导出功能 注意：泛型T类字段名和containBean集合里字段名字的一致性
	 *
	 * @param response
	 * @param title       表名
	 * @param headers     表头
	 * @param list        数据集
	 * @param containBean 数据集类型字段
	 * @param             <T>
	 * @throws Exception
	 */
	public static <T> void exportExcel(HttpServletResponse response, String title, String[] headers, List<T> list,
			List<String> containBean) throws Exception {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(title);
			XSSFRow row = sheet.createRow(0);
			/* 创建第一行表头 */
			for (short i = 0; i < headers.length; i++) {
				XSSFCell cell = row.createCell(i);
				XSSFRichTextString text = new XSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
			Iterator<T> it = list.iterator();
			int index = 0;
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				T t = (T) it.next();
				/* 反射得到字段 */
				Field[] fields = t.getClass().getDeclaredFields();
				/* 如果需要匹配 */
				if (!CollectionUtils.isEmpty(containBean)) {
					for (int j = 0; j < containBean.size(); j++) {
						for (int i = 0; i < fields.length; i++) {
							Field field = fields[i];
							if (!field.getName().equals(containBean.get(j)))
								continue;
							/* 给每一列set值 */
							setCellValue(t, field, row, j);
						}
					}
				} else {
					for (int i = 0; i < fields.length; i++) {
						Field field = fields[i];
						setCellValue(t, field, row, i);
					}
				}
			}
			/* application/vnd.ms-excel告诉浏览器要下载的是个excel */
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			/* 请求头设置，Content-Disposition为下载标识，attachment标识以附件方式下载 */
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String((title).getBytes(), "UTF-8") + ".xls");
			workbook.write(response.getOutputStream());
		} finally {
			if (workbook != null) {
				workbook.cloneSheet(0);
			}
		}
	}

	/**
	 * 设置每一行中的列
	 *
	 * @param t
	 * @param field
	 * @param row
	 * @param index
	 * @param       <T>
	 */
	private static <T> void setCellValue(T t, Field field, XSSFRow row, int index) {
		XSSFCell cell = row.createCell(index);
		Object value = invoke(t, field);
		String textValue = null;
		if (value != null) {
			if (value instanceof Date) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = (Date) value;
				textValue = format.format(date);
			} else {
				textValue = value.toString();
			}
		}
		if (textValue != null) {
			cell.setCellValue(textValue);
		}
	}

	/**
	 * 反射映射数据集字段
	 *
	 * @param t
	 * @param field
	 * @param       <T>
	 * @return
	 */
	private static <T> Object invoke(T t, Field field) {
		try {
			String fieldName = field.getName();
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, t.getClass());
			Method method = pd.getReadMethod();
			return method.invoke(t);
		} catch (Exception e) {
			return null;
		}
	}

}
