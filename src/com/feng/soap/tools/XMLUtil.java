package com.feng.soap.tools;

import com.feng.soap.exceptions.NotDataException;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.simpleframework.xml.core.Persister;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class XMLUtil<T> {


    public final static String SOAP_METHOD = "&method";
    public final static String SOAP_NAMESPACE = "&namespace";
    public final static String SOAP_REQUEST = "&request";
    public final static String FLAG_RESULT_DATE_BEGIN = "<&value>";
    public final static String FLAG_RESULT_DATE_END = "</&value>";


    public static String ObjToRequesXml(Object object) {
        if (object == null) {
            return null;
        }
       return ObjToRequesXml(object.getClass(), object);
    }

    public static String ObjToRequesXml(@NotNull Class pClass,@NotNull Object obj){
        String _Request = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <"+SOAP_METHOD+" xmlns=\""+SOAP_NAMESPACE+"\">\n" +
                "     "+SOAP_REQUEST+" \n" +
                "    </&method>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";
        try {
            JAXBContext _context = JAXBContext.newInstance(pClass);
            Marshaller _marshaller = _context.createMarshaller();
            _marshaller.setProperty(Marshaller.JAXB_ENCODING,"utf-8");
            _marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            _marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);

            StringWriter _Writer = new StringWriter();
            _marshaller.marshal(obj,_Writer);
            _Request = _Request.replaceAll(SOAP_REQUEST,_Writer.toString());
            return _Request;
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception pE) {
            pE.printStackTrace();
        }
        return null;
    }

    public static Object XmlStrToObj(@NotNull Class pClass,@NotNull@Nullable String pXMLStr) throws NotDataException {
        String _ClassName = pClass.getSimpleName();
        return XmlStrToObj(pClass,pXMLStr,_ClassName);
    }

    /**
     * SOAP返回数据解析为Object
     * @param pClass            反序列化的目标类
     * @param pXMLStr
     * @param pNodeName
     * @return
     * @throws NotDataException
     */
    public static Object XmlStrToObj(@NotNull Class pClass, @NotNull String pXMLStr, @NotNull String pNodeName) throws NotDataException {
        String _begin = String.format("<%s>",pNodeName);
        String _end = String.format("</%s>", pNodeName);
        int _BeginIndex = pXMLStr.indexOf(_begin);
        int _EndIndex = pXMLStr.indexOf(_end) + _end.length();

        if (_EndIndex <= _BeginIndex) {
            throw new NotDataException("没有结果数据,请指定Node名称");
        }
        String _ResultXML = pXMLStr.substring(_BeginIndex, _EndIndex);
        System.out.println(_ResultXML);
        try {
            JAXBContext _context =  JAXBContext.newInstance(pClass);
            Unmarshaller _marshaller = _context.createUnmarshaller();
            StringReader _reader = new StringReader(_ResultXML);
            Object _obj = _marshaller.unmarshal(_reader);
            return _obj;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {

        String _Msg = "<hello><1>hello</1></hello>";
        int _index = _Msg.indexOf("<1>") + "<1>".length();
        int _lastIndex = _Msg.lastIndexOf("</1>");

        System.out.println(_Msg.substring(_index,_lastIndex));

        String _end = String.format("</%s>", "asssss");

        System.out.println(_end);

        try {
            XmlStrToObj(XMLUtil.class,null);
        } catch (NotDataException e) {
            System.out.println(e.getMessage());
        }

    }


}
