package me.coding;

public class Member {

    public static String A = "variable A";

    private String B = "variable B";

    public Member() {

    }

    public Member(String B) {
        this.B = B;
    }

    public void c() {
        System.out.println("C");
    }

    public int sum(int left, int right) {
        return left + right;
    }
}
