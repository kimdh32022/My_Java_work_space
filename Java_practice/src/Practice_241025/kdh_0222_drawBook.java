package Practice_241025;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class kdh_0222_drawBook extends JFrame {
    kdh_0222_Toolbar toolbar = new kdh_0222_Toolbar(); // 새롭게 만든 툴바 사용
    private CanvasPanel canvas = new CanvasPanel(); // 도형을 그릴 캔버스

    public kdh_0222_drawBook() {
        setTitle("버튼 클릭, 선, 타원, 사각형 그리기 응용 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1440, 800);

        add(toolbar, BorderLayout.NORTH); // 툴바를 프레임 상단에 추가
        add(canvas, BorderLayout.CENTER); // 캔버스를 프레임 중앙에 추가
        setVisible(true);
    }

    public static void main(String[] args) {
        new kdh_0222_drawBook();
    }

    class CanvasPanel extends JPanel {
        private Point startPoint = null;
        private Point endPoint = null;

        public CanvasPanel() {
            // 마우스 클릭 이벤트 추가
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint(); // 시작 지점 설정
                }

                public void mouseReleased(MouseEvent e) {
                    endPoint = e.getPoint(); // 끝 지점 설정
                    repaint(); // 다시 그리기 요청
                }
            });
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (startPoint != null && endPoint != null) {
                String shape = toolbar.getSelectedShape(); // 툴바에서 선택된 도형 가져오기
                
                if (shape.equals("LINE")) {
                    g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                } else if (shape.equals("RECT")) {
                    int x = Math.min(startPoint.x, endPoint.x);
                    int y = Math.min(startPoint.y, endPoint.y);
                    int width = Math.abs(startPoint.x - endPoint.x);
                    int height = Math.abs(startPoint.y - endPoint.y);
                    g.drawRect(x, y, width, height);
                }
            }
        }
    }
}

