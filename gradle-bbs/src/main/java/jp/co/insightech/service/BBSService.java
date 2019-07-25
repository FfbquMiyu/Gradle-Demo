package jp.co.insightech.service;

import java.util.Vector;

import jp.co.insightech.BBSInfoModel;
import jp.co.insightech.Message;

/**
 * Serviceのインターフェース.
 * 
 * @author MiyuSuzuki
 *
 */
public interface BBSService {
	
	/**
	 * 投稿メッセージを登録するメソッド.
	 * 
	 * @param bbsInfo 投稿情報
	 * @throws Exception
	 */
	public void register(BBSInfoModel bbsInfo) throws Exception;
	
	/**
	 * 投稿メッセージを削除するメソッド.
	 * 
	 * @param id 削除したい投稿メッセージのID
	 * @param password パスワード
	 * @return パスワードが一致し、削除処理を実行するときにtrue
	 * @throws Exception
	 */
	public boolean delete(int id, String password) throws Exception;
	
	/**
	 * 投稿メッセージの一覧を取得するメソッド.
	 *
	 * @throws Exception
	 */
	public Vector<Message> getMessageList() throws Exception;
}
