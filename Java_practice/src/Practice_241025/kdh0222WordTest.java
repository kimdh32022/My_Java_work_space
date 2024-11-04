package Practice_241025;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class kdh0222WordTest extends JFrame {
	private JTextField wordField;
	private JTextField meaningField;
	private JPanel wordPanel;
	private Map<String, JLabel> wordLabelMap;
	private Map<String, String> wordMeaningLabelMap;
	private Random random;

	public kdh0222WordTest() {
		setTitle("단어 프로그램 V 1.0.0");
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wordLabelMap = new HashMap<>();
		wordMeaningLabelMap = new HashMap<>();
		random = new Random();
		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		inputPanel.add(new JLabel("단어: "));
		wordField = new JTextField(10);
		inputPanel.add(wordField);
		inputPanel.add(new JLabel("뜻: "));
		meaningField = new JTextField(10);
		inputPanel.add(meaningField);
		// 단어 추가
		JButton addButton = new JButton("단어추가");
		inputPanel.add(addButton);
		// 랜덤한 단어 뽑기
		JButton randomButton = new JButton("랜덤 단어 뽑기");
		inputPanel.add(randomButton);
		// 저장하기
		JButton saveButton = new JButton("Save");
		inputPanel.add(saveButton);
		// 저장된 값 불러오기
		JButton loadButton = new JButton("Load");
		inputPanel.add(loadButton);
		// 저장된 값 초기화 
		JButton resetButton = new JButton("초기화");
		inputPanel.add(resetButton);

		wordPanel = new JPanel();
		wordPanel.setLayout(new FlowLayout());
		add(inputPanel, BorderLayout.NORTH);
		add(wordPanel, BorderLayout.CENTER);
		//이벤트 리스너를 선언 및 버튼 기능에 추가
		addButton.addActionListener(e -> addWord());
		randomButton.addActionListener(e -> showRandomWord());
		saveButton.addActionListener(e -> saveWordsToFile());
		loadButton.addActionListener(e -> loadWordsFromFile());
		resetButton.addActionListener(e -> resetWords());

		// 프로그램 시작 시 자동으로 저장된 파일 불러오기
		loadWordsFromFile();

		setVisible(true);
	}

	private void addWord() {
		String word = wordField.getText();
		String meaning = meaningField.getText();
		if (!word.isEmpty() && !meaning.isEmpty()) {
			JLabel wordLabel = new JLabel(word);
			wordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			wordLabel.addMouseListener(new MouseAdapter() {
				private boolean showMeaning = false;

				@Override
				public void mouseClicked(MouseEvent e) {
					if (!showMeaning) {
						wordLabel.setText("뜻 : " + meaning);
						showMeaning = true;
					} else {
						wordLabel.setText(word);
						showMeaning = false;
					}
				}
			});
			wordLabelMap.put(word, wordLabel);
			wordMeaningLabelMap.put(word, meaning);
			wordPanel.add(wordLabel);
			wordPanel.revalidate();
			wordPanel.repaint();
		} else {
			JOptionPane.showMessageDialog(null, "단어와 뜻을 모두 입력해주세요.", "입력오류: 모두 입력해주세요", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void showRandomWord() {
		if (wordLabelMap.isEmpty()) {
			JOptionPane.showMessageDialog(null, "단어가 입력 안됨", "먼저, 단어를 추가해주세요", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (JLabel jLabel : wordLabelMap.values()) {
			jLabel.setVisible(false);
		}
		List<String> words = new ArrayList<>(wordLabelMap.keySet());
		String randomWord = words.get(random.nextInt(words.size()));
		wordLabelMap.get(randomWord).setVisible(true);
	}

	private void saveWordsToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("wordTestCsv.csv"))) {
			for (String word : wordLabelMap.keySet()) {
				String meaning = wordMeaningLabelMap.get(word);
				writer.write(word + "," + meaning);
				writer.newLine();
			}
			JOptionPane.showMessageDialog(null, "단어가 성공적으로 저장되었습니다.", "저장 완료", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "단어 저장 중 오류가 발생했습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadWordsFromFile() {
		File csvFile = new File("wordTestCsv.csv");

		// 파일이 없을 때 메시지 표시 후 종료
		if (!csvFile.exists()) {
			JOptionPane.showMessageDialog(null, "파일이 없습니다. 단어를 추가 후 저장하세요.", "파일 없음", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			List<String> lines = Files.readAllLines(csvFile.toPath());
			wordPanel.removeAll();
			wordLabelMap.clear();
			wordMeaningLabelMap.clear();
			for (String line : lines) {
				String[] words = line.split(",");
				for (int i = 0; i < words.length; i += 2) {
					String word = words[i];
					String meaning = i + 1 < words.length ? words[i + 1] : "";
					addLoadedWord(word, meaning);
				}
			}
			wordPanel.revalidate();
			wordPanel.repaint();
			JOptionPane.showMessageDialog(null, "단어가 성공적으로 불러와졌습니다.", "불러오기 완료", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "파일을 불러오는 중 오류가 발생했습니다.", "불러오기 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addLoadedWord(String word, String meaning) {
		JLabel wordLabel = new JLabel(word);
		wordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		wordLabel.addMouseListener(new MouseAdapter() {
			private boolean showMeaning = false;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!showMeaning) {
					wordLabel.setText("뜻 : " + meaning);
					showMeaning = true;
				} else {
					wordLabel.setText(word);
					showMeaning = false;
				}
			}
		});
		wordLabelMap.put(word, wordLabel);
		wordMeaningLabelMap.put(word, meaning);
		wordPanel.add(wordLabel);
	}

	private void resetWords() {
		// 화면에서 단어 목록 제거
		wordPanel.removeAll();
		wordLabelMap.clear();
		wordMeaningLabelMap.clear();
		wordPanel.revalidate();
		wordPanel.repaint();

		// 파일 데이터 초기화
		try {
			new FileWriter("wordTestCsv.csv", false).close(); // 빈 파일로 덮어쓰기
			JOptionPane.showMessageDialog(null, "단어 목록이 초기화되었습니다.", "초기화 완료", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "파일 초기화 중 오류가 발생했습니다.", "초기화 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new kdh0222WordTest();
	}
}
