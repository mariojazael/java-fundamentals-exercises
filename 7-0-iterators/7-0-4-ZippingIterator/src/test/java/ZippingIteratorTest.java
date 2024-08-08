import com.mramirez.ObjectsHolder;
import com.mramirez.ZippingIterator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZippingIteratorTest {

    private static final Queue<ObjectsHolder<?, ?>> answers = new LinkedList<>();

    private static BiFunction<?, ?, ObjectsHolder<?, ?>> combiner;

    @BeforeAll
    static void setUp() {
        fillAnswers();
        initCombiner();
    }

    @ParameterizedTest
    @MethodSource("argsStream")
    public <T, R> void testIterator(Iterator<T> itA, Iterator<R> itB, BiFunction<T, R, ObjectsHolder<T, R>> combiner) {
        ZippingIterator zippingIterator = new ZippingIterator(itA, itB, combiner);
        while (zippingIterator.hasNext()) {
            assertEquals(answers.poll(), zippingIterator.next());
        }
    }

    static Stream<Arguments> argsStream() {
        return Stream.of(Arguments.of(List.of(1, 2, 3).iterator(), List.of(4, 5, 6).iterator(), combiner),
                Arguments.of(List.of(1, 2, 3).iterator(), List.of("a", "b", "c").iterator(), combiner),
                Arguments.of(List.of(1, 2, 3).iterator(), List.of("a", "c").iterator(), combiner),
                Arguments.of(List.of(true, false, true).iterator(), List.of('a', 'b', 'c').iterator(), combiner),
                Arguments.of(List.of(1.2, -2, false, 'c').iterator(), List.of(true, 0, "m", 'x').iterator(), combiner));
    }

    private static void fillAnswers() {
        answers.add(new ObjectsHolder<>(1, 4));
        answers.add(new ObjectsHolder<>(2, 5));
        answers.add(new ObjectsHolder<>(3, 6));
        answers.add(new ObjectsHolder<>(1, "a"));
        answers.add(new ObjectsHolder<>(2, "b"));
        answers.add(new ObjectsHolder<>(3, "c"));
        answers.add(new ObjectsHolder<>(1, "a"));
        answers.add(new ObjectsHolder<>(2, "c"));
        answers.add(new ObjectsHolder<>(true, 'a'));
        answers.add(new ObjectsHolder<>(false, 'b'));
        answers.add(new ObjectsHolder<>(true, 'c'));
        answers.add(new ObjectsHolder<>(1.2, true));
        answers.add(new ObjectsHolder<>(-2, 0));
        answers.add(new ObjectsHolder<>(false, "m"));
        answers.add(new ObjectsHolder<>('c', 'x'));
    }

    private static void initCombiner() {
        combiner = ObjectsHolder::new;
    }
}
