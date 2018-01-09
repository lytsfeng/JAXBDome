package example;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;



@WebService()
public class HelloWorld {
  @WebMethod
  public String sayHelloWorldFrom(String from) {
    String result = "Hello, world, from " + from;
    System.out.println(result);
    return result;
  }

  @WebMethod
  public p hellop(p f){

    System.out.println(f);
    return f;
  }


  public static void main(String[] argv) {
    Object implementor = new HelloWorld ();
    String address = "http://localhost:9001/HelloWorld";
    Endpoint.publish(address, implementor);

  }
}
