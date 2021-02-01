package be.pxl.ja.exercise74;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

public class CartUtil {
    public static BigDecimal checkout(List<CartItem> items) {
        return items.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal checkout(List<CartItem> items, Predicate<CartItem> predicate) {
        return items.stream()
                .filter(predicate)
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal checkoutWithDiscount(List<CartItem> items) {
        BigDecimal total = checkout(items);
        BigDecimal totalInShoes = checkout(items, item -> item.getName().equalsIgnoreCase("shoes"));

        int numberOfHats = (int) items.stream().filter(item -> item.getName().equalsIgnoreCase("hat")).count();
        BigDecimal totalInHats = checkout(items, item -> item.getName().equalsIgnoreCase("hat"));

        if (BigDecimal.valueOf(100).compareTo(totalInShoes) < 0) {
            total = total.subtract(totalInShoes.multiply(BigDecimal.valueOf(0.2)));
        }

        if (numberOfHats >= 2 && BigDecimal.valueOf(50).compareTo(totalInHats) <= 0) {
            total = total.subtract(BigDecimal.TEN);
        }

        return total;
    }
}
