package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerMoveIngredientTest {
    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock1;

    @Mock
    private Ingredient ingredientMock2;

    @Mock
    private Ingredient ingredientMock3;

    private Burger burger;

    private int fromIndex;
    private int toIndex;

    public BurgerMoveIngredientTest(int fromIndex, int toIndex) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, 1},
                {1, 0},
                {0, 2},
                {2, 0},
                {1, 2},
                {2, 1}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();

        when(ingredientMock1.getName()).thenReturn("ingredient1");
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);

        when(ingredientMock2.getName()).thenReturn("ingredient2");
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);

        when(ingredientMock3.getName()).thenReturn("ingredient3");
        when(ingredientMock3.getType()).thenReturn(IngredientType.SAUCE);

        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);
    }

    @Test
    public void testMoveIngredientParameterized() {
        Ingredient ingredientToMove = burger.ingredients.get(fromIndex);

        burger.moveIngredient(fromIndex, toIndex);

        assertEquals(ingredientToMove, burger.ingredients.get(toIndex));
        assertEquals(3, burger.ingredients.size());

        long distinctCount = burger.ingredients.stream().distinct().count();
        assertEquals(3, distinctCount);
    }
}
