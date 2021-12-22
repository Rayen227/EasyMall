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
		
		person shop1 = new person("��ҫPlay5T", 1190.0 ,0 , 1000 );
		person shop2 = new person("����9S(0315-01)",1000.0, 0, 1000);
		person shop3 = new person("��ѩ��װ",2000.0, 0, 1000);
		person shop4 = new person("��ӡ��",180.0, 0, 1000);
		person shop5 = new person("1208����01iphone7",12, 0, 1000);
		
		person shop6 = new person("ս������",12, 0, 1000);
		person shop7 = new person("������Ʒ",12, 0, 1000);
		person shop8 = new person("��ӡ��021701",3000, 0, 1000);
		person shop9 = new person("��ʿ��8G�ڴ���",10000, 0, 1000);
		person shop10 = new person("����֮��",79.8, 0, 1000);
		person shop11 = new person("���Ŀյ�",1200, 0, 1000);
		
		
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
//		����һ��������
		HSSFWorkbook workbook = new HSSFWorkbook();
//		����һ��������
		HSSFSheet sheet = workbook.createSheet("��Ʒ����"); //������������
		
//		���õ�Ԫ��Ŀ��
		sheet.setColumnWidth(3, 15 * 256 ); //��һ���������еı�ţ��ڶ����ǿ�ȣ��������ַ���
		

		
		
		
		
//		HSSFSheet sheet2 = workbook.createSheet("Ա����Ϣ��2");
//		�����У���������д������
//		˳�㴴������ʽ
//		����һ����ʽ����
		
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //����
		HSSFFont font = workbook.createFont();
		
		font.setBold(true);
		
		HSSFColor color = new HSSFColor();
		
		style.setFont(font);
		
		String[] title = {"��Ʒ����","��Ʒ����","��������","�������"};
		
		HSSFRow row = sheet.createRow(0); //��0��ʼ��
		
		for(int i = 0 ; i < title.length ; i++) {
			HSSFCell cell = row.createCell(i); //0��1,2,3,4,5����Ԫ��
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		
		//4.��list�����ֵ�Ž�ȥ
		List<person> list = getList();
		
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); //����
		
		
		for(int i= 0  ; i < list.size() ; i++) { //ѭ�����δ�������
			HSSFRow row2 = sheet.createRow(i + 1);//���ڵ�һ���Ǳ�ͷ��
			
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
		
//		�ڴ棬�����������Ӳ��
		File file = new File("C:\\Users\\Lenovo\\Desktop\\webchat\\test.xls");
		
		try {
			OutputStream outputStream = new FileOutputStream(file);
			
//			�ѹ���������������ļ�
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
