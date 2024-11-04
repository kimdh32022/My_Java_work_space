package Practice_241025;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class kdh_0222_Toolbar extends JToolBar {
    // 직선 그리기와 사각형 그리기 버튼 추가
    JButton drawLineButton = new JButton("직선 그리기");
    JButton drawRectButton = new JButton("사각형 그리기");

    // shape 선택을 위한 변수
    private String selectedShape = "LINE";

    public kdh_0222_Toolbar() {
        // 툴바에 버튼 추가
        add(drawLineButton);
        add(drawRectButton);

        // 직선 그리기 버튼 클릭 시  "LINE"으로 설정
        drawLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = "LINE";
            }
        });

        // 사각형 그리기 버튼 클릭 시 "RECT"으로 설정
        drawRectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = "RECT";
            }
        });
    }

    // 현재 선택된 shape 반환 메서드
    public String getSelectedShape() {
        return selectedShape;
    }
}
