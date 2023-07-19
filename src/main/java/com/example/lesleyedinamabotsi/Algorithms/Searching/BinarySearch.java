package com.example.lesleyedinamabotsi.Algorithms.Searching;
import com.example.lesleyedinamabotsi.data.Entity;
import java.util.List;

public class BinarySearch {
    public static Entity binarySearch(List<Entity> goods, int id) {
        int low = 0;
        int high = goods.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            Entity midGood = goods.get(mid);
            if (midGood.getId() == id) {
                return midGood;
            } else if (midGood.getId() < id) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}
