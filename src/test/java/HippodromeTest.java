import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    Hippodrome hippodromeT = mock(Hippodrome.class);
    List<Horse> horseListT = Collections.singletonList(mock(Horse.class));

    @Test
    void getHorsesTest() {
        doReturn(horseListT).when(hippodromeT).getHorses();
    }

    @Test
    void moveTest() {
//
//       Horse horse = any(Horse.class);
//       when(horseListT.forEach()
//
//        doAnswer(
//                () -> {
//                    horseListT.forEach();
//                }
//        )

    }

    @Test
    void getWinnerTest() {
    }
}