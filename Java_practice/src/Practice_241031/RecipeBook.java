package Practice_241031;

import javax.swing.*;
import java.awt.*; // AWT 라이브러리를 사용하기 위한 import
import java.awt.event.*; // 이벤트 처리를 위한 import
import java.sql.*; // JDBC를 사용하기 위한 import
import java.util.ArrayList; // ArrayList 사용을 위한 import

public class RecipeBook {
	// 레시피 정보를 저장하기 위한 ArrayList
	private static ArrayList<String> recipes = new ArrayList<>();
	private static ArrayList<String> ingredients = new ArrayList<>();
	private static ArrayList<String> recipeDetails = new ArrayList<>();
	private static ArrayList<String> recipeImages = new ArrayList<>();

	// 데이터베이스 연결을 위한 변수
	private static Connection connection;

	public static void main(String[] args) {
		// 데이터베이스 초기화
		initializeDatabase();

		// 레시피 데이터 불러오기
		loadRecipesFromDatabase();

		// JFrame을 생성하여 기본 창을 설정합니다.
		JFrame frame = new JFrame("Recipe Book"); // 창 제목 설정
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 설정
		frame.setSize(800, 600); // 창 크기 설정 (너비, 높이)

		// 전체 레이아웃을 설정합니다.
		frame.setLayout(new BorderLayout()); // BorderLayout 사용

		// 최상단에 제목을 추가합니다.
		JLabel titleLabel = new JLabel("나의 Recipe Book", SwingConstants.CENTER); // 가운데 정렬
		titleLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 24)); // 글꼴 설정
		frame.add(titleLabel, BorderLayout.NORTH); // 제목을 북쪽에 추가
		titleLabel.setBackground(new Color(116, 119, 254));
		titleLabel.setOpaque(true);

		// 왼쪽에 메뉴 목록을 위한 패널을 생성합니다.
		JPanel menuPanel = new JPanel(); // 패널 생성
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // 수직 정렬
		menuPanel.setBackground(new Color(183, 131, 248)); // 배경색 설정 (7477FE)

		// 메뉴 이름을 추가합니다.
		JLabel menuLabel = new JLabel("메뉴", SwingConstants.LEFT); // 왼쪽 정렬
		menuLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 18)); // 글꼴 설정
		menuPanel.add(menuLabel); // 메뉴 이름 추가

		// JList를 사용하여 레시피 목록을 생성합니다.
		JList<String> recipeList = new JList<>(recipes.toArray(new String[0])); // 레시피 목록 생성
		recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 단일 선택 모드
		recipeList.setVisibleRowCount(3); // 보이는 행 수 설정
		JScrollPane listScrollPane = new JScrollPane(recipeList); // 스크롤 가능하게 설정
		menuPanel.add(listScrollPane); // 메뉴 패널에 추가

		// 버튼을 추가합니다.
		JButton addButton = new JButton("레시피 추가하기"); // 버튼 생성
		JButton findButton = new JButton("레시피 찾기"); // 버튼 생성
		JButton deleteButton = new JButton("레시피 삭제"); // 버튼 생성

		// 버튼을 패널에 추가합니다.
		menuPanel.add(addButton); // 추가 버튼
		menuPanel.add(findButton); // 찾기 버튼
		menuPanel.add(deleteButton); // 삭제 버튼

		// 메뉴 패널을 왼쪽에 추가합니다.
		frame.add(menuPanel, BorderLayout.WEST); // 왼쪽에 메뉴 패널 추가

		// 오른쪽 영역에 사진과 텍스트 필드를 추가합니다.
		JPanel rightPanel = new JPanel(); // 오른쪽 패널 생성
		rightPanel.setLayout(new BorderLayout()); // BorderLayout 사용

		// 사진을 넣을 레이블 생성
		JLabel imageLabel = new JLabel("여기에 사진이 들어갑니다.", SwingConstants.CENTER); // 이미지 자리 표시자
		rightPanel.add(imageLabel, BorderLayout.CENTER); // 중앙에 이미지 추가

		// 레시피 순서를 적는 텍스트 필드 생성
		JTextArea recipeTextArea = new JTextArea(10, 30); // 텍스트 영역 생성 (행, 열)
		recipeTextArea.setLineWrap(true); // 줄 바꿈 설정
		recipeTextArea.setWrapStyleWord(true); // 단어 단위 줄 바꿈 설정
		JScrollPane scrollPane = new JScrollPane(recipeTextArea); // 스크롤 가능하게 설정
		rightPanel.add(scrollPane, BorderLayout.SOUTH); // 아래쪽에 텍스트 필드 추가

		// 오른쪽 패널을 프레임에 추가합니다.
		frame.add(rightPanel, BorderLayout.CENTER); // 중앙에 오른쪽 패널 추가

		// 레시피 목록 클릭 이벤트 처리
		recipeList.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) {
		        int selectedIndex = recipeList.getSelectedIndex();
		        if (selectedIndex != -1) {
		            String imagePath = recipeImages.get(selectedIndex);

		            // imagePath가 null이 아닌지 확인 후 처리
		            if (imagePath != null && !imagePath.isEmpty()) {
		                ImageIcon imageIcon = new ImageIcon(imagePath); // 이미지 로드
		                imageLabel.setIcon(imageIcon); // 이미지 레이블에 설정
		                imageLabel.setText(""); // 텍스트를 비워서 이미지만 표시
		            } else {
		                imageLabel.setIcon(null); // 이미지 제거
		                imageLabel.setText("이미지 없음"); // 이미지가 없는 경우 텍스트 표시
		            }

		            recipeTextArea.setText(recipeDetails.get(selectedIndex)); // 텍스트 필드 업데이트
		        }
		    }
		});
		// 레시피 추가하기 버튼 클릭 이벤트 처리
		addButton.addActionListener(e -> {
			JTextField nameField = new JTextField(20);
			JTextField ingredientsField = new JTextField(20);
			JTextArea detailsArea = new JTextArea(5, 20);
			JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
			
			JButton imageButton = new JButton("사진 선택하기");
		    JLabel imagePathLabel = new JLabel("사진 파일 경로: 선택되지 않음");
		    
		    imageButton.addActionListener(imgEvent -> {
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
			addPanel.add(detailsScrollPane);
			addPanel.add(imageButton);
			addPanel.add(imagePathLabel);

			int result = JOptionPane.showConfirmDialog(frame, addPanel, "레시피 추가하기", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
		        String recipeName = nameField.getText();
		        String ingredientList = ingredientsField.getText();
		        String recipeDetail = detailsArea.getText();
		        String imagePath = imagePathLabel.getText().replace("사진 파일 경로: ", ""); // 사진 경로 추출

		        recipes.add(recipeName);
		        ingredients.add(ingredientList);
		        recipeDetails.add(recipeDetail);
		        recipeImages.add(imagePath);

				// 데이터베이스에 레시피 추가
				addRecipeToDatabase(recipeName, ingredientList, recipeDetail, imagePath);

				// JList 업데이트
				recipeList.setListData(recipes.toArray(new String[0])); // JList 업데이트
			}
		});

		// 레시피 삭제 버튼 클릭 이벤트 처리
		deleteButton.addActionListener(e -> {
			int selectedIndex = recipeList.getSelectedIndex(); // 선택된 인덱스 가져오기
			if (selectedIndex != -1) { // 유효한 인덱스인지 확인
				// 선택된 레시피 삭제
				deleteRecipeFromDatabase(selectedIndex); // 데이터베이스에서 삭제
				recipes.remove(selectedIndex);
				ingredients.remove(selectedIndex);
				recipeDetails.remove(selectedIndex);
				recipeImages.remove(selectedIndex);

				// JList 업데이트
				recipeList.setListData(recipes.toArray(new String[0])); // JList 업데이트
			}
		});

		// 레시피 찾기 버튼 클릭 이벤트 처리
		findButton.addActionListener(e -> {
			JTextField searchField = new JTextField(20);
			JPanel searchPanel = new JPanel();
			searchPanel.add(new JLabel("레시피 이름:"));
			searchPanel.add(searchField);

			int result = JOptionPane.showConfirmDialog(frame, searchPanel, "레시피 찾기", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				String searchName = searchField.getText();
				int index = recipes.indexOf(searchName); // 검색된 인덱스 찾기
				if (index != -1) {
					recipeList.setSelectedIndex(index); // 해당 인덱스 선택
					recipeList.ensureIndexIsVisible(index); // 선택된 인덱스가 보이도록 스크롤
				} else {
					JOptionPane.showMessageDialog(frame, "해당 레시피를 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// 프레임을 보이게 합니다.
		frame.setVisible(true); // 창을 보이게 설정
	}
	
	
	// 데이터베이스 초기화 메서드
	private static void initializeDatabase() {
	    try {
	        // Oracle 데이터베이스 연결
	        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 URL
	        String user = "scott"; // 사용자 이름
	        String password = "tiger"; // 비밀번호
	        connection = DriverManager.getConnection(url, user, password);
	        Statement statement = connection.createStatement();

	        // 테이블에 이미지 경로 컬럼 추가
	        statement.execute(
	            "BEGIN " +
	            "EXECUTE IMMEDIATE 'CREATE TABLE recipes (id NUMBER PRIMARY KEY, name VARCHAR2(100), ingredients VARCHAR2(255), details VARCHAR2(4000), image_path VARCHAR2(255))'; " +
	            "EXCEPTION " +
	            "WHEN OTHERS THEN " +
	            "IF SQLCODE != -955 THEN " + // -955는 테이블이 이미 존재하는 경우 발생하는 오류 코드
	            "RAISE; " +
	            "END IF; " +
	            "END;");

	        ResultSet resultSet = statement.executeQuery("SELECT column_name FROM user_tab_columns WHERE table_name = 'RECIPES' AND column_name = 'IMAGE_PATH'");
	        if (!resultSet.next()) {
	            // IMAGE_PATH 열이 없으면 추가
	            statement.execute("ALTER TABLE recipes ADD image_path VARCHAR2(255)");
	        }

	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	// 데이터베이스에서 레시피 불러오기
	private static void loadRecipesFromDatabase() {
	    try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM recipes");
	        while (resultSet.next()) {
	            recipes.add(resultSet.getString("name"));
	            ingredients.add(resultSet.getString("ingredients"));
	            recipeDetails.add(resultSet.getString("details"));
	            recipeImages.add(resultSet.getString("image_path")); // 이미지 경로 불러오기
	        }
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	// 데이터베이스에 레시피 추가 메서드
	private static void addRecipeToDatabase(String name, String ingredients, String details, String imagePath) {
	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(
	            "INSERT INTO recipes (id, name, ingredients, details, image_path) VALUES (recipe_seq.NEXTVAL, ?, ?, ?, ?)");
	        preparedStatement.setString(1, name);
	        preparedStatement.setString(2, ingredients);
	        preparedStatement.setString(3, details);
	        preparedStatement.setString(4, imagePath);
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	// 데이터베이스에서 레시피 삭제
	private static void deleteRecipeFromDatabase(int index) {
		try {
			String recipeName = recipes.get(index); // 삭제할 레시피 이름 가져오기
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM recipes WHERE name = ?");
			preparedStatement.setString(1, recipeName); // 레시피 이름으로 삭제
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

