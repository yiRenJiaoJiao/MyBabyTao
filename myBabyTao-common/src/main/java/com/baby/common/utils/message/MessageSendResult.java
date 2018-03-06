package com.baby.common.utils.message;

import com.alibaba.rocketmq.client.producer.SendResult;

import java.io.Serializable;

/**
 * Created by SEELE on 2017/9/12.
 */
public class MessageSendResult  implements Serializable {

    SendResult result;

    public SendResult getResult() {
        return result;
    }

    public void setResult(SendResult result) {
        this.result = result;
    }
}
