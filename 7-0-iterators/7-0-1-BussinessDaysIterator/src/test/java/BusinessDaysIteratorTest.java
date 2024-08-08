import com.mramirez.BusinessDaysIterator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusinessDaysIteratorTest {

    private static final Queue<String> answers = new LinkedList<>();

    @BeforeAll
    static void setUp() {
        fillAnswers();
    }

    @ParameterizedTest
    @MethodSource("argsStream")
    public void testIterator(LocalDate date, int daysToSkip) {
        BusinessDaysIterator businessDaysIterator = new BusinessDaysIterator(date);
        LocalDate actualDate = LocalDate.now();
        while(daysToSkip-- > 0) { actualDate = businessDaysIterator.next(); }
        assertEquals(answers.poll(), actualDate.toString());
    }

    static Stream<Arguments> argsStream() {
        return Stream.of(Arguments.of(LocalDate.of(2001, 12, 12), 3),
                Arguments.of(LocalDate.of(2021, 1, 12), 32),
                Arguments.of(LocalDate.of(2002, 12, 11), 33),
                Arguments.of(LocalDate.of(2031, 12, 22), 22),
                Arguments.of(LocalDate.of(2019, 1, 12), 42));
    }

    private static void fillAnswers() {
        answers.add("2001-12-17");
        answers.add("2021-02-25");
        answers.add("2003-01-27");
        answers.add("2032-01-21");
        answers.add("2019-03-12");
    }
}
