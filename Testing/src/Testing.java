public class Testing {

    public static void main(String[] args) {
        A objectInstance = new B();
        objectInstance.method();

        long startTime = System.currentTimeMillis();
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            if (i == Integer.MAX_VALUE) System.out.println("Max int");
            float b = 10000/2;

        }
        System.out.println("Execution time 1: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            float b = 10000* 0.5f;
        }
        System.out.println("Execution time 2: " + (System.currentTimeMillis() - startTime));
    }


    public static class A {
        public A() {}

        protected void method() {
            System.out.println("Calling from A");
        }
    }


    public static class B extends A {
        public B() {}

        public void method() {
            super.method();
            System.out.println("Calling from B");
        }

        public void method2() {
            super.method();
        }
    }


}
