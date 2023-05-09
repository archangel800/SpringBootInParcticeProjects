package org.example;


import reactor.core.publisher.Mono;

import java.io.*;

public class FileService {
    private static final String FILE_PATH = "src/main/resources/myfile.txt";

    public static Mono<String> readFile()  throws IOException {
        return Mono.fromCallable(() -> {
            FileInputStream inputStream = new FileInputStream(FILE_PATH);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder builder = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    builder.append(line).append(System.lineSeparator());
                }
                return builder.toString();
            }
        });
    }
    public static Mono<Void> writeFile(String content) throws IOException {
       return Mono.fromRunnable(() -> {
            File file = new File(FILE_PATH);
            try(FileOutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(content.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("File writing successful");
        });

    }

    public static  Mono<Void> deleteFIle() throws  IOException {
        return Mono.fromRunnable(() -> {
            File file = new File(FILE_PATH);
            if(file.exists()) {
                if(!file.delete()) {
                    try {
                        throw new IOException("Failed to delete file");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("File Deleted Successfully");
            }
        });
    }
}
