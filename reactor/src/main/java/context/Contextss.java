package context;

import org.example.corseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Contextss {
    public static void main(String[] args) {
        getWelcomeM()
                .contextWrite(context -> context.put("user", context.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("user", "Jake"))
                .subscribe(Util.subscriber());
    }
    private static Mono<String> getWelcomeM() {
        return Mono.deferContextual(contextView -> {
            if (contextView.hasKey("user")) {
                return Mono.just("Welcome " + contextView.get("user"));
            }
            else {
                return Mono.error(new RuntimeException("unauthenticated"));
            }
        });
    }
}
