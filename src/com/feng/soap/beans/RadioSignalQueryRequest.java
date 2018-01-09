package com.feng.soap.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "request")
public class RadioSignalQueryRequest {
    // 信号ID
    public String ID;
    // 开始频点，为空不过滤
    public String BeginFreq;
    // 结束频点，为空不过滤
    public String EndFreq;
    // 异常类型，为空不过滤
    @XmlElementWrapper(name = "TypeCodes")
    @XmlElement(name = "SignalTypeDTO")
    public List<SignalTypeDTO> TypeCodes = new ArrayList<>();
    // 区域码，为空不过滤
    @XmlElementWrapper(name = "AreaCodes")
    @XmlElement(name = "int")
    public List<Integer> AreaCodes = new ArrayList<>();
    // 监测站序列号，为空或为“”不过滤
    @XmlElementWrapper(name = "StationIDs")
    @XmlElement(name = "string")
    public List<String> StationIDs = new ArrayList<>();
    // 原本是台站频率ID，此处被当做台站ID过滤条件
    public String StationKey;
    // 是否是人工添加
    public Boolean IsManualInsert = false;
    // 开始时间，为空不过滤

    public Date StartTime = null;
    // 结束时间，为空不过滤
    public Date StopTime = new Date();
    // 分页条件，为空不分页。开始条数
    public long StartIndex = 0;
    // 分页条件，为空不分页。结束条数
    public long Count = 0;
    // 是否返回台站信息
    public Boolean IsReturnRadioStation = false;

}
