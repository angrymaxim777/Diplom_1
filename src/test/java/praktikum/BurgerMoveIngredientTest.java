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
    private Ingredient firstIngredientMock;

    @Mock
    private Ingredient secondIngredientMock;

    @Mock
    private Ingredient thirdIngredientMock;

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

        when(firstIngredientMock.getName()).thenReturn("ingredient1");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);

        when(secondIngredientMock.getName()).thenReturn("ingredient2");
        when(secondIngredientMock.getType()).thenReturn(IngredientType.FILLING);

        when(thirdIngredientMock.getName()).thenReturn("ingredient3");
        when(thirdIngredientMock.getType()).thenReturn(IngredientType.SAUCE);

        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);
        burger.addIngredient(thirdIngredientMock);
    }

    @Test
    public void testMoveIngredientChangesPosition() {
        Ingredient ingredientToMove = burger.ingredients.get(fromIndex);

        burger.moveIngredient(fromIndex, toIndex);

        assertEquals("Ингредиент должен переместиться на новую позицию",
                ingredientToMove, burger.ingredients.get(toIndex));
    }
}
