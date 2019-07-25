package jp.co.insightech.controller;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.insightech.BBSInfoModel;
import jp.co.insightech.service.BBSService;

/**
 * �f���R���g���[��.
 * 
 * @author MiyuSuzuki
 */
@Controller
public class BBSController {
	
	private BBSService service;
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param service �T�[�r�X�w�C���X�^���X
	 */
	@Autowired
	public void setBBSService(BBSService service) {
		this.service = service;
	}
	
	/**
	 * /show �ɃA�N�Z�X���ꂽ�ۂɌĂяo����郁�\�b�h.
	 * 
	 * @param bbsInfo �f�����
	 * @return bbs.jsp���Ăяo��ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(path = "/show")
	public ModelAndView show(@ModelAttribute BBSInfoModel bbsInfo)
			throws Exception {
		bbsInfo.addMessageList(this.service.getMessageList());
		if (!bbsInfo.hasErrors()) {
			bbsInfo.setName("");
			bbsInfo.setMailaddress("");
			bbsInfo.setSubject("");
			bbsInfo.setContent("");
			bbsInfo.setWordColor("");
			bbsInfo.setPassword("");
		}
		return new ModelAndView("bbs");
	}
	
	/**
	 * �o�^�{�^���������ꂽ�Ƃ��ɌĂяo����郁�\�b�h.
	 * 
	 * @param bbsInfo ���e���
	 * @return bbs.jsp���Ăяo��ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(path = "/show", params = "sendMessage")
	public ModelAndView register(@ModelAttribute BBSInfoModel bbsInfo) 
			throws Exception {
		// �G���[���b�Z�[�W���x�N�g���ɑ}�����܂�
		if (bbsInfo.getName().isEmpty()) {
			bbsInfo.addErrorMessage("���O�����͂���Ă��܂���B");
		}
		if (bbsInfo.getName().length() > 20) {
			bbsInfo.addErrorMessage("���O�̓��͏����20�����ł��B");
		}
		if (bbsInfo.getMailaddress().length() > 254) {
			bbsInfo.addErrorMessage("���[���A�h���X�̓��͏����254�����ł��B");
		}
		if (bbsInfo.getSubject().length() > 40) {
			bbsInfo.addErrorMessage("�����̓��͏����40�����ł��B");
		}
		if (bbsInfo.getContent().isEmpty()) {
			bbsInfo.addErrorMessage("���b�Z�[�W�����͂���Ă��܂���B");
		}
		if (bbsInfo.getContent().length() > 400) {
			bbsInfo.addErrorMessage("���b�Z�[�W�̓��͏����400�����ł��B");
		}
		if (bbsInfo.getPassword().isEmpty()) {
			bbsInfo.addErrorMessage("�p�X���[�h�����͂���Ă��܂���B");
		}
		if (bbsInfo.getPassword().length() > 20) {
			bbsInfo.addErrorMessage("�p�X���[�h�̓��͏����20�����ł��B");
		}
		if (!bbsInfo.hasErrors()) {
			this.service.register(bbsInfo);
			bbsInfo.setName("");
			bbsInfo.setMailaddress("");
			bbsInfo.setSubject("");
			bbsInfo.setContent("");
			bbsInfo.setWordColor("");
			bbsInfo.setPassword("");
		}
		// �f�[�^�x�[�X�ɂ���f�[�^��S���擾���܂�
		Vector messageList = this.service.getMessageList();

		// JSP�ɏo�������x�N�g���E�������ModelAttrbute�ɃZ�b�g���܂�
		bbsInfo.addMessageList(this.service.getMessageList());

		return new ModelAndView("bbs");
	}
	
	/**
	 * �폜�{�^���������ꂽ�Ƃ��ɌĂяo����郁�\�b�h.
	 *
	 * @param bbsInfo ���e���
	 * @param deleteId �폜���������e��ID
	 * @param request ���N�G�X�g
	 * @return bbs.jsp���Ăяo��ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(path = "/show", params = "deleteId")
	public ModelAndView delete(@ModelAttribute BBSInfoModel bbsInfo, int deleteId, 
			HttpServletRequest request) throws Exception {
		// ���e���b�Z�[�W�̈ꗗ����I�����ꂽ���b�Z�[�W���폜���܂�= ;
		String sendPassword = request.getParameter("sendPassword" + deleteId);
		if (!this.service.delete(deleteId, sendPassword)) {
			bbsInfo.addErrorMessage("�p�X���[�h���Ⴂ�܂��B");
		}
		// ���e�ꗗ��ModelAttrbute�ɃZ�b�g���܂�
		bbsInfo.addMessageList(this.service.getMessageList());

		return new ModelAndView("bbs");
	}
}
