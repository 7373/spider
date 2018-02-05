package 采集;

import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 采集基类
 * @author Yien
 * @when 2017年12月20日 下午5:25:54 
 *
 *
 */
public class AbstractSpider {

	public static String getResult(String url) throws Exception {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				CloseableHttpResponse response = httpClient.execute(new HttpGetConfig(url))) {
			String result = EntityUtils.toString(response.getEntity());
			return result;
		} catch (Exception e) {
			System.out.println("获取失败");
			return "";
		}
	}
}

/**
 * 内部类，继承HttpGet，为了设置请求超时的参数
 * @author yien
 *
 */
class HttpGetConfig extends HttpGet {
	public HttpGetConfig(String url) {
		super(url);
		setDefaulConfig();
	}

	private void setDefaulConfig() {
		this.setConfig(RequestConfig.custom()
				.setConnectionRequestTimeout(10000)
				.setConnectTimeout(10000)
				.setSocketTimeout(10000).build());
	//	this.setHeader("User-Agent", "spider"); //imgs头
		//
		/**
		 * 常用头；
		 */
		this.setHeader(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
	}
}
