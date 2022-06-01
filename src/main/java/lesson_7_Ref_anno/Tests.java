package lesson_7_Ref_anno;

public class Tests {
    @BeforeSuite
    public void init() {
        System.out.println("BeforeSuite");
    }

    @Test(priority = 1)
    public void test1() {
        System.out.println("test1");
    }

    @Test(priority = 8)
    public void test2() {
        System.out.println("test2");
    }

    @Test(priority = 7)
    public void test3() {
        System.out.println("test3");
    }

    @Test(priority = 6)
    public void test4() {
        System.out.println("test4");
    }

    public void method() {
        System.out.println("method");
    }


    @AfterSuite
    public void stop() {
        System.out.println("AfterSuite");
    }

}
