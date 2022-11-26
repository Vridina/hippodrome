import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

class HorseTest {


    public static final String NAME_CANNOT_BE_NULL = "Name cannot be null.";
    public static final String NAME_CANNOT_BE_BLANK = "Name cannot be blank.";
    public static final String SPEED_CANNOT_BE_NEGATIVE = "Speed cannot be negative.";
    public static final String DISTANCE_CANNOT_BE_NEGATIVE = "Distance cannot be negative.";
    public static final String NAME = "Name";

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
    void getSpeed() {                                               //упрощенный вариант без рефлексии
        Horse horse = new Horse(NAME, 1, 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse(NAME, 1, 1);
        assertEquals(1, horse.getDistance());
    }

    @Test
    void getDistanceZero() {
        Horse horse = new Horse(NAME, 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse(NAME, 1, 1).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }
}

