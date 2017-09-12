package com.abin.lee.grpc.rpc.stub;

import com.abin.lee.grpc.rpc.service.OrderRequest;
import com.abin.lee.grpc.rpc.service.OrderResponse;
import com.abin.lee.grpc.rpc.service.OrderServiceGrpc;
import com.google.common.base.Verify;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.netty.NettyChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by abin on 2017/8/2 18:34.
 * gpc-svr
 * com.rpc.mail.client
 */

public class GrpcClient {
    public static void main(String[] args) {
        try {
            ManagedChannel channel = NettyChannelBuilder.forAddress("localhost", 10086).usePlaintext(true).build();
            //同步调用(异步调用的话，就是：SendMailServiceGrpc.newFutureStub(channel))
            OrderServiceGrpc.OrderServiceBlockingStub stub = OrderServiceGrpc.newBlockingStub(channel);

            //设置请求参数
            OrderRequest param = OrderRequest.newBuilder().setRecipient("admin@google.com").setTitle("Email Title").setContent("This is email content").build();
            OrderResponse resp = stub.createOrder(param);
            System.out.println(resp.getMsg() + "\t" + resp.getCode());

            //close

            channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Status status = Status.fromThrowable(e);
            Verify.verify(status.getCode() == Status.Code.INTERNAL);
            Verify.verify(status.getDescription().contains("Eggplant"));
            // Cause is not transmitted over the wire.
            e.printStackTrace();
        }
    }
}