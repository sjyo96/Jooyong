
package univ.bucheon.member.DAO;

import univ.bucheon.member.DbUtil.*;

import univ.bucheon.member.VO.MemberVO;
import univ.bucheon.member.Util.ExceptionMetadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jooyong
 *
 */
public class MemberDAOImpl implements MemberDAO {
	
	private static MemberDAOImpl instance = null;
	private MemberDAOImpl() {}
	
	public static MemberDAOImpl getInstance() {
		
		if(instance == null) {
			instance = new MemberDAOImpl();
		}
		return instance;
	}

	@Override
	public void insertMember(MemberVO member) throws Exception {
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.Connect();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER VALUES"
				+ "(?,?,?,?,?,?,?,?,?,TO_DATE (? , 'yyyy-mm-dd'), SYSDATE)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,member.getId());
			pstmt.setString(2,member.getPw());
			pstmt.setString(3,member.getName());
			pstmt.setString(4,member.getGender());
			pstmt.setString(5,member.getEmail());
			pstmt.setString(6,member.getMobile());
			pstmt.setString(7,member.getPhone());
			pstmt.setString(8,member.getZip());
			pstmt.setString(9,member.getAddress());
			pstmt.setString(10,member.getBirthday());
			if(pstmt.executeUpdate() == 1) {
				System.out.println("회원 정보 저장에 성공하였습니다.");
			}else {
				System.out.println("회원 정보 저장에 실패하였습니다.");
			}
		} catch(SQLException e) {
			emd.printErr(e, con, true);
		}catch (Exception e) {
			emd.printErr(e, con, true);
        } finally {
            // 7. DB 연결 해제(자원 반납)
            DbUtil.close(con, pstmt, null);
        }

	}

	@Override
	public void updateMember(MemberVO member) throws Exception {
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.Connect();
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET pw = ?, name = ?, gender = ?, email = ?, mobile = ?, phone = ?, "
				   + "zip = ?, address = ?, birthday = ? WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,member.getPw());
			pstmt.setString(2,member.getName());
			pstmt.setString(3,member.getGender());
			pstmt.setString(4,member.getEmail());
			pstmt.setString(5,member.getMobile());
			pstmt.setString(6,member.getPhone());
			pstmt.setString(7,member.getZip());
			pstmt.setString(8,member.getAddress());
			pstmt.setString(9,member.getBirthday());
			pstmt.setString(10,member.getId());
			if(pstmt.executeUpdate() == 1) {
				System.out.println("회원 정보 수정에 성공하였습니다.");
			}else {
				System.out.println("회원 정보 수정에 실패하였습니다.");
			}
		} catch(SQLException e) {
			emd.printErr(e, con, true);
		}catch (Exception e) {
			emd.printErr(e, con, true);
            e.printStackTrace();
        } finally {
            // 7. DB 연결 해제(자원 반납)
            DbUtil.close(con, pstmt, null);
        }
	}

	@Override
	public List<MemberVO> getAllMembers() throws Exception {
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.Connect(); // DB 연결
        ArrayList<MemberVO> list = new ArrayList<MemberVO>();
        PreparedStatement pstmt = null; // SQL 처리 객체
        ResultSet rs = null; // SQL 결과셋 객체
        String sql = "SELECT * FROM MEMBER";
        try {
            pstmt = con.prepareStatement(sql); // SQL 구문 처리
            rs = pstmt.executeQuery(); // SQL 구문 실행 -> 결과셋     
            // if (rs.next()) {
            while (rs.next()) {
               MemberVO member = new MemberVO();
                // member.setId(rs.getString(1)); // (O)
               member.setId(rs.getString("id")); // (O)
               member.setPw(rs.getString("pw"));
               member.setName(rs.getString("name"));
               member.setGender(rs.getString("gender"));
               member.setEmail(rs.getString("email"));
               member.setMobile(rs.getString("mobile"));
               member.setPhone(rs.getString("phone"));
               member.setZip(rs.getString("zip"));
               member.setAddress(rs.getString("address"));
               member.setBirthday(rs.getString("birthday"));
               member.setJoindate(rs.getString("joindate"));
               list.add(member);
            } //
        } catch (SQLException e) {
        	emd.printErr(e, con, true);
        } catch (Exception e) {
        	emd.printErr(e, con, true);
        } finally {
            DbUtil.close(con, pstmt, rs);
        } //
       for(int i = 0; i < list.size(); i++) {
    	   System.out.println(list.get(i));
       }
       return list;
	}

	@Override
	public MemberVO getMember(String id) throws Exception {
			ExceptionMetadata emd
	        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
			Connection con = DbUtil.Connect();
	        MemberVO member = new MemberVO();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        String sql = "SELECT * FROM MEMBER "
	                   + "WHERE id=?";
	        try {
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1,  id);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	            	member.setId(rs.getString("id"));
	                member.setPw(rs.getString("pw"));
	                member.setName(rs.getString("name"));
	                member.setGender(rs.getString("gender"));
	                member.setEmail(rs.getString("email"));
	                member.setMobile(rs.getString("mobile"));
	                member.setPhone(rs.getString("phone"));
	                member.setZip(rs.getString("zip"));
	                member.setAddress(rs.getString("address"));
	                member.setBirthday(rs.getString("birthday"));
	                member.setJoindate(rs.getString("joindate"));
	            }
	        } catch (SQLException e) {
	        	emd.printErr(e, con, true);
	        } catch (Exception e) {
	        	emd.printErr(e, con, true);
	        } finally {
	            DbUtil.close(con, pstmt, rs);
	        }
	        return member;
	}
	
	@Override
	public void deleteMember(String id) throws Exception{
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.Connect();		
		PreparedStatement pstmt = null;	
		String sql = "DELETE FROM MEMBER WHERE id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);		
			pstmt.setString(1,id);	
			if(pstmt.executeUpdate() == 1) {
				System.out.println("회원 정보 삭제에 성공하였습니다.");
			}else {
				System.out.println("회원 정보 삭제에 실패하였습니다.");
				
			}
		} catch(SQLException e) {
			emd.printErr(e, con, true);
		}catch (Exception e) {
			emd.printErr(e, con, true);
        } finally {
            DbUtil.close(con, pstmt, null);
        }
	}

	@Override
	public boolean isMember(String id) throws Exception {
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.Connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM MEMBER WHERE id = ?";
        boolean check = true;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,  id);      
            rs = pstmt.executeQuery();
            while(rs.next()) {
            	if(rs.getInt("COUNT(*)") == 0) {
            		check = false;
            		System.out.println("해당 ID의 회원정보가 존재하지 않습니다.");
            	}else {
            		System.out.println("해당 ID의 회원정보가 존재합니다.");
            	}
            	
            }
            
        } catch (SQLException e) {
        	emd.printErr(e, con, true);
        } catch (Exception e) {
        	emd.printErr(e, con, true);
        } finally {
            DbUtil.close(con, pstmt, rs);
        }
       
        return check;
	}

	@Override
	public boolean isMember(String id, String pw) throws Exception {
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.Connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM MEMBER WHERE id = ? AND pw = ?";
        boolean check = true;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,  id);
            pstmt.setString(2,  pw);
            rs = pstmt.executeQuery();
            while(rs.next()) {
            	if(rs.getInt("COUNT(*)") == 0) {
            		check = false;
            		System.out.println("해당 ID,PW의 회원정보가 존재하지 않습니다.");
            	}else {
            		System.out.println("해당 ID,PW의 회원정보가 존재합니다.");
            	}
            }
        } catch (SQLException e) {
        	emd.printErr(e, con, true);
        } catch (Exception e) {
        	emd.printErr(e, con, true);
        } finally {
            DbUtil.close(con, pstmt, rs);
        } 
        return check;
	}

	@Override
	public int getRowCount() throws Exception {
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.Connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM MEMBER";
        int rc = 0;
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	rc = rs.getInt("count(*)");
            } 
        } catch (SQLException e) {
        	emd.printErr(e, con, true);
        } catch (Exception e) {
        	emd.printErr(e, con, true);
        } finally {
            DbUtil.close(con, pstmt, rs);
        } 
		return rc;
	}

	@Override
	public MemberVO[] getAllMembersArray() throws Exception {
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		MemberVO[] mbvo = new MemberVO[this.getRowCount()];
		Connection con = DbUtil.Connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM MEMBER";
        int x = 0;
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	MemberVO member = new MemberVO();
            	member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setGender(rs.getString("gender"));
                member.setEmail(rs.getString("email"));
                member.setMobile(rs.getString("mobile"));
                member.setPhone(rs.getString("phone"));
                member.setZip(rs.getString("zip"));
                member.setAddress(rs.getString("address"));
                member.setBirthday(rs.getString("birthday"));
                member.setJoindate(rs.getString("joindate"));
            	mbvo[x++] = member;
            } //
        } catch (SQLException e) {
        	emd.printErr(e, con, true);
        } catch (Exception e) {
        	emd.printErr(e, con, true);
        } finally {
            DbUtil.close(con, pstmt, rs);
        } //
       for(int i = 0; i < this.getRowCount(); i++) {
    	   System.out.println(mbvo[i]);
       }
		return mbvo;
	}

	@Override
	public void getMemberInfo() throws Exception {
		Connection con = DbUtil.Connect();
	    DatabaseMetaData dbmd = con.getMetaData();
	    ResultSet rs = null;
	    ResultSetMetaData rsmd = null;
	    PreparedStatement pstmt = null;
	    String sql = "SELECT * FROM MEMBER";
	   
	    pstmt = con.prepareStatement(sql);
	    rs = pstmt.executeQuery();
	   
	    rsmd = rs.getMetaData();
	   
	    System.out.println("DB 종류 : "+dbmd.getDatabaseProductName());
	    System.out.println("DB 버전1 : "+dbmd.getDatabaseMajorVersion()+"."
	                                  +dbmd.getDatabaseMinorVersion());
	    System.out.println("DB 버전(full) : "+dbmd.getDatabaseProductVersion());
	    System.out.println("DB JDBC 드라이버 버전 : "+dbmd.getDriverMajorVersion()+"."
	                                              +dbmd.getDriverMinorVersion());
	   
	    System.out.println("컬럼의 수 : "+ rsmd.getColumnCount());
	    System.out.println("컬럼 라벨명 : "+ rsmd.getColumnLabel(1));
	    System.out.println("컬럼 라벨명 : "+ rsmd.getColumnLabel(2));
	    System.out.println("컬럼명 : "+ rsmd.getColumnName(1));
	    System.out.println("컬럼명 : "+ rsmd.getColumnName(2));
	    System.out.println("컬럼 타입 : "+ rsmd.getColumnTypeName(1));
	    System.out.println("컬럼 타입 : "+ rsmd.getColumnTypeName(5));
	    System.out.println("컬럼 타입 대응 클래스명 : "+rsmd.getColumnClassName(1));
	   
	    ResultSet pks = dbmd.getPrimaryKeys(null, null, "MEMBER");
	   
	    while (pks.next()) {
	       
	        System.out.println("현재 테이블 기본키명 : "+pks.getString("COLUMN_NAME"));
	        // index = 4
	        System.out.println("현재 계정명 : " + pks.getString("TABLE_SCHEM")); // index = 2
	        System.out.println("현재 테이블명 : " +pks.getString("TABLE_NAME")); // index = 3
	        System.out.println("현재 키 시퀀스 : " +pks.getString("KEY_SEQ")); // index = 5
	        System.out.println("현재 기본키 제약조건명 : " +pks.getString("PK_NAME"));
	        // index = 6
	       
	    } // while
	   
	    // System.out.println("Member 테이블 기본키 : "
	    //                + (pks.next() ? pks.getString("COLUMN_NAME") : "없음"));
	   
	    DbUtil.close(con, pstmt, rs);   
		
	}
}

