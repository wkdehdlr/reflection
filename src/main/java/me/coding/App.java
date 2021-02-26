package me.coding;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {

    public static void main(String[] args)
        throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<Book> bookClass = Book.class;
        //필드 가져오기
        Arrays.stream(bookClass.getFields()).forEach(System.out::println);
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);

        //필드 및 값 가져오기
        Book book = new Book();
        Arrays.stream(bookClass.getFields()).forEach(field -> {
            field.setAccessible(true);
            try {
                System.out.printf("%s %s\n", field, field.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        //메소드 가져오기
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);
        Arrays.stream(bookClass.getDeclaredMethods()).forEach(System.out::println);

        //생성자 가져오기
        Arrays.stream(bookClass.getConstructors()).forEach(System.out::println);

        //슈퍼클래스 가져오기
        System.out.println(MyBook.class.getSuperclass());

        //인터페이스 가져오기
        Arrays.stream(bookClass.getInterfaces()).forEach(System.out::println);

        //Modifier 클래스 활용
        //필드
        Arrays.stream(bookClass.getDeclaredFields()).forEach(field -> {
            int modifiers = field.getModifiers();
            System.out.println(modifiers);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
            System.out.println(Modifier.isFinal(modifiers));
        });

        //Modifier 클래스 활용
        //메소드
        Arrays.stream(bookClass.getDeclaredMethods()).forEach(method -> {
            int modifiers = method.getModifiers();
            System.out.println(modifiers);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
            System.out.println(Modifier.isFinal(modifiers));
        });

        //객체 생성하기
        Class<?> memberClass = Class.forName("me.coding.Book");
        Constructor<?> constructor1 = memberClass.getConstructor(null);
        Member member1 = (Member) constructor1.newInstance();

        Constructor<?> constructor2 = memberClass.getConstructor(String.class);
        Member member2 = (Member) constructor2.newInstance("aaa");

        Constructor<Member> constructor3 = Member.class.getConstructor();
        Member member3 = constructor3.newInstance();

        Constructor<Member> constructor4 = Member.class.getConstructor(String.class);
        Member member4 = constructor4.newInstance("bbb");

        //static 필드
        //get, set
        Field fieldA = Member.class.getDeclaredField("A");
        System.out.println(fieldA.get(null));
        fieldA.set(null, "variable AAA");
        System.out.println(fieldA.get(null));

        //non static 필드
        //get, set
        Field fieldB = Member.class.getDeclaredField("B");
        System.out.println(fieldB.get(member1));
        fieldB.set(member1, "variable BBB");
        System.out.println(fieldB.get(member1));

        //메소드 호출
        Method methodC = Member.class.getDeclaredMethod("c");
        methodC.invoke(member1);

        Method methodSum = Member.class.getDeclaredMethod("sum", int.class, int.class);
        int result = (int) methodSum.invoke(member1, 1, 2);
        System.out.println(result);
    }
}
