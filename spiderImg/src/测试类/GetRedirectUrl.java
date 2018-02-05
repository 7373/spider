package ������;

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
		 * 0���Ա���������ת�Ա�����
		 */
		// �ݹ飻
		String tu = getRedirectUrl(url);
		;
		System.out.println("tu����" + tu);
		// ����tu��������
		// System.out.println(tu.substring(tu.indexOf("tu=")+3));
		String jiemiUrl2 = unescape(tu.substring(tu.indexOf("tu=") + 3));
		System.out.println("tu������������" + jiemiUrl2);
		String jiemiUrl = "https://s.click.taobao.com/t?e=m%3D2%26s%3DshhycMgyX5YcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67gtlG6lwPBS0UkCu4LW4fHWggE%2B81xYdaYgwvAJKzKRhR0QUH89hs6odoD3G8jExAxAqvUhwaPOiypAbhWWbtazmAClNJkpsCzHrKHhQWCLYttvJ9Z4G1Zg%3D&pvid=10_183.135.6.227_21727_1512373605774&sc=c2Z10Xw&ref=&et=nQZarJKAELgKLFmnQOOdGhGI9MyeD0rm";
		// String jiemiUrl =
		// "https://s.click.taobao.com/t?e=m%3D2%26s%3DshhycMgyX5YcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67gtlG6lwPBS0UkCu4LW4fHWggE%2B81xYdaYgwvAJKzKRhR0QUH89hs6odoD3G8jExAxAqvUhwaPOiypAbhWWbtazmAClNJkpsCzHrKHhQWCLYttvJ9Z4G1Zg%3D&pvid=10_183.135.6.227_21727_1512373605774&sc=c2Z10Xw&ref=&et=XDdI5IoTDHiv%2FPC6VebP6npa%2BXvuZjy1";
		// // �����ȡ��tu��������)��
		System.out.println("�Ա�����:" + getBaseUrl(jiemiUrl2, tu));

		/**
		 * 1����ͨ����ת��ȡ���ݹ麯����
		 */

		// System.out.println("�ݹ�����:"+getRedirectUrl(url));
		/**
		 * 2�����ݹ��ȡ
		 */
		// System.out.println("�ǵݹ�����:"+getUrl(url));

		/**
		 * ���ܡ�����
		 */
		// String path1 =
		// "https%3a%2f%2fs.click.taobao.com%2ft%3fe%3dm%253D2%2526s%253DshhycMgyX5YcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67gtlG6lwPBS0UkCu4LW4fHWggE%252B81xYdaYgwvAJKzKRhR0QUH89hs6odoD3G8jExAxAqvUhwaPOiypAbhWWbtazmAClNJkpsCzHrKHhQWCLYttvJ9Z4G1Zg%253D%26pvid%3d10_183.135.6.227_21727_1512373605774%26sc%3dc2Z10Xw%26ref%3d%26et%3dXDdI5IoTDHiv%252FPC6VebP6npa%252BXvuZjy1";
		//
		// String path2 =
		// "https://s.click.taobao.com/t?e=m%3D2%26s%3DshhycMgyX5YcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67gtlG6lwPBS0UkCu4LW4fHWggE%2B81xYdaYgwvAJKzKRhR0QUH89hs6odoD3G8jExAxAqvUhwaPOiypAbhWWbtazmAClNJkpsCzHrKHhQWCLYttvJ9Z4G1Zg%3D&pvid=10_183.135.6.227_21727_1512373605774&sc=c2Z10Xw&ref=&et=XDdI5IoTDHiv%2FPC6VebP6npa%2BXvuZjy1";
		// System.out.println("���ܺ�" + unescape(path1));
		// System.out.println("��ȷֵ��" + path2);
	}

	/**
	 * �ݹ��ȡ�ض����ַ
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
		 * �ݹ��ҵ����յ�url(����location)
		 */
		if (conn.getHeaderField("location") == null)
			return needRedirectUrl;
		else
			return getRedirectUrl(conn.getHeaderField("location"));
		// }

		/**
		 * ������
		 */
		// //����һ��http����
		// HttpClient client = new HttpClient();
		// //��header������ʽ�����ٷ���ֵ��һЩ�Ǳ�Ҫ��ȡ����Ϣ
		// HttpMethod method = new HeadMethod(url);
		// HttpParams params = client.getParams();
		// params.setParameter(AllClientPNames.HANDLE_REDIRECTS, false);
		// client.executeMethod(method);
		// //��ȡ����
		// url = method.getURI().getURI();
		// //�ͷ����ӣ���Ҫ��
		// method.releaseConnection();
	}

	/**
	 * ���ݹ��ȡ��ת����
	 * 
	 * @author Yien
	 * @when 2017��12��5�� ����4:09:47
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
	 * �Ա������������ת�Ա�����
	 * 
	 * @author Yien
	 * @when 2017��12��5�� ����4:03:40
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
		 * tuΪ����location������url
		 */
		// String tu =
		// "https://s.click.taobao.com/t_js?tu=https%3A%2F%2Fs.click.taobao.com%2Ft%3Fe%3Dm%253D2%2526s%253DshhycMgyX5YcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67gtlG6lwPBS0UkCu4LW4fHWggE%252B81xYdaYgwvAJKzKRhR0QUH89hs6odoD3G8jExAxAqvUhwaPOiypAbhWWbtazmAClNJkpsCzHrKHhQWCLYttvJ9Z4G1Zg%253D%26pvid%3D10_183.135.6.227_21727_1512373605774%26sc%3Dc2Z10Xw%26ref%3D%26et%3DXDdI5IoTDHiv%252FPC6VebP6npa%252BXvuZjy1";
		/**
		 * ����������
		 */
		conn.setRequestProperty(HttpHeaders.REFERER, tu);
		// System.out.println(conn.getURL());
		return getRedirectUrl(conn.getHeaderField("Location"));

	}

	/**
	 * URL����
	 * 
	 * @author Yien
	 * @when 2017��12��5�� ����4:26:51
	 *
	 *
	 * @param str
	 * @return
	 */

	public String unescape(String str) {

		try {
			// ��application/x-www-from-urlencoded�ַ���ת������ͨ�ַ���
			return URLDecoder.decode(str, "GBK");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Url����
	 * 
	 * @author Yien
	 * @when 2017��12��5�� ����4:27:04
	 *
	 *
	 * @param str
	 * @return
	 */
	public String escape(String str) {

		try {
			// ����ͨ�ַ���ת����application/x-www-from-urlencoded�ַ���
			return URLEncoder.encode(str, "GBK");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
