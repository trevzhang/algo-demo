/**
 * @author trevor
 * @since 2023/11/28 18:23
 **/
public class Demo {

    private int state = 0;
    private static int times;

    /**
     * input: 10
     * <p>
     * output:
     * abcabcabcabcabcabcabcabcabcabc
     *
     * @param args
     */

    public static void main(String[] args) throws InterruptedException {
        times = 10;
        Demo demo = new Demo();

        Thread threadA = new Thread(() -> demo.printLetter("a", 0), "Tread-A");
        Thread threadB = new Thread(() -> demo.printLetter("b", 1), "Tread-B");
        Thread threadC =  new Thread(() -> demo.printLetter("c", 2), "Tread-C");

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println();
        System.out.println("finished");
    }

    private void printLetter(String letter, int targetState) {
        for (int i = 0; i < times; i++) {
            synchronized (this) {
                while (state % 3 != targetState) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.print(letter);
                state++;
                i++;
                this.notifyAll();
            }
        }
    }
}
