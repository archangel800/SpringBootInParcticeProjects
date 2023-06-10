package context;

import context.helper.BookService;
import context.helper.UserService;
import org.example.corseUtil.Util;
import reactor.util.context.Context;

public class CtxRateLimiterPractice {
    public static void main(String[] args) {
        BookService.getBook()
                .repeat(3)
                .contextWrite(UserService.userCategoryContext())
                .contextWrite(Context.of("user", "mike"))
                .subscribe(Util.subscriber());
    }
}
