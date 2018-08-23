/**
 * 
 */
package univ.bucheon.member.Test;

import univ.bucheon.member.DAO.MemberDAO;
import univ.bucheon.member.DAO.MemberDAOImpl;
import univ.bucheon.member.VO.MemberVO;

/**
 * @author Jooyong
 *
 */
public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		MemberDAO dao = MemberDAOImpl.getInstance();
		MemberVO vo = new MemberVO("ronaldo7", "142414", "호날두", "M", "ronaldo7@juventus.com",
				"010-1241-2525", "112-414-7521", "52322", "유벤투스","19850225");
		
		dao.deleteMember("ronaldo7");
		
		dao.insertMember(vo); //회원 정보 삽입
		
		dao.updateMember(vo); //회원 정보 수정
		dao.getAllMembers(); //전체 회원정보 조회(ArrayList)
		
		dao.getAllMembersArray(); //전체 회원정보 조회(배열)
		
		System.out.println(dao.getMember("sjyo96")); //개별 회원 정보 조회
		
		System.out.println("행의 수 = " + dao.getRowCount()); //행 개수조회
		
		dao.getMemberInfo();
		
		
	}
		
}


