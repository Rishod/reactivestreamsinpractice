package com.example.rishod.reactivestreamsinpractice.spring;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import reactor.core.publisher.Flux;

public class DataBufferTest {
    public static void main(String[] args) {
        // reactive read text
        Flux<DataBuffer> bufferFlux = DataBufferUtils.read(
                new DefaultResourceLoader().getResource("someResource.txt"),
                new DefaultDataBufferFactory(),
                1024
        );
    }
}
