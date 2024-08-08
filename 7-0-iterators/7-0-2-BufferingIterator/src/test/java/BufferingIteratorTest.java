import com.mramirez.BufferingIterator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BufferingIteratorTest<T> {
    private static final Queue<List<?>> answers = new LinkedList<>();

    @BeforeAll
    static void setUp() {
        fillAnswers();
    }

    @ParameterizedTest
    @MethodSource("argsStream")
    @SuppressWarnings("unchecked")
    public void testIterator(Iterator<T> iterator, int batchSize) {
        BufferingIterator<T> bufferingIterator = new BufferingIterator<>(iterator, batchSize);
        while (bufferingIterator.hasNext()) {
            List<T> testAnswers = (List<T>) answers.poll();
            assertEquals(testAnswers, bufferingIterator.next());
        }
    }

    static Stream<Arguments> argsStream() {
        return Stream.of(Arguments.of(List.of(1, 2, 3, 4, 5).iterator(), 3),
                Arguments.of(List.of("m", "a", "r", "i", "o").iterator(), 2),
                Arguments.of(List.of(1, "3", true).iterator(), 2),
                Arguments.of(List.of(22, 'x', 22, -2).iterator(), 2),
                Arguments.of(List.of(LocalDate.of(2019, 1, 12), 42, new LinkedList<>(List.of(true))).iterator(), 2));
    }

    private static void fillAnswers() {
        answers.add(new LinkedList<>(List.of(1, 2, 3)));
        answers.add(new LinkedList<>(List.of(4, 5)));
        answers.add(new LinkedList<>(List.of("m", "a")));
        answers.add(new LinkedList<>(List.of("r", "i")));
        answers.add(new LinkedList<>(List.of("o")));
        answers.add(new LinkedList<>(List.of(1, "3")));
        answers.add(new LinkedList<>(List.of(true)));
        answers.add(new LinkedList<>(List.of(22, 'x')));
        answers.add(new LinkedList<>(List.of(22, -2)));
        answers.add(new LinkedList<>(List.of(LocalDate.of(2019, 1, 12), 42)));
        answers.add(new LinkedList<>(List.of(new LinkedList<>(List.of(true)))));
    }
}
