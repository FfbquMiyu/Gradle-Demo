package jp.co.insightech.service.impl;

import java.util.Date;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.insightech.BBSInfoModel;
import jp.co.insightech.Message;
import jp.co.insightech.dao.MessageDao;
import jp.co.insightech.service.BBSService;

/**
 * Service�w�̎����N���X.
 * 
 * @author MiyuSuzuki
 *
 */
@Service
@Transactional
public class BBSServiceImpl implements BBSService {
	
	private MessageDao dao;
	
	@Autowired
	public void setMessageDao(MessageDao dao) {
		this.dao = dao;
	}
	
	/**
	 * ���e���b�Z�[�W��o�^���܂�.
	 * 
	 * @param bbsInfo ���e���
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void register(BBSInfoModel bbsInfo) throws Exception {
		Message message = new Message();
		message.setName(bbsInfo.getName());
		message.setMailaddress(bbsInfo.getMailaddress());
		message.setSubject(bbsInfo.getSubject());
		message.setContent(bbsInfo.getContent());
		message.setWordColor(bbsInfo.getWordColor());
		message.setPassword(bbsInfo.getPassword());
		message.setPostDate(new Date());
		
		dao.registerMessage(message);
	}
	
	/**
	 * ���e���b�Z�[�W���폜���܂�.
	 * 
	 * @param id ���eID
	 * @param password �p�X���[�h
	 * @return �p�X���[�h����v���A�폜���������s����Ƃ���true
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean delete(int id, String password) throws Exception {
		Message message = dao.getMessage(id);
		if (password.equals(message.getPassword())) {
			dao.deleteMessage(id);
			return true;
		}
		return false;
	}
	
	/**
	 * ���e���b�Z�[�W�̈ꗗ���擾���܂�.
	 * 
	 * @return ���e�ꗗ
	 * @throws Exception
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Vector<Message> getMessageList() throws Exception {
		return dao.getMessageList();
	}
}
