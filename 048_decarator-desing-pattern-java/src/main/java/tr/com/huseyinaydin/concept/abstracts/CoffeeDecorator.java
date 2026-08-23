package tr.com.huseyinaydin.concept.abstracts;

import tr.com.huseyinaydin.concept.interfaces.Coffee;

public abstract class CoffeeDecorator implements Coffee {

    protected final Coffee coffee;

    protected CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}