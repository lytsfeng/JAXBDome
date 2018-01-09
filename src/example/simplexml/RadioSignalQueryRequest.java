package example.simplexml;

//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlElementWrapper;
//import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.Root;

import java.util.Date;

@Root(name = "request")
public class RadioSignalQueryRequest {
    // 信号ID
    @Element(required = false)
    public String ID;
    // 开始频点，为空不过滤
    @Element(required = false)
    public String BeginFreq;
    // 结束频点，为空不过滤
    @Element(required = false)
    public String EndFreq;
    // 异常类型，为空不过滤
    @ElementArray(name = "TypeCodes",entry = "SignalTypeDTO",required = false)
    public SignalTypeDTO[] TypeCodes;
    // 区域码，为空不过滤)
    @ElementArray(name = "AreaCodes",entry = "int",required = false)
    public Integer[] AreaCodes ;
    // 监测站序列号，为空或为“”不过滤
    @ElementArray(name = "StationIDs",entry = "string",required = false)
    public String[] StationIDs;
    // 原本是台站频率ID，此处被当做台站ID过滤条件
    @Element(required = false)
    public String StationKey;
    // 是否是人工添加
    @Element(required = false)
    public Boolean IsManualInsert = false;
    // 开始时间，为空不过滤
    @Element(required = false)
    public Date StartTime = null;
    // 结束时间，为空不过滤
    @Element(required = false)
    public Date StopTime = new Date();
    // 分页条件，为空不分页。开始条数
    @Element(required = false)
    public long StartIndex = 0;
    // 分页条件，为空不分页。结束条数
    @Element(required = false)
    public long Count = 0;
    // 是否返回台站信息
    @Element(required = false)
    public Boolean IsReturnRadioStation = false;

}
