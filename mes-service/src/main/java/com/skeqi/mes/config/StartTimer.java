package com.skeqi.mes.config;

import com.skeqi.mes.controller.chenj.srm.timer.SrmPoOrderTimer;
import com.skeqi.mes.controller.chenj.srm.timer.SrmPoReceiveTimer;
import com.skeqi.mes.controller.chenj.srm.timer.SrmPoRequestTimer;
import com.skeqi.mes.controller.chenj.srm.timer.SrmTransferOfOrderTimer;
import com.skeqi.mes.controller.wf.timer.instantiationTimer.AlarmNoticeTimer;
import com.skeqi.mes.controller.wf.timer.instantiationTimer.MarginLibraryTimer;
import com.skeqi.mes.pojo.wf.timer.CMesTimerConfigT;
import com.skeqi.mes.service.wf.timer.CMesTimerConfigTService;
import com.skeqi.mes.util.wf.timer.TimerConfigConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时器操作类
 *
 * @author Lenovo
 */
@Configuration
public class StartTimer {

    /**
     * 轮询定时任务类
     */
    @Resource
    private MarginLibraryTimer libraryTimer;


    /**
     * 告警通知定时任务类
     */
    @Resource
    private AlarmNoticeTimer alarmNoticeTimer;


    /**
     * SRM采购申请定时任务类
     */
    @Resource
    private SrmPoRequestTimer srmPoRequestTimer;

    /**
     * SRM采购订单定时任务类
     */
    @Resource
    private SrmPoOrderTimer srmPoOrderTimer;

    /**
     * SRM送货单定时任务类
     */
    @Resource
    private SrmPoReceiveTimer srmPoReceiveTimer;

	/**
	 * SRM采购申请转订单定时器
	 */
	@Resource
	private SrmTransferOfOrderTimer srmTransferOfOrderTimer;

	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		threadPoolTaskScheduler= new ThreadPoolTaskScheduler();
		return threadPoolTaskScheduler;
	}


	/**
     * 实例化一个线程池任务调度类
     */
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 接收生成的定时计划
     */
    private ScheduledFuture<?> future;


    /**
     * 定时器配置类
     */
    @Resource
    private CMesTimerConfigTService timerConfigTService;


    /**
     * 定时任务存储类
     */
    private Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();

    /**
     * 定时器选择方法
     *
     * @param timerConfigT
     * @return
     */
    private Object switchTimer(CMesTimerConfigT timerConfigT) {
        Object object = null;
        switch (timerConfigT.getCode()) {
            case TimerConfigConstant.PRODUCE_LINE:
                object = libraryTimer;
                break;
            case TimerConfigConstant.ALAR_CONFIG_INFORM:
                object = alarmNoticeTimer;
                break;
            // SRM采购申请定时器
            case TimerConfigConstant.SRM_PO_REQUEST:
                object = srmPoRequestTimer;
                break;
            // SRM采购订单定时器
            case TimerConfigConstant.SRM_PO_ORDER:
                object=srmPoOrderTimer;
                break;
            // SRM送货单定时器
            case TimerConfigConstant.SRM_PO_RECEIVE:
                object = srmPoReceiveTimer;
                break;
			// SRM采购申请转订单定时器
			case TimerConfigConstant.SRM_TRANSFER_OF_ORDER:
				object = srmTransferOfOrderTimer;
				break;
            default:
                break;
        }
        return object;
    }

    /**
     * 定时器开启
     */
    public Boolean startCron(String taskCode) {
        try {
            List<CMesTimerConfigT> timerConfigT = timerConfigTService.selectByCode(taskCode);
            if (timerConfigT.size() > 0) {
                if (taskFutures.containsKey(taskCode)) {
                    stopCron(taskCode);
                }
                Boolean flag = false;
                for (CMesTimerConfigT cMesTimerConfigT : timerConfigT) {
                    //if (produceLine.getStatus()==0){
                    //生成一个定时计划类，传入的线程会被定时执行
                    //cron是你定义的表达式
                    if (cMesTimerConfigT.getCron() != null) {
                        //启动定时器
                        Object object = this.switchTimer(cMesTimerConfigT);
                        future = threadPoolTaskScheduler.schedule((Runnable) object, new CronTrigger(cMesTimerConfigT.getCron()));
                        taskFutures.put(taskCode, future);
                        //修改定时器状态
                        cMesTimerConfigT.setStatus(1);
                        timerConfigTService.updateByStatus(cMesTimerConfigT);
                        flag = true;
                    }
                    //}
                }
                return flag;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 定时器关闭
     */
    public Boolean stopCron(String taskCode) {
        try {
            //定时计划类提供方法能结束当前的定时任务
            if (future != null) {
                CMesTimerConfigT produceLine = new CMesTimerConfigT();
                produceLine.setStatus(0);
                produceLine.setCode(taskCode);
                timerConfigTService.updateByStatus(produceLine);
                ScheduledFuture<?> scheduledFuture = taskFutures.get(taskCode);
                if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
                    scheduledFuture.cancel(true);
                }
                taskFutures.remove(taskCode);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 初始化定时器
     *
     * @param taskCode
     * @return
     */
    public Boolean initTimer(String taskCode) {
        try {
            List<CMesTimerConfigT> timerConfigT = timerConfigTService.selectByCode(taskCode);
            if (timerConfigT.size() > 0) {
                for (CMesTimerConfigT cMesTimerConfigT : timerConfigT) {
                    //定时计划类提供方法能结束当前的定时任务
                    switch (cMesTimerConfigT.getStatus()) {
                        case 0:
                        case 2:
                            if (future != null) {
                                ScheduledFuture<?> scheduledFuture = taskFutures.get(taskCode);
                                if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
                                    scheduledFuture.cancel(true);
                                }
                                taskFutures.remove(taskCode);
                            }
                            break;
                        case 1:
                            if (future == null) {
                                CMesTimerConfigT produceLine = new CMesTimerConfigT();
                                produceLine.setStatus(0);
                                produceLine.setCode(taskCode);
                                Integer integer = timerConfigTService.updateByStatusNoTime(produceLine);
                                if (integer < 1) {
                                    return false;
                                }
                                taskFutures.remove(taskCode);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
