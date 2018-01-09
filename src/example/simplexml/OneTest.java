package example.simplexml;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class OneTest {


    public final static String SOAP_METHOD = "QueryRadioSignal";
    public final static String SOAP_NAMESPACE = "http://tempuri.org/";
    public final static String SOAP_REQUEST = "&request";

    public static void main(String[] args) {

        String _Request = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <"+SOAP_METHOD+" xmlns=\""+SOAP_NAMESPACE+"\">\n" +
                "     "+SOAP_REQUEST+" \n" +
                "    </"+SOAP_METHOD+">\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";


        String _NameSpace = "http://tempuri.org/";

        String _Method = "QueryRadioSignal";

        try {
            URL _Url = new URL("http://192.168.0.75:8192/RadioSignalWebService.asmx");
            URLConnection _conn = _Url.openConnection();
            _conn.setDoOutput(true);
            _conn.setDoInput(true);
            _conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");

//            RadioSignalQueryRequest _re = new RadioSignalQueryRequest();
//
//            _re.BeginFreq = 88 * 1_000_000 + "";
//            _re.EndFreq = (108 * 1_000_000) + "";
//            _re.StationIDs =new String[]
//                {
//                    "00000059"
//                }
//            ;
//            _re.Count = 30;
//            _re.StartIndex = 0;
//
//
//            StringWriter _Writer = new StringWriter();
//
//            new Persister().write(_re, _Writer);
//            String _s = _Writer.toString();
        //    String _requestStr = _Request.replace(SOAP_REQUEST, _s);

            String _requestStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                    "  <soap12:Body>\n" +
                    "    <QueryRadioSignal xmlns=\"http://tempuri.org/\">\n" +
                    "     <request>\n" +
                    "   <BeginFreq>88000000</BeginFreq>\n" +
                    "   <EndFreq>108000000</EndFreq>\n" +
                    "<TypeCodes/>\n" +
                    "    <AreaCodes/>\n" +
                    "   <StationIDs>\n" +
                    "      <string>00000059</string>\n" +
                    "   </StationIDs>\n" +
                    "   <IsManualInsert>false</IsManualInsert>\n" +
                    "   <StopTime>2017-11-22 15:36:50.86 CST</StopTime>\n" +
                    "   <StartIndex>0</StartIndex>\n" +
                    "   <Count>30</Count>\n" +
                    "   <IsReturnRadioStation>false</IsReturnRadioStation>\n" +
                    "</request> \n" +
                    "    </QueryRadioSignal>\n" +
                    "  </soap12:Body>\n" +
                    "</soap12:Envelope>";

            byte[] _RequesBytes = _requestStr.getBytes();
            _conn.setRequestProperty("Content-Length", String.valueOf(_RequesBytes.length));
            //发送

            OutputStream _outputStream = _conn.getOutputStream();
            _outputStream.write(_RequesBytes);
            _outputStream.close();


            try (InputStream _in = _conn.getInputStream();
                 InputStreamReader _isr = new InputStreamReader(_in, "utf-8");
                 BufferedReader _br = new BufferedReader(_isr)) {
                String _ReadLine;

                String _ResultStr = "";
                while ((_ReadLine = _br.readLine()) != null) {
                    _ResultStr += _ReadLine;
                }
                System.out.println(_requestStr);

            }


            //接收数据

        } catch (MalformedURLException pE) {
            pE.printStackTrace();

        } catch (IOException pE) {
            pE.printStackTrace();

        } catch (Exception pE) {
            pE.printStackTrace();
        }


    }
}
