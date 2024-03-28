package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.Scanner;

public class ShopManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShopManagement shopManagement = new ShopManagement();
        Product product = new Product();
        Categories categories1 = new Categories();
        Categories[] categories = new Categories[5];
        Product[] products = new Product[5];
        // [null,null,..]
        while (true) {
            System.out.println("*******************SHOP MENU*****************");
            System.out.println("1.  Categories Management");
            System.out.println("2.  Product Management");
            System.out.println("3.  Sign Out");
            byte shopChoice = Byte.parseByte(product.inputFormUser("\\d+", scanner));
            switch (shopChoice) {
                case 1:
                    shopManagement.showCategoriesMenu(categories1, categories, product, scanner);
                    break;
                case 2:
                    shopManagement.showProductMenu(scanner, products, categories, product);
                    break;
                case 3:
                    return;

                default:
                    System.out.println("Your Choice Out Of Range");
                    break;
            }
        }

    }



    public  void showCategoriesMenu(Categories categories1, Categories[] categories, Product product, Scanner scanner) {
        System.out.println("1. Input Category Info");
        System.out.println("2. Display All Category Info");
        System.out.println("3. Update Category By Id");
        System.out.println("4. Delete Category Info By ProductID");
        System.out.println("5. Update Category Status");
        System.out.println("6. Back to Shop Menu");
        byte categoriesChoice = Byte.parseByte(product.inputFormUser("\\d+", scanner));
        switch (categoriesChoice) {
            case 1:
                categories1.inputData(scanner, categories);
                break;
            case 2:
                categories1.displayAllCategories(categories);
                break;
            case 3:
                categories1.updateCategoriesById(scanner, categories);
                break;
            case 4:
                categories = categories1.deleteCategoriesById(scanner, categories);
                for (Categories categories2 : categories) {
                    if (categories2 != null) {
                        System.out.println(categories2);
                    }
                }
                break;
            case 5:
                categories1.updateCategoriesStatus(categories, scanner);
                break;
            case 6:
                break;

            default:
                System.out.println("Your Choice Out Of Range");
                break;
        }

    }

    public  void showProductMenu(Scanner scanner, Product[] products, Categories[] categories, Product product) {
        System.out.println("1. Input Product Info");
        System.out.println("2. Display All Product Info");
        System.out.println("3. Sort Product By Price");
        System.out.println("4. Update Product Info By ProductID");
        System.out.println("5. Delete Product By ProductID");
        System.out.println("6. Find By Name");
        System.out.println("7. Find Product By Price Range");
        System.out.println("8. Back to Shop Menu");
        byte productChoice = Byte.parseByte(product.inputFormUser("\\d+", scanner));
        switch (productChoice) {
            case 1:
                product.inputData(scanner, products, categories);
                break;
            case 2:
                product.displayAllProduct(products);
                break;
            case 3:
                product.sortProductByPrice(products);
                break;
            case 4:
                product.updateProductById(products, scanner);
                break;
            case 5:
                products = product.deleteProductById(scanner, products);
                for (Product product1 : products) {
                    if (product1 != null) {
                        System.out.println(product1);
                    }
                }
                break;
            case 6:
                product.searchProductByName(scanner, products);
                break;
            case 7:
                product.sortByRangePrice(scanner, products);
                break;
            case 8:
                break;

            default:
                System.err.println("Your Choice Out Of Range");
                break;
        }

    }
}
