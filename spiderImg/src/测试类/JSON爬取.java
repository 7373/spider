package ������;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import Model.MiAo;
import net.sf.json.JSONArray;
import �ɼ�.SpiderMethod;
import �ɼ�.SpiderMethod.RootResult;
import �ɼ�.SpiderMethod.ResultStatus;

public class JSON��ȡ {

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

