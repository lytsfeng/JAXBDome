package com.feng.soap.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Date;
import java.util.List;


public class RadioSignalDTO {
    // ID

    @XmlElement(nillable = true)
    public String ID;
    // 名称
    @XmlElement(nillable = true)
    public String Name;
    // 中心频率
    public long CenterFreq;
    // 带宽
    public int BandWidth;
    // 区域码
    public int AreaCode;
    // 信号类型 1：合法 2：已知 3：不明 4：非法
    public int TypeCode;
    // 经度
    public double Longitude;
    // 纬度
    public double Latitude;
    // 海拔高度
    public double Altitude;
    // 台站频率记录ID
    @XmlElement(nillable = true)
    public String StationKey;
    // 是否无效
    @XmlElement(nillable = true)
    public Boolean IsInvalid;
    // 录入时间
    @XmlElement(nillable = true)
    public Date SaveDate;
    // 扩展字段
    @XmlElement(nillable = true)
    public String ExtendFields;
    // 告警频率ID
    @XmlElement(nillable = true)
    public String WarningFreqID;
    // 0：代表自动添加 1：代表人工添加
    @XmlElement(nillable = true)
    public Boolean IsManualInsert;
    // 无效时间
    @XmlElement(nillable = true)
    public Date InvalidDate;
    // 描述
    @XmlElement(nillable = true)
    public String Des;
    // 附件信息
    @XmlElementWrapper(name = "AppendDTOs", nillable = true)
    @XmlElement(name = "RadioSignalAppendDTO")
    public List<RadioSignalAppendDTO> AppendDTOs;
    // 监测信息
    @XmlElementWrapper(name = "StationDTOs", nillable = true)
    @XmlElement(name = "RadioSignalStationDTO")
    public List<RadioSignalStationDTO> StationDTOs;
    // 台站信息   目前不支持添加台站信息
    @XmlElement(nillable = true)
    public RadioStationSignalDTO RadioStation;
    // 违规信息   目前不支持添加违规信息
    @XmlElementWrapper(name = "AbnormalHistory", nillable = true)
    @XmlElement(name = "RadioSignalAbnormalHistoryDTO")
    public List<RadioSignalAbnormalHistoryDTO> AbnormalHistory;


}
