package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Product {

    private String productId;
    private String productName;
    private String descriptions;
    private float price;
    private Date created_date;
    private int catalogId ;
    private int productStatus;

    @Override
    public String toString() {
        String status = "";
        switch (this.getProductStatus()){
            case 0:
                status = "Dang Ban ";
                break;
            case 1:
                status = " Het Hang";
                break;
            case 2:
                status = "Ko ban ";
                break;
            default:
                break;
        }

        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", Create At='" + created_date + '\'' +
                ", price=" + price +
                ", catalogId=" + catalogId +
                ", productStatus=" + status +
                '}';
    }

    public void displayAllProduct(Product[] products){

        for (Product product: products){
            if(product != null){
                System.out.println(product);
            }
        }
    }



    public void searchProductByName(Scanner scanner, Product[] products){
        System.out.println("Input ProductName ");
        String productName  = inputFormUser("\\w+", scanner);

        for (Product product: products){
            if(product!= null && product.getProductName().contains(productName)){
                System.out.println(product);
            }
        }

    }

    public void sortByRangePrice(Scanner scanner, Product[] products){

        System.out.println("Input From Price");
        float inputFromPrice = Float.parseFloat(inputFormUser("\\d+", scanner));
        System.out.println("Input To Price");
        float inputToPrice = Float.parseFloat(inputFormUser("\\d+", scanner));

        byte count = 0;
        boolean isInRange = false;
        for (Product product : products){
            if(product != null && inputFromPrice < product.price && inputToPrice > product.price  ){
                System.out.println(product);
                count ++;
                isInRange = true;
            }
        }
        if(!isInRange){
            System.out.println("Your Input Range Have No Product");
        }else{
            System.out.printf("There are %d product in range %f to %f \n", count, inputFromPrice, inputToPrice);
        }


    }

    public void updateProductById(Product[] products, Scanner scanner){

        System.out.println("Input ProductId");
        String updateProductId = inputFormUser("A\\w{3}||C\\w{3}||C\\w{3}", scanner);

        boolean checkExist = false;
        for (Product product : products){
            if(product != null && product.getProductId().equals(updateProductId)){

                System.out.println("Input ProductName");
                product.setProductName(checkProductName(scanner, products));

                System.out.println("Input Product Price ");
                product.setPrice(Float.parseFloat(inputFormUser("[1-9]\\d*", scanner)));

                System.out.println("Input Product Description ");
                product.setDescriptions(inputFormUser("\\w+", scanner));

                System.out.println("Input Product Product Status ");
                product.setProductStatus(Integer.parseInt(inputFormUser("[0-2]", scanner)));

                checkExist = true;
            }
        }
        if(!checkExist){
            System.out.println("Product Id not found");
        }else{
            System.out.println("Update successfully");
        }

    }

    public Product[] deleteProductById(Scanner scanner, Product[] products){

        System.out.println("Input ProductId");
        String deleteProductId = inputFormUser("A\\w{3}||C\\w{3}||C\\w{3}", scanner);

        byte lengthOfArr = (byte) products.length;
        Product[] newArr = new Product[lengthOfArr - 1];
        byte indexOfNewArr = 0;

        for (Product product : products){
            if(product != null && !product.getProductId().equals(deleteProductId)){

                    newArr[indexOfNewArr] = product;
                    indexOfNewArr++;

            }

        }

        System.out.println("Delete Successful");
        return newArr;

    }

    public void sortProductByPrice(Product[] products){

        byte lengthOfProduct = (byte) products.length;

        byte minIndex ;
        Product product;

        for (byte i = 0; i < lengthOfProduct; i++) {
            minIndex = i;
            for (byte j = 0; j < lengthOfProduct; j++) {
                if(products[j] != null) {

                    if (products[minIndex] != null && products[minIndex].getPrice() < products[j].getPrice()) {
                        minIndex = j;
                    }
                }
            }

            if(products[minIndex] != null){
                product = products[i];
                products[i] = products[minIndex];
                products[minIndex] = product;
            }

        }

        for (Product product1: products){
            System.out.println(product1);
        }

    }

    public void inputData(Scanner scanner, Product[] products
    , Categories[] categories){

        for (int i = 0; i < products.length ; i++) {
            if(products[i] == null){
                products[i] = checkInputProduct(scanner, categories, products);
                break;
            }
        }
        System.out.println("Add successfully");
    }

    public Product checkInputProduct(Scanner scanner,Categories[] categories, Product[] products){
        Product product = new Product();

        System.out.println("Input ProductId");
        product.setProductId(checkProductId(scanner, products));

        System.out.println("Input ProductName");
        product.setProductName(checkProductName(scanner, products));


        System.out.println("Input Product Price ");
        product.setPrice(Float.parseFloat(inputFormUser("[1-9]\\d*", scanner)));

        System.out.println("Input Product Description ");
        product.setDescriptions(inputFormUser("\\w+", scanner));

        System.out.println("Input Product created date ");
        SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
        try {
            product.setCreated_date(sdt.parse(inputFormUser("[0-31]/[1-12]/[2010-2030]$", scanner)));
        } catch (ParseException e) {
            System.err.println("Định dạng ngày nhập ko đúng, vui lòng nhập lại");
        } catch (Exception ex){
            System.err.println("Có lỗi trong quá trình xử lý, vui lòng nhập lại");
        }

        System.out.println("Input Product Categories Id ");

        product.setCatalogId(checkCategoriesId(categories, scanner));

        System.out.println("Input Product Product Status ");
        product.setProductStatus(Integer.parseInt(inputFormUser("[0-2]", scanner)));

        return product;
    }



    public int checkCategoriesId(Categories[] categories, Scanner scanner) {

        boolean isExist = false;
        System.out.println("CategoriesID List");

        for (int i = 0; i < categories.length; i++) {
            if(categories[i] != null ){
                System.out.printf("%d ", categories[i].getCatalogId());
                isExist = true;
            }
        }

        System.out.println();
        if(!isExist){
                System.out.println("Categories is empty, please add Category");
                return 0;
        }

        System.out.println("What CategoriesID You Choose?");

        int categoriesId = Integer.parseInt(inputFormUser("\\d+", scanner));

        boolean isExist1 = false;
        for (Categories category : categories) {
            if (category != null && category.getCatalogId() == categoriesId) {
                isExist1 = true;
                break;
            }
        }


        if (isExist1) {

            return categoriesId;
        } else {
            System.out.println("Categories ID not found, please add category");
        }
        return 0;

    }


    public String checkProductName(Scanner scanner, Product[] products){
        String productName = inputFormUser("\\w{10,50}", scanner);
//        return productName;
        boolean isExist = false;
        for (Product product : products) {
            if (product != null && product.getProductName().equals(productName)) {
                isExist = true;
                break;
            }
        }

        while (true){
            if(isExist){
                System.out.println("ProductName is exist, try another ProductId");
                isExist = false;
                productName = inputFormUser("\\w{10,50}", scanner);

            }else{
                return productName;
            }
        }


    }

    public String checkProductId(Scanner scanner, Product[] products){

//        "^[C|S|A][0-9]{3}$";
        String productId = inputFormUser("A\\w{3}||C\\w{3}||C\\w{3}", scanner);
//        return productId;

        boolean isExist = false;
        for (Product product : products) {
            if (product != null && product.getProductId().equals(productId)) {
                isExist = true;
                break;
            }
        }

        while (true){
            if(isExist){
                System.out.println("ProductID is exist, try another ProductId");
                isExist = false;
                productId = inputFormUser("A\\w{3}||C\\w{3}||C\\w{3}", scanner);
            }else{
                return productId;
            }
        }

    }



    public String inputFormUser(String regex, Scanner scanner){

        while (true){
            String inputValue = scanner.nextLine();
            if(isValid(regex, inputValue)){
                return inputValue;
            }else{
                System.out.println("Your input invalid! Please try again");
            }
        }

    }


    public boolean isValid(String regex, String inputValue){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputValue);
        return matcher.matches();

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

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }
}
