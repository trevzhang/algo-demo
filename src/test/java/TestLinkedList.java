import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import org.junit.Test;

/**
 * @author trevor
 * @since 2023/11/28 12:20
 **/
public class TestLinkedList {
    @Test
    public void testLinkedListAsQueue() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            q.offer(i);
            System.out.println(q);
        }

        while(!q.isEmpty()) {
            Integer poll = q.poll();
            System.out.println(poll);
        }
    }
}
