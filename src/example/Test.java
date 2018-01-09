package example;


import org.apache.http.client.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Test {

    public static void main(String[] args) throws IOException {


        String _soapReques = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <RStatDeleteFreq xmlns=\"http://tempuri.org/\">\n" +
                "      <freqs>\n" +
                "        <string>string</string>\n" +
                "        <string>string</string>\n" +
                "      </freqs>\n" +
                "    </RStatDeleteFreq>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";

        URL _url = new URL("http://192.168.0.53:8007/RadioStationWebService.asmx");
        HttpURLConnection _con = (HttpURLConnection) _url.openConnection();
        _con.setRequestMethod("POST");
        _con.setRequestProperty("Content-Length",_soapReques.getBytes().length+"");
        _con.setRequestProperty("Content-Type","text/xml; charset=utf-8");
        _con.setRequestProperty("SOAPAction","http://tempuri.org/RStatDeleteFreq");
        _con.setDoOutput(true);
        _con.setDoInput(true);

        OutputStream _out = _con.getOutputStream();
        _out.write(_soapReques.getBytes());
        _out.close();


        InputStreamReader _ISR = new InputStreamReader(_con.getInputStream(),"utf-8");

        BufferedReader in = new BufferedReader(_ISR);
        String inputLine;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("result.xml")));// 将结果存放的位置
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            bw.write(inputLine);
            bw.newLine();
        bw.close();
        in.close();
        }

//        InputStream is = _con.getErrorStream();    //通过getErrorStream了解错误的详情，因为错误详情也以XML格式返回，因此也可以用JDOM来获取。
//        InputStreamReader isr = new InputStreamReader(is,"utf-8");
//        BufferedReader in = new BufferedReader(isr);
//        String inputLine;
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
//                new FileOutputStream("result.xml")));// 将结果存放的位置
//        while ((inputLine = in.readLine()) != null)
//        {
//            System.out.println(inputLine);
//            bw.write(inputLine);
//            bw.newLine();
//            bw.close();
//        }
//        in.close();


        _con.disconnect();






    }

}
