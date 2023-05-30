package flatmapDemo;

import reactor.core.publisher.Flux;

public class UserService {
    public static Flux<User> getUsers() {
        return Flux.range(1, 3)
                .map(user -> new User(user));
    }
}
