package 测试类;

import java.util.List;

import org.junit.Test;

import Model.Data;
import 解析.JSONP解析;
import 采集.SpiderMethod;

/**
 * 测试类
 * 
 * @author Yien
 * @when 2017年12月4日 下午2:54:25
 */
public class 阿里云 {
	@Test
	public void testGetResult() throws Exception {
		SpiderMethod spider = new SpiderMethod();

		/**
		 * 1、阿里云 4字符
		 */
		String ali1 = "https://domainapi.aliyun.com/preDelete/search.jsonp?keyWord=&excludeKeyWord=&keywordAsPrefix=false&keywordAsSuffix=false&exKeywordAsPrefix=false&exKeywordAsSuffix=false&constitute=1204&suffix=com%2Ccn&endDate=&regDate=&pageSize=50&currentPage=1&bookStatus=&sortBy=2&sortType=1&_t=self&minLength=&maxLength=&minPrice=&maxPrice=&token=tdomain-aliyun-com%3AY4809a09c080a77ea633edad36074b82b&callback=jsonp_1513761578861_25108";
		/**
		 * 2、阿里云 双拼
		 */
		String ali2 = "https://domainapi.aliyun.com/preDelete/search.jsonp?keyWord=&excludeKeyWord=&keywordAsPrefix=false&keywordAsSuffix=false&exKeywordAsPrefix=false&exKeywordAsSuffix=false&constitute=1207002&suffix=cn%2Ccom&endDate=&regDate=&pageSize=50&currentPage=1&bookStatus=&sortBy=2&sortType=1&_t=self&minLength=&maxLength=&minPrice=&maxPrice=&token=tdomain-aliyun-com%3Aundefined&callback=jsonp_1513757204660_94228";
		/**
		 * 获取jsonp；
		 */
		String jsonp = spider.getAliJSON(ali2);
		/**
		 * 获取json字符串；
		 */
		String json = JSONP解析.getJson(jsonp);
		/**
		 * json转成POJO
		 */
		List<Data> allData = JSONP解析.getBean(json);
		/**
		 * 打印bean数据
		 */
		JSONP解析.displayJson(allData);

	}
}