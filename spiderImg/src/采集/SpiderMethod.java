package 采集;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 2017/11/30
 * 
 * @author Administrator
 *
 */
public class SpiderMethod {
	/**
	 * http默认编码
	 */
	protected static final String default_charset = "UTF-8";

	/**
	 * 获取简单页面
	 * 
	 * @author Yien
	 * @when 2017年12月4日 下午4:26:56
	 *
	 *
	 * @param url
	 * @throws Exception
	 */

	public void getHtml(String url) throws Exception {
		// Document doc=Jsoup.connect(url).get();
		// System.out.println(doc.baseUri());
		String result = AbstractSpider.getResult(getBaseUrl(url));
		Document document = Jsoup.parse(result);
		System.out.println(document.html());

	}

	/**
	 * 爬取图片
	 * 
	 * @author Yien
	 * @when 2017年12月27日 下午4:05:50
	 *
	 *
	 * @param url
	 * @throws Exception
	 */

	public void getImgs(String url) throws Exception {

		// 获取工具类返回的html,并用Jsoup解析
		String result = AbstractSpider.getResult(url);
		Document document = Jsoup.parse(result);
		document.setBaseUri(url);
		// 获取所有的img元素
		Elements elements = document.select("img");
		for (Element e : elements) {
			// 获取每个src的绝对路径
			String src = e.absUrl("src");
			URL urlSource = new URL(src);
			URLConnection urlConnection = urlSource.openConnection();
			String imageName = src.substring(src.lastIndexOf("/") + 1, src.length());
			System.out.println(e.absUrl("src"));

			// 通过URLConnection得到一个流，将图片写到流中，并且新建文件保存
			/*
			 * InputStream in = urlConnection.getInputStream(); OutputStream out = new
			 * FileOutputStream(new File("D:\\java\\workSpace\\spiderImg\\image",
			 * imageName)); byte[] buf = new byte[1024]; int l = 0; while ((l =
			 * in.read(buf)) != -1) { out.write(buf, 0, l); }
			 */
		}
	}

	/**
	 * 爬取阿里云的网站JSONP域名信息
	 */

	public String getAliJSON(String url) throws Exception {
		String resultJson = null;
		HttpGet request = new HttpGet(url);

		request.setHeader(HttpHeaders.AUTHORIZATION, "domainapi.aliyun.com");
		request.setHeader(HttpHeaders.CONTENT_TYPE, "application/javascript;charset=UTF-8");

		request.setHeader(HttpHeaders.ACCEPT, "*/*");
		request.setHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");

		request.setHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN");
		request.setHeader(HttpHeaders.USER_AGENT,
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)");

		request.setHeader(HttpHeaders.REFERER, "https://wanwang.aliyun.com/domain/reserve?");
		resultJson = getSourceHtml(request, "UTF-8", Long.parseLong("1000")).html;
		System.out.println(resultJson);
		// if (StringUtils.isNotBlank(resultJson))
		// return resultJson;
		return resultJson;
	}

	/**
	 * httpclient post请求
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void post(String url) throws Exception {

		HttpPost request = new HttpPost(url);
		// PostMethod postMethod = new PostMethod(url);
		/**
		 * 设置表单提交
		 */
		List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
		pairList.add(new BasicNameValuePair("p", "1"));
		// pairList.add(new BasicNameValuePair("pass", "123456"));
		request.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
		// request.set
		// request.setHeader(HttpHeaders.AUTHORIZATION, "s.click.taobao.com");
		// request.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urle");
		// request.setHeader("Cookie","PHPSESSID=14f59qo2hhoeucab72ioidklc5");
		// request.setHeader(HttpHeaders.REFERER, "http://caiji.miaotk.com/");
		// System.out.println(Arrays.toString(GetHtml(request, "UTF-8",
		// Long.parseLong("1000")).heads));
		System.out.println(getSourceHtml(request, "UTF-8", Long.parseLong("1000")).html);
	}

	/**
	 * 爬取源html
	 * 
	 * @author Yien
	 * @when 2017年12月27日 下午4:07:03
	 *
	 *
	 * @param request
	 * @param charset
	 * @param waitTime
	 * @return
	 */
	public HtmlData getSourceHtml(HttpUriRequest request, String charset, Long waitTime) {
		HtmlData result = new HtmlData();
		System.out.println("请求头---" + request);
		CloseableHttpResponse response = null;
		try {

			HttpContext context = new BasicHttpContext();
			// 这句不加报空指针
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			/**
			 * 设置代理
			 */
			// if(socksProxy){
			// InetSocketAddress socksaddr = new InetSocketAddress(proxyHost, proxyPort);
			// context.setAttribute(proxyKey, socksaddr);
			// }
			// response = httpClient.execute(request, context);
			/**
			 * 获取响应
			 */
			response = httpClient.execute(request);
			if (null != waitTime && waitTime > 0l) {
				try {
					Thread.sleep(waitTime);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println("----response 已获得数据！正在解析---");
				result.heads = response.getAllHeaders();
				result.html = EntityUtils.toString(entity, charset);
				Object req = context.getAttribute(HttpCoreContext.HTTP_REQUEST);
				if (req instanceof HttpUriRequest) {
					HttpUriRequest realRequest = (HttpUriRequest) req;
					result.url = realRequest.getURI().toString();
				} else {
					result.url = request.getURI().toString();
				}
			}
			// if (maintainCookie) {
			// RestoreCookie(response);
			// }
			/**
			 * 释放持有的所有资源 httpEntity
			 */
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			result.url = request.getURI().toString();
		} catch (NoHttpResponseException e) {
			result.url = request.getURI().toString();
		} catch (SocketTimeoutException e) {
			result.url = request.getURI().toString();
		} catch (IOException e) {
			result.url = request.getURI().toString();
		}

		finally {
		}
		if (response != null) {
			try {
				response.close();
			} catch (IOException e) {
				System.out.println("----response 关闭错误！---");
			}
		}

		return result;
	}

	/**
	 * 不递归 获取跳转链接
	 * 
	 * @author Yien
	 * @when 2017年12月20日 下午5:27:13
	 *
	 *
	 * @param ul
	 * @return
	 * @throws Exception
	 */
	public String getBaseUrl(String ul) throws Exception {
		URL url = new URL(ul);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.getResponseCode();
		String realUrl = conn.getURL().toString();
		conn.disconnect();
		System.out.println("真实URL:" + realUrl);
		return realUrl;
	}

	/**
	 * 
	 * @author Yien
	 * @when 2018年1月4日 下午3:40:06
	 *
	 *       爬取的JSON数据
	 * @param index_mask
	 * @param pageNO
	 * @return
	 */
	public RootResult getJSONResult(String index_mask, int pageNO) {

		String pageNo = String.valueOf(pageNO);
		if (StringUtils.isNotBlank(pageNo)) {
			RootResult result = new RootResult();
			HttpPost request = new HttpPost(index_mask);
			if (!"1".equals(pageNo)) {
				List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
				pairList.add(new BasicNameValuePair("p", pageNo));
				try {
					request.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			}
			// request.setHeader(HttpHeaders.REFERER, index_referer);
			HtmlData html = getSourceHtml(request, default_charset, 0l);
			if (html.error == ResultStatus.Normal) {
				result.html = html.html;
				result.Error = ResultStatus.Normal;
			} else {
				result.Error = html.error;
			}

			return result;
		}
		return null;

	}

	/**
	 * 爬取结果类基类
	 *
	 */
	public static class RootResult {

		/**
		 * 结果代码
		 */
		public int Error;

		/**
		 * 结果账号标识
		 */
		public String html;

		/** jsoup doc */
		public Document doc;
	}

	/**
	 * 任务爬取结果状态
	 * 
	 * @author dijun
	 *
	 */
	public static class ResultStatus {

		/**
		 * success
		 */
		public static final int Normal = 0;

		/**
		 * success and finished
		 */
		public static final int Finished = 1;

		/**
		 * cookie wrong, need retry
		 */
		public static final int NeedTry = -5;

		/**
		 * account wrong, give up
		 */
		public static final int NotExists = -3;

		/**
		 * ip banned, need delay
		 */
		public static final int IpBaned = -10;

		/**
		 * nserver busy, delay and retry
		 */
		public static final int ServerBusy = -20;

		/**
		 * no data,pass
		 */
		public static final int NoData = -8;

		/**
		 * net error or html error
		 */
		public static final int OtherError = -2;

	}

	/**
	 * 源页面结果
	 * Html数据结构（内部类）
	 * 
	 * @author Yien
	 * @when 2017年12月20日 下午5:27:33
	 *
	 *
	 */
	public static class HtmlData {
		public String html;
		public Header[] heads;
		public String url;
		public int error;
	}
}
