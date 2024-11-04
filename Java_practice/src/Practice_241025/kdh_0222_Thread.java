package Practice_241025;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class kdh_0222_Thread extends JFrame {
    private MyLabel bar = new MyLabel(100);
    private JLabel percentageLabel = new JLabel("0%"); // 퍼센트 표시용 라벨

    public kdh_0222_Thread(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        
        // 바 설정
        bar.setBackground(Color.ORANGE);
        bar.setOpaque(true);
        bar.setLocation(20, 50);
        bar.setSize(300, 20);
        c.add(bar);

        // 퍼센트 표시 라벨 설정
        percentageLabel.setSize(50, 20);
        percentageLabel.setLocation(150, 80); // 바 밑에 위치
        c.add(percentageLabel);

        // 키 이벤트 리스너 추가
        c.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                bar.fill();
                updatePercentage();
            }
        });

        setSize(350, 200);
        setVisible(true);

        c.setFocusable(true);
        c.requestFocus();

        // 소비자 스레드 시작
        ConsumerThread th = new ConsumerThread(bar);
        th.start();
    }

    // 퍼센트 업데이트 메소드
    private void updatePercentage() {
        int percent = (int) (((double) bar.getBarSize() / bar.getMaxBarSize()) * 100);
        percentageLabel.setText(percent + "%");
    }

    class MyLabel extends JLabel {
        private int barSize = 0; // 현재 바의 크기
        private int maxBarSize;

        public MyLabel(int maxBarSize) {
            this.maxBarSize = maxBarSize;
        }

        public int getBarSize() {
            return barSize;
        }

        public int getMaxBarSize() {
            return maxBarSize;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.MAGENTA);
            int width = (int) (((double) (this.getWidth())) / maxBarSize * barSize);
            if (width == 0)
                return;
            g.fillRect(0, 0, width, this.getHeight());
        }

        synchronized void fill() {
            if (barSize == maxBarSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
            barSize++;
            repaint();
            notify();
        }

        synchronized void consume() {
            if (barSize == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
            barSize--;
            repaint();
            notify();
        }
    }

    class ConsumerThread extends Thread {
        private MyLabel bar;

        public ConsumerThread(MyLabel bar) {
            this.bar = bar;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    sleep(200);
                    bar.consume();
                    updatePercentage(); // 소비 시 퍼센트 업데이트
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        new kdh_0222_Thread("아무키나 빨리 눌러 바 채우기");
    }
}
