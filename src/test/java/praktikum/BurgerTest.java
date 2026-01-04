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

    @Test
    public void testGetPriceWithOneIngredient() {
        when(bunMock.getPrice()).thenReturn(100.0f);
        when(ingredientMock1.getPrice()).thenReturn(50.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        float price = burger.getPrice();

        assertEquals(250.0f, price, 0.001);
    }

    @Test
    public void testGetPriceWithMultipleIngredients() {
        when(bunMock.getPrice()).thenReturn(100.0f);
        when(ingredientMock1.getPrice()).thenReturn(50.0f);
        when(ingredientMock2.getPrice()).thenReturn(75.0f);
        when(ingredientMock3.getPrice()).thenReturn(25.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        float price = burger.getPrice();

        assertEquals(350.0f, price, 0.001);
    }

    @Test
    public void testGetReceiptWithOnlyBun() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();

        String expected = "(==== black bun ====)\n" +
                "(==== black bun ====)\n" +
                "\nPrice: 200,000000\n";
        assertEquals(expected, receipt);
    }

    @Test
    public void testGetReceiptWithOneIngredient() {
        when(bunMock.getName()).thenReturn("white bun");
        when(bunMock.getPrice()).thenReturn(200.0f);

        when(ingredientMock1.getName()).thenReturn("hot sauce");
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getPrice()).thenReturn(100.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        String receipt = burger.getReceipt();

        String expected = "(==== white bun ====)\n" +
                "= sauce hot sauce =\n" +
                "(==== white bun ====)\n" +
                "\nPrice: 500,000000\n";
        assertEquals(expected, receipt);
    }

    @Test
    public void testGetReceiptWithMultipleIngredients() {
        when(bunMock.getName()).thenReturn("red bun");
        when(bunMock.getPrice()).thenReturn(300.0f);

        when(ingredientMock1.getName()).thenReturn("hot sauce");
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getPrice()).thenReturn(100.0f);

        when(ingredientMock2.getName()).thenReturn("cutlet");
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getPrice()).thenReturn(200.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String receipt = burger.getReceipt();

        String expected = "(==== red bun ====)\n" +
                "= sauce hot sauce =\n" +
                "= filling cutlet =\n" +
                "(==== red bun ====)\n" +
                "\nPrice: 900,000000\n";
        assertEquals(expected, receipt);
    }

    @Test
    public void testGetReceiptIngredientTypesFormatting() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(ingredientMock1.getName()).thenReturn("sour cream");
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);

        when(ingredientMock2.getName()).thenReturn("dinosaur");
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("= sauce sour cream ="));
        assertTrue(receipt.contains("= filling dinosaur ="));
    }

    @Test
    public void testGetReceiptTotalPrice() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(ingredientMock1.getName()).thenReturn("hot sauce");
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getPrice()).thenReturn(100.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        String receipt = burger.getReceipt();
        float calculatedPrice = burger.getPrice();

        String priceString = String.format("\nPrice: %f\n", calculatedPrice);
        assertTrue(receipt.contains(priceString));
    }

    @Test
    public void testIngredientOrderAfterOperations() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        burger.removeIngredient(1);
        burger.addIngredient(ingredientMock2);
        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size());
        assertEquals(ingredientMock3, burger.ingredients.get(0));
        assertEquals(ingredientMock2, burger.ingredients.get(1));
        assertEquals(ingredientMock1, burger.ingredients.get(2));
    }

    @Test
    public void testBurgerStateConsistency() {
        when(bunMock.getName()).thenReturn("test bun");
        when(bunMock.getPrice()).thenReturn(50.0f);

        when(ingredientMock1.getName()).thenReturn("ingredient1");
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getPrice()).thenReturn(10.0f);

        when(ingredientMock2.getName()).thenReturn("ingredient2");
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getPrice()).thenReturn(20.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float price1 = burger.getPrice();
        String receipt1 = burger.getReceipt();

        burger.moveIngredient(0, 1);

        float price2 = burger.getPrice();
        String receipt2 = burger.getReceipt();

        assertEquals(price1, price2, 0.001);

        assertNotEquals(receipt1, receipt2);
    }
}
