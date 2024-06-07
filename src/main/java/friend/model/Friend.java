package friend.model;

public class Friend {
	private int index;
	private	int userCodeSelf;
	private	int userCodeFriend;
	private	String userId;
	private	String userName;
	private	String userProfileImage;

	public Friend(int index, int userCodeSelf, int userCodeFriend, String userId, String userName, String userProfileImage) {
		this.index = index;
		this.userCodeSelf = userCodeSelf;
		this.userCodeFriend = userCodeFriend;
		this.userId = userId;
		this.userName = userName;
		this.userProfileImage = userProfileImage;
	}

	public int getIndex() {
		return index;
	}

	public int getUserCodeSelf() {
		return userCodeSelf;
	}

	public int getUserCodeFriend() {
		return userCodeFriend;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserProfileImage() {
		return userProfileImage;
	}
}
