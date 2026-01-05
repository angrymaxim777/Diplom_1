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
    private Ingredient ingredientMock1;

    @Mock
    private Ingredient ingredientMock2;

    private Burger burger;

    private float bunPrice;
    private float ingredientPrice1;
    private float ingredientPrice2;
    private float expectedTotal;

    public BurgerPriceTest(float bunPrice, float ingredientPrice1, float ingredientPrice2, float expectedTotal) {
        this.bunPrice = bunPrice;
        this.ingredientPrice1 = ingredientPrice1;
        this.ingredientPrice2 = ingredientPrice2;
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
        when(ingredientMock1.getPrice()).thenReturn(ingredientPrice1);
        when(ingredientMock2.getPrice()).thenReturn(ingredientPrice2);

        when(bunMock.getName()).thenReturn("test bun");
        when(ingredientMock1.getName()).thenReturn("ingredient1");
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock2.getName()).thenReturn("ingredient2");
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void testGetPriceParameterized() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float actualPrice = burger.getPrice();

        assertEquals(expectedTotal, actualPrice, 0.001);
    }
}
