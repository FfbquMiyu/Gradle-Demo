package jp.co.insightech.dao;

import java.util.Vector;

import jp.co.insightech.Message;

/**
 * MessageDao�̃C���^�[�t�F�[�X.
 * 
 * @author MiyuSuzuki
 *
 */
public interface MessageDao {
	
	/**
	 * ���e���b�Z�[�W��o�^���郁�\�b�h.
	 * 
	 * @param message �o�^���铊�e���b�Z�[�W
	 * @throws Exception
	 */
	public void registerMessage(Message message) throws Exception;
	
	/**
	 * ���e���b�Z�[�W���폜���郁�\�b�h.
	 *
	 * @param id ID
	 * @throws Exception
	 */
	public void deleteMessage(int id) throws Exception;
	
	/**
	 * ���e���b�Z�[�W�̈ꗗ���擾���郁�\�b�h.
	 * 
	 * @return ���e���b�Z�[�W�ꗗ
	 * @throws Exception
	 */
	public Vector getMessageList() throws Exception;
	
	/**
	 * ���e���b�Z�[�W��1���擾���郁�\�b�h.
	 * 
	 * @param id
	 * @return ���e���b�Z�[�W
	 * @throws Exception
	 */
	public Message getMessage(int id) throws Exception;
}
