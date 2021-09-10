package com.skeqi.mes.service.yp.system;

/**
 * 邮件服务
 *
 * @author yinp
 * @date 2021年8月11日
 */
public interface MailService {

	/**
	 * 发送
	 *
	 * @param mailbox 邮箱
	 * @param title   邮件标题
	 * @param content 内容
	 */
	public void sendOut(String mailbox, String title, String content) throws Exception;

}
