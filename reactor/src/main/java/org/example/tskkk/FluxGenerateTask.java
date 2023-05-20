package org.example.tskkk;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FluxGenerateTask {
    public static void main(String[] args) {
        String filePath = "src/main/resources/file.txt";
        Flux.generate(() -> {
            try {
               return new BufferedReader(new FileReader(filePath));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, (reader, synchronouseSink) -> {
            String line;
                    try {
                        line = reader.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if(line != null) {
                        synchronouseSink.next(line);
                    }
                    else {
                        synchronouseSink.complete();
                    }

            return reader;
            }, reader -> {
                    try {
                        reader.close();
                        System.out.println("Reader Closed");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            ).subscribe(Util.subscriber());
    }
}
