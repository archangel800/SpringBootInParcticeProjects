package contextTask;

import org.example.corseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BookService {
    private static Map<String, Integer> TRY = new HashMap<>();
    static {
        TRY.put("std", 2);
        TRY.put("prime", 3);
    }

    public static Mono<String> provideBooks() {
       return Mono.deferContextual(contextView -> {
            if (contextView.hasKey("allow")) {
                if(contextView.get("allow")) {
                    return Mono.just(Util.faker().book().title());
                }
                else {
                    return Mono.error(new RuntimeException("Not allowed"));
                }
            }
                return Mono.error(new RuntimeException("There is not such key with allow"));
        }).contextWrite(turnLicenseIntoTries());
    }

    private static Function<Context, Context> turnLicenseIntoTries() {
        return context -> {
            String category = context.get("category");
            Integer tries = TRY.get(category);
            if(tries > 0) {
                TRY.put(category, tries-1);
                return context.put("allow", true);
            }
                return context.put("allow", false);
        };
    }
}
