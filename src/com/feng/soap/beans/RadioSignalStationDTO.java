package com.feng.soap.beans;

import java.util.Date;

public class RadioSignalStationDTO {
    // ID，用于区分监测信息
    public  String ID;
    // 信号ID，绑定到某一信号
    public String SignalID;
    // 监测站序列号
    public String StationNumber;
    // 开始时间
    public Date StartTime;
    // 结束时间
    public Date StopTime;
}
