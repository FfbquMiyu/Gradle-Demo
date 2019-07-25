package jp.co.insightech;

import java.util.Date;
import java.util.Vector;

import lombok.Data;

/**
 * データ格納のためのModelクラス.
 * 
 * @author MiyuSuzuki
 */
@Data
public class BBSInfoModel {

	private int id;
	
	private String name;

	private String mailaddress;

	private String subject;

	private String content;

	private String wordColor;

	private String password;
	
	private Date postDate;

	private final Vector<Message> messageList = new Vector<Message>();
	
	private final Vector<String> errorMessageList = new Vector<String>();
	

	/**
	 * ベクトルmessageListにすべての投稿メッセージを追加します.
	 * 
	 * @param messageList 投稿メッセージ一覧
	 */
	public void addMessageList(Vector<Message> messageList) {
		this.messageList.addAll(messageList);
	}

	/**
	 * ベクトルerrorMessageListにエラーメッセージを追加します.
	 * 
	 * @param errorMessage エラーメッセージ
	 */
	public void addErrorMessage(String errorMessage) {
		this.errorMessageList.add(errorMessage);
	}
	
	/**
	 * ベクトルerrorMessageListが空かどうか判定します.
	 * 
	 * @return errorMessageListにエラーメッセージが一つ以上入っているとき、true
	 */
	public boolean hasErrors() {
		return !errorMessageList.isEmpty();
	}
}