package com.abin.lee.grpc.rpc.skeleton;

import com.abin.lee.grpc.rpc.skeleton.facade.OrderServiceImpl;
import com.abin.lee.grpc.rpc.skeleton.facade.TeamServiceImpl;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;

/**
 * Created by abin on 2017/8/2 18:33.
 * gpc-svr
 * com.rpc.mail.server
 */

public class GrpcServer {
    public static void main(String[] args) throws Exception {
        NettyServerBuilder builder = NettyServerBuilder.forPort(10086);
        builder.addService(new OrderServiceImpl());
        builder.addService(new TeamServiceImpl());

        Server server = builder.build();
        server.start();

        System.out.println("............................................................................................................................");
        System.out.println(".................................................GRPC Server is Running now!................................................");
        System.out.println("............................................................................................................................");

//        System.in.read(); // 按任意键退出
        server.awaitTermination();
    }
}

