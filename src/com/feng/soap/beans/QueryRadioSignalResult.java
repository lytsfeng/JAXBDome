package com.feng.soap.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "QueryRadioSignalResult")
public class QueryRadioSignalResult {


    // 是否成功
    public Boolean IsSucess = true;

    // 如果失败，返回失败信息
    @XmlElement(nillable = true)
    public String ErrorMessage;
    // 信号
    @XmlElementWrapper(name = "RadioSignals")
    @XmlElement(name = "RadioSignalDTO")
    public List<RadioSignalDTO> RadioSignals;
    // 信号总数。为通过传入条件能够获取到的信号总数
    public long TotalCount;
    // 分页查询开始条
    public long StartIndex;
    // 分页查询结束条
    public long StopIndex;
}
