package com.feng.soap;

import com.feng.soap.beans.QueryRadioSignalResult;

import com.feng.soap.beans.RadioSignalQueryRequest;
import com.feng.soap.tools.SoapUtil;


import java.util.ArrayList;
import java.util.Date;

public class Test {

    public static void main(String[] args) {

        Date _date = new Date();
        System.out.println(_date.toString());

        SoapUtil _util = new SoapUtil();
        _util.setCallback(new SoapUtil.SoapRequestCallBack() {
            @Override
            public void Success(Object obj) {
                if (obj instanceof QueryRadioSignalResult) {
                    QueryRadioSignalResult _result = (QueryRadioSignalResult) obj;

                    System.out.println(_result.IsSucess);


                }
            }

            @Override
            public void Failed(String msg) {
                System.out.println(msg);
            }
        });
        String _Url = "http://192.168.0.75:8192/RadioSignalWebService.asmx";
        String _NameSpace = "http://tempuri.org/";

        String _Method = "QueryRadioSignal";

        RadioSignalQueryRequest _re = new RadioSignalQueryRequest();

        _re.BeginFreq = 88* 1_000_000+"";
        _re.EndFreq =  (108*1_000_000) +"";
        _re.StationIDs = new ArrayList<String>() {
            {
                add("00000059");
            }
        };
//        _re.StationIDs =new String[]
//                {
//                    "00000059"
//                }
//            ;
        _re.Count = 30;
        _re.StartIndex = 0;
        _util.SoapRequest(_Url,_Method,_NameSpace,_re, QueryRadioSignalResult.class,null);


    }

}
