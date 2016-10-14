package memcached;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class TestMemcache {
	public static void main(String[] args) {
		
		double startTime, endTime, totTime;
        startTime = System.currentTimeMillis();

        MemCachedClient client=new MemCachedClient();  
        String [] addr ={"127.0.0.1:11211"};  
        Integer [] weights = {3};  
        SockIOPool pool = SockIOPool.getInstance();  
        pool.setServers(addr);  
        pool.setWeights(weights);  
        pool.setInitConn(5);  
        pool.setMinConn(5);  
        pool.setMaxConn(200);  
        pool.setMaxIdle(1000*30*30);  
        pool.setMaintSleep(30);  
        pool.setNagle(false);  
        pool.setSocketTO(30);  
        pool.setSocketConnectTO(0);  
        pool.initialize();  
          
//      String [] s  =pool.getServers();  
//        client.setCompressEnable(true);  
//        client.setCompressThreshold(1000*1024);  
          
//      将数据放入缓存  
        client.set("test2","6666666666666666666");  
          
//      将数据放入缓存,并设置失效时间  
        Date date=new Date(2000000);  
        client.set("test1","test1", date);  
          
//      删除缓存数据  
//      client.delete("test1");  
        String str = "";
//      获取缓存数据
        for(int i=1; i<=1000000; i++){
        	str =(String)client.get("test" + i);
        }
//        System.out.println(str);
        
        endTime = System.currentTimeMillis();
        // 取得程式結束的時間
        totTime = endTime - startTime;
        System.out.println("總時間 Time: " + totTime / 1000 + " sec");
        System.out.println("總時間 Time: " + totTime + " ms");
    }
	
}
