package contextTask;

import org.example.corseUtil.Util;
import reactor.util.context.Context;

import java.awt.print.Book;

public class ContextTaskPractice {
    public static void main(String[] args) {
        BookService.provideBooks()
                .repeat(1)
                .contextWrite(UserService.getCategoryOutOfUser())
                .contextWrite(Context.of("user", "giorgi"))
                .subscribe(Util.subscriber());
    }
}
