package 测试包;

import org.junit.Test;

import com.yuanhai.test.HtmlUnitAndJsoup;

public class jsoup请求并解析 {
	@Test
	public void testGetResult() throws Exception {
		HtmlUnitAndJsoup bean=new HtmlUnitAndJsoup();
		bean.jsoupCrawl();
		
	}
}
