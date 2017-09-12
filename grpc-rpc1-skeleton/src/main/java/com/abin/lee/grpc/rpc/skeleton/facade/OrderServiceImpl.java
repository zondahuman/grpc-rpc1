package com.abin.lee.grpc.rpc.skeleton.facade;

import com.abin.lee.grpc.rpc.common.util.JsonUtil;
import com.abin.lee.grpc.rpc.service.OrderRequest;
import com.abin.lee.grpc.rpc.service.OrderResponse;
import com.abin.lee.grpc.rpc.service.OrderServiceGrpc;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;

/**
 * Created by abin on 2017/9/12 0:21.
 * grpc-rpc1
 * com.abin.lee.grpc.rpc.skeleton.facade
 */
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    @Override
    public void createOrder(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        System.out.println("request= " + request.toString());

    }


}
