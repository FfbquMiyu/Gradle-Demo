package jp.co.insightech.controller;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.insightech.BBSInfoModel;
import jp.co.insightech.service.BBSService;

/**
 * 掲示板コントローラ.
 * 
 * @author MiyuSuzuki
 */
@Controller
public class BBSController {
	
	private BBSService service;
	
	/**
	 * コンストラクタ
	 * 
	 * @param service サービス層インスタンス
	 */
	@Autowired
	public void setBBSService(BBSService service) {
		this.service = service;
	}
	
	/**
	 * /show にアクセスされた際に呼び出されるメソッド.
	 * 
	 * @param bbsInfo 掲示板情報
	 * @return bbs.jspを呼び出すModelAndView
	 * @throws Exception
	 */
	@RequestMapping(path = "/show")
	public ModelAndView show(@ModelAttribute BBSInfoModel bbsInfo)
			throws Exception {
		bbsInfo.addMessageList(this.service.getMessageList());
		if (!bbsInfo.hasErrors()) {
			bbsInfo.setName("");
			bbsInfo.setMailaddress("");
			bbsInfo.setSubject("");
			bbsInfo.setContent("");
			bbsInfo.setWordColor("");
			bbsInfo.setPassword("");
		}
		return new ModelAndView("bbs");
	}
	
	/**
	 * 登録ボタンが押されたときに呼び出されるメソッド.
	 * 
	 * @param bbsInfo 投稿情報
	 * @return bbs.jspを呼び出すModelAndView
	 * @throws Exception
	 */
	@RequestMapping(path = "/show", params = "sendMessage")
	public ModelAndView register(@ModelAttribute BBSInfoModel bbsInfo) 
			throws Exception {
		// エラーメッセージをベクトルに挿入します
		if (bbsInfo.getName().isEmpty()) {
			bbsInfo.addErrorMessage("名前が入力されていません。");
		}
		if (bbsInfo.getName().length() > 20) {
			bbsInfo.addErrorMessage("名前の入力上限は20文字です。");
		}
		if (bbsInfo.getMailaddress().length() > 254) {
			bbsInfo.addErrorMessage("メールアドレスの入力上限は254文字です。");
		}
		if (bbsInfo.getSubject().length() > 40) {
			bbsInfo.addErrorMessage("件名の入力上限は40文字です。");
		}
		if (bbsInfo.getContent().isEmpty()) {
			bbsInfo.addErrorMessage("メッセージが入力されていません。");
		}
		if (bbsInfo.getContent().length() > 400) {
			bbsInfo.addErrorMessage("メッセージの入力上限は400文字です。");
		}
		if (bbsInfo.getPassword().isEmpty()) {
			bbsInfo.addErrorMessage("パスワードが入力されていません。");
		}
		if (bbsInfo.getPassword().length() > 20) {
			bbsInfo.addErrorMessage("パスワードの入力上限は20文字です。");
		}
		if (!bbsInfo.hasErrors()) {
			this.service.register(bbsInfo);
			bbsInfo.setName("");
			bbsInfo.setMailaddress("");
			bbsInfo.setSubject("");
			bbsInfo.setContent("");
			bbsInfo.setWordColor("");
			bbsInfo.setPassword("");
		}
		// データベースにあるデータを全件取得します
		Vector messageList = this.service.getMessageList();

		// JSPに出したいベクトル・文字列をModelAttrbuteにセットします
		bbsInfo.addMessageList(this.service.getMessageList());

		return new ModelAndView("bbs");
	}
	
	/**
	 * 削除ボタンが押されたときに呼び出されるメソッド.
	 *
	 * @param bbsInfo 投稿情報
	 * @param deleteId 削除したい投稿のID
	 * @param request リクエスト
	 * @return bbs.jspを呼び出すModelAndView
	 * @throws Exception
	 */
	@RequestMapping(path = "/show", params = "deleteId")
	public ModelAndView delete(@ModelAttribute BBSInfoModel bbsInfo, int deleteId, 
			HttpServletRequest request) throws Exception {
		// 投稿メッセージの一覧から選択されたメッセージを削除します= ;
		String sendPassword = request.getParameter("sendPassword" + deleteId);
		if (!this.service.delete(deleteId, sendPassword)) {
			bbsInfo.addErrorMessage("パスワードが違います。");
		}
		// 投稿一覧をModelAttrbuteにセットします
		bbsInfo.addMessageList(this.service.getMessageList());

		return new ModelAndView("bbs");
	}
}
