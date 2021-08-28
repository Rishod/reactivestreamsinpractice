package com.example.rishod.reactivestreamsinpractice.projreactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.BaseStream;

@Slf4j
public class UsingTest {

    public static void main(String[] args) {
        Flux<String> ioFlux = Flux.using(
                Connection::nexConnection,
                connection -> Flux.fromIterable(connection.getData()),
                Connection::close
        );

        ioFlux.subscribe(Utils::showOnNext, Utils::showErrorMessage, () -> log.info("Stream finished"));

        // Reading from file in reactive way
        Flux<String> dataFromFile = Flux.using(
                () -> Files.lines(Paths.get("path/some_file")),
                Flux::fromStream,
                BaseStream::close
        );

        dataFromFile.subscribe(Utils::showOnNext);
    }

    public static class Connection implements AutoCloseable {
        private Random random = new Random();

        public Iterable<String> getData() {
            if (random.nextInt(10) < 3) {
                throw new RuntimeException("Communication error");
            }

            return Arrays.asList("Some", "data", "exist");
        }

        @Override
        public void close() {
            log.info("Close");
        }

        public static Connection nexConnection() {
            log.info("Connection created");
            return new Connection();
        }
    }
}
