import com.mramirez.FlatteningIterator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlatteningIteratorTest<T> {

    private static final Queue<List<?>> answers = new LinkedList<>();

    @BeforeAll
    static void setUp() {
        fillAnswers();
    }

    @ParameterizedTest
    @MethodSource("argsStream")
    @SuppressWarnings("unchecked")
    public void testIterator(Iterator<T>[] iterators) {
        FlatteningIterator<T> flatteningIterator = new FlatteningIterator<>(iterators);
        List<T> testAnswers = (List<T>) answers.poll();
        assertEquals(testAnswers, buildActualList(flatteningIterator));
    }

    static Stream<Arguments> argsStream() {
        return Stream.of(Arguments.of((Object) new Iterator<?>[]{ List.of(1, 2).iterator(), List.of(3, 4).iterator() }),
                Arguments.of((Object) new Iterator<?>[]{ List.of("mario", "melissa").iterator(), List.of("love", "shine").iterator(), List.of("3").iterator() }),
                Arguments.of((Object) new Iterator<?>[]{ List.of(true, false, true).iterator(), List.of(false, false).iterator() }),
                Arguments.of((Object) new Iterator<?>[]{ List.of("mario", 'x').iterator(), List.of(true, 3).iterator() }),
                Arguments.of((Object) new Iterator<?>[]{ List.of("y", -2, 1.2).iterator(), List.of(true, 0).iterator() }));
    }

    private static void fillAnswers() {
        answers.add(List.of(1, 2, 3, 4));
        answers.add(List.of("mario", "melissa", "love", "shine", "3"));
        answers.add(List.of(true, false, true, false, false));
        answers.add(List.of("mario", 'x', true, 3));
        answers.add(List.of("y", -2, 1.2, true, 0));
    }

    private List<T> buildActualList(FlatteningIterator<T> iterator) {
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}
