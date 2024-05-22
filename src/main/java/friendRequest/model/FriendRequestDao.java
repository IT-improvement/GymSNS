package friendRequest.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class FriendRequestDao {
	private static FriendRequestDao instance = new FriendRequestDao();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private FriendRequestDao() { }
	
	public static FriendRequestDao getInstance() {
		return instance;
	}
	
	public List<FriendRequestResponseDto> findFriendRequestAll(FriendRequestRequestDto friendDto) {
		List<FriendRequestResponseDto> friendRequests = new ArrayList<>();
		String sql = "SELECT user_code_self, user_code_other "
					+ "FROM friend_requests "
					+ "WHERE user_code_self=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, friendDto.getUserCodeSelf());

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int userCodeSelf = rs.getInt(1);
				int userCodeOther = rs.getInt(2);
				
				friendRequests.add(new FriendRequestResponseDto(userCodeSelf, userCodeOther));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return friendRequests;
	}

	public boolean addFriendRequest(FriendRequestRequestDto friendDto) {
		boolean isAdded = true;
		String sql = "INSERT INTO friend_requests(user_code_self, user_code_other) "
				+ "VALUES (?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, friendDto.getUserCodeOther());
			pstmt.setInt(2, friendDto.getUserCodeSelf());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			isAdded = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isAdded;
	}
	
	public boolean deleteReceivedFriendRequest(FriendRequestRequestDto friendDto) {
		boolean isDeleted = true;
		String sql = "DELETE FROM friend_requests "
				+ "WHERE user_code_self=? AND user_code_other=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, friendDto.getUserCodeSelf());
			pstmt.setInt(2, friendDto.getUserCodeOther());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			isDeleted = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isDeleted;
	}

	public boolean deleteSentFriendRequest(FriendRequestRequestDto friendDto) {
		boolean isDeleted = true;
		String sql = "DELETE FROM friend_requests "
				+ "WHERE user_code_other=? AND user_code_self=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, friendDto.getUserCodeSelf());
			pstmt.setInt(2, friendDto.getUserCodeOther());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			isDeleted = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isDeleted;
	}
}