package com.coffee.core;

import com.coffee.utils.Validator;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Ingredient {

    private static final Logger logger = LoggerFactory.getLogger(Ingredient.class);

    private String name;

    private int quantity;

    private final static int MAX_QUANTITY = 50;

    public Ingredient() {

    }

    public Ingredient(Ingredient copy) {
        this.name = copy.getName();
        this.quantity = copy.getQuantity();
    }

    public Ingredient(String name, int quantity) {
        setName(name);
        setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.validateString(name);
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0 || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException("Invalid ingredient quantity");
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return this.name + " [" + quantity + "]";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + this.quantity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ingredient other = (Ingredient) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return this.quantity == other.quantity;
    }

    void decreaseQuantity(int value) {
        if (value > quantity) {
            throw new IllegalStateException("Cannot use more: " + this + ".");
        }
        logger.debug("Reducing {} quantity for the ingredient {}", value, this);
        setQuantity(quantity - value);
        logger.debug("New quantity for the ingredient is: {}", this);
    }

    void increaseQuantity(int value) {
        logger.debug("Increasing {} quantity for the ingredient {}", value, this);
        setQuantity(quantity + value);
        logger.debug("New quantity for the ingredient is: {}", this);
    }
}
