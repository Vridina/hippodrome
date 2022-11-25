import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class HorseTest {


    public static final String NAME_CANNOT_BE_NULL = "Name cannot be null.";
    public static final String NAME_CANNOT_BE_BLANK = "Name cannot be blank.";
    public static final String SPEED_CANNOT_BE_NEGATIVE = "Speed cannot be negative.";
    public static final String DISTANCE_CANNOT_BE_NEGATIVE = "Distance cannot be negative.";
    public static final String NAME = "Name";
    public Horse horseT = Mockito.mock(Horse.class);

    @Test
    public void nameCannotBeNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 1, 1));
        assertEquals(NAME_CANNOT_BE_NULL, e.getMessage());
    }


    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\n", "\t"})
    void nameCannotBeBlank(String s) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Horse(s, 1, 1));
        assertEquals(NAME_CANNOT_BE_BLANK, e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -2232.0, -5, -345L})
    void speedCannotBeNegative(double speed) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Horse(NAME, speed, 1));
        assertEquals(SPEED_CANNOT_BE_NEGATIVE, e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -2232.0, -5, -345L})
    void setDistanceCannotBeNegative(double distance) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Horse(NAME, 1, distance));
        assertEquals(DISTANCE_CANNOT_BE_NEGATIVE, e.getMessage());
    }

    @Test
    void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse(NAME, 1, 1);         //создаем объект образец
        Field name = Horse.class.getDeclaredField("name");   //рефлексим поле имя
        name.setAccessible(true);                                  //разрешаем  к нему доступ
        String n = (String) name.get(horse);                       //получаем значение поля у объекта horse
        assertEquals(NAME, n);                                     //сравниваем
//        Mockito.when(horseT.getName()).thenReturn(anyString());
    }

    @Test
    void getSpeedTest() {
        Mockito.when(horseT.getSpeed()).thenReturn(anyDouble());
    }

    @Test
    void getDistanceTest() {
        Mockito.when(horseT.getDistance()).thenReturn(anyDouble());
    }


    @Test
    void moveTest() {
        Mockito.doAnswer(distance -> {
            double s = horseT.getSpeed();
            double r = Horse.getRandomDouble(anyDouble(), anyDouble());
            return s * r;
        }).when(horseT).move();
    }

    @Test
    void getRandomDoubleTest() {
        double min = 0.2;
        double max = 0.9;
        try (MockedStatic<Horse> util = Mockito.mockStatic(Horse.class)) {


            util.when(() -> Horse.getRandomDouble(min, max)).thenReturn(anyDouble());
            assertEquals(anyDouble(), Horse.getRandomDouble(min, max));
        }
    }
}

