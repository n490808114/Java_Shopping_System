package xyz.n490808114.shopWeb.messageQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;


public class RedisListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(RedisListener.class);

    @Override
    public void onMessage(Message arg0, byte[] arg1) {
        logger.debug("从消息通道={}监听到消息", new String(arg1));
        logger.debug("从消息通道={}监听到消息", new String(arg0.getChannel()));
        RedisSerializer<Object> serializer = new GenericJackson2JsonRedisSerializer();
        logger.debug("反序列化后的消息={}", serializer.deserialize(arg0.getBody()));
    }
    
}