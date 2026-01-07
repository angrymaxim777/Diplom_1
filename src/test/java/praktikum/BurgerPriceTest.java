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
public class BurgerPriceTest {
    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient firstIngredientMock;

    @Mock
    private Ingredient secondIngredientMock;

    private Burger burger;

    private float bunPrice;
    private float firstIngredientPrice;
    private float secondIngredientPrice;
    private float expectedTotal;

    public BurgerPriceTest(float bunPrice, float firstIngredientPrice, float secondIngredientPrice, float expectedTotal) {
        this.bunPrice = bunPrice;
        this.firstIngredientPrice = firstIngredientPrice;
        this.secondIngredientPrice = secondIngredientPrice;
        this.expectedTotal = expectedTotal;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {100.0f, 50.0f, 75.0f, 325.0f},
                {200.0f, 100.0f, 0.0f, 500.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {150.5f, 30.25f, 45.75f, 377.0f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();

        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(firstIngredientMock.getPrice()).thenReturn(firstIngredientPrice);
        when(secondIngredientMock.getPrice()).thenReturn(secondIngredientPrice);

        when(bunMock.getName()).thenReturn("test bun");
        when(firstIngredientMock.getName()).thenReturn("ingredient1");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(secondIngredientMock.getName()).thenReturn("ingredient2");
        when(secondIngredientMock.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void testGetPriceParameterized() {
        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        float actualPrice = burger.getPrice();

        assertEquals(expectedTotal, actualPrice, 0.001);
    }
}
