package com.abin.lee.grpc.rpc.skeleton;

import com.abin.lee.grpc.rpc.skeleton.facade.OrderServiceImpl;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;

/**
 * Created by abin on 2017/8/2 18:33.
 * gpc-svr
 * com.rpc.mail.server
 */

public class GrpcServer {
    public static void main(String[] args) throws Exception {
        Server server = NettyServerBuilder.forPort(10086).addService(new OrderServiceImpl()).build();
        server.start();
        System.out.println("server startup at 8080");
        server.awaitTermination();
    }
}

