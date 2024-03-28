package oop.string.regex.ra.imp;

import oop.string.regex.ra.entity.Product;

import java.util.Scanner;

public class ProductsImp {

    private static byte indexOfArray = 0;

    public static void main(String[] args) {
        Product product = new Product();
        Scanner scanner = new Scanner(System.in);
        Product[] products = new Product[100];
        ProductsImp productImp = new ProductsImp();
        while (true) {
            System.out.println("***********************MENU**************************");
            System.out.println("1. Nhập thông tin n sản phẩm (n nhập từ bàn phím)");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Tính lợi nhuận các sản phẩm");
            System.out.println("4. Sắp xếp các sản phẩm theo lợi nhuận giảm dần");
            System.out.println("5. Thống kê các sản phẩm theo giá");
            System.out.println("6. Tìm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Nhập sản phẩm");
            System.out.println("8. Bán sản phẩm");
            System.out.println("9. Cập nhật trạng thái sản phẩm");
            System.out.println("10. Thoát");

            byte choice = Byte.parseByte(product.inputFromUser("\\d+", scanner));
            switch (choice) {
                case 1:
                    productImp.inputProductData(scanner, products, product);
                    break;
                case 2:
                    productImp.displayAllProduct(products);
                    break;
                case 3:
                    product.calProfit(products);
                    break;
                case 4:
                    productImp.sortProfitDesc(products);
                    break;
                case 5:
                    productImp.sortByRangeImportPrice(products, product, scanner);
                    break;
                case 6:
                    productImp.searchByProductName(product,products,scanner);
                    break;
                case 7:
                    productImp.updateQuantity(products, scanner ,product);
                    break;
                case 8:
                    productImp.sellQuantity(products, scanner ,product);
                    break;
                case 9:
                    productImp.updateProductStatus(products, scanner, product);
                    break;
                case 10:
                    System.exit(0);
                    break;

            }

        }

    }

    public void updateQuantity(Product[] products, Scanner scanner, Product product){
        boolean isExist = false;
        System.out.println("input product id");

        String productId =product.inputFromUser("\\w+", scanner);
        for (int i = 0; i < products.length; i++) {
            if(products[i] != null && products[i].getProductId().equals(productId)){
                System.out.println("How many amount of products you want to add");
                int totalQuantity = Integer.parseInt(product.inputFromUser("\\d+", scanner)) +  products[i].getQuantity();
                products[i].setQuantity(totalQuantity);
                isExist = true;
                System.out.println("Update successfully");

            }
        }
        if(!isExist){
            System.out.println("Product ID not found");
        }
    }
    public void sellQuantity(Product[] products, Scanner scanner, Product product){
        boolean isExist = false;
        System.out.println("input product Name");
        String productName =product.inputFromUser("\\w+", scanner);
        for (int i = 0; i < products.length; i++) {
            if(products[i] != null && products[i].getProductName().equals(productName)){
                System.out.println("How many amount of products you want to sell");
                int totalQuantity = products[i].getQuantity() - Integer.parseInt(product.inputFromUser("\\d+", scanner));
                if(totalQuantity >= 0){
                    products[i].setQuantity(totalQuantity);
                    System.out.println("Sell successfully");
                    isExist = true;

                }else{
                    System.out.println("Amount Not Enough, please add more product");
                    isExist = true;
                }

            }
        }
        if(!isExist){
            System.out.println("Product Name not found");
        }
    }

    public void updateProductStatus(Product[] products, Scanner scanner, Product product){
        boolean isExist = false;
        System.out.println("input product id");
        String productId =product.inputFromUser("\\w+", scanner);
        for (int i = 0; i < products.length; i++) {
            if(products[i] != null && products[i].getProductId().equals(productId)){
                boolean result = !products[i].isStatus();
                products[i].setStatus(result);
                isExist = true;
                System.out.println("Update successfully");

            }
        }
        if(!isExist){
            System.out.println("Product ID not found");
        }

    }

    public void searchByProductName(Product product, Product[] products, Scanner scanner){
        System.out.println("Input product Name");
        String productName = product.inputFromUser("\\w+", scanner);

        boolean isSearch = false;
        for (int i = 0; i < products.length; i++) {
            if(products[i] != null &&  products[i].getProductName().contains(productName)){
                System.out.println(products[i].toString());
                isSearch = true;
            }
        }

        if(!isSearch){
            System.out.println("Product Not Found");
        }

    }

    public void sortByRangeImportPrice(Product[] products, Product product, Scanner scanner) {
        System.out.println("Input To Price");
        float toPrice = Float.parseFloat(product.inputFromUser("\\d+", scanner));


        System.out.println("Input From Price");
        float fromPrice = Float.parseFloat(product.inputFromUser("\\d+", scanner));

        byte count = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null && toPrice < products[i].getImportPrice() && fromPrice > products[i].getImportPrice()) {
                count++;
                System.out.println(products[i].toString());
            }
        }
        if (count > 0) {
            System.out.printf("Co Tong Cong %d San Pham \n", count);
        } else {
            System.out.printf("Ko co san pham nao trong khoang gia %f to %f \n", toPrice, fromPrice);
        }
    }

    public void sortProfitDesc(Product[] products) {

        Product temp;
        byte minIndex = 0;
        byte arrLength = (byte) products.length;
        for (byte i = 0; i < arrLength; i++) {
            minIndex = i;
            for (byte j = 0; j < arrLength; j++) {
                if (products[minIndex] != null && products[j] != null && products[minIndex].getProfit() > products[j].getProfit()) {
                    minIndex = j;
                }
            }
            temp = products[i];
            products[i] = products[minIndex];
            products[minIndex] = temp;
        }

        System.out.println("Mang sau khi sap xep");
        for (Product product: products){
            if (product != null) {
                System.out.println(product);
            }
        }
    }

    public void inputProductData(Scanner scanner, Product[] products, Product product) {
        System.out.println("How many products you want to add");
        byte productAmount = Byte.parseByte(product.inputFromUser("\\d+", scanner));
        for (int i = 0; i < productAmount; i++) {
            products[indexOfArray] = new Product();
            products[indexOfArray].inputData(scanner, products);
            indexOfArray++;
        }
    }

    public void displayAllProduct(Product[] products) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                System.out.println(products[i].toString());
            }
        }
    }
}
