import java.util.function.Predicate;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * @author trevor
 * @since 2023/11/28 12:27
 **/
public class TestStream {

    @Test
    public void testAllMatch() {
        Stream<Integer> intStream = Stream.of();
        // Stream中没有元素，输出allMatch默认为true
        System.out.println(intStream.allMatch(new Predicate<Integer>() {
            @Override
            public boolean test(Integer i) {
                return i > 0;
            }
        }));
    }
}
