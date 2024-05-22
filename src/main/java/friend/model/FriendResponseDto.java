package friend.model;

public class FriendResponseDto {
	private int index;
	private	int userCodeSelf;
	private	int userCodeFriend;
	private	String userId;
	private	String userName;
	
	public FriendResponseDto(int index, int userCodeSelf, int userCodeFriend, String userId, String userName) {
		this.index = index;
		this.userCodeSelf = userCodeSelf;
		this.userCodeFriend = userCodeFriend;
		this.userId = userId;
		this.userName = userName;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getUserCodeSelf() {
		return userCodeSelf;
	}

	public void setUserCodeSelf(int userCodeSelf) {
		this.userCodeSelf = userCodeSelf;
	}

	public int getUserCodeFriend() {
		return userCodeFriend;
	}

	public void setUserCodeFriend(int userCodeFriend) {
		this.userCodeFriend = userCodeFriend;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}