package com.baby.common.utils.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by SEELE on 2017/12/19.
 * 微信群发推送xml转换对象
 */
@XmlRootElement(name ="xml")
public class PushMassmsg {
    @XmlElement
    String ToUserName;
    @XmlElement
    String CreateTime;
    @XmlElement
    String MsgType;
    @XmlElement
    String Event;
    @XmlElement
    String MsgID;
    @XmlElement
    String Status;
    @XmlElement
    String TotalCount;
    @XmlElement
    String FilterCount;
    @XmlElement
    Integer SentCount;
    @XmlElement
    Integer ErrorCount;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getMsgID() {
        return MsgID;
    }

    public void setMsgID(String msgID) {
        MsgID = msgID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(String totalCount) {
        TotalCount = totalCount;
    }

    public String getFilterCount() {
        return FilterCount;
    }

    public void setFilterCount(String filterCount) {
        FilterCount = filterCount;
    }

    public Integer getSentCount() {
        return SentCount;
    }

    public void setSentCount(Integer sentCount) {
        SentCount = sentCount;
    }

    public Integer getErrorCount() {
        return ErrorCount;
    }

    public void setErrorCount(Integer errorCount) {
        ErrorCount = errorCount;
    }
}
