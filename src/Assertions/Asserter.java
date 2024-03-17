package Assertions;

public class Asserter {

    public static void assertEquals(int expected, int actual) throws AssertionOk, AssertionFail {
        if (expected == actual) {
            throw new AssertionOk("OK");
        } else {
            throw new AssertionFail(String.format("Expected %d, actual %d", expected, actual));
        }
    }
}
