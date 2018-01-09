package com.feng.soap.beans;

import java.util.Date;

public class RadioSignalAbnormalHistoryDTO {

    // ID
    public String ID;

    // 信号ID
    public String FREQ_GUID;

    // 是否无效
    public Boolean IsInvalid;

    // 录入时间
    public Date SaveDate;

    // 无效时间
    public Date InvalidDate;

    // 信号类型
    public int HistoryType;

    // 描述
    public String Des;
}
