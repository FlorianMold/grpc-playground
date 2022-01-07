package at.agnet.firstclient.service;

import at.agnet.server.GreetingRequest;
import at.agnet.server.GreetingServiceGrpc;
import org.springframework.stereotype.Service;

import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class GreetingService {

    @GrpcClient("myService")
    private GreetingServiceGrpc.GreetingServiceBlockingStub greetingServiceStub;

    public String receiveGreeting(final String name) {
        var request = GreetingRequest.newBuilder()
                .setMessage(name)
                .build();
        return greetingServiceStub.greeting(request).getMessage();
    }
}
