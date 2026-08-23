package tr.com.huseyinaydin.concretes;

import tr.com.huseyinaydin.concept.abstracts.CoffeeDecorator;
import tr.com.huseyinaydin.concept.interfaces.Coffee;

public class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Şeker";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 5.0;
    }
}