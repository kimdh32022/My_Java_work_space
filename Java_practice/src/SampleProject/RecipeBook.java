package SampleProject;

import javax.swing.*; // Swing 라이브러리를 사용하기 위한 import
import java.awt.*; // AWT 라이브러리를 사용하기 위한 import
import java.awt.event.*; // 이벤트 처리를 위한 import
import java.util.ArrayList; // ArrayList 사용을 위한 import

public class RecipeBook {
	// 레시피 정보를 저장하기 위한 ArrayList
	private static ArrayList<String> recipes = new ArrayList<>();
	private static ArrayList<String> ingredients = new ArrayList<>();
	private static ArrayList<String> recipeDetails = new ArrayList<>();
	private static ArrayList<String> recipeImages = new ArrayList<>();

	public static void main(String[] args) {
		// 초기 레시피 데이터 추가
		recipes.add("레시피 목록 1");
		ingredients.add("재료 목록 1");
		recipeDetails.add("레시피 목록 1의 순서:\n1. 재료 준비\n2. 조리 방법\n3. 서빙");
		recipeImages.add("레시피 1의 이미지"); // 실제 이미지 경로로 변경 필요

		recipes.add("레시피 목록 2");
		ingredients.add("재료 목록 2");
		recipeDetails.add("레시피 목록 2의 순서:\n1. 재료 준비\n2. 조리 방법\n3. 서빙");
		recipeImages.add("레시피 2의 이미지"); // 실제 이미지 경로로 변경 필요

		recipes.add("레시피 목록 3");
		ingredients.add("재료 목록 3");
		recipeDetails.add("레시피 목록 3의 순서:\n1. 재료 준비\n2. 조리 방법\n3. 서빙");
		recipeImages.add("레시피 3의 이미지"); // 실제 이미지 경로로 변경 필요

		// JFrame을 생성하여 기본 창을 설정합니다.
		JFrame frame = new JFrame("Recipe Book"); // 창 제목 설정
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 설정
		frame.setSize(800, 600); // 창 크기 설정 (너비, 높이)

		// 전체 레이아웃을 설정합니다.
		frame.setLayout(new BorderLayout()); // BorderLayout 사용

		// 최상단에 제목을 추가합니다.
		JLabel titleLabel = new JLabel("00의 Recipe Book", SwingConstants.CENTER); // 가운데 정렬
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // 글꼴 설정
		frame.add(titleLabel, BorderLayout.NORTH); // 제목을 북쪽에 추가

		// 왼쪽에 메뉴 목록을 위한 패널을 생성합니다.
		JPanel menuPanel = new JPanel(); // 패널 생성
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // 수직 정렬
		menuPanel.setBackground(new Color(116, 119, 254)); // 배경색 설정 (7477FE)

		// 메뉴 이름을 추가합니다.
		JLabel menuLabel = new JLabel("메뉴", SwingConstants.LEFT); // 왼쪽 정렬
		menuLabel.setFont(new Font("Arial", Font.BOLD, 18)); // 글꼴 설정
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
			if (!e.getValueIsAdjusting()) { // 선택이 완료되었을 때
				int selectedIndex = recipeList.getSelectedIndex(); // 선택된 인덱스 가져오기
				if (selectedIndex != -1) { // 유효한 인덱스인지 확인
					// 선택된 레시피에 따라 이미지와 레시피 순서 업데이트
					imageLabel.setText(recipeImages.get(selectedIndex)); // 이미지 레이블 업데이트
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

			JPanel addPanel = new JPanel(new GridLayout(0, 1));
			addPanel.add(new JLabel("메뉴 이름:"));
			addPanel.add(nameField);
			addPanel.add(new JLabel("재료:"));
			addPanel.add(ingredientsField);
			addPanel.add(new JLabel("레시피 순서:"));
			addPanel.add(detailsScrollPane);

			int result = JOptionPane.showConfirmDialog(frame, addPanel, "레시피 추가하기", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				String recipeName = nameField.getText();
				String ingredientList = ingredientsField.getText();
				String recipeDetail = detailsArea.getText();
				recipes.add(recipeName);
				ingredients.add(ingredientList);
				recipeDetails.add(recipeDetail);
				recipeImages.add("여기에 " + recipeName + "의 사진이 들어갑니다."); // 이미지 자리 표시자

				// JList 업데이트
				recipeList.setListData(recipes.toArray(new String[0])); // JList 업데이트
			}
		});

		// 레시피 삭제 버튼 클릭 이벤트 처리
		deleteButton.addActionListener(e -> {
			int selectedIndex = recipeList.getSelectedIndex(); // 선택된 인덱스 가져오기
			if (selectedIndex != -1) { // 유효한 인덱스인지 확인
				// 선택된 레시피 삭제
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
}
