package jp.co.insightech.service;

import java.util.Vector;

import jp.co.insightech.BBSInfoModel;
import jp.co.insightech.Message;

/**
 * Service�̃C���^�[�t�F�[�X.
 * 
 * @author MiyuSuzuki
 *
 */
public interface BBSService {
	
	/**
	 * ���e���b�Z�[�W��o�^���郁�\�b�h.
	 * 
	 * @param bbsInfo ���e���
	 * @throws Exception
	 */
	public void register(BBSInfoModel bbsInfo) throws Exception;
	
	/**
	 * ���e���b�Z�[�W���폜���郁�\�b�h.
	 * 
	 * @param id �폜���������e���b�Z�[�W��ID
	 * @param password �p�X���[�h
	 * @return �p�X���[�h����v���A�폜���������s����Ƃ���true
	 * @throws Exception
	 */
	public boolean delete(int id, String password) throws Exception;
	
	/**
	 * ���e���b�Z�[�W�̈ꗗ���擾���郁�\�b�h.
	 *
	 * @throws Exception
	 */
	public Vector<Message> getMessageList() throws Exception;
}
