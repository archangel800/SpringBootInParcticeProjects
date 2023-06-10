package sinks;

import org.example.corseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkDemo {
    public static void main(String[] args) {

        Sinks.One<Object> sink = Sinks.one();

        Mono<Object> objectMono = sink.asMono();

        objectMono.subscribe(Util.subscriber("Sam"));
        objectMono.subscribe(Util.subscriber("Mike"));

        //sink.tryEmitEmpty();
        //sink.tryEmitValue("Hi " + Thread.currentThread().getName());
        //sink.tryEmitError(new RuntimeException("ErrorA"));

        sink.emitValue("Hi", ((signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        }));

//        sink.emitValue("Hi", ((signalType, emitResult) -> {
//            System.out.println(signalType.name());
//            System.out.println(emitResult.name());
//            return true;
//        }));

//        sink.emitValue("hello", ((signalType, emitResult) -> {
//            System.out.println(signalType.name());
//            System.out.println(signalType.name());
//            return false;
//        }));

//        sink.tryEmitValue("Hello");
    }
}
