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
 * Service層の実装クラス.
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
	 * 投稿メッセージを登録します.
	 * 
	 * @param bbsInfo 投稿情報
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
	 * 投稿メッセージを削除します.
	 * 
	 * @param id 投稿ID
	 * @param password パスワード
	 * @return パスワードが一致し、削除処理を実行するときにtrue
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
	 * 投稿メッセージの一覧を取得します.
	 * 
	 * @return 投稿一覧
	 * @throws Exception
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Vector<Message> getMessageList() throws Exception {
		return dao.getMessageList();
	}
}
