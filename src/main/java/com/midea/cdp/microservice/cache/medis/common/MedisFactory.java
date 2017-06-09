package com.midea.cdp.microservice.cache.medis.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.midea.cdp.microservice.cache.medis.client.Medis;
import com.midea.cdp.microservice.cache.medis.entity.Node;
import com.yutong.framework.components.codis.client.RedisFactory;
import com.yutong.framework.components.codis.impl.CodisImpl;

public class MedisFactory {
	static RedisFactory redisFactory = new RedisFactory("10.8.7.200:2181", "/zk/codis/db_ydp-codis/proxy");
	
	static {
        redisFactory.setMinIdle(10);
        redisFactory.setMaxIdle(20);
        redisFactory.setMaxTotal(100);
        redisFactory.setMaxWaitMillis(2000);
        redisFactory.setTestOnBorrow(true);
        redisFactory.setTestWhileIdle(true);
	}
	

	
	public static Medis<Node> buildClient(String cache){
		return new Medis<Node>(redisFactory, cache);
	}
	

	//TODO Actually, Not used now. 
	private ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	public static void main(String[] args){
//		CodisImpl codis = new CodisImpl();
//		codis.setSalt("abc");
//		codis.setRedisFactory(redisFactory);
//		
//		Map<String, Object> hash = new HashMap<String, Object>();
//		hash.put("kv1", "v1");
//		hash.put("kv2", "v2");
//		
//		codis.hmset("key1", hash);
//		codis.expire("key1", 100);
//		System.out.println("ok");
		
		
		Medis<Node> codis = MedisFactory.buildClient("abc");
		
		Map<String, Object> hash = new HashMap<String, Object>();
		hash.put("kv1", "v1");
		hash.put("kv2", "v2");
		
		codis.hmset2("key1", hash);
		codis.expire("key1", 100);
		System.out.println("ok");

	}
	
	
}
