package 测试包;

import org.junit.Test;

import com.yuanhai.test.HtmlUnitAndJsoup;

public class htmlunit设置代理上网 {
	@Test
	public void testGetResult() throws Exception {
		HtmlUnitAndJsoup bean=new HtmlUnitAndJsoup();
		bean.proxy();
		
	}
}
