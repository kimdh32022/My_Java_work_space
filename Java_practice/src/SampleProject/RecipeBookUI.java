package SampleProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RecipeBookUI {
    private JFrame frame;
    private JList<String> recipeList;
    private JLabel imageLabel;
    private JTextArea recipeTextArea;
    private DefaultListModel<String> recipeListModel;
    private RecipeDatabase database;
    private ArrayList<RecipeModel> recipes;

    public RecipeBookUI() {
        database = new RecipeDatabase();
        recipes = database.loadRecipesFromDatabase();
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Recipe Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("나의 Recipe Book", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
        titleLabel.setBackground(new Color(116, 119, 254));
        titleLabel.setOpaque(true);
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(183, 131, 248));

        JLabel menuLabel = new JLabel("메뉴", SwingConstants.LEFT);
        menuLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
        menuPanel.add(menuLabel);

        recipeListModel = new DefaultListModel<>();
        recipes.forEach(recipe -> recipeListModel.addElement(recipe.getName()));

        recipeList = new JList<>(recipeListModel);
        recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recipeList.addListSelectionListener(e -> displaySelectedRecipe());
        JScrollPane listScrollPane = new JScrollPane(recipeList);
        menuPanel.add(listScrollPane);

        JButton addButton = new JButton("레시피 추가하기");
        addButton.addActionListener(e -> addRecipe());

        JButton findButton = new JButton("레시피 찾기");
        findButton.addActionListener(e -> findRecipe());

        JButton deleteButton = new JButton("레시피 삭제");
        deleteButton.addActionListener(e -> deleteRecipe());

        menuPanel.add(addButton);
        menuPanel.add(findButton);
        menuPanel.add(deleteButton);
        frame.add(menuPanel, BorderLayout.WEST);

        imageLabel = new JLabel("여기에 사진이 들어갑니다.", SwingConstants.CENTER);
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(imageLabel, BorderLayout.CENTER);

        recipeTextArea = new JTextArea(10, 30);
        recipeTextArea.setLineWrap(true);
        recipeTextArea.setWrapStyleWord(true);
        rightPanel.add(new JScrollPane(recipeTextArea), BorderLayout.SOUTH);

        frame.add(rightPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void displaySelectedRecipe() {
        int selectedIndex = recipeList.getSelectedIndex();
        if (selectedIndex != -1) {
            RecipeModel recipe = recipes.get(selectedIndex);
            recipeTextArea.setText(recipe.getDetails());

            String imagePath = recipe.getImagePath();
            if (imagePath != null && !imagePath.isEmpty()) {
                imageLabel.setIcon(new ImageIcon(imagePath));
                imageLabel.setText("");
            } else {
                imageLabel.setIcon(null);
                imageLabel.setText("이미지 없음");
            }
        }
    }

    private void addRecipe() {
        JTextField nameField = new JTextField(20);
        JTextField ingredientsField = new JTextField(20);
        JTextArea detailsArea = new JTextArea(5, 20);
        JButton imageButton = new JButton("사진 선택하기");
        JLabel imagePathLabel = new JLabel("사진 파일 경로: 선택되지 않음");

        imageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                String selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();
                imagePathLabel.setText("사진 파일 경로: " + selectedImagePath);
            }
        });

        JPanel addPanel = new JPanel(new GridLayout(0, 1));
        addPanel.add(new JLabel("메뉴 이름:"));
        addPanel.add(nameField);
        addPanel.add(new JLabel("재료:"));
        addPanel.add(ingredientsField);
        addPanel.add(new JLabel("레시피 순서:"));
        addPanel.add(new JScrollPane(detailsArea));
        addPanel.add(imageButton);
        addPanel.add(imagePathLabel);

        int result = JOptionPane.showConfirmDialog(frame, addPanel, "레시피 추가하기", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            RecipeModel recipe = new RecipeModel(
                nameField.getText(),
                ingredientsField.getText(),
                detailsArea.getText(),
                imagePathLabel.getText().replace("사진 파일 경로: ", "")
            );
            recipes.add(recipe);
            database.addRecipeToDatabase(recipe);
            recipeListModel.addElement(recipe.getName());
        }
    }

    private void deleteRecipe() {
        int selectedIndex = recipeList.getSelectedIndex();
        if (selectedIndex != -1) {
            RecipeModel recipe = recipes.get(selectedIndex);
            database.deleteRecipeFromDatabase(recipe.getName());
            recipes.remove(selectedIndex);
            recipeListModel.remove(selectedIndex);
        }
    }

    private void findRecipe() {
        JTextField searchField = new JTextField(20);
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("레시피 이름:"));
        searchPanel.add(searchField);

        int result = JOptionPane.showConfirmDialog(frame, searchPanel, "레시피 찾기", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String searchName = searchField.getText();
            int index = recipes.stream()
                .map(RecipeModel::getName)
                .toList()
                .indexOf(searchName);

            if (index != -1) {
                recipeList.setSelectedIndex(index);
                recipeList.ensureIndexIsVisible(index);
            } else {
                JOptionPane.showMessageDialog(frame, "해당 레시피를 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new RecipeBookUI();
    }
}



