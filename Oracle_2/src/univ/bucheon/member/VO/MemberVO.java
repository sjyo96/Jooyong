/**
 * 
 */
package univ.bucheon.member.VO;

import java.sql.Date;

/**
 * @author Jooyong
 *
 */
public class MemberVO {
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String email;
	private String mobile;
	private String phone;
	private String zip;
	private String address;
	private String birthday;
	private String joindate;
	

	
	public MemberVO(String id, String pw, String name, String gender, String email, String mobile, String phone,
			String zip, String address, String birthday) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.mobile = mobile;
		this.phone = phone;
		this.zip = zip;
		this.address = address;
		this.birthday = birthday;
	}

	public MemberVO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
		
	}

	@Override
	public String toString() {
		return "id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", email=" + email
				+ ", mobile=" + mobile + ", phone=" + phone + ", zip=" + zip + ", address=" + address + ", birthday="
				+ birthday + ", joindate=" + joindate;
	}

}