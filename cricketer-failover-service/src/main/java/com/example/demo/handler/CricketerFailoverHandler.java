package com.example.demo.handler;

import com.example.demo.model.Cricketer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class CricketerFailoverHandler {
    public Mono<ServerResponse> getCricketers(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        Mono.just(Arrays.asList(
                                new Cricketer(
                                        "1",
                                        "Sachin",
                                        "india",
                                        100
                                )
                        ))
                        , Cricketer.class);
    }

}
