package com.feng.soap.tools;

import com.feng.soap.exceptions.NotDataException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * SOAP工具类
 * author： 张亚峰
 * date： 2017-11-20
 */
public class SoapUtil {

    /**
     * Soap请求毁掉函数
     */
    public interface SoapRequestCallBack{
        void Success(Object obj);

        void Failed(String msg);
    }

    private SoapRequestCallBack mCallback;

    public void setCallback(SoapRequestCallBack pCallback) {
        this.mCallback = pCallback;
    }

    /**
     *
     * @param pUrl              webservice的地址
     * @param pMethod           webservice请求的参数
     * @param pNameSpace        webservice请求的方法
     * @param pRequestObj       请求参数的对象
     * @param pReturnClazz      返回结果的对象类型 clazz
     * @param NodeName          如果数据结果说在的节点和 pReturnClazz的名字不能请将名字写到这里不然不能解析出结果
     *                          相同怎为空即可
     */
    public void SoapRequest(String pUrl, String pMethod, String pNameSpace,
                    Object pRequestObj, Class pReturnClazz,String NodeName) {
        String _requestStr =  XMLUtil.ObjToRequesXml(pRequestObj);
        _requestStr = _requestStr.replaceAll(XMLUtil.SOAP_METHOD, pMethod);
        _requestStr = _requestStr.replaceAll(XMLUtil.SOAP_NAMESPACE, pNameSpace);
        String _ErrMsg = "";
        try {
            URL _Url = new URL(pUrl);
            URLConnection _conn = _Url.openConnection();
            _conn.setDoOutput(true);
            _conn.setDoInput(true);
            _conn.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");
            byte[] _RequesBytes = _requestStr.getBytes();
            _conn.setRequestProperty("Content-Length",String.valueOf(_RequesBytes.length));
            //发送
            SendData(_conn, _RequesBytes);
            //接收数据
            ReadData(pReturnClazz,NodeName, _conn);
        } catch (MalformedURLException pE) {
            pE.printStackTrace();
            _ErrMsg += pE.getMessage();
        } catch (IOException pE) {
            pE.printStackTrace();
            _ErrMsg+= pE.getMessage();
        }

        if ((_ErrMsg != "" && _ErrMsg != null) && mCallback != null) {
            mCallback.Failed(_ErrMsg);
        }
    }

    /**
     *  发送SOAP 请求
     * @param pConn
     * @param pRequesBytes
     * @throws IOException
     */
    private void SendData(URLConnection pConn, byte[] pRequesBytes) throws IOException{
        try (OutputStream _out = pConn.getOutputStream()){
            _out.write(pRequesBytes);
            _out.flush();
        }
    }

    /**
     * 接收数据
     * @param pReturnClazz
     * @param pNodeName
     * @param pConn
     */
    private void ReadData(Class pReturnClazz,String pNodeName, URLConnection pConn) {
        new Thread(new Runnable() {
            Boolean _IsFlag = false;
            String _ErrMsg = "";
            String _ResultStr="";
            Object _obj;
            @Override
            public void run() {

                try (InputStream _in = pConn.getInputStream();
                     InputStreamReader _isr = new InputStreamReader(_in,"utf-8");
                     BufferedReader _br = new BufferedReader(_isr)){
                    String _ReadLine;

                    while ((_ReadLine = _br.readLine()) != null) {
                        _ResultStr += _ReadLine;
                    }
                    System.out.println(_ResultStr);
                    if(pNodeName == null || pNodeName == "") {
                        _obj = XMLUtil.XmlStrToObj(pReturnClazz, _ResultStr);
                    }
                    else {
                        _obj = XMLUtil.XmlStrToObj(pReturnClazz, _ResultStr, pNodeName);
                    }
                    _IsFlag = true;
                }
                catch (UnsupportedEncodingException pE) {
                    pE.printStackTrace();
                    _ErrMsg += pE.getMessage();
                } catch (IOException pE) {
                    pE.printStackTrace();
                    _ErrMsg += pE.getMessage();
                } catch (NotDataException pE) {
                    pE.printStackTrace();
                    _ErrMsg += pE.getMessage();
                }
                if (mCallback != null) {
                    mCallback.Success(_obj);
                }else {
                    mCallback.Failed(_ErrMsg);
                }
            }
        }).start();
    }


}
