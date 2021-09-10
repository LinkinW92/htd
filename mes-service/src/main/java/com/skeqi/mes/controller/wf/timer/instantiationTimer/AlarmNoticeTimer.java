package com.skeqi.mes.controller.wf.timer.instantiationTimer;

import com.skeqi.mes.pojo.wf.baseMode.alarmConfiguration.email.AlarmEmailConfig;
import com.skeqi.mes.pojo.wf.baseMode.alarmConfiguration.inform.CMesAlarmConfigT;
import com.skeqi.mes.pojo.wf.timer.CMesTimerConfigT;
import com.skeqi.mes.service.wf.baseMode.alarmConfiguration.email.AlarmEmailConfigService;
import com.skeqi.mes.service.wf.baseMode.alarmConfiguration.inform.CMesAlarmConfigTService;
import com.skeqi.mes.service.wf.timer.CMesTimerConfigTService;
import com.skeqi.mes.util.wf.timer.TimerConfigConstant;
import com.skeqi.mes.util.wf.timer.log.TimePerformLogUtil;
import com.sun.mail.util.MailConnectException;
import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 告警通知定时任务类
 * @author Lenovo
 */
@Component
@EnableScheduling
public class AlarmNoticeTimer implements Runnable{

    @Override
    public void run() {
        //1.查询告警通知定时器配置
        List<CMesTimerConfigT> timerConfig = timerConfigTService.selectByCode(TimerConfigConstant.ALAR_CONFIG_INFORM);
        if (timerConfig.size()>0){
            this.alarmNotice(timerConfig);
        }
    }

    /**
     * 定时器配置类
     */
    @Resource
    private CMesTimerConfigTService timerConfigTService;

    /**
     * 告警配置类
     */
    @Resource
    private CMesAlarmConfigTService alarmConfigTService;

    @Resource
    private AlarmEmailConfigService emailConfigService;

    /**
     * 列名
     */
    private static final String  COLUMN_NAME ="column_name";

    /**
     * 列名注释
     */
    private static final String  COLUMN_COMMENT ="column_comment";

    /**
     * 列名注释
     */
    private static final String  TITLE_SQL ="select column_name,column_comment from information_schema.columns where table_name = '?'";

    /**
     * 告警通知任务
     */
    public void alarmNotice(List<CMesTimerConfigT> timerConfig){
        LocalDateTime dataTime = LocalDateTime.now();
        Exception exception = null;
        try {
            //1.查询告警通知配置信息
            CMesAlarmConfigT alarmConfig = alarmConfigTService.selectByCode(timerConfig.get(0).getParams());
            if (!StringUtils.isEmpty(alarmConfig)){
                //2.得到查询出的sql（推送邮箱附件/短信内容）
                List<Map<String,Object>> contentList = alarmConfigTService.selectSqlBySql(alarmConfig.getSql());
                if (contentList.size()>0){
                    //获取sql并转换为小写
                    String sql1 = alarmConfig.getSql().toLowerCase();
                    //截取条件
                    int startIndex = sql1.indexOf("from");
                    int stopIndex = sql1.indexOf("where");
                    //表名
                    String tableName = sql1.substring(startIndex+4, stopIndex==-1?sql1.length():stopIndex).trim();
                    //3.查询表头
                    List<Map<String,Object>> titleList = alarmConfigTService.selectSqlBySql(TITLE_SQL.replace("?", tableName));
                        //4.按推送方式推送通知
                        switch (alarmConfig.getWay()){
                            case "0":
                                //邮箱
                                //附件内容生成excel
                                ByteArrayInputStream byteArrayInputStream = this.writeExcel(titleList,contentList);
                                //查询发件人邮箱配置
                                AlarmEmailConfig emailConfig = emailConfigService.selectByIfEnable(1);
                                //发送邮件
                                this.sendMail(alarmConfig,emailConfig,byteArrayInputStream);
                                break;
                            case "1":
                                //短信
                                // TODO: 2021/6/18  告警通知定时任务推送短信信息 待完成
                                break;
                            case "2":
                                //电话
                                // TODO: 2021/6/18  告警通知定时任务推送电话信息 待完成
                                break;
                            default:
                                break;
                        }
                }
            }
        } catch (Exception e) {
            exception =e;
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            //记录定时任务日志
            TimePerformLogUtil.addLog(timerConfig.get(0).getCode(),exception,dataTime);
        }
    }

    /**
     * 发送邮件
     * @param cMesAlarmConfigT
     * @param cAlarmEmailConfig
     * @throws Exception
     */
    public void sendMail(CMesAlarmConfigT cMesAlarmConfigT, AlarmEmailConfig cAlarmEmailConfig,ByteArrayInputStream inputstream) throws Exception {
        try {
            // 创建一个配置文件并保存
            Properties properties = new Properties();

            properties.setProperty("mail.host", cAlarmEmailConfig.getTheServer());

            properties.setProperty("mail.transport.protocol", "smtp");

            properties.setProperty("mail.smtp.auth", "true");

            // QQ存在一个特性设置SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            // 创建一个session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(cAlarmEmailConfig.getSenderEmail(), cAlarmEmailConfig.getAuthorizationCode());
                }
            });

            // 开启debug模式
            //session.setDebug(true);

            // 获取连接对象
            Transport transport = session.getTransport();

            // 1，连接服务器
            transport.connect(cAlarmEmailConfig.getTheServer(), cAlarmEmailConfig.getSenderEmail(), cAlarmEmailConfig.getAuthorizationCode());

            // 2，创建邮件对象
            MimeMessage mimeMessage = new MimeMessage(session);

            // 3，邮件发送人
            mimeMessage.setFrom(new InternetAddress(cAlarmEmailConfig.getSenderEmail()));

            // 4，邮件接收人
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(cMesAlarmConfigT.getReceptionStaff()));

            // 5，邮件标题
            mimeMessage.setSubject(cMesAlarmConfigT.getTitle());

            // 6，设置邮件内容，混合模式
            MimeMultipart msgMultipart = new MimeMultipart("mixed");
            mimeMessage.setContent(msgMultipart);

            // 7，设置消息正文
            MimeBodyPart content = new MimeBodyPart();
            msgMultipart.addBodyPart(content);

            // 8，设置正文格式
            MimeMultipart bodyMultipart = new MimeMultipart("related");
            content.setContent(bodyMultipart);

            // 9，设置正文内容和附件
            //正文部分
            MimeBodyPart contentPart = new MimeBodyPart();
            MimeMultipart contentMultipart = new MimeMultipart("related");
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(cMesAlarmConfigT.getContent(), "text/html;charset=UTF-8");
            contentMultipart.addBodyPart(htmlPart);
            //附件部分
            MimeBodyPart excelBodyPart = new MimeBodyPart();
            DataSource dataSource = new ByteArrayDataSource(inputstream, "application/excel");
            DataHandler dataHandler = new DataHandler(dataSource);
            excelBodyPart.setDataHandler(dataHandler);
            excelBodyPart.setFileName(MimeUtility.encodeText(cMesAlarmConfigT.getTitle()+".xlsx"));
            contentMultipart.addBodyPart(excelBodyPart);
            contentPart.setContent(contentMultipart);

            //11.正文赋值
            MimeMultipart mime = new MimeMultipart("mixed");
            mime.addBodyPart(contentPart);
            content.setContent(mime);

            // 发送邮件
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

            // 关闭连接
            transport.close();
        } catch (AuthenticationFailedException e1) {
            e1.printStackTrace();
            throw new Exception("登录失败。授权码已过期");
        } catch (MailConnectException e2) {
            e2.printStackTrace();
            throw new Exception("无法连接到主机," + cAlarmEmailConfig.getTheServer());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("发送失败了");
        }
    }

    /**
     * 生成excel文件流
     * @param titleList
     * @param contentList
     * @return
     */
    public ByteArrayInputStream writeExcel(List<Map<String,Object>> titleList,List<Map<String,Object>> contentList){
        ByteArrayOutputStream outputStream = null;
        ByteArrayInputStream inputStream = null;
        //1写入表头信息
        // 创建一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表sheet
        HSSFSheet sheet = workbook.createSheet();
        // 创建第一行
        HSSFRow row = sheet.createRow(0);
        // 创建一个单元格
        HSSFCell cell = null;
        // 创建表头
        for (int i = 0; i < titleList.size(); i++) {
            //创建单元格
            cell = row.createCell(i);
            // 设置列宽
            int v = 0;
            v = Math.round(Float.parseFloat("16.0") * 267.5F);
            sheet.setColumnWidth(i, v);
            // 设置样式
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            // 设置字体居中
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 设置字体
            HSSFFont font = workbook.createFont();
            font.setFontName("宋体");
            // 字体加粗
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short) 13);
            cellStyle.setFont(font);
            cell.setCellStyle(cellStyle);
            if (StringUtils.isEmpty(titleList.get(i).get(COLUMN_COMMENT))){
                cell.setCellValue(titleList.get(i).get(COLUMN_NAME).toString());
            }else {
                cell.setCellValue(titleList.get(i).get(COLUMN_COMMENT).toString());
            }
        }

        // 2写入数据
        // 从第二行开始追加数据
        for (int i = 1, length1 = (contentList.size() + 1); i < length1; i++) {
            // 创建第i行
            HSSFRow nextRow = sheet.createRow(i);
            // 设置样式
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            // 设置字体居中
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 获取数据(一条数据)
            Map<String, Object> course = contentList.get(i - 1);
            for (int j = 0; j < titleList.size(); j++) {
                HSSFCell cell2 = nextRow.createCell(j);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(course.get(titleList.get(j).get(COLUMN_NAME)).toString());
            }
        }
        try {
            outputStream = new ByteArrayOutputStream(1000);
            workbook.write(outputStream);
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
