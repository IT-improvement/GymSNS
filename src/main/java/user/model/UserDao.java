package user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import user.controller.PagingManager;
import util.DBManager;
import util.PasswordCrypto;

public class UserDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private UserDao() {
		
	}
	
	private static UserDao instance = new UserDao();
	
	public static UserDao getInstance() {
		return instance;
	}

	public ArrayList<User> getAllUser(Integer code, int pageNumber) {
		ArrayList<User> list = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT DISTINCT code, name, profile_image " +
					"FROM users" +
					"ORDER BY reg_date DESC " +
					"LIMIT ?," +
					PagingManager.LIMIT;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageNumber * PagingManager.LIMIT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setCode(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setProfileImage(rs.getString(3));

				list.add(user);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		System.out.println(list.size());

		System.out.println("><><>><><");
		//
		if (code != null) {
			//
		}

		return list;
	}
	
	public List<UserResponseDto> findUserAll() {
		List<UserResponseDto> list = new ArrayList<UserResponseDto>();
		
		try {
			conn = DBManager.getConnection();
			
			// 쿼리할 준비 
			String sql = "SELECT code, id, email, name, birth, gender, telecom, phone, profile_image FROM users";
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리 실행 
			rs = pstmt.executeQuery();
			
			// 튜플 읽기 
			while(rs.next()) {
				int code = rs.getInt(1);
				String id = rs.getString(2);
				String email = rs.getString(3);
				String name = rs.getString(4);
				String birth = rs.getString(5);
				String gender = rs.getString(6);
				String telecom = rs.getString(7);
				String phone = rs.getString(8);
				String profileImage = rs.getString(9);

				System.out.println("finduserall");
				System.out.println("id + profileImage" + id + profileImage);
				
				UserResponseDto user = new UserResponseDto(code, id, email, name, birth, gender, telecom, phone, profileImage);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public List<UserResponseDto> findUserAllWithLimit(int limit) {
		List<UserResponseDto> list = new ArrayList<UserResponseDto>();

		try {
			conn = DBManager.getConnection();

			// 쿼리할 준비
			String sql = "SELECT code, id, email, name, birth, gender, telecom, phone "
						+ "FROM users "
						+ "LIMIT ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, limit);

			rs = pstmt.executeQuery();

			// 튜플 읽기
			while(rs.next()) {
				int code = rs.getInt(1);
				String id = rs.getString(2);
				String email = rs.getString(3);
				String name = rs.getString(4);
				String birth = rs.getString(5);
				String gender = rs.getString(6);
				String telecom = rs.getString(7);
				String phone = rs.getString(8);

				UserResponseDto user = new UserResponseDto(code, id, email, name, birth, gender, telecom, phone);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public List<UserResponseDto> findUserAllByIdOrName(String query) {
		List<UserResponseDto> list = new ArrayList<UserResponseDto>();
		String sql = "SELECT code, id, email, name, birth, gender, telecom, phone "
				+ "FROM users "
				+ "WHERE id LIKE ? OR name LIKE ?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			pstmt.setString(2, "%" + query + "%");

			rs = pstmt.executeQuery();

			while(rs.next()) {
				int code = rs.getInt(1);
				String id = rs.getString(2);
				String email = rs.getString(3);
				String name = rs.getString(4);
				String birth = rs.getString(5);
				String gender = rs.getString(6);
				String telecom = rs.getString(7);
				String phone = rs.getString(8);

				UserResponseDto user = new UserResponseDto(code, id, email, name, birth, gender, telecom, phone);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public List<UserResponseDto> findUserAllByIdOrNameWithLimit(String query, int limit) {
		List<UserResponseDto> list = new ArrayList<UserResponseDto>();
		String sql = "SELECT code, id, email, name, birth, gender, telecom, phone "
				+ "FROM users "
				+ "WHERE id LIKE ? OR name LIKE ? "
				+ "LIMIT ?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			pstmt.setString(2, "%" + query + "%");
			pstmt.setInt(3, limit);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				int code = rs.getInt(1);
				String id = rs.getString(2);
				String email = rs.getString(3);
				String name = rs.getString(4);
				String birth = rs.getString(5);
				String gender = rs.getString(6);
				String telecom = rs.getString(7);
				String phone = rs.getString(8);

				UserResponseDto user = new UserResponseDto(code, id, email, name, birth, gender, telecom, phone);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}

	public UserResponseDto findUserByIdAndPassword(String id, String password) {
		UserResponseDto user = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT id, code, email, name, birth, gender, telecom, phone, password FROM users WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int code = rs.getInt(2);
				String email = rs.getString(3);
				String name = rs.getString(4);
				String birth = rs.getString(5);
				String gender = rs.getString(6);
				String telecom = rs.getString(7);
				String phone = rs.getString(8);
				String encryptedPassword = rs.getString(9);
				
				if(PasswordCrypto.decrypt(password, encryptedPassword))
					user = new UserResponseDto(code, id, email, name, birth, gender, telecom, phone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public UserResponseDto findUserById(String id) {
		UserResponseDto user = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT id, email, name, birth, gender, telecom, phone, reg_date, mod_date FROM users WHERE id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				String email = rs.getString(2);
				String name = rs.getString(3);
				String birth = rs.getString(4);
				String gender = rs.getString(5);
				String telecom = rs.getString(6);
				String phone = rs.getString(7);
				Timestamp regDate = rs.getTimestamp(8);
				Timestamp modDate = rs.getTimestamp(9);

				user = new UserResponseDto(id, email, name, birth, gender, telecom, phone);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public UserResponseDto findUserByCode(int code) {
		UserResponseDto user = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT id, email, name, birth, gender, telecom, phone, profile_image, reg_date, mod_date FROM users WHERE code=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				String id = rs.getString(1);
				String email = rs.getString(2);
				String name = rs.getString(3);
				String birth = rs.getString(4);
				String gender = rs.getString(5);
				String telecom = rs.getString(6);
				String phone = rs.getString(7);
				String profileImage = rs.getString(8);
				Timestamp regDate = rs.getTimestamp(9);
				Timestamp modDate = rs.getTimestamp(10);

				user = new UserResponseDto(id, email, name, birth, gender, telecom, phone, profileImage);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public String findPassword(String id, String password) {
		User user = null;
		String encryptedPassword = "";

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT id, password FROM users WHERE id=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				encryptedPassword = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return encryptedPassword;
	}
	
	public boolean userExists(UserRequestDto userDto) {
		return findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) != null;
	}
	
	public boolean userExists(String id) {
		return findUserById(id) != null;
	}
	
	
	public UserResponseDto createUser(UserRequestDto userDto) {
		try {
			conn = DBManager.getConnection();
			
			String sql = "INSERT INTO users(id, password, email, name, birth, gender, telecom, phone) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			System.out.println("conn : " + conn);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, PasswordCrypto.encrypt(userDto.getPassword()));
			
			String email = userDto.getEmail().equals("") ? null : userDto.getEmail();
			pstmt.setString(3, email);
			
			pstmt.setString(4, userDto.getName());
			pstmt.setString(5, userDto.getBirth());
			pstmt.setString(6, userDto.getGender());
			pstmt.setString(7, userDto.getTelecom());
			pstmt.setString(8, userDto.getPhone());
			
			pstmt.execute();
			
			return findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			DBManager.close(conn, pstmt);
		}
		return null;
	}
	
	
	public void updateUserPassword(UserRequestDto userDto, String newPassword) {
		UserResponseDto user = null;
		
//		if(newPassword == null || newPassword.equals("")) {
//			return user;
//		}
//
//		if (!userExists(userDto)) {
//			return user;
//		}
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "UPDATE users SET password=? WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, PasswordCrypto.encrypt(newPassword));
			pstmt.setString(2, userDto.getId());

			pstmt.execute();
			
//			User userVo = findUserById(userDto.getId());
//			user = new UserResponseDto(userVo);
			user = findUserById(userDto.getId());
//			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
//		return user;
	}
	
	public void updateUserName(UserRequestDto userDto) {
		UserResponseDto user = null;
		
//		if (!userExists(userDto)) {
//			return user;
//		}
		try {
			conn = DBManager.getConnection();
			
			String sql = "UPDATE users SET name=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getName());
			pstmt.setString(2, userDto.getId());

			pstmt.execute();
			
			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
//		return user;
	}
	
	public void updateUserEmail(UserRequestDto userDto) {
		UserResponseDto user = null;
		
//		if (!userExists(userDto)) {
//			return user;
//		}
		try {
			conn = DBManager.getConnection();
			
			String sql = "UPDATE users SET email=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getEmail());
			pstmt.setString(2, userDto.getId());

			pstmt.execute();

			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
//		return user;
	}

	public void updateUserProfileImage(UserRequestDto userDto) {
		UserResponseDto user = null;

//		if (!userExists(userDto)) {
//			return user;
//		}
		try {
			conn = DBManager.getConnection();

			String sql = "UPDATE users SET profile_image=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getProfileImage());
			pstmt.setString(2, userDto.getId());
			System.out.println(userDto.getProfileImage());
			System.out.println(userDto.getId());

			pstmt.execute();

			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
//		return user;
	}

	public void updateUserTelecom(UserRequestDto userDto) {
		UserResponseDto user = null;

//		if (!userExists(userDto)) {
//			return user;
//		}

		try {
			conn = DBManager.getConnection();

			String sql = "UPDATE users SET telecom=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getTelecom());
			pstmt.setString(2, userDto.getId());

			pstmt.execute();

			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
//		return user;
	}
	
	public void updateUserPhone(UserRequestDto userDto) {
		UserResponseDto user = null;

		try {
			conn = DBManager.getConnection();
			
			String sql = "UPDATE users SET phone=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getPhone());
			pstmt.setString(2, userDto.getId());

			pstmt.execute();

			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
//		return user;
	}
	
	public boolean deleteUser(UserRequestDto userDto) {
		if (!userExists(userDto)) {
			return false;
		}

		try {
			conn = DBManager.getConnection();
			
			String sql = "DELETE FROM users WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userDto.getId());
			
			pstmt.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return false;
	}

	public boolean isIdDuplicate(String id) {
		boolean isDuplicate = false;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT id FROM users WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isDuplicate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return isDuplicate;
	}
	
	public boolean isEmailDuplicate(String email) {
		boolean isDuplicate = false;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT email FROM users WHERE email=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isDuplicate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return isDuplicate;
	}

	public boolean isPhoneDuplicate(String phone) {
		boolean isDuplicate = false;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT phone FROM users WHERE phone=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, phone);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				isDuplicate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return isDuplicate;
	}
	
}
