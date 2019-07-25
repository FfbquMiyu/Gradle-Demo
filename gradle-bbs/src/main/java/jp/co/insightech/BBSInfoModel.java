package jp.co.insightech;

import java.util.Date;
import java.util.Vector;

import lombok.Data;

/**
 * �f�[�^�i�[�̂��߂�Model�N���X.
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
	 * �x�N�g��messageList�ɂ��ׂĂ̓��e���b�Z�[�W��ǉ����܂�.
	 * 
	 * @param messageList ���e���b�Z�[�W�ꗗ
	 */
	public void addMessageList(Vector<Message> messageList) {
		this.messageList.addAll(messageList);
	}

	/**
	 * �x�N�g��errorMessageList�ɃG���[���b�Z�[�W��ǉ����܂�.
	 * 
	 * @param errorMessage �G���[���b�Z�[�W
	 */
	public void addErrorMessage(String errorMessage) {
		this.errorMessageList.add(errorMessage);
	}
	
	/**
	 * �x�N�g��errorMessageList���󂩂ǂ������肵�܂�.
	 * 
	 * @return errorMessageList�ɃG���[���b�Z�[�W����ȏ�����Ă���Ƃ��Atrue
	 */
	public boolean hasErrors() {
		return !errorMessageList.isEmpty();
	}
}