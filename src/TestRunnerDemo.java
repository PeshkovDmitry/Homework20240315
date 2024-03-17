import Annotations.*;
import Assertions.Asserter;

public class TestRunnerDemo {

    public static void main(String[] args) {
        TestRunner.run(TestRunnerDemo.class);
    }

    @BeforeAll
    public void beforeAll() {
        System.out.println("Команды перед всем тестом");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Команды перед каждым тестом");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Команды после каждого теста");
    }

    @AfterAll
    public void afterAll() {
        System.out.println("Команды после всего теста");
    }

    @Test(order = 2)
    public void test2() {
        System.out.println("Тест №3");
        Asserter.assertEquals(3, 3);
    }

    @Test(order = 1)
    public void test1() {
        System.out.println("Тест №1");
    }

    @Test
    public void test0() {
        System.out.println("Тест №0");
        Asserter.assertEquals(1, 2);
    }

}