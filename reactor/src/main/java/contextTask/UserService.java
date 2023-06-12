package contextTask;

import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

public class UserService {
    private static final Map<String, String> USERS = Map.of("giorgi", "std", "abula", "prime");

    public static Function<Context, Context> getCategoryOutOfUser() {
        return context -> {
            String user = context.get("user");
            String license = USERS.get(user);
            return context.put("category", license);
        };
    }
}
