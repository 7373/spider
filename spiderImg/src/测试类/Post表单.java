package ������;

import org.junit.Test;

import �ɼ�.SpiderMethod;

public class Post�� {
	@Test
	public void testGetResult() throws Exception {
		
		SpiderMethod spider = new SpiderMethod();
		spider.post("http://caiji.miaotk.com/index.php/home/fan/coupon_ajax");
	}
}
