import java.util.Stack;
import org.junit.Test;

/**
 * @author trevor
 * @since 2023/11/28 12:24
 **/
public class TestStack {

    @Test
    public void testStack() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        while(!stack.isEmpty()) {
            Integer pop = stack.pop();
            System.out.println(pop);
        }
    }
}
