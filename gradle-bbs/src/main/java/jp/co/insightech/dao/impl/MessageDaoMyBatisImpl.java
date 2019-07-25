package jp.co.insightech.dao.impl;

import java.util.List;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.insightech.Message;
import jp.co.insightech.dao.MessageDao;

/**
 * データベースとやりとりを行うクラス.
 * 
 * @author MiyuSuzuki
 */
@Repository
public class MessageDaoMyBatisImpl implements MessageDao {
	
	@Autowired
	private SqlSession session;
	
	/**
	 * 投稿メッセージを登録します.
	 * 
	 * @param message 登録する投稿メッセージ
	 * @throws Exception
	 */
	public void registerMessage(Message message) throws Exception {
		message.setId(getNextId());
		session.insert("register", message);
	}
	
	/**
	 * 投稿メッセージを削除します.
	 *
	 * @param id ID
	 * @throws Exception
	 */
	public void deleteMessage(int id) throws Exception {
		session.delete("remove", id);
	}
	
	/**
	 * 投稿メッセージの一覧を取得します.
	 *
	 * @return 投稿メッセージの一覧
	 * @throws Exception
	 */
	public Vector getMessageList() throws Exception {
		List<Message> messageList = session.selectList("getMessageList");
		return new Vector(messageList);
	}
	
	/**
	 * 投稿メッセージを1件取得します.
	 *
	 * @param id ID
	 * @return 投稿メッセージ
	 * @throws Exception
	 */
	public Message getMessage(int id) throws Exception {
		return session.selectOne("getMessage", id);
	}
	
	/**
	 * 登録したいメッセージのIDを取得します.
	 * 
	 * @return ID
	 * @throws Exception
	 */
	private Integer getNextId() throws Exception {
		Integer maxId = session.selectOne("maxId");
		int nextId = 0;
		if (maxId != null) { 
			nextId = maxId + 1;
		}
		return nextId;
	}
}
