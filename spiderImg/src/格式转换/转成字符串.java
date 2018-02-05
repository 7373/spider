package 格式转换;

import java.text.SimpleDateFormat;

public class 转成字符串 {

	
	public String getDateStr(java.util.Date date ) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
//		System.out.println(date);
		String str=sdf.format(date);  
		return str;
	}
}
