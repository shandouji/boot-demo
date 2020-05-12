package com.klayiu.bootdemo.Utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author klayiu
 * @create 2020-04-08 10:09
 * @Blog www.klayiu.com
 *
 * 封装redis 工具类
 * 注意：
 * Springboot 2.2.4 版本之后不支持jedis ,支持lutt , 现在在这里将lutt 改为redis
 *
 * 如何更改
 *
 * ip和端口号自行更改
 *
 *
 * 封装常见的redis 数据结构
 * String ,hash, list, set, zset
 *
 *
 *
 * opsForValue 主要操作字符串
 *
 * opsForHash  主要操作hash
 *
 */

@Component
public class RedisUtil {

    /**
     * 放弃在这里配置 ip 和端口号 ， 在yml文件里面配置
     *
     */

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //private static final String  ip = "localhost"; //ip
    //private static final String port = "6379" ; // 端口号, redis 默认端口为6379

    private final static Logger logger = LoggerFactory.getLogger(RedisUtil.class);


    /**
     * 指定缓存失效时间
     * @param key 键值,
     * @param time 时间(秒)
     * @return
     */

    public boolean expire(String key,long time){
        try{
            if(time>0){
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            logger.info(ExceptionUtil.getStackTrace(e));
        }

        return false;
    }


    /**
     * 根据key 值获取过期时间
     * @param key
     * @return
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 判断 redis 中是否存在指定的 key
     *
     * @param key 键值 , 不为空
     * @return true 表示存在， false 表示不存在
     */
    public boolean exists(String key){
        boolean result = false;
        try{
            if(StringUtils.isNotEmpty(key)){
                result = this.redisTemplate.hasKey(key);
            }
        }catch (Exception e){
            logger.info(ExceptionUtil.getStackTrace(e));
        }
        return result;
    }


    /**
     * 从redis 中移除指定的key值
     * @param keys
     * @return
     */

    public long removeKey(String... keys){
           long count = 0L;
           if(keys !=null && keys.length>0){
                if(keys.length ==1){
                    boolean result = redisTemplate.delete(keys[0]);
                    if(result){
                        count = keys.length;
                    }
                }else{
                       count = this.redisTemplate.delete(CollectionUtils.arrayToList(keys));
                }
           }
           return count;
    }


    /**
     * 从redis 中获取 key 对应的String 数据
     * @param key 键 , 不能为null
     * @param <T>
     * @return 对应的字符串数据
     */

    public <T> T get(String key){
        T t = null;
        try{
            if(StringUtils.isNotEmpty(key)){
                t = (T)this.redisTemplate.opsForValue().get(key);
            }
        }catch (Exception e){
            logger.info(ExceptionUtil.getStackTrace(e));
        }
        return t;
    }


    /**
     *
     * 将指定的 key ,value 放到redis 中
     * @param key 键 , 不能为空
     * @param value 键, 不能为空
     * @param <T>
     * @return true 表示成功，false 表示失败
     */

    public <T> boolean set(String key,T value){
        boolean result = false;
        try{
            if(StringUtils.isNotEmpty(key)){
                this.redisTemplate.opsForValue().set(key, value);
                result  = true;
            }
        }catch (Exception e){
            logger.info(ExceptionUtil.getStackTrace(e));
        }
        return result ;
    }

    /**
     * 将指定的key,value 放到对应的redis 中,并设置过期时间
     * @param key
     * @param value
     * @param time
     * @param <T>
     * @return
     */

    public <T> boolean set(String key ,T value ,long time){
        try{
            if(time>0){
                this.redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
            }else{
                this.set(key,value);
            }
            return true;
        }catch (Exception e){
            logger.info(ExceptionUtil.getStackTrace(e));
        }
        return false;
    }

    /**
     * 对redis 中指定的数据进行递增 ,并且返回递增后的值。
     * @param key 键 , 不能为空
     * @param data 要增加几
     * @return
     */
    public long incr(String key,long data){
        if(data<0){
            throw new RuntimeException("递增数必须大于0");   //抛出封装的异常
        }
        return this.redisTemplate.opsForValue().increment(key);
    }

    /**
     * 对redis 中指定的数据进行递减 ,并且返回递减后的值。
     * @param key 键 , 不能为空
     * @param data 递减的值
     * @return
     */

    public long decr(String key,long data){
        if(data<0){
            throw new RuntimeException("递减必须大于0");   //抛出封装的异常
        }
        return this.redisTemplate.opsForValue().increment(key);
    }


    /**
     * 判断redis 中指定的 key 对应hash 表中是否有 hashkey
     *
     * @param key 键 , 不能为 null
     * @param hashKey 表中的键, 不能为null
     * @return true 标书存在，false 表示不存在
     */
    public boolean hashExits(String key,String hashKey){
        return this.redisTemplate.opsForHash().hasKey(key,hashKey);
    }


    /**
     * 从redis 中获取指定的key,对应hash表中的指定 hashKey 所对应的值
     * @param key 键值不能为空
     * @param hashKey hash表中的键，不能为null
     * @param <T>
     * @return true 表示存在 ，false 表示不存在
     */
    public <T> T hget(String key,String hashKey){
        T t = null;
        this.redisTemplate.opsForHash().get(key,hashKey);
        return t;
    }

    /**
     * 向redis 中指定的key对应的hash表(如果hash表不存在则自动创建,) 中放入hashKey ,value 数据
     * @param key 键值，不能为null
     * @param hashKey hash表中的键, 不能为null
     * @param value 值 ,不能为null
     * @param <T>
     * @return
     */

    public <T> boolean hset(String key,String hashKey,T value){
        try{
            this.redisTemplate.opsForHash().put(key,hashKey,value);
            return true;
        }catch(Exception e){
            logger.info(ExceptionUtil.getStackTrace(e));
        }
        return false;
    }


    /**
     * 刪除redis 中 指定key 对应的hash 表中等于hashkeys 的数据
     * @param key 键，不能为null
     * @param hashKeys  hash 表中的键，可以是多个，不能为null
     * @param <T>
     */
    public <T> void hdel(String key,T...hashKeys){
       this.redisTemplate.opsForHash().delete(key,hashKeys);
    }


    /**
     * 从redis 中获取指定key 对应的hash 表，并返回相应的map 对象
     * @param key
     * @return
     */
    public Map<?,?> hmget(String key){
        return this.redisTemplate.opsForHash().entries(key);
    }


    /**
     * 向redis 中放入指定key,并设置对应的数据类型为map
     * @param key 键, 不能为null
     * @param map 多个键值对应的map,不能为null
     * @return true表示成功 ,false 表示失败
     */
    public boolean hmset(String key,Map<String,?> map){
        try{
            this.redisTemplate.opsForHash().putAll(key,map);
            return true;
        }catch (Exception e){
            logger.info(ExceptionUtil.getStackTrace(e));
            return false;
        }
    }
    /**
     * 向 redis 中放入指定 key，并设置对应的数据类型为 map 以及过期时间
     *
     * @param key  键，不能为null
     * @param map  对应多个键值，不能为null
     * @param time 时间（秒），time要大于0，如果time小于等于0，将设置无限期
     * @return true表示成功，false表示失败
     */
    public boolean hmset(String key, Map<String, ?> map, long time) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                // 设置过期时间
              //  this.expire(key, time);
            }
            return true;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return false;
        }
    }



    /**
     * 对 redis 中指定 key 对应的 hash 表（如果 hash 表不存在则自动创建）递增，并返回新增后的值
     *
     * @param key   键，不能为null
     * @param item  项，不能为null
     * @param delta 要增加几（大于0）
     * @return 新增后的值
     */
    public double hincr(String key, String item, double delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0。");
        }

        return this.redisTemplate.opsForHash().increment(key, item, delta);
    }

    /**
     * 对 redis 中指定 key 对应的 hash 表（如果 hash 表不存在则自动创建）递增，并返回新增后的值
     *
     * @param key   键，不能为null
     * @param item  项，不能为null
     * @param delta 要减少几（大于0）
     * @return
     */
    public double hdecr(String key, String item, double delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0。");
        }

        return this.redisTemplate.opsForHash().increment(key, item, -delta);
    }


    /**
     * 从 redis 中移除指定 key 对应 hash 表中键为 values 的数据
     *
     * @param key    键，不能为null
     * @param values 值，不能为null
     * @return 移除的个数
     */
    public <T> long hremove(String key, T... values) {
        try {
            Long count = this.redisTemplate.opsForHash().delete(key, values);
            return count;
        } catch (Exception ex) {
            logger.info(ex.getMessage(), ex);
            return 0;
        }
    }



    /**
     * 判断 redis 中是否存在指定 key 对应的 set 对象
     *
     * @param key   键，不能为null
     * @param value 值，不能为null
     * @return true表示存在，false表示不存在
     */
    public <T> boolean sexists(String key, T value) {
        try {
            return this.redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return false;
        }
    }

    /**
     * 从 redis 中根据指定 key 对应的值，并返回 set 对象
     *
     * @param key 键，不能为null
     * @return
     */
    public Set<?> sget(String key) {
        try {
            return this.redisTemplate.opsForSet().members(key);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 向 redis 中放入指定 key 和数据，并设置其数据类型为 set
     *
     * @param key    键，不能为null
     * @param values 值，不能为null
     * @return 成功个数
     */
    public <T> long sset(String key, T... values) {
        try {
            return this.redisTemplate.opsForSet().add(key, values);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return 0;
        }
    }

    /**
     * 向 redis 中放入指定 key 和数据，并设置其数据类型为 set 以及过期时间
     *
     * @param key    键，不能为null
     * @param time   过期时间（秒），注意:如果已存在的hash表有时间，这里将会替换原有的时间
     * @param values 值，不能为null
     * @return 成功个数
     */
    public <T> long sset(String key, long time, T... values) {
        try {
            Long count = this.redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
            //    expire(key, time);
            }
            return count;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return 0;
        }
    }

    /**
     * 获取 redis 中指定 key 对应 set 的大小
     *
     * @param key 键，不能为null
     * @return
     */
    public long ssize(String key) {
        try {
            return this.redisTemplate.opsForSet().size(key);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return 0;
        }
    }

    /**
     * 从 redis 中移除指定 key 对应 set 中键为 values 的数据
     *
     * @param key    键，不能为null
     * @param values 值，不能为null
     * @return 移除的个数
     */
    public <T> long sremove(String key, T... values) {
        try {
            Long count = this.redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            logger.info(ExceptionUtil.getStackTrace(e));
            return 0;
        }
    }


    /**
     * 从 redis 中获取指定 key 对应 list 的大小
     *
     * @param key 键，不能为null
     * @return
     */
    public long lsize(String key) {
        try {
            return this.redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            logger.info(ExceptionUtil.getStackTrace(e));
            return 0;
        }
    }

    /**
     * 从 redis 中获取指定 key 对应 list 中 index 位置的值
     *
     * @param key   键，不能为null
     * @param index 当index>=0时，0为表头，1为第二个元素，依次类推；当index<0时，-1为表尾，-2为倒数第二个元素，依次类推
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T lindex(String key, long index) {
        try {
            return (T) this.redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            logger.info(ExceptionUtil.getStackTrace(e));
            return null;
        }
    }

    /**
     * 从 redis 中获取指定 key 对应 list 指定范围的值（start~end设置为0~-1将返回所有值）
     *
     * @param key   键，不能为null
     * @param start 起始位置，0表示起始位置
     * @param end   结束位置，-1表示结束位置
     * @return
     */
    public List<?> lget(String key, long start, long end) {
        try {
            return this.redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            logger.info(ExceptionUtil.getStackTrace(e));
            return null;
        }
    }

    /**
     * 向 redis 中放入指定 key，并设置数组类型为 list，将 value 加入到 list 尾部
     *
     * @param key   键，不能为null
     * @param value 值，不能为null
     * @return
     */
    public <T> boolean lset(String key, T value) {
        try {
            this.redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            logger.info(ExceptionUtil.getStackTrace(e));
            return false;
        }
    }

    /**
     * 向 redis 中放入指定 key，并设置数组类型为 list，将 value 加入到 list 尾部，同时设置过期时间
     *
     * @param key   键，不能为null
     * @param value 值，不能为null
     * @param time  时间（秒），time要大于0，如果time小于等于0，将设置无限期
     * @return
     */
    public <T> boolean lset(String key, T value, long time) {
        try {
            this.redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                 this.expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.info(ExceptionUtil.getStackTrace(e));
            return false;
        }
    }

    /**
     * 向 redis 中放入指定 key，并设置数组类型为 list，并以 value 填入 list
     *
     * @param key   键，不能为null
     * @param value 值，不能为null
     * @return
     */
    public boolean lset(String key, List<?> value) {
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            logger.info(ExceptionUtil.getStackTrace(e));
            return false;
        }
    }

    /**
     * 向 redis 中放入指定 key，并设置数组类型为 list，并以 value 填入 list，同时设置过期时间
     *
     * @param key   键，不能为null
     * @param value 值，不能为null
     * @param time  时间（秒），time要大于0，如果time小于等于0，将设置无限期
     * @return
     */
    public boolean lset(String key, List<?> value, long time) {
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                this.expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.info(ExceptionUtil.getStackTrace(e));
            return false;
        }
    }

    /**
     * 将 redis 中指定 key 对应的 list 数据中指定 index 位置的数据更新为 value
     *
     * @param key   键，不能为null
     * @param index 索引
     * @param value 值，不能为null
     * @return
     */
    public <T> boolean lupdate(String key, long index, T value) {
        try {
            this.redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            logger.info(ExceptionUtil.getStackTrace(e));
            return false;
        }
    }

    /**
     * 从 redis 中指定 key 对应的 list 中移除 n 个值为 value 的数据
     *
     * @param key   键，不能为null
     * @param count 移除多少个
     * @param value 值，不能为null
     * @return 移除的个数
     */
    public <T> long lremove(String key, long count, T value) {
        try {
            Long remove = this.redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            logger.info(ExceptionUtil.getStackTrace(e));
            return 0;
        }
    }
}
