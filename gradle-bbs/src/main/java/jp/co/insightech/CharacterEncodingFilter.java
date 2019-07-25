package jp.co.insightech;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * �����G���R�[�h�t�B���^�B
 */
public class CharacterEncodingFilter implements Filter {

	/**
	 * �G���R�[�h�w��̏������p�����[�^���B
	 */
	private static final String INIT_PARAMETER_ENCODING = "encoding";

	/**
	 * �f�t�H���g�̃G���R�[�h�B
	 */
	private static final String DEFAULT_ENCODING = "MS932";

	/**
	 * �����G���R�[�h�B
	 */
	private String encoding;

	/**
	 * �����������B
	 * �����p�����[�^�w��ɂ�蕶���G���R�[�h�����肵�܂��B
	 * 
	 * @throws ServletException
	 *             �����������s�����ꍇ�B
	 */
	public void init(FilterConfig config) throws ServletException {
		// �����G���R�[�h�w��
		this.encoding = config.getInitParameter(INIT_PARAMETER_ENCODING);
		if (this.encoding == null) {
			this.encoding = DEFAULT_ENCODING;
		}
	}

	public void destroy() {
		this.encoding = null;
	}

	/**
	 * ���N�G�X�g�̃G���R�[�f�B���O���Z�b�g����Ă��Ȃ��ꍇ�A
	 * �������p�����[�^ encoding �Ŏw�肳�ꂽ�G���R�[�f�B���O��
	 * ���N�G�X�g�ɃZ�b�g���܂��B
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

		// HTTP�v���ɕ����G���R�[�h�w�肪�Ȃ��ꍇ�̂�
		if (servletRequest.getCharacterEncoding() == null) {
			servletRequest.setCharacterEncoding(encoding);
		}
		chain.doFilter(servletRequest, servletResponse);
	}
}
