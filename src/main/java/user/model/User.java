package user.model;

import java.sql.Timestamp;

public class User {
	private int code;
	private String id;
	private String password;
	private String email;
	private String name;
	private String birth;
	private String gender;
	private String telecom;
	private String phone;
	private String profileImage;
	private Timestamp regDate;
	private Timestamp modDate;
		
	public User() {
		
	}
	
	public User(String id, String email, String name, String birth, String gender, String telecom, String phone,
			Timestamp regDate, Timestamp modDate) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.telecom = telecom;
		this.phone = phone;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public User(String id, String password, String email, String name, String birth, String gender, String telecom,
			String phone) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.telecom = telecom;
		this.phone = phone;
	}

	public User(String id, String password, String email, String name, String birth, String gender, String telecom,
				String phone, String profileImage) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.telecom = telecom;
		this.phone = phone;
		this.profileImage = profileImage;
	}

	public User(int code, String id, String password, String email, String name, String birth, String gender,
			String telecom, String phone) {
		super();
		this.code = code;
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.telecom = telecom;
		this.phone = phone;
	}

	public int getCode() {
		return code;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getBirth() {
		return birth;
	}

	public String getGender() {
		return gender;
	}

	public String getTelecom() {
		return telecom;
	}

	public String getPhone() {
		return phone;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

}