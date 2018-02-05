package 测试类;

import org.junit.Test;

import 采集.SpiderMethod;

public class Post表单 {
	@Test
	public void testGetResult() throws Exception {
		
		SpiderMethod spider = new SpiderMethod();
		spider.post("http://caiji.miaotk.com/index.php/home/fan/coupon_ajax");
	}
}
