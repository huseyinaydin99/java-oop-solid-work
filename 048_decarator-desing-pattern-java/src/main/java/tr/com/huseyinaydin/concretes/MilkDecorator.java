package tr.com.huseyinaydin.concretes;

import tr.com.huseyinaydin.concept.abstracts.CoffeeDecorator;
import tr.com.huseyinaydin.concept.interfaces.Coffee;

public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Süt";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 10.0;
    }
}