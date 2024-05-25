package friend.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class FriendDao {
	private static FriendDao instance = new FriendDao();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private FriendDao() { }

	public static FriendDao getInstance() {
		return instance;
	}
	
	public boolean isFriend(FriendRequestDto friendDto) {
		return findFriendByUserCode(friendDto) != null;
	}

	public List<FriendResponseDto> findFriendAll(FriendRequestDto friendDto) {
		List<FriendResponseDto> friends = new ArrayList<>();
		friends.addAll(findFriendAllFromLeftColumn(friendDto));
		friends.addAll(findFriendAllFromRightColumn(friendDto));
		
		return friends;
	}

	public FriendResponseDto findFriendByUserCode(FriendRequestDto friendDto) {
		FriendResponseDto friend = null;
		String sql = "SELECT friend_index, user_code_one, user_code_two, users.id, users.name "
					+ "FROM friends "
					+ "JOIN users on users.code = friends.user_code_two "
					+ "WHERE user_code_one=? AND user_code_two=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			if (friendDto.getUserCodeSelf() < friendDto.getUserCodeFriend()) {
				pstmt.setInt(1, friendDto.getUserCodeSelf());
				pstmt.setInt(2, friendDto.getUserCodeFriend());
			} else {
				pstmt.setInt(1, friendDto.getUserCodeFriend());
				pstmt.setInt(2, friendDto.getUserCodeSelf());
			}
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int index = rs.getInt(1);
				int userCodeSelf = rs.getInt(2);
				int userCodeFriend = rs.getInt(3);
				String friendUserId = rs.getString(4);
				String friendUserName = rs.getString(5);
				
				friend = new FriendResponseDto(index, userCodeSelf, userCodeFriend, friendUserId, friendUserName);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return friend;
	}

	private List<FriendResponseDto> findFriendAllFromLeftColumn(FriendRequestDto friendDto) {
		List<FriendResponseDto> friends = new ArrayList<>();
		String sql = "SELECT friend_index, user_code_two, user_code_one, users.id, users.name "
					+ "FROM friends "
					+ "JOIN users on users.code = friends.user_code_one "
					+ "WHERE user_code_two=?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, friendDto.getUserCodeSelf());
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int index = rs.getInt(1);
				int userCodeSelf = rs.getInt(2);
				int userCodeFriend = rs.getInt(3);
				String friendUserId = rs.getString(4);
				String friendUserName = rs.getString(5);
				
				friends.add(new FriendResponseDto(index, userCodeSelf, userCodeFriend, friendUserId, friendUserName));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return friends;
	}

	private List<FriendResponseDto> findFriendAllFromRightColumn(FriendRequestDto friendDto) {
		List<FriendResponseDto> friends = new ArrayList<>();
		String sql = "SELECT friend_index, user_code_one, user_code_two, users.id, users.name "
					+ "FROM friends "
					+ "JOIN users on users.code = friends.user_code_two "
					+ "WHERE user_code_one=?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, friendDto.getUserCodeSelf());
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int index = rs.getInt(1);
				int userCodeSelf = rs.getInt(2);
				int userCodeFriend = rs.getInt(3);
				String friendUserId = rs.getString(4);
				String friendUserName = rs.getString(5);
				
				friends.add(new FriendResponseDto(index, userCodeSelf, userCodeFriend, friendUserId, friendUserName));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return friends;
	}
	
	public boolean createFriend(FriendRequestDto friendRequestDto) {
		boolean isAdded = true;
		String sql = "INSERT INTO friends(user_code_one, user_code_two) "
				+ "VALUES (?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			if (friendRequestDto.getUserCodeSelf() < friendRequestDto.getUserCodeFriend()) {
				pstmt.setInt(1, friendRequestDto.getUserCodeSelf());
				pstmt.setInt(2, friendRequestDto.getUserCodeFriend());
			} else {
				pstmt.setInt(1, friendRequestDto.getUserCodeFriend());
				pstmt.setInt(2, friendRequestDto.getUserCodeSelf());
			}

			pstmt.execute();
			
		} catch (Exception e) {
			System.out.println(e);
			isAdded = false;
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return isAdded;
	}
	
	public boolean deleteFriendById(FriendRequestDto friendDto) {
		boolean isDeleted = true;
		FriendResponseDto friend = findFriendByUserCode(friendDto);
		String sql = "DELETE FROM friends "
					+ "WHERE user_code_one=? AND user_code_two=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			if (friendDto.getUserCodeSelf() < friend.getUserCodeFriend()) {
				pstmt.setInt(1, friendDto.getUserCodeSelf());
				pstmt.setInt(2, friendDto.getUserCodeFriend());
			} else {
				pstmt.setInt(1, friendDto.getUserCodeFriend());
				pstmt.setInt(2, friendDto.getUserCodeSelf());
			}

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