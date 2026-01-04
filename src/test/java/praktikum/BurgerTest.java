package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock1;

    @Mock
    private Ingredient ingredientMock2;

    @Mock
    private Ingredient ingredientMock3;

    private Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        burger.setBuns(bunMock);

        assertSame(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        when(ingredientMock1.getName()).thenReturn("hot sauce");
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getPrice()).thenReturn(100.0f);

        burger.addIngredient(ingredientMock1);

        assertEquals(1, burger.ingredients.size());
        assertSame(ingredientMock1, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredients() {
        when(ingredientMock1.getName()).thenReturn("hot sauce");
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);

        when(ingredientMock2.getName()).thenReturn("cutlet");
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        assertEquals(2, burger.ingredients.size());
        assertSame(ingredientMock1, burger.ingredients.get(0));
        assertSame(ingredientMock2, burger.ingredients.get(1));
    }

    @Test
    public void testRemoveIngredientByIndex() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(ingredientMock2, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientWithInvalidIndex() {
        burger.addIngredient(ingredientMock1);

        burger.removeIngredient(5);
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        List<Ingredient> originalOrder = List.of(ingredientMock1, ingredientMock2, ingredientMock3);

        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(0));
        assertEquals(ingredientMock3, burger.ingredients.get(1));
        assertEquals(ingredientMock1, burger.ingredients.get(2));
    }

    @Test
    public void testGetPriceWithNoIngredients() {
        when(bunMock.getPrice()).thenReturn(100.0f);
        burger.setBuns(bunMock);

        float price = burger.getPrice();

        assertEquals(200.0f, price, 0.001);
    }

}
