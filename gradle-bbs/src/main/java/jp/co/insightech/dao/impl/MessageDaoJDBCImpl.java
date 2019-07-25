package jp.co.insightech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import jp.co.insightech.Message;
import jp.co.insightech.dao.MessageDao;

/**
 * MESSAGE_TABLE からデータを取得するための Data Access Object クラス.
 *
 * @author MiyuSuzuki
 */
@Repository
public class MessageDaoJDBCImpl implements MessageDao {
	
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 投稿メッセージを登録します.
	 * 
	 * @param message 登録する投稿メッセージ
	 * @throws Exception
	 */
	public void registerMessage(Message message) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.getConnection();
			
			pstmt = conn.prepareStatement("INSERT INTO MESSAGE_TABLE VALUES(?,?,?,?,?,?,?,?)");
			
			pstmt.setInt(1, this.getNextId());
			pstmt.setString(2, message.getName());
			pstmt.setString(3, message.getMailaddress());
			pstmt.setString(4, message.getSubject());
			pstmt.setString(5, message.getContent());
			pstmt.setString(6, message.getWordColor());
			pstmt.setString(7, message.getPassword());
			pstmt.setTimestamp(8, new Timestamp(message.getPostDate().getTime()));
			
			pstmt.executeUpdate();
			
		} finally {
			this.close(pstmt);
		}
	}
	
	/**
	 * 投稿したいメッセージのIDを取得します.
	 * 
	 * @return ID
	 * @throws Exception
	 */
	private int getNextId() throws Exception {
		int nextId = 0;
		
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			String sql = "SELECT MAX(ID) AS MAX_ID FROM MESSAGE_TABLE";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				nextId = rs.getInt("MAX_ID") + 1;
			}
			
		} finally {
			this.close(rs);
			this.close(pstmt);
		}
		return nextId;
	}
	
	/**
	 * 投稿メッセージの一覧を取得します.
	 *
	 * @return 投稿メッセージの一覧
	 * @throws Exception
	 */
	public Vector getMessageList() throws Exception {
		Vector messageList = new Vector();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			String sql = "SELECT * FROM MESSAGE_TABLE ORDER BY ID DESC";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Message message = new Message();
				
				int    id           = rs.getInt("ID");
				String name         = rs.getString("NAME");
				String mailaddress  = rs.getString("MAILADDRESS");
				String subject      = rs.getString("SUBJECT");
				String content      = rs.getString("CONTENT");
				String wordColor    = rs.getString("WORD_COLOR");
				String password     = rs.getString("PASSWORD");
				Timestamp postDate  = rs.getTimestamp("POST_DATE");
				
				message.setId(id);
				message.setName(name);
				message.setMailaddress(mailaddress);
				message.setSubject(subject);
				message.setContent(content);
				message.setWordColor(wordColor);
				message.setPassword(password);
				message.setPostDate(postDate);
				messageList.add(message);
			}
		
		} finally {
			this.close(rs);
			this.close(pstmt);
		}
		return messageList;
	}
	
	/**
	 * 投稿メッセージを削除します.
	 *
	 * @param id ID
	 * @throws Exception
	 */
	public void deleteMessage(int id) throws Exception {
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		
		try {
			conn = this.getConnection();
			
			pstmt = conn.prepareStatement("DELETE FROM MESSAGE_TABLE WHERE ID = ?");
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
		} finally {
			this.close(pstmt);
		}
	}
	
	/**
	 * 投稿メッセージを1件取得します.
	 *
	 * @param id ID
	 * @return 投稿メッセージ
	 * @throws Exception
	 */
	public Message getMessage(int id) throws Exception {		
		Message message = null;
		
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = this.getConnection();
			
			pstmt = conn.prepareStatement("SELECT * FROM MESSAGE_TABLE WHERE ID = ?");
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			message = new Message();

			if (rs.next()) {
				String name         = rs.getString("NAME");
				String mailaddress  = rs.getString("MAILADDRESS");
				String subject      = rs.getString("SUBJECT");
				String content      = rs.getString("CONTENT");
				String wordColor    = rs.getString("WORD_COLOR");
				String password     = rs.getString("PASSWORD");
				Timestamp postDate  = rs.getTimestamp("POST_DATE");
				
				message.setId(id);
				message.setName(name);
				message.setMailaddress(mailaddress);
				message.setSubject(subject);
				message.setContent(content);
				message.setWordColor(wordColor);
				message.setPassword(password);
				message.setPostDate(postDate);
			}
		
		} finally {
			this.close(pstmt);
		}
		return message;
	}
	
	/**
	 * DBと接続します
	 * 
	 * @return Connectインスタンス
	 * @throws Exception
	 */
	private Connection getConnection() throws Exception {
		Connection conn = DataSourceUtils.getConnection(dataSource);
		return conn;
	}

	/**
	 * Statement を破棄します.
	 *
	 * @param stmt Statement
	 * @throws SQLException
	 */
	private void close(Statement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
	}
	
	/**
	 * ResultSet を破棄します.
	 *
	 * @param rs ResultSet
	 * @throws SQLException
	 */
	private void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}
}
