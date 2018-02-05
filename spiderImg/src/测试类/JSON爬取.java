package 测试类;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import Model.MiAo;
import net.sf.json.JSONArray;
import 采集.SpiderMethod;
import 采集.SpiderMethod.RootResult;
import 采集.SpiderMethod.ResultStatus;

public class JSON爬取 {

		@Test
		public void testGetResult() throws Exception {
			
			SpiderMethod spider = new SpiderMethod();
			String index_url="http://caiji.miaotk.com/index.php/home/fan/coupon_ajax";
			RootResult resultDetail=spider.getJSONResult(index_url, 2);
			
			if (resultDetail.Error == ResultStatus.Normal && StringUtils.isNotEmpty(resultDetail.html)) {
				JSONArray json = JSONArray.fromObject(resultDetail.html);
				List<MiAo> resultList = JSONArray.toList(json, MiAo.class);
				for(MiAo miao:resultList) {
					System.out.println(miao.getQuan_id());	
				}
		}
	}
}

