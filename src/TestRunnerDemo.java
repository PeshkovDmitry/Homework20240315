import Annotations.*;

public class TestRunnerDemo {

    public static void main(String[] args) {
        TestRunner.run(TestRunnerDemo.class);
    }

    @BeforeAll
    private void beforeAll() {
        System.out.println("Команды перед всем тестом");
    }

    @BeforeEach
    private void beforeEach() {
        System.out.println("Команды перед каждым тестом");
    }

    @AfterEach
    private void afterEach() {
        System.out.println("Команды после каждого теста");
    }

    @AfterAll
    private void afterAll() {
        System.out.println("Команды после всего теста");
    }

    @Test(order = 2)
    private void test2() {
        System.out.println("Тест №3");
    }

    @Test(order = 1)
    public void test1() {
        System.out.println("Тест №1");
    }

    @Test
    public void test0() {
        System.out.println("Тест №0");
    }

}