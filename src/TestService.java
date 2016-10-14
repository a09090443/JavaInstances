import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;


public class TestService {
	public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ITest.class);
        factory.setAddress("http://localhost:9000/test");
        
        ITest service = (ITest) factory.create();
        System.out.println("[1235] 股價: " + service.getTest("aaa"));
    } 
}
