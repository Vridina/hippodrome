import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HorseTest {


    public static final String NAME_CANNOT_BE_NULL = "Name cannot be null.";
    public static final String NAME_CANNOT_BE_BLANK = "Name cannot be blank.";
    public static final String SPEED_CANNOT_BE_NEGATIVE = "Speed cannot be negative.";
    public static final String DISTANCE_CANNOT_BE_NEGATIVE = "Distance cannot be negative.";
     public Horse horseT = Mockito.mock(Horse.class);

//    @BeforeEach
//@ParameterizedTest
//@CsvSource({
//        "alex, 30, Программист, Работает",
//        "brian, 35, Тестировщик, Работает",
//        "charles, 40, Менеджер, Пинает"
//})
//    public void initHorseTest() {
//
//        horseTest = Mockito.mock(Horse.class);
//
//        String s = anyString();
//        Double d = anyDouble();
//        verify(horseTest.getName())
//
////        Mockito.when(new Horse(null, anyDouble(), anyDouble()))
////                .thenThrow(IllegalArgumentException.class);
//
//
//        horseTest = Mockito.mock(Horse.class);
//        if (horseTest.getName() == null) {
//            Throwable exception = assertThrows(
//                    IllegalArgumentException.class, () -> {
//                        throw new IllegalArgumentException(NAME_CANNOT_BE_NULL);
//                    });
//            assertEquals(NAME_CANNOT_BE_NULL, exception.getMessage());
//        }
//        if (horseTest.getName().isBlank()) {
//            Throwable exception = assertThrows(
//                    IllegalArgumentException.class, () -> {
//                        throw new IllegalArgumentException(NAME_CANNOT_BE_BLANK);
//                    });
//            assertEquals(NAME_CANNOT_BE_BLANK, exception.getMessage());
//        }
//        if (horseTest.getSpeed() < 0) {
//            Throwable exception = assertThrows(
//                    IllegalArgumentException.class, () -> {
//                        throw new IllegalArgumentException(SPEED_CANNOT_BE_NEGATIVE);
//                    });
//            assertEquals(SPEED_CANNOT_BE_NEGATIVE, exception.getMessage());
//        }
//        if (horseTest.getDistance() < 0) {
//            Throwable exception = assertThrows(
//                    IllegalArgumentException.class, () -> {
//                        throw new IllegalArgumentException(DISTANCE_CANNOT_BE_NEGATIVE);
//                    });
//            assertEquals(DISTANCE_CANNOT_BE_NEGATIVE, exception.getMessage());
//        }
//
////            Horse horseWhite = new Horse(null, anyDouble(), anyDouble());
////        Mockito.when(new Horse(null, anyDouble(), anyDouble()))
////                .thenThrow(IllegalArgumentException.class);
////        Mockito.when(new Horse(null, anyDouble(), anyDouble()))
////                .thenThrow(IllegalArgumentException.class);
////        assertEquals("Name cannot be null.", .getMessage());
//    }


//    @BeforeAll
//    public void setup() {
//        cut = new CodeUnderTest(fakeRandom);
//        Math.random()
//    }

    @Test
    void getName() {
        Mockito.when(horseT.getName()).thenReturn(anyString());
    }

    @Test
    void getSpeed() {
        Mockito.when(horseT.getSpeed()).thenReturn(anyDouble());
    }

    @Test
    void getDistance() {
        Mockito.when(horseT.getDistance()).thenReturn(anyDouble());
    }


    @Test
    void move() {
        Mockito.doAnswer(distance -> {
            double s = horseT.getSpeed();
            double r = Horse.getRandomDouble(anyDouble(), anyDouble());
            return s * r;
        }).when(horseT).move();
    }

    @Test
    void getRandomDouble() {
        double min = 0.2;
        double max = 0.9;
        try (MockedStatic<Horse> util = Mockito.mockStatic(Horse.class)) {


            util.when(() -> Horse.getRandomDouble(min, max)).thenReturn(anyDouble());
            assertEquals(anyDouble(), Horse.getRandomDouble(min, max ));
        }
    }
}

