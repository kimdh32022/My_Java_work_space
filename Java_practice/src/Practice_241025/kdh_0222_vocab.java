package Practice_241025;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class kdh_0222_vocab extends JFrame {
    private ArrayList<JLabel> wordLabels; // 단어를 표시하는 라벨 리스트
    private ArrayList<String> wordList; // 단어 리스트
    private ArrayList<String> meaningList; // 뜻 리스트
    private int pageIndex = 0; // 페이지 인덱스

    public kdh_0222_vocab() {
        setTitle("Vocabulary App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2)); // 4행 2열 레이아웃 설정
        wordLabels = new ArrayList<>();
        wordList = new ArrayList<>();
        meaningList = new ArrayList<>();
        initUI();
    }

    private void initUI() {
        // 8개의 빈 라벨 초기화 및 클릭 이벤트 추가
        for (int i = 0; i < 8; i++) {
            JLabel label = new JLabel("", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            label.setOpaque(true);
            label.setBackground(Color.LIGHT_GRAY);
            label.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    toggleWord(label);
                }
            });
            wordLabels.add(label);
            add(label);
        }

        // 단어 추가 버튼
        JButton addButton = new JButton("단어 추가");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWord();
            }
        });

        // 다음 페이지 버튼
        JButton nextPageButton = new JButton("다음 페이지");
        nextPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextPage();
            }
        });

        JPanel panel = new JPanel();
        panel.add(addButton);
        panel.add(nextPageButton);
        add(panel);
    }

    // 단어 추가 메서드
    private void addWord() {
        String word = JOptionPane.showInputDialog(this, "Enter English word:");
        String meaning = JOptionPane.showInputDialog(this, "Enter meaning:");

        if (word != null && meaning != null) {
            wordList.add(word);
            meaningList.add(meaning);
            displayPage();
        }
    }

    // 페이지 표시 메서드
    private void displayPage() {
        int start = pageIndex * 8;
        int end = Math.min(start + 8, wordList.size());

        // 라벨 초기화
        for (int i = 0; i < 8; i++) {
            if (i + start < end) {
                wordLabels.get(i).setText(wordList.get(i + start));
                wordLabels.get(i).setName("word:" + (i + start));
            } else {
                wordLabels.get(i).setText("");
                wordLabels.get(i).setName("");
            }
        }
    }

    // 다음 페이지로 이동
    private void nextPage() {
        if ((pageIndex + 1) * 8 < wordList.size()) {
            pageIndex++;
            displayPage();
        }
    }

    // 단어 토글 메서드
    private void toggleWord(JLabel label) {
        String labelName = label.getName();
        if (labelName != null && labelName.startsWith("word:")) {
            int index = Integer.parseInt(labelName.split(":")[1]);
            String currentText = label.getText();
            if (currentText.equals(wordList.get(index))) {
                label.setText(meaningList.get(index)); // 뜻으로 변경
            } else {
                label.setText(wordList.get(index)); // 영어 단어로 복구
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            kdh_0222_vocab app = new kdh_0222_vocab();
            app.setVisible(true);
        });
    }
}
