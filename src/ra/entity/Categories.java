package ra.entity;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Categories {
    private static byte count = 0;
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;

    public void inputData(Scanner scanner, Categories[] categories){

        byte categoriesLength = (byte) categories.length;

        for (int i = 0; i < categoriesLength; i++) {
            if(categories[i] == null){
                categories[i] = inputInfoCaterories(scanner, categories);
                break;
            }
        }
        System.out.println("Add successfully");

    }
    public void updateCategoriesById(Scanner scanner, Categories[] categories){

        for (Categories categories1: categories){
            if (categories1!= null && categories1.getCatalogId() == checkCategoriesId(categories, scanner)){

                System.out.println("Input Categories Name");
                categories1.setCatalogName(checkCatalogName(categories, scanner));

                System.out.println("Input Categories Description");
                categories1.setDescriptions(inputFormUser("\\w+", scanner));
                System.out.println("Update successfully");

            }
        }
    }
    public void updateCategoriesStatus(Categories[] categories, Scanner scanner){
        for (Categories categories1: categories){
            if (categories1!= null && categories1.getCatalogId() == checkCategoriesId(categories, scanner)){

                System.out.println("Input Categories Status");
                categories1.setCatalogStatus(Boolean.parseBoolean(inputFormUser("(true|false)", scanner)));
                System.out.println("Update successfully");
            }
        }

    }

    public Categories[] deleteCategoriesById(Scanner scanner, Categories[] categories){

        int idToDelete = checkCategoriesId(categories, scanner); // Lấy ID cần xóa

        // Tạo mảng mới ngắn hơn 1 phần tử cho kết quả
        Categories[] newCategories = new Categories[categories.length - 1];

        int j = 0; // Index cho mảng mới
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] != null && categories[i].getCatalogId() != idToDelete) {
                newCategories[j] = categories[i];
                j++;
            }
        }

        System.out.println("Delete successfully");
        return newCategories;


    }
    public int checkCategoriesId(Categories[] categories, Scanner scanner){

        System.out.println("CategoriesID List");
        for (Categories category : categories) {
            if(category != null ){
                System.out.printf("%d \n", category.getCatalogId());
            }
        }
        System.out.println();
        System.out.println("What CategoriesID You Choose?");

        int categoriesId = Integer.parseInt(inputFormUser("\\d+", scanner));

        boolean isExist = false;
        for (Categories category : categories) {
            if( category!= null && category.getCatalogId() == categoriesId){
                isExist = true;
            }
        }
            if(isExist){
                return categoriesId;
            }else{
                System.out.println("Categories ID not found");
                return 0;
            }
    }





    @Override
    public String toString() {
        return "Categories{" +
                "catalogId=" + catalogId +
                ", catalogName='" + catalogName + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", catalogStatus=" + (catalogStatus ? "Active" : "Inactive") +
                '}';
    }

    public void displayAllCategories(Categories[] categories){
        for (Categories category: categories){
            if(category != null){
                System.out.println(category);

            }
        }

    }

    public Categories inputInfoCaterories(Scanner scanner, Categories[] categories){

        Categories categories1 = new Categories();
//        count++;
//        categories1.setCatalogId(checkCategoriId);
        this.catalogId = checkCategoriId(categories, 0);

        System.out.println("Input Categories Name");
        categories1.setCatalogName(checkCatalogName(categories, scanner));

        System.out.println("Input Categories Description");
        categories1.setDescriptions(inputFormUser("\\w*", scanner));

        System.out.println("Input Categories Status");
        categories1.setCatalogStatus(Boolean.parseBoolean(inputFormUser("(true|false)", scanner)));

        return categories1;
    }


    public int checkCategoriId(Categories[] categories, int indexCategories){

        if(indexCategories == 0){
            return 1;
        }

        //tim max cua catalogId
        int max = categories[0].getCatalogId();
        for (int i = 1; i < indexCategories; i++) {
            if(max < categories[i].getCatalogId()){
                max = categories[i].getCatalogId();
            }
        }
        return max;

    }

    public String checkCatalogName(Categories[] categories, Scanner scanner) {

        String catalogName = inputFormUser("\\w{1-50}", scanner);
        byte categoriesLength = (byte) categories.length;

        boolean isValid = false;
        for (int i = 0; i < categoriesLength; i++) {
            if (categories[i] != null && categories[i].getCatalogName().equals(catalogName)) {
                isValid = true;
                break;
            }
        }

        while (true){
            if (isValid) {
                System.out.println("Catagories Name Is Exist, Type Another Name");
                catalogName = inputFormUser("\\w{1-50}", scanner);
            } else {
              break;
            }
        }

        return catalogName;

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




    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String descriptions, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public static byte getCount() {
        return count;
    }

    public static void setCount(byte count) {
        Categories.count = count;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }
}
