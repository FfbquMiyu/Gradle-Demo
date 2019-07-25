package jp.co.insightech;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 文字エンコードフィルタ。
 */
public class CharacterEncodingFilter implements Filter {

	/**
	 * エンコード指定の初期化パラメータ名。
	 */
	private static final String INIT_PARAMETER_ENCODING = "encoding";

	/**
	 * デフォルトのエンコード。
	 */
	private static final String DEFAULT_ENCODING = "MS932";

	/**
	 * 文字エンコード。
	 */
	private String encoding;

	/**
	 * 初期化処理。
	 * 初期パラメータ指定により文字エンコードを決定します。
	 * 
	 * @throws ServletException
	 *             初期化が失敗した場合。
	 */
	public void init(FilterConfig config) throws ServletException {
		// 文字エンコード指定
		this.encoding = config.getInitParameter(INIT_PARAMETER_ENCODING);
		if (this.encoding == null) {
			this.encoding = DEFAULT_ENCODING;
		}
	}

	public void destroy() {
		this.encoding = null;
	}

	/**
	 * リクエストのエンコーディングがセットされていない場合、
	 * 初期化パラメータ encoding で指定されたエンコーディングを
	 * リクエストにセットします。
	 * 
	 * @param servletRequest
	 *            ServletRequest
	 * @param servletResponse
	 *            ServletResponse
	 * @param chain
	 *            FilterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		// HTTP要求に文字エンコード指定がない場合のみ
		if (servletRequest.getCharacterEncoding() == null) {
			servletRequest.setCharacterEncoding(encoding);
		}
		chain.doFilter(servletRequest, servletResponse);
	}
}
