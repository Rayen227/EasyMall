package easymall.poiexcel;

import java.io.File;
import java.sql.DriverManager;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
//import org.junit.Test;

public class testPOI {
	
	public List<person> getList() {
		List<person> list = new ArrayList<person>();
		
		person shop1 = new person("荣耀Play5T", 1190.0 ,0 , 1000 );
		person shop2 = new person("爱疯9S(0315-01)",1000.0, 0, 1000);
		person shop3 = new person("滑雪套装",2000.0, 0, 1000);
		person shop4 = new person("打印机",180.0, 0, 1000);
		person shop5 = new person("1208——01iphone7",12, 0, 1000);
		
		person shop6 = new person("战神主机",12, 0, 1000);
		person shop7 = new person("床上用品",12, 0, 1000);
		person shop8 = new person("打印机021701",3000, 0, 1000);
		person shop9 = new person("金士顿8G内存量",10000, 0, 1000);
		person shop10 = new person("计算之魂",79.8, 0, 1000);
		person shop11 = new person("美的空调",1200, 0, 1000);
		
		
		list.add(shop1);
		list.add(shop2);
		list.add(shop3);
		list.add(shop4);
		list.add(shop5);
		
		list.add(shop6);
		list.add(shop7);
		list.add(shop8);
		list.add(shop9);
		list.add(shop10);
		list.add(shop11);
		
		return list;
	}
	
	
	
	
	
//	@Test
	public void testOutputExcel() {
//		创建一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
//		创建一个工作表
		HSSFSheet sheet = workbook.createSheet("商品报表"); //可以这样命名
		
//		设置单元格的宽度
		sheet.setColumnWidth(3, 15 * 256 ); //第一个参数是列的编号，第二个是宽度（数的是字符）
		

		
		
		
		
//		HSSFSheet sheet2 = workbook.createSheet("员工信息表2");
//		创建行，并在行内写入数据
//		顺便创建个样式
//		创建一个样式对象
		
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中
		HSSFFont font = workbook.createFont();
		
		font.setBold(true);
		
		HSSFColor color = new HSSFColor();
		
		style.setFont(font);
		
		String[] title = {"商品名称","商品单价","销售数量","库存数量"};
		
		HSSFRow row = sheet.createRow(0); //从0开始的
		
		for(int i = 0 ; i < title.length ; i++) {
			HSSFCell cell = row.createCell(i); //0，1,2,3,4,5个单元格
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		
		//4.把list里的数值放进去
		List<person> list = getList();
		
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中
		
		
		for(int i= 0  ; i < list.size() ; i++) { //循环几次创建几行
			HSSFRow row2 = sheet.createRow(i + 1);//由于第一行是表头了
			
			person persont = list.get(i);
			
			
			HSSFCell cell = row2.createCell(0);
			cell.setCellValue(persont.getName());
			cell.setCellStyle(style2);
			
			HSSFCell cell2 = row2.createCell(1);
			cell2.setCellValue(persont.getPrice());
			cell2.setCellStyle(style2);
			
			HSSFCell cell3 = row2.createCell(2);
			cell3.setCellValue(persont.getSellNum());
			cell3.setCellStyle(style2);
			
			HSSFCell cell4 = row2.createCell(3);
			cell3.setCellValue(persont.getSellNum());
			cell3.setCellStyle(style2);
			
			HSSFCell cell5 = row2.createCell(4);
			cell5.setCellValue(persont.getStoreNum());
			cell5.setCellStyle(style2);
			
		
		}
		
//		内存，把数据输出到硬盘
		File file = new File("C:\\Users\\Lenovo\\Desktop\\webchat\\test.xls");
		
		try {
			OutputStream outputStream = new FileOutputStream(file);
			
//			把工作簿内容输出到文件
			try {
				workbook.write(outputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
} 
