package SampleProject;

import java.sql.*;
import java.util.ArrayList;

public class RecipeDatabase {
    private Connection connection;

    public RecipeDatabase() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 URL
            String user = "scott";
            String password = "tiger";
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            // 테이블 생성
            statement.execute(
                "BEGIN " +
                "EXECUTE IMMEDIATE 'CREATE TABLE recipes (id NUMBER PRIMARY KEY, name VARCHAR2(100), ingredients VARCHAR2(255), details VARCHAR2(4000), image_path VARCHAR2(255))'; " +
                "EXCEPTION " +
                "WHEN OTHERS THEN " +
                "IF SQLCODE != -955 THEN " +
                "RAISE; " +
                "END IF; " +
                "END;");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<RecipeModel> loadRecipesFromDatabase() {
        ArrayList<RecipeModel> recipes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM recipes");
            while (resultSet.next()) {
                String imagePath = resultSet.getString("image_path");
                recipes.add(new RecipeModel(
                    resultSet.getString("name"),
                    resultSet.getString("ingredients"),
                    resultSet.getString("details"),
                    imagePath != null ? imagePath : ""
                ));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public void addRecipeToDatabase(RecipeModel recipe) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO recipes (id, name, ingredients, details, image_path) VALUES (recipe_seq.NEXTVAL, ?, ?, ?, ?)");
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getIngredients());
            preparedStatement.setString(3, recipe.getDetails());
            preparedStatement.setString(4, recipe.getImagePath());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecipeFromDatabase(String recipeName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM recipes WHERE name = ?");
            preparedStatement.setString(1, recipeName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

