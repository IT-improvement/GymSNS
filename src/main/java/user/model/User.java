package user.model;

import java.sql.Timestamp;

public class User {
	private String code;
	private String id;
	private String password;
	private String email;
	private String name;
	private String birth;
	private String gender;
	private String telecom;
	private String phone;
	private Timestamp regDate;
	private Timestamp modDate;
	
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

	public User(String code, String id, String password, String email, String name, String birth, String gender,
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

	public String getCode() {
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

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}
	
	
}