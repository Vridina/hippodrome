import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {


    public static final String HORSES_CANNOT_BE_NULL = "Horses cannot be null.";
    public static final String HORSES_CANNOT_BE_EMPTY = "Horses cannot be empty.";

    @Test
    void hippodromeNotNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals(HORSES_CANNOT_BE_NULL, e.getMessage());
    }

    @Test
    void setHorsesListCannotBeNull() {
        ArrayList<Horse> horses = new ArrayList<>();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals(HORSES_CANNOT_BE_EMPTY, e.getMessage());
    }


    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Horse horse = new Horse("Name" + i, i, i);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = mock(Horse.class);
            horses.add(horse);
        }

        new Hippodrome(horses).move();

        for (Horse h : horses) {
            verify(h).move();
        }

    }

        @Test
        void getWinnerTest () {
            Horse horse1 = new Horse("1", 1.1, 1);
            Horse horse2 = new Horse("2", 2, 2.12);
            Horse horse3 = new Horse("3", 1.1, 3);
            Horse horse4 = new Horse("4", 4, 4);
            Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4));

            Horse winner = hippodrome.getWinner();

            assertSame(horse4, winner);
        }
}