package delay;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DelayTime {
    public static void main(String[] args) throws InterruptedException {

        //default limit rate changed to 9
        System.setProperty("reactor.bufferSize.x", "9");
        //by default it has limit rate of 32 items
        Flux.range(1, 1000)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());



        Thread.sleep(10000);
    }
}
