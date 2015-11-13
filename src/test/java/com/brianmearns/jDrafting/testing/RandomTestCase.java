package com.brianmearns.jDrafting.testing;

import org.junit.Test;
import org.junit.Before;

import javax.validation.constraints.NotNull;

import java.util.Random;

public class RandomTestCase {

    @NotNull
    private Random rand;

    @Before
    public void setupRandomTestCase() {
        rand = null;
    }

    protected void seed(long seed) {
        rand = new Random(seed);
    }

    @NotNull
    protected Random getRand() {
        if(rand == null) {
            throw new NullPointerException("Random hasn't been initialized yet. Use seed() at the start of the test method.");
        }
        return rand;
    }

    protected double nextDouble() {
        return Double.MAX_VALUE * getRand().nextDouble();
    }

}

