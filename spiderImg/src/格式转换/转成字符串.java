package ��ʽת��;

import java.text.SimpleDateFormat;

public class ת���ַ��� {

	
	public String getDateStr(java.util.Date date ) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
//		System.out.println(date);
		String str=sdf.format(date);  
		return str;
	}
}
