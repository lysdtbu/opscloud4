package com.baiyi.opscloud.leo.task;

import com.baiyi.opscloud.leo.message.factory.LeoContinuousDeliveryMessageHandlerFactory;
import com.baiyi.opscloud.leo.message.handler.base.ILeoContinuousDeliveryRequestHandler;
import com.baiyi.opscloud.leo.task.session.LeoSessionQueryMap;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author baiyi
 * @Date 2022/11/28 09:48
 * @Version 1.0
 */
@Slf4j
@Data
public class WatchLeoQueryTask implements Runnable {

    private final String sessionId;

    private final Session session;

    public WatchLeoQueryTask(String sessionId, Session session) {
        this.sessionId = sessionId;
        this.session = session;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!this.session.isOpen()) {
                    log.info("WatchLeoQueryTask会话关闭任务退出！");
                    LeoSessionQueryMap.removeSessionQueryMap(this.sessionId);
                    break;
                }
                if (LeoSessionQueryMap.sessionQueryMapContainsKey(this.sessionId)) {
                    Map<String, String> queryMap = LeoSessionQueryMap.getSessionQueryMap(this.sessionId);
                    queryMap.keySet().forEach(messageType -> {
                        ILeoContinuousDeliveryRequestHandler handler = LeoContinuousDeliveryMessageHandlerFactory.getHandlerByMessageType(messageType);
                        if (handler != null) {
                            handler.handleRequest(session, queryMap.get(messageType));
                        }
                    });
                }
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException ie) {
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

}
