package example.simplexml;

import java.util.List;

public class SignalTypeDTO {

    //信号类型
    public int SignalType;
    // 数据状态
    public Boolean isInvalid = false;
    // 异常类型
   // @XmlElementWrapper(nillable = true)
    public List<SignalTypeDTO> AbnormalTypes;

}
