package com.midea.cdp.microservice.cache.medis.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.yutong.framework.components.codis.client.RedisFactory;
import com.yutong.framework.components.codis.impl.CodisImpl;

public class Medis<T> {
	
	private CodisImpl redis;
	
	public Medis(RedisFactory redisFactory, String salt){
		redis = new CodisImpl();
		redis.setSalt(salt);
		redis.setRedisFactory(redisFactory);
	}
	
	public void set(final String key, final Object value){
		redis.set(key, value);
	}
	
	public void hmset(final String key, final Map<String, String> hash){
		Map<String, Object> map = new HashMap<String, Object>();
		for(Entry<String, String> entry: hash.entrySet()){
			map.put(entry.getKey(), entry.getValue());
		}
		
		redis.hmset(key, map);
	}
	
	public void hmset2(final String key, final Map<String, Object> hash){
		redis.hmset(key, hash);
	}
	
	public String get(final String key){
		return redis.get(key, String.class);
	}
	
	public Map<String, String> hgetAll(final String key){
		return redis.hgetAll(key, String.class);
	}
	
//	public <T> Map<String, T> hgetAll(final String key, final Class<T> clazz){
//		return redis.hgetAll(key, clazz);
//	}
	
	public boolean exists(final String key){
		if(redis.get(key, String.class) == null){
			return false;	
		}
		
		return true;
	}
	
	
	public long expire(final String key, final int seconds){
		return redis.expire(key, seconds);
	}
	
	public void del(final String key){
		redis.del(key);
	}
	

	
	
}
