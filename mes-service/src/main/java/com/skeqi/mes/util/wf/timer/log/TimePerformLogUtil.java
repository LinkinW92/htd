package com.skeqi.mes.util.wf.timer.log;

import com.skeqi.mes.pojo.wf.timer.log.CMesTimerPerformLogT;
import com.skeqi.mes.service.wf.timer.log.CMesTimerPerformLogTService;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 定时器执行日志记录工具类
 * @author Lenovo
 */
@Configuration
public class TimePerformLogUtil {
    private static final String SUCCEED ="S";
    private static final String ERROR ="E";
    private static TimePerformLogUtil performLogUtil;
    @Resource
    private CMesTimerPerformLogTService performLogTService;

    @PostConstruct
    public void init() {
        performLogUtil = this;
        performLogUtil.performLogTService = this.performLogTService;
    }

    /**
     * 添加操作日志
     * @param e
     */
    public static void addLog(String tackCode,Throwable e, LocalDateTime dateTime) {
        CMesTimerPerformLogT cMesTimerPerformLogT = new CMesTimerPerformLogT();
        cMesTimerPerformLogT.setTaskCode(tackCode);
        cMesTimerPerformLogT.setResult(TimePerformLogUtil.SUCCEED);
        cMesTimerPerformLogT.setStartTime(dateTime.toString());
        if (e!=null){
            cMesTimerPerformLogT.setResult(TimePerformLogUtil.ERROR);
            cMesTimerPerformLogT.setLogContent(e.getMessage());
        }
        performLogUtil.performLogTService.insertSelective(cMesTimerPerformLogT);
    }

    /**
     * 添加操作日志--自定义
     * @param errorMsg
     */
    public static void addCustomLog(String tackCode,String errorMsg, LocalDateTime dateTime) {
        CMesTimerPerformLogT cMesTimerPerformLogT = new CMesTimerPerformLogT();
        cMesTimerPerformLogT.setTaskCode(tackCode);
        cMesTimerPerformLogT.setResult(TimePerformLogUtil.SUCCEED);
        cMesTimerPerformLogT.setStartTime(dateTime.toString());
        if (errorMsg!=null){
            cMesTimerPerformLogT.setResult(TimePerformLogUtil.ERROR);
            cMesTimerPerformLogT.setLogContent(errorMsg);
        }
        performLogUtil.performLogTService.insertSelective(cMesTimerPerformLogT);
    }

}
