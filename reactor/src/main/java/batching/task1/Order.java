package batching.task1;

import org.example.corseUtil.Util;

public class Order {
    private String category;
    private String bookName;
    private int price;
    private long id;

    public Order(long id) {
        this.category = Util.faker().book().genre();
        this.bookName = Util.faker().book().title();
        this.price = Util.faker().random().nextInt(5) + 10;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
