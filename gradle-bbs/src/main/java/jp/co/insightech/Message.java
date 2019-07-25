package jp.co.insightech;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


/**
 * 投稿メッセージ.
 *
 * @author MiyuSuzuki
 */
@Data
public class Message implements Serializable {

	private int id;

	private String name;

	private String mailaddress;
	
	private String subject;

	private String content;
	
	private String wordColor;
	
	private String password;
	
	private Date postDate;
}
