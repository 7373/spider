package 测试类;

import org.junit.Test;

import 采集.SpiderMethod;

public class 图片爬取 {
	@Test
	public void testGetResult() throws Exception {
		
		SpiderMethod spider = new SpiderMethod();
		spider.getImgs("http://jandan.net/ooxx/page-60#comments");

	}
}
