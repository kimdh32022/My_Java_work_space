package Practice_241031;

import java.util.List;

public class Kdh0222MemberService {
	private Kdh0222MemberDAO dao = new Kdh0222MemberDAO();
	
    public void registerMember(String name, String email, String password) {
        Kdh0222MemberDTO member = new Kdh0222MemberDTO(name, email, password);
        dao.addMember(member);
    }

    public List<Kdh0222MemberDTO> getAllMembers() {
        return dao.getMembers();
    }
}

	
	
	
	
	
