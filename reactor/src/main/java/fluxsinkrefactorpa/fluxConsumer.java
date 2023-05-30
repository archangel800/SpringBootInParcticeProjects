package fluxsinkrefactorpa;

import org.example.corseUtil.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class fluxConsumer implements Consumer<FluxSink> {
    private FluxSink fluxSink;
    @Override
    public void accept(FluxSink fluxSink) {
        this.fluxSink = fluxSink;
    }
    public void proceed() {
        String country;
        do{
            country = Util.faker().country().name();
            fluxSink.next(country);
        }
        while(!country.toLowerCase().equals("canada"));
        fluxSink.complete();
    }
}
