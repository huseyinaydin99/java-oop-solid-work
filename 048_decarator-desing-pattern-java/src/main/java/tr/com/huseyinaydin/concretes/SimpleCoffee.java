package tr.com.huseyinaydin.concretes;

import tr.com.huseyinaydin.concept.interfaces.Coffee;

public class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Kahve";
    }

    @Override
    public double getCost() {
        return 50.0;
    }
}