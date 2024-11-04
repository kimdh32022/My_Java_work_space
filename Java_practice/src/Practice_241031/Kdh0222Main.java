package Practice_241031;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Kdh0222Main {

		public static void main(String[] args) {
			JFrame frame = new JFrame("회원 관리 프로그램 V 1.0.0");
			frame.setSize(800, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new FlowLayout());

			JLabel nameLabel = new JLabel("ID:");
			JTextField nameField = new JTextField(10);

			JLabel emailLabel = new JLabel("Email:");
			JTextField emailField = new JTextField(10);

			JLabel passwordLabel = new JLabel("PassWord:");
			JTextField passwordField = new JTextField(10);

			JButton joinButton = new JButton("Join");
			JButton searchButton = new JButton("search");

			JTextArea resultArea = new JTextArea(10, 50);
			resultArea.setEditable(false);

			frame.add(nameLabel);
			frame.add(nameField);
			frame.add(emailLabel);
			frame.add(emailField);
			frame.add(passwordLabel);
			frame.add(passwordField);
			frame.add(joinButton);
			frame.add(searchButton);
			frame.add(new JScrollPane(resultArea));

			Kdh0222MemberService service = new Kdh0222MemberService();

			joinButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = nameField.getText(); // 이름 입력값 가져오기
					String email = emailField.getText(); // 이메일 입력값 가져오기
					String password = passwordField.getText(); // 비밀번호 입력값 가져오기

					if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "모든 필드를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
						return;
					}
					service.registerMember(name, email, password);
					JOptionPane.showMessageDialog(frame, "회원이 추가되었습니다.", "추가 완료", JOptionPane.INFORMATION_MESSAGE);

					// 입력 필드 초기화
					nameField.setText("");
					emailField.setText("");
					passwordField.setText("");
				}
			});
			searchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 모든 회원 정보를 가져와 resultArea에 출력
					List<Kdh0222MemberDTO> members = service.getAllMembers();
					resultArea.setText(""); // 이전 조회 결과 초기화
					for (Kdh0222MemberDTO member : members) {
						resultArea.append("이름: " + member.getName() + ", 이메일: " + member.getEmail() + "\n");
					}

					// 만약 조회된 회원이 없다면 안내 메시지
					if (members.isEmpty()) {
						resultArea.setText("등록된 회원이 없습니다.");
					}
				}
			});

			frame.setVisible(true);
		}
	
}