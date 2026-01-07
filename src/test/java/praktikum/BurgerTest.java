package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient firstIngredientMock;

    @Mock
    private Ingredient secondIngredientMock;

    @Mock
    private Ingredient thirdIngredientMock;

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
    public void testAddIngredientIncreasesSize() {
        when(firstIngredientMock.getName()).thenReturn("hot sauce");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredientMock.getPrice()).thenReturn(100.0f);

        burger.addIngredient(firstIngredientMock);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        when(firstIngredientMock.getName()).thenReturn("hot sauce");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredientMock.getPrice()).thenReturn(100.0f);

        burger.addIngredient(firstIngredientMock);

        assertSame(firstIngredientMock, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredientsSize() {
        when(firstIngredientMock.getName()).thenReturn("hot sauce");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);

        when(secondIngredientMock.getName()).thenReturn("cutlet");
        when(secondIngredientMock.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testAddMultipleIngredientsFirstElement() {
        // Arrange
        when(firstIngredientMock.getName()).thenReturn("hot sauce");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);

        when(secondIngredientMock.getName()).thenReturn("cutlet");
        when(secondIngredientMock.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        assertSame(firstIngredientMock, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredientsSecondElement() {
        when(firstIngredientMock.getName()).thenReturn("hot sauce");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);

        when(secondIngredientMock.getName()).thenReturn("cutlet");
        when(secondIngredientMock.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        assertSame(secondIngredientMock, burger.ingredients.get(1));
    }

    @Test
    public void testRemoveIngredientByIndexSize() {
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientByIndexRemainingElement() {
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        burger.removeIngredient(0);

        assertSame(secondIngredientMock, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientWithInvalidIndex() {
        burger.addIngredient(firstIngredientMock);

        burger.removeIngredient(5);
    }

    @Test
    public void testMoveIngredientSizeRemainsSame() {
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);
        burger.addIngredient(thirdIngredientMock);

        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientNewFirstElement() {
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);
        burger.addIngredient(thirdIngredientMock);

        burger.moveIngredient(0, 2);

        assertEquals(secondIngredientMock, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientNewSecondElement() {
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);
        burger.addIngredient(thirdIngredientMock);

        burger.moveIngredient(0, 2);

        assertEquals(thirdIngredientMock, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredientNewThirdElement() {
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);
        burger.addIngredient(thirdIngredientMock);

        burger.moveIngredient(0, 2);

        assertEquals(firstIngredientMock, burger.ingredients.get(2));
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
        when(firstIngredientMock.getPrice()).thenReturn(50.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);

        float price = burger.getPrice();

        assertEquals(250.0f, price, 0.001);
    }

    @Test
    public void testGetPriceWithMultipleIngredients() {
        when(bunMock.getPrice()).thenReturn(100.0f);
        when(firstIngredientMock.getPrice()).thenReturn(50.0f);
        when(secondIngredientMock.getPrice()).thenReturn(75.0f);
        when(thirdIngredientMock.getPrice()).thenReturn(25.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);
        burger.addIngredient(thirdIngredientMock);

        float price = burger.getPrice();

        assertEquals(350.0f, price, 0.001);
    }

    @Test
    public void testGetReceiptWithOnlyBun() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();

        String lineSeparator = System.lineSeparator();
        String expected = "(==== black bun ====)" + lineSeparator +
                "(==== black bun ====)" + lineSeparator +
                lineSeparator +
                "Price: 200,000000" + lineSeparator;
        assertEquals(expected, receipt);
    }

    @Test
    public void testGetReceiptWithOneIngredient() {
        when(bunMock.getName()).thenReturn("white bun");
        when(bunMock.getPrice()).thenReturn(200.0f);

        when(firstIngredientMock.getName()).thenReturn("hot sauce");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredientMock.getPrice()).thenReturn(100.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);

        String receipt = burger.getReceipt();

        String lineSeparator = System.lineSeparator();
        String expected = "(==== white bun ====)" + lineSeparator +
                "= sauce hot sauce =" + lineSeparator +
                "(==== white bun ====)" + lineSeparator +
                lineSeparator +
                "Price: 500,000000" + lineSeparator;
        assertEquals(expected, receipt);
    }

    @Test
    public void testGetReceiptWithMultipleIngredients() {
        when(bunMock.getName()).thenReturn("red bun");
        when(bunMock.getPrice()).thenReturn(300.0f);

        when(firstIngredientMock.getName()).thenReturn("hot sauce");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredientMock.getPrice()).thenReturn(100.0f);

        when(secondIngredientMock.getName()).thenReturn("cutlet");
        when(secondIngredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(secondIngredientMock.getPrice()).thenReturn(200.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        String receipt = burger.getReceipt();

        String lineSeparator = System.lineSeparator();
        String expected = "(==== red bun ====)" + lineSeparator +
                "= sauce hot sauce =" + lineSeparator +
                "= filling cutlet =" + lineSeparator +
                "(==== red bun ====)" + lineSeparator +
                lineSeparator +
                "Price: 900,000000" + lineSeparator;
        assertEquals(expected, receipt);
    }

    @Test
    public void testGetReceiptIngredientTypesFormattingForSauce() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(firstIngredientMock.getName()).thenReturn("sour cream");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("= sauce sour cream ="));
    }

    @Test
    public void testGetReceiptIngredientTypesFormattingForFilling() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(secondIngredientMock.getName()).thenReturn("dinosaur");
        when(secondIngredientMock.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(bunMock);
        burger.addIngredient(secondIngredientMock);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("= filling dinosaur ="));
    }

    @Test
    public void testGetReceiptContainsPriceLine() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(firstIngredientMock.getName()).thenReturn("hot sauce");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredientMock.getPrice()).thenReturn(100.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать строку 'Price: '", receipt.contains("Price: "));
    }

    @Test
    public void testGetReceiptTotalPriceMatchesCalculation() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(firstIngredientMock.getName()).thenReturn("hot sauce");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredientMock.getPrice()).thenReturn(100.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);

        String receipt = burger.getReceipt();
        float calculatedPrice = burger.getPrice();

        assertTrue(receipt.contains(String.format("Price: %f", calculatedPrice)));
    }

    @Test
    public void testIngredientOrderAfterOperationsSize() {
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);
        burger.addIngredient(thirdIngredientMock);

        burger.removeIngredient(1);
        burger.addIngredient(secondIngredientMock);
        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void testIngredientOrderAfterOperationsFirstElement() {
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);
        burger.addIngredient(thirdIngredientMock);

        burger.removeIngredient(1);
        burger.addIngredient(secondIngredientMock);
        burger.moveIngredient(0, 2);

        assertEquals(thirdIngredientMock, burger.ingredients.get(0));
    }

    @Test
    public void testIngredientOrderAfterOperationsSecondElement() {
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);
        burger.addIngredient(thirdIngredientMock);

        burger.removeIngredient(1);
        burger.addIngredient(secondIngredientMock);
        burger.moveIngredient(0, 2);

        assertEquals(secondIngredientMock, burger.ingredients.get(1));
    }

    @Test
    public void testIngredientOrderAfterOperationsThirdElement() {
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);
        burger.addIngredient(thirdIngredientMock);

        burger.removeIngredient(1);
        burger.addIngredient(secondIngredientMock);
        burger.moveIngredient(0, 2);

        assertEquals(firstIngredientMock, burger.ingredients.get(2));
    }

    @Test
    public void testBurgerStateConsistencyPrice() {
        when(bunMock.getName()).thenReturn("test bun");
        when(bunMock.getPrice()).thenReturn(50.0f);

        when(firstIngredientMock.getName()).thenReturn("ingredient1");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredientMock.getPrice()).thenReturn(10.0f);

        when(secondIngredientMock.getName()).thenReturn("ingredient2");
        when(secondIngredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(secondIngredientMock.getPrice()).thenReturn(20.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        float price1 = burger.getPrice();

        burger.moveIngredient(0, 1);

        float price2 = burger.getPrice();

        assertEquals(price1, price2, 0.001);
    }

    @Test
    public void testBurgerStateConsistencyReceiptChanges() {
        when(bunMock.getName()).thenReturn("test bun");
        when(bunMock.getPrice()).thenReturn(50.0f);

        when(firstIngredientMock.getName()).thenReturn("ingredient1");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredientMock.getPrice()).thenReturn(10.0f);

        when(secondIngredientMock.getName()).thenReturn("ingredient2");
        when(secondIngredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(secondIngredientMock.getPrice()).thenReturn(20.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        String receipt1 = burger.getReceipt();

        burger.moveIngredient(0, 1);

        String receipt2 = burger.getReceipt();

        assertNotEquals(receipt1, receipt2);
    }
}