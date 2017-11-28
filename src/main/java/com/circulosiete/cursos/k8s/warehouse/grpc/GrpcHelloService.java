package com.circulosiete.cursos.k8s.warehouse.grpc;

import com.circulosiete.cursos.k8s.hello.HelloReply;
import com.circulosiete.cursos.k8s.hello.HelloRequest;
import com.circulosiete.cursos.k8s.hello.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

import java.security.SecureRandom;
import java.util.stream.IntStream;

@Slf4j
@GRpcService
public class GrpcHelloService extends HelloServiceGrpc.HelloServiceImplBase {
  @Override
  public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
    SecureRandom random = new SecureRandom();
    int i = random.nextInt(200);
    log.info("Generando {} mensajes", i);

    IntStream.rangeClosed(1, i).forEach(value -> {
      try {
        //Simulamos operacion tardada
        Thread.sleep(random.nextInt(500));
      } catch (InterruptedException e) {
        //En caso de error
        responseObserver.onError(e);
      }
      //Send event!
      responseObserver.onNext(HelloReply.newBuilder()
        .setMessage(String.format("Hola %s. #%d de %d", request.getName(), value, i))
        .build());
    });

    //Terminate streaming
    responseObserver.onCompleted();
  }
}