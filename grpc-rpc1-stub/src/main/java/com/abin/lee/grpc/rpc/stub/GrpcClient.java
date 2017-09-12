package com.abin.lee.grpc.rpc.stub;

import com.abin.lee.grpc.rpc.service.*;
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

            System.out.println("--------------------------------------------OrderServiceGrpc----------------------------------------------------");
            //同步调用(异步调用的话，就是：SendMailServiceGrpc.newFutureStub(channel))
            OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub = OrderServiceGrpc.newBlockingStub(channel);
            //设置请求参数
            OrderRequest orderRequest = OrderRequest.newBuilder().setRecipient("admin@google.com").setTitle("Email Title").setContent("This is email content").build();
            OrderResponse orderResponse = orderServiceBlockingStub.createOrder(orderRequest);
            System.out.println(orderResponse.toString());
            System.out.println("--------------------------------------------OrderServiceGrpc----------------------------------------------------");

            System.out.println("--------------------------------------------TeamServiceGrpc----------------------------------------------------");
            //同步调用(异步调用的话，就是：SendMailServiceGrpc.newFutureStub(channel))
            TeamServiceGrpc.TeamServiceBlockingStub teamServiceBlockingStub = TeamServiceGrpc.newBlockingStub(channel);
            //设置请求参数
            TeamRequest teamRequest = TeamRequest.newBuilder().setTeamName("FISH").setBusinessId(1000000000L).setTeamAddress("peking").setTeamPrice(298.00).build();
            TeamResponse teamResponse = teamServiceBlockingStub.createTeam(teamRequest);
            System.out.println(teamResponse.toString());
            System.out.println("--------------------------------------------TeamServiceGrpc----------------------------------------------------");

            System.out.println("--------------------------------------------TeamServiceGrpc----------------------------------------------------");
            //同步调用(异步调用的话，就是：SendMailServiceGrpc.newFutureStub(channel))
            VoucherServiceGrpc.VoucherServiceBlockingStub voucherServiceBlockingStub = VoucherServiceGrpc.newBlockingStub(channel);
            //设置请求参数
            VoucherRequest voucherRequest = VoucherRequest.newBuilder().setVoucherName("FISH").setBatchId(1000000000L).setVoucherSize(10).setVoucherPrice(298.00).build();
            VoucherResponse voucherResponse = voucherServiceBlockingStub.createVoucher(voucherRequest);
            System.out.println(voucherResponse.toString());
            System.out.println("--------------------------------------------TeamServiceGrpc----------------------------------------------------");

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