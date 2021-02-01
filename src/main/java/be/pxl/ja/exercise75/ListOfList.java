package be.pxl.ja.exercise75;

import java.util.List;

public class ListOfList {
    public static int sumLargest(List<List<Integer>> listList) {
        return listList.stream()
                .mapToInt(list -> list.stream().mapToInt(Integer::intValue).max().getAsInt())
                .sum();
    }
}
