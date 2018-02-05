package ����;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import Model.Data;
import RootBean.JsonRootBean;
import net.sf.json.JSONArray;

public class JSONP���� {
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static List<Data> getBean(String jsonS) {
		JSONArray json = JSONArray.fromObject(jsonS);
		/**
		 * �����
		 */
		List<Data> all = null;
		List<JsonRootBean> resultList = JSONArray.toList(json, JsonRootBean.class);
		for (JsonRootBean ps : resultList) {
			List<Data> allData = ps.getData().getPageResult().getData();
			JSONArray obj = JSONArray.fromObject(allData);
			all = JSONArray.toList(obj, Model.Data.class);
		}
		return all;
	}

	public static String getJson(String jsonp) {
		String jsonto = StringUtils.substringBetween(jsonp, "(", ")");
		String json = "[" + jsonto + "]";
		return json;
	}

	public static void displayJson(List<Data> all) {
		System.out.println("��������----" + all.size());
		for (Data dt : all) {
			int status=dt.getBook_status();
			/**
			 * ����������������Data
			 */
			if(1==status) 
				continue;
			System.out.print("����---" + dt.getDomain_name());
			// if (null != dt.getReg_date())
				System.out.print("---ע��ʱ��---" + dt.getReg_date());
			System.out.println("---״̬---" + ( status== 1 ? "��Ԥ��" : "δԤ��"));
				
		}
	}
}
