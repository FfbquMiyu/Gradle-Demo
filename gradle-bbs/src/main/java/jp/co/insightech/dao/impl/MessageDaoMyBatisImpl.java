package jp.co.insightech.dao.impl;

import java.util.List;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.insightech.Message;
import jp.co.insightech.dao.MessageDao;

/**
 * �f�[�^�x�[�X�Ƃ��Ƃ���s���N���X.
 * 
 * @author MiyuSuzuki
 */
@Repository
public class MessageDaoMyBatisImpl implements MessageDao {
	
	@Autowired
	private SqlSession session;
	
	/**
	 * ���e���b�Z�[�W��o�^���܂�.
	 * 
	 * @param message �o�^���铊�e���b�Z�[�W
	 * @throws Exception
	 */
	public void registerMessage(Message message) throws Exception {
		message.setId(getNextId());
		session.insert("register", message);
	}
	
	/**
	 * ���e���b�Z�[�W���폜���܂�.
	 *
	 * @param id ID
	 * @throws Exception
	 */
	public void deleteMessage(int id) throws Exception {
		session.delete("remove", id);
	}
	
	/**
	 * ���e���b�Z�[�W�̈ꗗ���擾���܂�.
	 *
	 * @return ���e���b�Z�[�W�̈ꗗ
	 * @throws Exception
	 */
	public Vector getMessageList() throws Exception {
		List<Message> messageList = session.selectList("getMessageList");
		return new Vector(messageList);
	}
	
	/**
	 * ���e���b�Z�[�W��1���擾���܂�.
	 *
	 * @param id ID
	 * @return ���e���b�Z�[�W
	 * @throws Exception
	 */
	public Message getMessage(int id) throws Exception {
		return session.selectOne("getMessage", id);
	}
	
	/**
	 * �o�^���������b�Z�[�W��ID���擾���܂�.
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
