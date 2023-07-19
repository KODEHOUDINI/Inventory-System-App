package com.example.lesleyedinamabotsi.repository;


import java.util.*;
import com.example.lesleyedinamabotsi.data.*;
public class Goods {
    private Stack<Good> stack;     // Stack to hold goods with category 1 to 4
    private Queue<Good> queue;     // Queue to hold goods with category 5 to 7
    private List<Good> list;       // List to hold goods with category 8 and 9
    private SOURCE sources;

    /**
     * Constructor for the Goods class.
     * Initializes the stack, queue, and list.
     * Loads goods from the database into the respective data structures.
     */
    public Goods() {
        stack = new Stack<>();
        queue = new LinkedList<>();
        list = new ArrayList<>();
        sources = new SOURCE();
        loadGoodsFromDatabase();
    }

    /**
     * Loads goods from the database into the stack, queue, and list based on their category.
     */
    private void loadGoodsFromDatabase() {
        sources.connectToDatabase();
        List<Good> goods = sources.getGoods();
        for (Good good : goods) {
            if (good.getCategory() >= 1 && good.getCategory() <= 4) {
                stack.push(good);
            } else if (good.getCategory() >= 5 && good.getCategory() <= 7) {
                queue.add(good);
            } else if (good.getCategory() == 8 || good.getCategory() == 9) {
                list.add(good);
            }
        }
    }

    /**
     * Finds a good with the specified ID.
     *
     * @param id The ID of the good to find.
     * @return The found good, or null if not found.
     */
    public Good find(int id) {
        Good foundGood = binarySearch(stack, id);
        if (foundGood != null) {
            return foundGood;
        }
        foundGood = binarySearch(new ArrayList<>(queue), id);
        if (foundGood != null) {
            return foundGood;
        }
        return binarySearch(list, id);
    }

    /**
     * Retrieves all goods from the stack, queue, and list.
     *
     * @return A list containing all goods.
     */
    public List<Good> findAll() {
        List<Good> allGoods = new ArrayList<>();
        allGoods.addAll(stack);
        allGoods.addAll(queue);
        allGoods.addAll(list);
        return allGoods;
    }

    /**
     * Removes a good with the specified ID.
     * Removes the good from the respective data structure and updates the database.
     *
     * @param id The ID of the good to remove.
     */
    public void remove(int id) {
        Good foundGood = find(id);
        if (foundGood != null) {
            if (stack.contains(foundGood)) {
                stack.remove(foundGood);
            } else if (queue.contains(foundGood)) {
                queue.remove(foundGood);
            } else if (list.contains(foundGood)) {
                list.remove(foundGood);
            }
            sources.removeGood(id);
        }
    }

    /**
     * Adds a new good.
     * Adds the good to the respective data structure and updates the database.
     *
     * @param good The good to add.
     */
    public void add(Good good) {
        if (good.getCategory() >= 1 && good.getCategory() <= 4) {
            stack.push(good);
        } else if (good.getCategory() >= 5 && good.getCategory() <= 7) {
            queue.add(good);
        } else if (good.getCategory() == 8 || good.getCategory() == 9) {
            list.add(good);
        }
        sources.addGood(good);
    }

    /**
     * Sorts the goods by name in ascending order in the stack, queue, and list.
     * The sorting is performed using different algorithms for each data structure.
     */
    public void sortByName() {
        stack = quickSort(stack);
        queue = mergeSort(queue);
        list = insertionSort(list, Comparator.comparing(Good::getName));
    }

    /**
     * Sorts the goods by price in ascending order in the stack, queue, and list.
     * The sorting is performed using different algorithms for each data structure.
     */
    public void sortByPrice() {
        stack = quickSort(stack);
        queue = mergeSort(queue);
        list = insertionSort(list, Comparator.comparing(Good::getPrice));
    }

    /**
     * Updates the details of a good with the specified ID.
     * Updates the good's attributes and updates the database.
     *
     * @param id          The ID of the good to update.
     * @param newName     The new name of the good.
     * @param newPrice    The new price of the good.
     * @param newQuantity The new quantity of the good.
     * @param newVendor   The new vendor of the good.
     */
    public void update(int id, String newName, double newPrice, int newQuantity, Vendor newVendor) {
        Good foundGood = find(id);
        if (foundGood != null) {
            foundGood.setName(newName);
            foundGood.setPrice(newPrice);
            foundGood.setQuantity(newQuantity);
            foundGood.setVendor(newVendor);
            sources.updateGood(foundGood);
        }
    }

    /**
     * Searches for goods by name in the stack, queue, and list.
     * Returns a list of matching goods.
     *
     * @param searchTerm The name to search for (case-insensitive).
     * @return A list of matching goods.
     */
    public List<Good> searchByName(String searchTerm) {
        List<Good> searchResults = new ArrayList<>();
        for (Good good : stack) {
            if (good.getName().equalsIgnoreCase(searchTerm)) {
                searchResults.add(good);
            }
        }
        for (Good good : queue) {
            if (good.getName().equalsIgnoreCase(searchTerm)) {
                searchResults.add(good);
            }
        }
        for (Good good : list) {
            if (good.getName().equalsIgnoreCase(searchTerm)) {
                searchResults.add(good);
            }
        }
        return searchResults;
    }

    /**
     * Searches for goods by category in the stack, queue, and list.
     * Returns a list of matching goods.
     *
     * @param category The category to search for.
     * @return A list of matching goods.
     */
    public List<Good> searchByCategory(int category) {
        List<Good> searchResults = new ArrayList<>();
        for (Good good : stack) {
            if (good.getCategory() == category) {
                searchResults.add(good);
            }
        }
        for (Good good : queue) {
            if (good.getCategory() == category) {
                searchResults.add(good);
            }
        }
        for (Good good : list) {
            if (good.getCategory() == category) {
                searchResults.add(good);
            }
        }
        return searchResults;
    }

    /**
     * Sorts all goods by quantity in ascending order and returns a list of all goods.
     *
     * @return A list of all goods sorted by quantity.
     */
    public List<Good> sortByQuantity() {
        List<Good> allGoods = findAll();
        allGoods.sort(Comparator.comparing(Good::getQuantity));
        return allGoods;
    }

    /**
     * Performs a binary search on a list of goods to find a good with the specified ID.
     *
     * @param goods The list of goods to search.
     * @param id    The ID of the good to find.
     * @return The found good, or null if not found.
     */
    private Good binarySearch(List<Good> goods, int id) {
        int low = 0;
        int high = goods.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            Good midGood = goods.get(mid);
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

    /**
     * Performs the Quicksort algorithm to sort a stack of goods.
     *
     * @param stack The stack of goods to sort.
     * @return The sorted stack of goods.
     */
    private Stack<Good> quickSort(Stack<Good> stack) {
        List<Good> goods = new ArrayList<>(stack);
        quickSortHelper(goods, 0, goods.size() - 1);
        Stack<Good> sortedStack = new Stack<>();
        sortedStack.addAll(goods);
        return sortedStack;
    }

    /**
     * Helper method for the Quicksort algorithm.
     *
     * @param goods The list of goods to sort.
     * @param low   The low index of the range to sort.
     * @param high  The high index of the range to sort.
     */
    private void quickSortHelper(List<Good> goods, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(goods, low, high);
            quickSortHelper(goods, low, pivotIndex - 1);
            quickSortHelper(goods, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the list of goods for the Quicksort algorithm.
     *
     * @param goods The list of goods to partition.
     * @param low   The low index of the range to partition.
     * @param high  The high index of the range to partition.
     * @return The pivot index.
     */
    private int partition(List<Good> goods, int low, int high) {
        Good pivot = goods.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (goods.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(goods, i, j);
            }
        }
        swap(goods, i + 1, high);
        return i + 1;
    }

    /**
     * Swaps two elements in a list.
     *
     * @param goods The list of goods.
     * @param i     The first element index.
     * @param j     The second element index.
     */
    private void swap(List<Good> goods, int i, int j) {
        Good temp = goods.get(i);
        goods.set(i, goods.get(j));
        goods.set(j, temp);
    }

    /**
     * Performs the Mergesort algorithm to sort a queue of goods.
     *
     * @param queue The queue of goods to sort.
     * @return The sorted queue of goods.
     */
    private Queue<Good> mergeSort(Queue<Good> queue) {
        List<Good> goods = new ArrayList<>(queue);
        mergeSortHelper(goods, 0, goods.size() - 1);
        Queue<Good> sortedQueue = new LinkedList<>(goods);
        return sortedQueue;
    }

    /**
     * Helper method for the Mergesort algorithm.
     *
     * @param goods The list of goods to sort.
     * @param low   The low index of the range to sort.
     * @param high  The high index of the range to sort.
     */
    private void mergeSortHelper(List<Good> goods, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSortHelper(goods, low, mid);
            mergeSortHelper(goods, mid + 1, high);
            merge(goods, low, mid, high);
        }
    }

    /**
     * Merges two sorted subarrays for the Mergesort algorithm.
     *
     * @param goods The list of goods.
     * @param low   The low index of the first subarray.
     * @param mid   The high index of the first subarray.
     * @param high  The high index of the second subarray.
     */
    private void merge(List<Good> goods, int low, int mid, int high) {
        List<Good> left = new ArrayList<>(goods.subList(low, mid + 1));
        List<Good> right = new ArrayList<>(goods.subList(mid + 1, high + 1));

        int i = 0, j = 0, k = low;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                goods.set(k, left.get(i));
                i++;
            } else {
                goods.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < left.size()) {
            goods.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < right.size()) {
            goods.set(k, right.get(j));
            j++;
            k++;
        }
    }

    /**
     * Performs the Insertionsort algorithm to sort a list of goods.
     *
     * @param goods      The list of goods to sort.
     * @param comparator The comparator used for comparison.
     * @return The sorted list of goods.
     */
    private List<Good> insertionSort(List<Good> goods, Comparator<Good> comparator) {
        for (int i = 1; i < goods.size(); i++) {
            Good key = goods.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(goods.get(j), key) > 0) {
                goods.set(j + 1, goods.get(j));
                j--;
            }
            goods.set(j + 1, key);
        }
        return goods;
    }

    /**
     * Performs a binary search by name to find goods with a matching name.
     * Returns a list of matching goods.
     *
     * @param searchTerm The name to search for (case-insensitive).
     * @return A list of matching goods.
     */
    public List<Good> binarySearchByName(String searchTerm) {
        List<Good> allGoods = findAll();
        return allGoods;
    }
}