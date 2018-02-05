package 测试包;

import org.junit.Test;

import com.yuanhai.test.HtmlUnitAndJsoup;

public class 百度高级搜索 {
	@Test
	public void testGetResult() throws Exception {
		HtmlUnitAndJsoup bean=new HtmlUnitAndJsoup();
		bean.HtmlUnitBaiduAdvanceSearch();
		
	}
}
