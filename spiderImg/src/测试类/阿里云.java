package ������;

import java.util.List;

import org.junit.Test;

import Model.Data;
import ����.JSONP����;
import �ɼ�.SpiderMethod;

/**
 * ������
 * 
 * @author Yien
 * @when 2017��12��4�� ����2:54:25
 */
public class ������ {
	@Test
	public void testGetResult() throws Exception {
		SpiderMethod spider = new SpiderMethod();

		/**
		 * 1�������� 4�ַ�
		 */
		String ali1 = "https://domainapi.aliyun.com/preDelete/search.jsonp?keyWord=&excludeKeyWord=&keywordAsPrefix=false&keywordAsSuffix=false&exKeywordAsPrefix=false&exKeywordAsSuffix=false&constitute=1204&suffix=com%2Ccn&endDate=&regDate=&pageSize=50&currentPage=1&bookStatus=&sortBy=2&sortType=1&_t=self&minLength=&maxLength=&minPrice=&maxPrice=&token=tdomain-aliyun-com%3AY4809a09c080a77ea633edad36074b82b&callback=jsonp_1513761578861_25108";
		/**
		 * 2�������� ˫ƴ
		 */
		String ali2 = "https://domainapi.aliyun.com/preDelete/search.jsonp?keyWord=&excludeKeyWord=&keywordAsPrefix=false&keywordAsSuffix=false&exKeywordAsPrefix=false&exKeywordAsSuffix=false&constitute=1207002&suffix=cn%2Ccom&endDate=&regDate=&pageSize=50&currentPage=1&bookStatus=&sortBy=2&sortType=1&_t=self&minLength=&maxLength=&minPrice=&maxPrice=&token=tdomain-aliyun-com%3Aundefined&callback=jsonp_1513757204660_94228";
		/**
		 * ��ȡjsonp��
		 */
		String jsonp = spider.getAliJSON(ali2);
		/**
		 * ��ȡjson�ַ�����
		 */
		String json = JSONP����.getJson(jsonp);
		/**
		 * jsonת��POJO
		 */
		List<Data> allData = JSONP����.getBean(json);
		/**
		 * ��ӡbean����
		 */
		JSONP����.displayJson(allData);

	}
}