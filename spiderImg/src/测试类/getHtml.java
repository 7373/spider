package ������;

import org.junit.Test;

import �ɼ�.SpiderMethod;

public class getHtml {
	@Test
	public void testGetResult() throws Exception {
		
		SpiderMethod spider = new SpiderMethod();
		spider.getHtml("http://cu.manmanbuy.com/goto_261499.aspx");
	}
}
