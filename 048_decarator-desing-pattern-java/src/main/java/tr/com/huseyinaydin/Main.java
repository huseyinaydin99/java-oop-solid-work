package tr.com.huseyinaydin;

import tr.com.huseyinaydin.concept.interfaces.Coffee;
import tr.com.huseyinaydin.concretes.MilkDecorator;
import tr.com.huseyinaydin.concretes.SimpleCoffee;
import tr.com.huseyinaydin.concretes.SugarDecorator;

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();

        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());
    }
}