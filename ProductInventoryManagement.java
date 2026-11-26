import java.util.HashMap;
import java.util.Map;

public class ProductInventoryManagement {
    public static void main(String[] args) {
        HashMap<Integer, Integer> products = new HashMap<>();

        products.put(101, 10);
        products.put(102, 4);
        products.put(103, 8);
        products.put(104, 3);
        products.put(105, 6);

        System.out.println("Product Inventory: " + products);

        products.put(106, 12);
        System.out.println("After Adding Product 106: " + products);

        products.put(101, 7);
        System.out.println("After Updating Product 101: " + products);

        int sold = 2;
        int quantity = products.getOrDefault(103, 0);
        products.put(103, quantity - sold);
        System.out.println("After Selling 2 of Product 103: " + products);

        System.out.println("Product 102 Quantity: " + products.get(102));

        System.out.println("Products Below 5:");
        for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
            if (entry.getValue() < 5) {
                System.out.println("Product ID: " + entry.getKey() + ", Quantity: " + entry.getValue());
            }
        }

        products.remove(104);
        System.out.println("After Removing Product 104: " + products);
    }
}