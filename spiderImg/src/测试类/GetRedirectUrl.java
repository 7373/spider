package 测试类;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.params.HttpParams;
import org.junit.Assert;
import org.junit.Test;

public class GetRedirectUrl {
	@Test
	public void test_getRedirectUrl() throws Exception {
		// String
		// url="http://www.baidu.com/link?url=ByBJLpHsj5nXx6DESXbmMjIrU5W4Eh0yg5wCQpe3kCQMlJK_RJBmdEYGm0DDTCoTDGaz7rH80gxjvtvoqJuYxK";
		String url = "http://cu.manmanbuy.com/goto_261499.aspx";
		String expectUrl = "http://www.zhihu.com/question/20583607/answer/16597802";
		/**
		 * 0、淘宝客链接跳转淘宝链接
		 */
		// 递归；
		String tu = getRedirectUrl(url);
		;
		System.out.println("tu链接" + tu);
		// 解密tu参数链接
		// System.out.println(tu.substring(tu.indexOf("tu=")+3));
		String jiemiUrl2 = unescape(tu.substring(tu.indexOf("tu=") + 3));
		System.out.println("tu参数解密链接" + jiemiUrl2);
		String jiemiUrl = "https://s.click.taobao.com/t?e=m%3D2%26s%3DshhycMgyX5YcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67gtlG6lwPBS0UkCu4LW4fHWggE%2B81xYdaYgwvAJKzKRhR0QUH89hs6odoD3G8jExAxAqvUhwaPOiypAbhWWbtazmAClNJkpsCzHrKHhQWCLYttvJ9Z4G1Zg%3D&pvid=10_183.135.6.227_21727_1512373605774&sc=c2Z10Xw&ref=&et=nQZarJKAELgKLFmnQOOdGhGI9MyeD0rm";
		// String jiemiUrl =
		// "https://s.click.taobao.com/t?e=m%3D2%26s%3DshhycMgyX5YcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67gtlG6lwPBS0UkCu4LW4fHWggE%2B81xYdaYgwvAJKzKRhR0QUH89hs6odoD3G8jExAxAqvUhwaPOiypAbhWWbtazmAClNJkpsCzHrKHhQWCLYttvJ9Z4G1Zg%3D&pvid=10_183.135.6.227_21727_1512373605774&sc=c2Z10Xw&ref=&et=XDdI5IoTDHiv%2FPC6VebP6npa%2BXvuZjy1";
		// // 请求获取（tu参数链接)；
		System.out.println("淘宝链接:" + getBaseUrl(jiemiUrl2, tu));

		/**
		 * 1、普通的跳转获取（递归函数）
		 */

		// System.out.println("递归链接:"+getRedirectUrl(url));
		/**
		 * 2、不递归获取
		 */
		// System.out.println("非递归链接:"+getUrl(url));

		/**
		 * 加密、解密
		 */
		// String path1 =
		// "https%3a%2f%2fs.click.taobao.com%2ft%3fe%3dm%253D2%2526s%253DshhycMgyX5YcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67gtlG6lwPBS0UkCu4LW4fHWggE%252B81xYdaYgwvAJKzKRhR0QUH89hs6odoD3G8jExAxAqvUhwaPOiypAbhWWbtazmAClNJkpsCzHrKHhQWCLYttvJ9Z4G1Zg%253D%26pvid%3d10_183.135.6.227_21727_1512373605774%26sc%3dc2Z10Xw%26ref%3d%26et%3dXDdI5IoTDHiv%252FPC6VebP6npa%252BXvuZjy1";
		//
		// String path2 =
		// "https://s.click.taobao.com/t?e=m%3D2%26s%3DshhycMgyX5YcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67gtlG6lwPBS0UkCu4LW4fHWggE%2B81xYdaYgwvAJKzKRhR0QUH89hs6odoD3G8jExAxAqvUhwaPOiypAbhWWbtazmAClNJkpsCzHrKHhQWCLYttvJ9Z4G1Zg%3D&pvid=10_183.135.6.227_21727_1512373605774&sc=c2Z10Xw&ref=&et=XDdI5IoTDHiv%2FPC6VebP6npa%2BXvuZjy1";
		// System.out.println("解密后：" + unescape(path1));
		// System.out.println("正确值：" + path2);
	}

	/**
	 * 递归获取重定向地址
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */

	private String getRedirectUrl(String needRedirectUrl) throws Exception {

		HttpURLConnection conn = (HttpURLConnection) new URL(needRedirectUrl).openConnection();
		conn.setInstanceFollowRedirects(false);
		// conn.setConnectTimeout(8000);
		/**
		 * 递归找到最终的url(包含location)
		 */
		if (conn.getHeaderField("location") == null)
			return needRedirectUrl;
		else
			return getRedirectUrl(conn.getHeaderField("location"));
		// }

		/**
		 * 方法二
		 */
		// //创建一个http请求
		// HttpClient client = new HttpClient();
		// //用header的请求方式，减少返回值和一些非必要获取的信息
		// HttpMethod method = new HeadMethod(url);
		// HttpParams params = client.getParams();
		// params.setParameter(AllClientPNames.HANDLE_REDIRECTS, false);
		// client.executeMethod(method);
		// //获取链接
		// url = method.getURI().getURI();
		// //释放链接（重要）
		// method.releaseConnection();
	}

	/**
	 * 不递归获取跳转链接
	 * 
	 * @author Yien
	 * @when 2017年12月5日 下午4:09:47
	 *
	 *
	 * @param path
	 * @return
	 * @throws Exception
	 */

	private String getUrl(String path) throws Exception {

		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.getResponseCode();
		conn.setInstanceFollowRedirects(true);
		String realUrl = conn.getURL().toString();
		conn.disconnect();
		// System.out.println(realUrl);
		return realUrl;
	}

	/**
	 * 淘宝客链接最后跳转淘宝链接
	 * 
	 * @author Yien
	 * @when 2017年12月5日 下午4:03:40
	 *
	 *
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String getBaseUrl(String redirectUrl, String tu) throws Exception {
		HttpURLConnection conn = (HttpURLConnection) new URL(redirectUrl).openConnection();
		conn.setInstanceFollowRedirects(false);
		// conn.setConnectTimeout(8000);
		/**
		 * tu为包含location的最终url
		 */
		// String tu =
		// "https://s.click.taobao.com/t_js?tu=https%3A%2F%2Fs.click.taobao.com%2Ft%3Fe%3Dm%253D2%2526s%253DshhycMgyX5YcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67gtlG6lwPBS0UkCu4LW4fHWggE%252B81xYdaYgwvAJKzKRhR0QUH89hs6odoD3G8jExAxAqvUhwaPOiypAbhWWbtazmAClNJkpsCzHrKHhQWCLYttvJ9Z4G1Zg%253D%26pvid%3D10_183.135.6.227_21727_1512373605774%26sc%3Dc2Z10Xw%26ref%3D%26et%3DXDdI5IoTDHiv%252FPC6VebP6npa%252BXvuZjy1";
		/**
		 * 设置请求行
		 */
		conn.setRequestProperty(HttpHeaders.REFERER, tu);
		// System.out.println(conn.getURL());
		return getRedirectUrl(conn.getHeaderField("Location"));

	}

	/**
	 * URL解密
	 * 
	 * @author Yien
	 * @when 2017年12月5日 下午4:26:51
	 *
	 *
	 * @param str
	 * @return
	 */

	public String unescape(String str) {

		try {
			// 将application/x-www-from-urlencoded字符串转换成普通字符串
			return URLDecoder.decode(str, "GBK");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Url加密
	 * 
	 * @author Yien
	 * @when 2017年12月5日 下午4:27:04
	 *
	 *
	 * @param str
	 * @return
	 */
	public String escape(String str) {

		try {
			// 将普通字符创转换成application/x-www-from-urlencoded字符串
			return URLEncoder.encode(str, "GBK");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
