package lk.inli.hackerrank;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Smoothie {

  private static final String ORDER_ITEM_SEPARATOR = ",";
  enum MenuType {CLASSIC, FREEZIE, GREENIE, JUST_DESSERTS}

  private static final String[] CLASSIC_MENU =
      {"strawberry", "banana", "pineapple", "mango", "peach", "honey"};
  private static final String[] FREEZIE_MENU =
      {"blackberry", "blueberry", "black currant", "grape juice", "frozen yogurt"};
  private static final String[] GREENIE_MENU =
      {"green apple", "lime", "avocado", "spinach", "ice", "apple juice"};
  private static final String[] JUST_DESSERTS_MENU =
      {"banana", "ice cream", "chocolate", "peanut", "cherry"};

  public static String ingredients(String order) {
    //Valid order: "Classic,-strawberry,-peanut" => "banana,honey,mango,peach,pineapple"
    //Invalid order: "Classic,chocolate" => IllegalArgumentException
    //Invalid order: "Vitamin smoothie" => IllegalArgumentException
    return getFilteredIngredients(order);
  }

  static List<String> getOrderDetails(String order) {
    if (null == order || order.isEmpty()) {
      throw new IllegalArgumentException("Empty order request");
    } else {
      return Arrays.stream(order.split(ORDER_ITEM_SEPARATOR))
          .map(String::trim)
          .collect(Collectors.toList());
    }
  }

  static MenuType getMenuFromOrder(String order) {
    return MenuType.valueOf(getOrderDetails(order)
        .get(0) //first element is the menu name
        .trim() //remove starting and ending spaces
        .replace(' ', '_') //replace middle space with '_' to match the enums
        .toUpperCase()); //convert to upper case to match the enums
  }

  private static List<String> getAllergens(String order) {
    List<String> orderDetails = getOrderDetails(order);

    if (orderDetails.size() > 1) {
      //first element is the menu name so start from 1st element
      return IntStream.range(1, orderDetails.size())
          .filter(i -> (null != orderDetails.get(i) && orderDetails.get(i).startsWith("-")))
          .mapToObj(i -> orderDetails.get(i).substring(1))
          .collect(Collectors.toList());
    } else {
      //no allergens
      return Collections.emptyList();
    }
  }

  static boolean hasAdditionalIngredients(String order) {
    List<String> orderDetails = getOrderDetails(order);
    MenuType menuName = getMenuFromOrder(order);
    String[] standardIngredients = getMenuIngredients(menuName);

    if (orderDetails.size() > 1) {
      Optional<String> addIngredient = IntStream.range(1, orderDetails.size())
          .filter(i -> (null != orderDetails.get(i) && !orderDetails.get(i).startsWith("-")))
          .mapToObj(orderDetails::get)
          /*
          * It was not clear how should we address if standard ingredient in the menu is given
          * in the order as additional ingredient.
          * Ex: "Classic,strawberry,-peanut"
          * So, such scenarios were considered valid
          */
          .filter(orderItem ->
              Arrays.stream(standardIngredients)
                  .noneMatch(validIngredient -> validIngredient.equalsIgnoreCase(orderItem)))
          .findAny();

      if (addIngredient.isPresent()) {
        throw new IllegalArgumentException("Additional ingredients are not supported");
      } else {
        return Boolean.FALSE;
      }
    } else {
      return Boolean.FALSE;
    }
  }

  private static String[] getMenuIngredients(MenuType menuType) {
    if (menuType.equals(MenuType.CLASSIC)) {
      return CLASSIC_MENU;
    } else if (menuType.equals(MenuType.FREEZIE)) {
      return FREEZIE_MENU;
    } else if (menuType.equals(MenuType.GREENIE)) {
      return GREENIE_MENU;
    } else if (menuType.equals(MenuType.JUST_DESSERTS)) {
      return JUST_DESSERTS_MENU;
    } else {
      throw new IllegalArgumentException("Menu is not supported");
    }
  }

  private static String getFilteredIngredients(String order) {
    if (!hasAdditionalIngredients(order)) {
      MenuType menuType = getMenuFromOrder(order);
      String[] menuIngredients = getMenuIngredients(menuType);
      List<String> allergens = getAllergens(order);

      return Arrays.stream(menuIngredients)
          .filter(ingredient ->
              allergens.stream()
                .noneMatch(allergen -> allergen.equalsIgnoreCase(ingredient)))
          .sorted()
          .collect(Collectors.joining(ORDER_ITEM_SEPARATOR));
    } else {
      return "";
    }
  }
}
