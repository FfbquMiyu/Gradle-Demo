package jp.co.insightech.dao;

import java.util.Vector;

import jp.co.insightech.Message;

/**
 * MessageDaoのインターフェース.
 * 
 * @author MiyuSuzuki
 *
 */
public interface MessageDao {
	
	/**
	 * 投稿メッセージを登録するメソッド.
	 * 
	 * @param message 登録する投稿メッセージ
	 * @throws Exception
	 */
	public void registerMessage(Message message) throws Exception;
	
	/**
	 * 投稿メッセージを削除するメソッド.
	 *
	 * @param id ID
	 * @throws Exception
	 */
	public void deleteMessage(int id) throws Exception;
	
	/**
	 * 投稿メッセージの一覧を取得するメソッド.
	 * 
	 * @return 投稿メッセージ一覧
	 * @throws Exception
	 */
	public Vector getMessageList() throws Exception;
	
	/**
	 * 投稿メッセージを1件取得するメソッド.
	 * 
	 * @param id
	 * @return 投稿メッセージ
	 * @throws Exception
	 */
	public Message getMessage(int id) throws Exception;
}
