package org.example.Test;

import contextTask.BookService;
import lombok.Data;
import org.example.corseUtil.Util;

@Data
public class BookOrder {
    private int orderCount;
    private String name;

    public BookOrder() {
        this.orderCount = Util.faker().random().nextInt(2, 5);
        this.name = Util.faker().book().title();
    }
}
