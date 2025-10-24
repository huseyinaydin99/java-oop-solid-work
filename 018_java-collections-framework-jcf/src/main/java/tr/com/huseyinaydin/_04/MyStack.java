package tr.com.huseyinaydin._04;

import java.util.Stack;

public class MyStack {
    public static void main(String[] args) {
        Stack<String> stackList = new Stack<>();

        stackList.add("Hüseyin");
        stackList.push("Selami");
        stackList.push("Şakir");
        stackList.push("Zımzımettin");
        stackList.push(null);
        stackList.push("Veli");
        stackList.push("Süleyman");
        stackList.push("Halit");

        System.out.println(stackList);

        System.out.println(stackList.peek());

        System.out.println(stackList.search("Betül"));

        System.out.println(stackList.empty());

        System.out.println(stackList.isEmpty());
    }
}