/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.cucumber;

import io.github.openmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for ScenarioContext class.
 */
public class ScenarioContextUnitTest {

    /**
     * Verifies the context can put values, get values and remove values by using names and objects
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testPutGetAndRemoveByName() {
        // Variables for testing
        String firstObjectName = "Object 1 - Int";
        String secondObjectName = "Object 2 - String";
        Integer firstObjectForTesting = 711;
        String secondObjectForTesting = "Testing String";

        // Adding Objects
        ScenarioContext.put(firstObjectName, firstObjectForTesting);
        ScenarioContext.put(secondObjectName, secondObjectForTesting);

        // Getting Objects
        Object firstObjectReturned = ScenarioContext.get(firstObjectName);
        Object secondObjectReturned = ScenarioContext.get(secondObjectName);

        // Validating types
        Assert.assertEquals(firstObjectForTesting.getClass(), firstObjectReturned.getClass());
        Assert.assertEquals(secondObjectForTesting.getClass(), secondObjectReturned.getClass());

        // Validate Values
        Assert.assertEquals(firstObjectReturned, firstObjectForTesting);
        Assert.assertEquals(secondObjectReturned, secondObjectForTesting);

        // Removes the second object
        ScenarioContext.remove(secondObjectName);

        // Gets both objects
        firstObjectReturned = ScenarioContext.get(firstObjectName);
        secondObjectReturned = ScenarioContext.get(secondObjectName);

        // Asserts the first still exists and the second is now null
        Assert.assertNotNull(firstObjectReturned);
        Assert.assertNull(secondObjectReturned);
    }

    /**
     * Verifies the context can put values, get values and remove values by using types and objects
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testPutGetAndRemoveByType() {
        // Variables for testing
        Class<Integer> firstClassForObject = Integer.class;
        Class<String> secondClassForObject = String.class;
        Class<Boolean> updatedClassForSecondObject = Boolean.class;
        Integer firstObjectForTesting = 711;
        String secondObjectForTesting = "Testing String";
        Boolean updatedObjectForTesting = true;

        // Adding Objects
        ScenarioContext.put(firstClassForObject, firstObjectForTesting);
        ScenarioContext.put(secondClassForObject, secondObjectForTesting);

        // Getting Objects
        Object firstObjectReturned = ScenarioContext.get(firstClassForObject);
        Object secondObjectReturned = ScenarioContext.get(secondClassForObject);

        // Validating types
        Assert.assertEquals(firstClassForObject, firstObjectReturned.getClass());
        Assert.assertEquals(secondClassForObject, secondObjectReturned.getClass());

        // Updating and checking that value
        ScenarioContext.put(secondClassForObject, updatedObjectForTesting);
        Object updatedObjectReturned = ScenarioContext.get(secondClassForObject);
        Assert.assertEquals(updatedClassForSecondObject, updatedObjectReturned.getClass());

        // Removes the second object
        ScenarioContext.remove(secondClassForObject);

        // Gets both objects
        firstObjectReturned = ScenarioContext.get(firstClassForObject);
        secondObjectReturned = ScenarioContext.get(secondClassForObject);

        // Asserts the first still exists and the second is now null
        Assert.assertNotNull(firstObjectReturned);
        Assert.assertNull(secondObjectReturned);
    }

    /**
     * Verifies the context can put values and get values values and forcing the type conversion
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testGetWithForcedTyping() {
        // Variables for testing
        Class<Integer> firstClassForObject = Integer.class;
        Class<String> secondClassForObject = String.class;
        String firstObjectName = "Object 1 - Int";
        String secondObjectName = "Object 2 - String";
        Class<Boolean> updatedClassForSecondObject = Boolean.class;
        Integer firstObjectForTesting = 711;
        String secondObjectForTesting = "Testing String";
        Boolean updatedObjectForTesting = true;

        // Adding Objects
        ScenarioContext.put(firstObjectName, firstObjectForTesting);
        ScenarioContext.put(secondObjectName, secondObjectForTesting);

        // Getting Objects
        Object firstObjectReturned = ScenarioContext.get(firstObjectName, firstClassForObject);
        Object secondObjectReturned = ScenarioContext.get(secondObjectName, secondClassForObject);

        // Validating types
        Assert.assertEquals(firstClassForObject, firstObjectReturned.getClass());
        Assert.assertEquals(secondClassForObject, secondObjectReturned.getClass());
    }
}