package com.squirrel.xmemcached.config;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.ElectionMemcachedSessionLocator;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;

@Configuration
public class MemcachedBuilder {
    protected static Logger LOGGER = LoggerFactory.getLogger(MemcachedBuilder.class);

    @Resource
    private XMemcachedProperties xMemcachedProperties;

    @Bean
    public MemcachedClient getMemcachedClient() {
        MemcachedClient memcachedClient = null;
        try {
            MemcachedClientBuilder builder = new XMemcachedClientBuilder(
                    AddrUtil.getAddresses(xMemcachedProperties.getServers()));

//            MemcachedClientBuilder builder1 = new XMemcachedClientBuilder(
//                    AddrUtil.getAddresses("server1:11211 server2:11211 server3:11211"),
//                    new int[]{1, 2, 3});
//
//            builder1.setSessionLocator(new KetamaMemcachedSessionLocator());
//            builder1.setSessionLocator(new ElectionMemcachedSessionLocator());
//
//            builder.addAuthInfo(
//                    AddrUtil.getOneAddress("localhost;11211"),
//                    AuthInfo.typical("Admin", "Password"));
//            builder.setCommandFactory(new BinaryCommandFactory());

            builder.getTranscoder().setCompressionThreshold(1024);
            builder.setTranscoder(new SerializingTranscoder());

            builder.setCommandFactory(new BinaryCommandFactory());
            builder.setFailureMode(false);
            builder.setConnectionPoolSize(xMemcachedProperties.getPoolSize());
            builder.setOpTimeout(xMemcachedProperties.getOpTimeout());
            memcachedClient = builder.build();
        } catch (IOException e) {
            LOGGER.error("init MemcachedClient failed ", e);
        }
        return memcachedClient;
    }
}
