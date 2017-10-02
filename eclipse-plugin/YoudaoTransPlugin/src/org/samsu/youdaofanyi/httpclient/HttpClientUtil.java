package org.samsu.youdaofanyi.httpclient;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jdom.Document;
import org.samsu.youdaofanyi.document.DocumentHelper;
import org.samsu.youdaofanyi.model.ResultModel;

public class HttpClientUtil {

	/**
	 * 调用有道翻译接口进行翻译
	 * 
	 * @param query
	 * @return
	 */
	public static Document getDocumentByQuery(String query) {
		// 有道翻译URL主域名
		String youdao = "fanyi.youdao.com";

		// 创建一个Http客户端
		HttpClient httpclient = new DefaultHttpClient();
		// 设置不允许循环重定向
		httpclient.getParams().setParameter(
				ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false);
		// 创建Http主机
		HttpHost targetHost = new HttpHost(youdao);
		// 把查询参数中的空格用%20替换
		query = query.replace(" ", "%20");
		// 创建一个Http GET请求
		HttpGet httpget = new HttpGet(
				"/openapi.do?keyfrom=Sam-Su&key=1825873072&type=data&doctype=xml&version=1.1&q="
						+ query);
		// 设置请求头
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
		httpget.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpget.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");

		HttpResponse response = null;
		InputStream content = null;
		HttpEntity entity = null;
		Document doc = null;
		try {
			// 发送请求
			response = httpclient.execute(targetHost, httpget);
			if (response != null) {
				entity = response.getEntity();
			}
			if (entity != null) {
				content = entity.getContent();
			}
			if (content != null) {
				// 获取响应内容
				doc = DocumentHelper.getDocument(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 关闭Http客户端
		httpclient.getConnectionManager().shutdown();
		return doc;
	}

	public static ResultModel convertDocToModel(Document doc) {
		return new ResultModel(doc);
	}

}
