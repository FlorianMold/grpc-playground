package at.agnet.server.service;

import at.agnet.server.GreetingRequest;
import at.agnet.server.GreetingResponse;
import at.agnet.server.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

// Similar to a rest controller.
@GrpcService // Helps spring-boot to find this service.
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        var message = request.getMessage();
        System.out.println("Received Message: " + message);

        // a builder is created
        var greetingResponse = GreetingResponse.newBuilder()
                .setMessage("Received your " + message + ". Hello from Server.")
                .build();

        responseObserver.onNext(greetingResponse);

        // on-complete closes the connection
        responseObserver.onCompleted();
    }
}
