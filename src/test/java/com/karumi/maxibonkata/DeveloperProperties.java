package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class) public class DeveloperProperties {
    private static final String ANY_NAME = "Pedro";
    private static final int ANY_NUMBER_OF_MAXIBONS = 1;

    @Property public void theNumberOfMaxibonsAssignedIsPostiveOrZero (int numberOfMaxibons){
        //System.out.println("------------------->" + numberOfMaxibons);
        Developer developer = new Developer(ANY_NAME, numberOfMaxibons);
        assertTrue(developer.getNumberOfMaxibonsToGrab()>=0);
    }

    @Property public void theNameOfTheDeveloperIsTheCorrectName (String name) {
        System.out.println("------------------->" + name);
        Developer developer = new Developer(name, ANY_NUMBER_OF_MAXIBONS);
        assertTrue(name == developer.getName());
    }
}
