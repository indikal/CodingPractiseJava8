package lk.inli.hackerrank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import lk.inli.hackerrank.Smoothie.MenuType;
import org.junit.Test;

public class SmoothieTest {

  @Test(expected = IllegalArgumentException.class)
  public void emptyOrder() {
    Smoothie.ingredients("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void newMenuOrder() {
    Smoothie.ingredients("Vitamin");
  }

  @Test(expected = IllegalArgumentException.class)
  public void menuOrderWithDifferentSeparator() {
    Smoothie.ingredients("Classic;-strawberry;-peanut");
  }

  @Test(expected = IllegalArgumentException.class)
  public void additionalIngredientInTheOrder() {
    Smoothie.ingredients("Classic,chocolate");
  }

  @Test
  public void additionalStandardIngredientInTheOrder() {
    assertEquals("banana,honey,mango,peach,pineapple,strawberry",
        Smoothie.ingredients("Classic,strawberry,-peanut"));
  }

  @Test
  public void classicSmoothieWithTwoAllergies() {
    assertEquals("banana,honey,mango,peach,pineapple",
        Smoothie.ingredients("Classic,-strawberry,-peanut"));
  }

  @Test
  public void classicSmoothieWithEmptyAllergens() {
    assertEquals("banana,honey,mango,peach,pineapple,strawberry",
        Smoothie.ingredients("Classic,-"));
  }

  @Test
  public void classicSmoothieWithNoneExistingAllergies() {
    assertEquals("banana,honey,mango,peach,pineapple,strawberry",
        Smoothie.ingredients("Classic,-guava,-jelly"));
  }

  @Test
  public void classicSmoothie() {
    assertEquals("banana,honey,mango,peach,pineapple,strawberry",
        Smoothie.ingredients("Classic"));
  }

  @Test
  public void classicSmoothieWithoutStrawberry() {
    assertEquals("banana,honey,mango,peach,pineapple",
        Smoothie.ingredients("Classic,-strawberry"));
  }

  @Test
  public void classicSmoothieOutputInorder() {
    assertEquals("banana,honey,mango,peach,pineapple,strawberry",
        Smoothie.ingredients("Classic"));
  }

  @Test
  public void classicSmoothieOutputDisorder() {
    assertNotEquals("mango,peach,pineapple,banana,honey,strawberry",
        Smoothie.ingredients("Classic"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void classicSmoothieWithAllergiesWithAdditions() {
    Smoothie.ingredients("Classic,-strawberry,-peanut,chocolate");
  }

  @Test(expected = IllegalArgumentException.class)
  public void classicSmoothieWithNoneExistingAllergiesWithAdditions() {
    Smoothie.ingredients("Classic,-guava,-jelly,chocolate");
  }

  @Test
  public void testGetMenuFromOrder() {
    assertEquals(MenuType.CLASSIC,
        Smoothie.getMenuFromOrder("Classic,-guava,-strawberry,chocolate"));
    assertEquals(MenuType.FREEZIE,
        Smoothie.getMenuFromOrder("Freezie,-guava,-strawberry,chocolate"));
    assertEquals(MenuType.GREENIE,
        Smoothie.getMenuFromOrder("Greenie,-guava,-strawberry,chocolate"));
    assertEquals(MenuType.JUST_DESSERTS,
        Smoothie.getMenuFromOrder("Just Desserts,-guava,-strawberry,chocolate"));
  }

  @Test
  public void testHasAdditionalIngredientsSuccess() {
    assertFalse(Smoothie.hasAdditionalIngredients("Classic,-guava,-strawberry"));
    assertFalse(Smoothie.hasAdditionalIngredients("Freezie,-guava,-strawberry"));
    assertFalse(Smoothie.hasAdditionalIngredients("Greenie,-guava,-strawberry"));
    assertFalse(Smoothie.hasAdditionalIngredients("Just Desserts,-guava,-strawberry"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHasAdditionalIngredientsFailure() {
    Smoothie.hasAdditionalIngredients("Classic,-guava,-strawberry,chocolate");
    Smoothie.hasAdditionalIngredients("Freezie,-guava,-strawberry,chocolate");
    Smoothie.hasAdditionalIngredients("Greenie,-guava,-strawberry,chocolate");
    Smoothie.hasAdditionalIngredients("Just Desserts,-guava,-strawberry,chocolate");
  }
}
