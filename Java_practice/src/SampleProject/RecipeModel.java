package SampleProject;

public class RecipeModel {
    private String name;
    private String ingredients;
    private String details;
    private String imagePath;

    public RecipeModel(String name, String ingredients, String details, String imagePath) {
        this.name = name;
        this.ingredients = ingredients;
        this.details = details;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public String getIngredients() { return ingredients; }
    public String getDetails() { return details; }
    public String getImagePath() { return imagePath; }
}

