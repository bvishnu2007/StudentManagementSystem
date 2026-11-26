
import java.util.*;

public class ProductInventorySystem {

    static HashMap<Integer, Integer> inventory = new HashMap<>();
    static HashMap<Integer, String> productNames = new HashMap<>();
    static HashMap<Integer, String> productCategories = new HashMap<>();
    static HashMap<Integer, Double> productPrices = new HashMap<>();

    static HashSet<String> categories = new HashSet<>();
    static TreeSet<String> sortedCategories = new TreeSet<>();
    static LinkedHashSet<String> recentProducts = new LinkedHashSet<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            printMenu();
            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayAllProducts();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    updateQuantity();
                    break;
                case 5:
                    sellProduct();
                    break;
                case 6:
                    removeProduct();
                    break;
                case 7:
                    addCategory();
                    break;
                case 8:
                    displayUniqueCategories();
                    break;
                case 9:
                    displaySortedCategories();
                    break;
                case 10:
                    displayRecentProducts();
                    break;
                case 11:
                    generateInventoryReport();
                    break;
                case 12:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void printMenu() {
        System.out.println("\n========================================");
        System.out.println("       PRODUCT INVENTORY SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Product");
        System.out.println("2. Display All Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product Quantity");
        System.out.println("5. Sell Product");
        System.out.println("6. Remove Product");
        System.out.println("7. Add Product Category");
        System.out.println("8. Display Unique Categories");
        System.out.println("9. Display Sorted Categories");
        System.out.println("10. Display Recently Added Products");
        System.out.println("11. Generate Inventory Report");
        System.out.println("12. Exit");
    }

    static void addProduct() {

        System.out.print("Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (inventory.containsKey(id)) {
            System.out.println("Duplicate Product ID!");
            return;
        }

        System.out.print("Product Name: ");
        String name = sc.nextLine();

        System.out.print("Category: ");
        String category = sc.nextLine();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        System.out.print("Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        if (price < 0 || qty < 0) {
            System.out.println("Price and Quantity cannot be negative!");
            return;
        }

        inventory.put(id, qty);
        productNames.put(id, name);
        productCategories.put(id, category);
        productPrices.put(id, price);

        categories.add(category);
        recentProducts.add(name);

        System.out.println("Product Added Successfully!");
    }

    static void displayAllProducts() {

        if (inventory.isEmpty()) {
            System.out.println("No Products Available!");
            return;
        }

        System.out.println("\n---------- PRODUCT INVENTORY ----------");
        System.out.println("ID\tName\t\tCategory\tPrice\tQuantity");

        for (Integer id : inventory.keySet()) {
            System.out.println(
                id + "\t" +
                productNames.get(id) + "\t\t" +
                productCategories.get(id) + "\t" +
                productPrices.get(id) + "\t" +
                inventory.get(id)
            );
        }

        System.out.println("Total Products: " + inventory.size());
    }

    static void searchProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        if (inventory.containsKey(id)) {
            System.out.println("Product Name: " + productNames.get(id));
            System.out.println("Category: " + productCategories.get(id));
            System.out.println("Price: " + productPrices.get(id));
            System.out.println("Quantity: " + inventory.get(id));
        } else {
            System.out.println("Product Not Found!");
        }
    }

    static void updateQuantity() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        if (inventory.containsKey(id)) {
            System.out.print("Enter New Stock Quantity: ");
            int qty = sc.nextInt();

            if (qty < 0) {
                System.out.println("Quantity cannot be negative!");
                return;
            }

            inventory.put(id, inventory.get(id) + qty);
            System.out.println("Stock Updated Successfully!");
            System.out.println("Available Quantity: " + inventory.get(id));
        } else {
            System.out.println("Product Not Found!");
        }
    }

    static void sellProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        if (!inventory.containsKey(id)) {
            System.out.println("Product Not Found!");
            return;
        }

        System.out.print("Enter Quantity Sold: ");
        int sold = sc.nextInt();

        int available = inventory.get(id);

        if (sold <= 0 || sold > available) {
            System.out.println("Insufficient Stock!");
            return;
        }

        inventory.put(id, available - sold);

        System.out.println("Sale Completed Successfully!");
        System.out.println("Product: " + productNames.get(id));
        System.out.println("Remaining Quantity: " + inventory.get(id));
    }

    static void removeProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        if (inventory.containsKey(id)) {

            String name = productNames.get(id);

            inventory.remove(id);
            productNames.remove(id);
            productCategories.remove(id);
            productPrices.remove(id);

            recentProducts.remove(name);

            categories.clear();
            categories.addAll(productCategories.values());

            System.out.println("Product Removed Successfully!");
        } else {
            System.out.println("Product Not Found!");
        }
    }

    static void addCategory() {

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        if (categories.add(category)) {
            System.out.println("Category Added Successfully!");
        } else {
            System.out.println("Category Already Exists!");
        }
    }

    static void displayUniqueCategories() {

        System.out.println("\n---------- PRODUCT CATEGORIES ----------");

        for (String category : categories) {
            System.out.println(category);
        }

        System.out.println("Total Unique Categories: " + categories.size());
    }

    static void displaySortedCategories() {

        sortedCategories.clear();
        sortedCategories.addAll(categories);

        System.out.println("\n---------- SORTED CATEGORIES ----------");
        System.out.println(sortedCategories);

        if (!sortedCategories.isEmpty()) {
            System.out.println("First Category: " + sortedCategories.first());
            System.out.println("Last Category: " + sortedCategories.last());
            System.out.println("Descending Order: " + sortedCategories.descendingSet());
        }
    }

    static void displayRecentProducts() {

        System.out.println("\n---------- RECENTLY ADDED PRODUCTS ----------");

        for (String name : recentProducts) {
            System.out.println(name);
        }

        System.out.println("Total Products: " + recentProducts.size());
    }

    static void generateInventoryReport() {

        displayAllProducts();

        int totalUnits = 0;
        int maxQty = -1;
        int maxId = -1;

        System.out.println("\n---------- LOW STOCK PRODUCTS ----------");

        for (Map.Entry<Integer, Integer> entry : inventory.entrySet()) {

            int id = entry.getKey();
            int qty = entry.getValue();

            totalUnits = totalUnits + qty;

            if (qty < 5) {
                System.out.println(productNames.get(id) + " - " + qty);
            }

            if (qty > maxQty) {
                maxQty = qty;
                maxId = id;
            }
        }

        System.out.println("Total Units Available: " + totalUnits);

        if (maxId != -1) {
            System.out.println("Highest Stock Product: " +
                    productNames.get(maxId) + " - " + maxQty);
        }

        displayUniqueCategories();
    }
}
