package com.hbdiy.sb.util.redis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;

import com.hbdiy.sb.util.spring.SpringContextUtil;

public class RedisUtil {
	
    private static RedisTemplate<String, ?> redisTemplate = SpringContextUtil.getBean("redisTemplate");
    
    
    public static void set(final String key, final Object value){
    	set(key, value, 0L);
    }
    /**
     * 
     * @param key 缓存的KEY
     * @param timeout 超时时间
     * @param value 缓存的值
     */
    public static void set(final String key, final Object value, final long timeout) {
    	redisTemplate.execute((RedisConnection connection)->{
    			connection.del(redisTemplate.getStringSerializer().serialize(key));
                connection.hSet(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(key), RedisSerializeUtils.serialize(value));
                boolean flag = true;
                if (timeout > 0) {
                    try {
                        flag = connection.pExpire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toMillis(timeout, TimeUnit.SECONDS));
                    } catch (Exception e) {
                        flag = connection.expire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toSeconds(TimeoutUtils.toMillis(timeout, TimeUnit.SECONDS), TimeUnit.SECONDS));
                    }
                }
                return flag;
    	});
    	
    }
    /**
     * 
     * @param key 缓存的KEY
     * @param field 缓存所属组
     * @param timeout 超时时间
     * @param value 缓存的值
     */
    public static void set(final String key, final String field, final Object value,final long timeout) {
    	redisTemplate.execute((RedisConnection connection)->{
    		connection.hDel(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(field));
            connection.hSet(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(field), RedisSerializeUtils.serialize(value));
            boolean flag = true;
            if (timeout > 0) {
                try {
                    flag = connection.pExpire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toMillis(timeout, TimeUnit.SECONDS));
                } catch (Exception e) {
                    flag = connection.expire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toSeconds(TimeoutUtils.toMillis(timeout, TimeUnit.SECONDS), TimeUnit.SECONDS));
                }
            }
            return flag;
    	});
    }
    /**
     * 
     * @param key 缓存的KEY
     * @param field 缓存的组
     */
    public static void delete(final String key, final String field) {
    	redisTemplate.execute((RedisConnection connection)->{
    		connection.hDel(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(field));
            return true;
    	});
    }
    /**
     * 
     * @param key 缓存的KEY
     */
    public static void delete(final String key) {
        redisTemplate.delete(key);
    }
    /**
     * 
     * @param key 缓存的KEY
     * @return
     */
    public static Object get(final String key) {
    	return redisTemplate.execute((RedisConnection connection)->{
    		byte[] value = connection.hGet(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(key));
            if (value == null) {
                return null;
            }
            return RedisSerializeUtils.deserialize(value);
    		
    	});
    }
    /**
     * 
     * @param key 缓存的KEY
     * @param field 缓存的组
     * @return
     */
    public static Object get(final String key, final String field) {
    	return redisTemplate.execute((RedisConnection connection)->{
    		byte[] value = connection.hGet(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(field));
            if (value == null) {
                return null;
            }
            return RedisSerializeUtils.deserialize(value);
    	});
    	
    }
    /**
     * 
     * @param key 缓存的field
     * @return
     */
    public static Map<String, Object> getKeyValues(final String field) {
    	return redisTemplate.execute((RedisConnection connection)->{
    		Map<byte[], byte[]> map = connection.hGetAll(redisTemplate.getStringSerializer().serialize(field));
            Map<String, Object> newValue = new HashMap<String, Object>();
            for (Entry<byte[], byte[]> entry : map.entrySet()) {
                newValue.put(redisTemplate.getStringSerializer().deserialize(entry.getKey()), RedisSerializeUtils.deserialize(entry.getValue()));
            }
            return newValue;
    	});
    }

    /**
     * 根据传入内容(匹配模式)模糊查询匹配的key<br/>
     * 举例：key值-one/two/three/four<br/>
     * 模式-*o* 返回
     *
     * @param keyPattern
     * @return
     */
    public static List<String> getKeys(final String keyPattern) {
    	return (List<String>) redisTemplate.execute((RedisConnection connection)->{
    		 Set<byte[]> keys = connection.keys(redisTemplate.getStringSerializer().serialize(keyPattern));
             if (keys != null && !keys.isEmpty()) {
                 List<String> keyList = new LinkedList<String>();
                 for (byte[] key : keys) {
                     keyList.add(redisTemplate.getStringSerializer().deserialize(key));
                 }
                 return keyList;
             } else {
                 return new LinkedList<String>();
             }
    	});
    	
    }
    /**
     * 将 key 中储存的数字值增一
     * 参考http://redisdoc.com/string/incr.html
     * @param key 缓存的key值
     * @param timeout 超时时间(单位毫秒)
     * @return
     */
    public static Long incr(final String key, final long timeout){
    	return redisTemplate.execute((RedisConnection connection)->{
    		Long result=0L;
			result = connection.incr(redisTemplate.getStringSerializer().serialize(key));
            if (timeout > 0) {
                try {
                    connection.pExpire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toMillis(timeout, TimeUnit.MILLISECONDS));
                } catch (Exception e) {
                    connection.expire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toSeconds(timeout, TimeUnit.MILLISECONDS));
                }
            }
			return result;
    	});
    }
    
    public static Long incr(final String key){
    	return redisTemplate.execute((RedisConnection connection)->{
    		Long result=0L;
			result = connection.incr(redisTemplate.getStringSerializer().serialize(key));
			return result;
    	});
    }
    
    
    /**
     * 将 key 所储存的值加上增量 increment
     * @param key	缓存的KEY
     * @param increment	自增步长
     * @param timeout 超时时间(单位毫秒)
     * @return
     */
    public static Long incrBy(final String key, final long increment, final long timeout) {
    	return redisTemplate.execute((RedisConnection connection)->{
    		Long result=0L;
    		result = connection.incrBy(redisTemplate.getStringSerializer().serialize(key), increment);
    		if (timeout > 0) {
                try {
                    connection.pExpire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toMillis(timeout, TimeUnit.MILLISECONDS));
                } catch (Exception e) {
                    connection.expire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toSeconds(timeout, TimeUnit.MILLISECONDS));
                }
            }
			return result;
    	});
	}
    /**
     * 将 key 所储存的值加上增量 increment
     * @param key	缓存的KEY
     * @param increment	自增步长
     * @param timeout 超时时间(单位毫秒)
     * @return
     */
    public static Double incrBy(final String key, final double increment, final long timeout) {
    	return redisTemplate.execute((RedisConnection connection)->{
    		Double result=0D;
    		result = connection.incrBy(redisTemplate.getStringSerializer().serialize(key), increment);
    		if (timeout > 0) {
                try {
                    connection.pExpire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toMillis(timeout, TimeUnit.MILLISECONDS));
                } catch (Exception e) {
                    connection.expire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toSeconds(timeout, TimeUnit.MILLISECONDS));
                }
            }
			return result;
    	});
	}
    /**
     * 可以获取incr和incrBy自增后的值
     * 参考http://redisdoc.com/string/get.html
     * @param key
     * @return	当 key 不存在时，返回 nil ，否则，返回 key 的值。如果 key 不是字符串类型，那么返回一个错误,因为该方法只能处理字符串。
     */
    public static Object sGet(final String key) {
    	return redisTemplate.execute((RedisConnection connection)->{
    		byte[] result = connection.get(redisTemplate.getStringSerializer().serialize(key));
    		if(null==result) return null;
    		return redisTemplate.getStringSerializer().deserialize(result);
    	});
	}
    /**
     * 为哈希表 key 中的域 key 的值加上增量 increment
     * 参考http://redisdoc.com/hash/hincrby.html
     * @param key	哈希表 key
     * @param field	域field
     * @param increment	增量
     * @param timeout 超时时间(单位毫秒)
     * @return
     */
    public static Long hIncrBy(final String key, final String field, final long increment, final long timeout) {
    	return redisTemplate.execute((RedisConnection connection)->{
    		Long result=0L;
    		
    		result = connection.hIncrBy(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(field), increment);
    
    		if (timeout > 0) {
                try {
                    connection.pExpire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toMillis(timeout, TimeUnit.MILLISECONDS));
                } catch (Exception e) {
                    connection.expire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toSeconds(timeout, TimeUnit.MILLISECONDS));
                }
            }
    		return result;
    	});
	}
    /**
     * 为哈希表 key 中的域 field 加上浮点数增量 increment
     * 参考http://redisdoc.com/hash/hincrbyfloat.html
     * @param key	哈希表 key
     * @param field	域 field
     * @param increment	步长
     * @param timeout 超时时间(单位毫秒)
     * @return
     */
    public static Double hIncrByDouble(final String key, final String field, final double increment, final long timeout) {
    	return redisTemplate.execute((RedisConnection connection)->{
    		Double result=0D;
    		result = connection.hIncrBy(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(field), increment);
    		if (timeout > 0) {
                try {
                    connection.pExpire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toMillis(timeout, TimeUnit.MILLISECONDS));
                } catch (Exception e) {
                    connection.expire(redisTemplate.getStringSerializer().serialize(key), TimeoutUtils.toSeconds(timeout, TimeUnit.MILLISECONDS));
                }
            }
    		return result;
    	});
	}
    /**
     * 可以获取hIncrBy和hIncrByDouble的Map结构
     * @param key	哈希表 key
     * @return
     */
    public static Map<String, String> getKeyValues2MapString(final String key) {
    	return redisTemplate.execute((RedisConnection connection)->{
    		Map<byte[], byte[]> map = connection.hGetAll(redisTemplate.getStringSerializer().serialize(key));
    		Map<String, String> newValue = new HashMap<String, String>();
            for (Entry<byte[], byte[]> entry : map.entrySet()) {
                newValue.put(redisTemplate.getStringSerializer().deserialize(entry.getKey()), redisTemplate.getStringSerializer().deserialize(entry.getValue()));
            }
            return newValue;
    	});
    }
    /**
     * 可以获取hIncrBy和hIncrByDouble的 哈希表 key 中的域 field的值
     * 通过getKeyValues2MapString先获取map,然后再从其中取出field的值
     * @param key	哈希表 key
     * @param field	域 field
     * @return
     */
    public static String getValues2String(final String key,final String field) {
    	Map<String, String> map=getKeyValues2MapString(key);
    	return map.get(field);
    }
}
