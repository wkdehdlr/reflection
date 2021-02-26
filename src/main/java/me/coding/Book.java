package me.coding;

@MyAnnotation
public class Book {

    private static String b = "bbb";

    private static final String c = "ccc";

    private String a = "aaa";

    public String d = "ddd";

    protected String e = "eee";

    public Book() {

    }

    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("method F");
    }

    public void g() {
        System.out.println("method g");
    }

    public String h() {
        return "method h";
    }
}
