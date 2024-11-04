package Practice_241031;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Kdh0222MemberDAO {

	private Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		return DriverManager.getConnection(url, user, password);
	}

	public void addMember(Kdh0222MemberDTO member) {
		String sql = "INSERT INTO Member501 (id, name, email, password) VALUES (member501_seq.NEXTVAL,?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Kdh0222MemberDTO> getMembers() {
		List<Kdh0222MemberDTO> members = new ArrayList<>();
		String sql = "SELECT * FROM Member501";
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				members.add(new Kdh0222MemberDTO(name, email, password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
}
