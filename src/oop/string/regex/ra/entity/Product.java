package oop.string.regex.ra.entity;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Product {

    private String productId;
    private String productName;
    private String description;
    private boolean status;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;


    public void inputData(Scanner scanner, Product[] arrProduct) {
        this.productId = inputProductId(arrProduct, scanner);
        this.productName = inputProductName(scanner, arrProduct);
        System.out.println("Input import Price");
        this.importPrice = Float.parseFloat(inputFromUser("[1-9]{1,10}", scanner));
        this.exportPrice = inputExportPrice(scanner, arrProduct);
        this.profit = Math.round(exportPrice - importPrice);
        System.out.println("Input quantity");
        this.quantity = Integer.parseInt(inputFromUser("[1-9]{1,10}", scanner));
        System.out.println("Input description");
        this.description = inputFromUser("\\w+", scanner);
        System.out.println("Input status");
        this.status = Boolean.parseBoolean(inputFromUser("(true|false)", scanner));
    }

    public void calProfit(Product[] products) {
        float totalProfit = 0;

        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                totalProfit += Math.round(products[i].getExportPrice()
                        - products[i].getImportPrice());

            }
        }
        System.out.println("Total profit: " + totalProfit);


    }

    public float inputExportPrice(Scanner scanner, Product[] products) {
        System.out.println("Input export Price");

        do {
            float exportPrice = Float.parseFloat(inputFromUser("\\d+", scanner));
            boolean checkGreater = false;
            for (int i = 0; i < products.length; i++) {
                if (products[i] != null && (products[i].getImportPrice() * 1.2 < exportPrice )) {
                    checkGreater = true;
                }
            }
            if (checkGreater) {
                return exportPrice;
            } else {
                System.out.println("Export price must greater than import price 20 %");
            }
        } while (true);

    }

    public String inputProductName(Scanner scanner, Product[] products) {

        System.out.println("Input productName");

        do {
            String productName = inputFromUser("\\w{6,50}", scanner);
            boolean isExist = false;
            //checkExist
            for (int i = 0; i < products.length; i++) {
                if (products[i] != null && products[i].getProductName() != null && products[i].getProductName().equals(productName)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.out.println("Product Name is exist");
            } else {
                return productName;
            }

        } while (true);
    }

    public String inputProductId(Product[] products, Scanner scanner) {
        System.out.println("input productId");

        do {
            String productId = inputFromUser("P\\w{3}", scanner);
            //check trung lap
            boolean isExist = false;
            for (int i = 0; i < products.length; i++) {
                if (products[i] != null && products[i].getProductId() != null && products[i].getProductId().equals(productId)) {
                    isExist = true;
                    break;
                }
            }

            if (isExist) {
                System.out.println("Product Id is Exist, Try another Name");

            } else {
                return productId;
            }

        } while (true);

    }

    public String inputFromUser(String regex, Scanner scanner) {

        while (true) {
            String value = scanner.nextLine();
            if (checkIsValid(regex, value)) {
                return value;
            } else {
                System.out.println("Your input invalid, please try again!");
            }
        }


    }

    public boolean checkIsValid(String regex, String inputValue) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputValue);
        return matcher.matches();
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", status=" + (status ? "dang ban" : " khong ban") +
                ", importPrice=" + importPrice +
                ", exportPrice=" + exportPrice +
                ", profit=" + profit +
                ", quantity=" + quantity +
                '}';
    }

    public Product() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
