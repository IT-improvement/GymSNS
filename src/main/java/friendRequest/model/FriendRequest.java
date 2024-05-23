package friendRequest.model;

public class FriendRequest {
	private	int userCodeSelf;
	private	int userCodeOther;
	private String userIdOther;
	private String userNameOther;

	public FriendRequest(int userCodeSelf, int userCodeOther, String userIdOther, String userNameOther) {
		this.userCodeSelf = userCodeSelf;
		this.userCodeOther = userCodeOther;
		this.userIdOther = userIdOther;
		this.userNameOther = userNameOther;
	}

	public int getUserCodeSelf() {
		return userCodeSelf;
	}

	public int getUserCodeOther() {
		return userCodeOther;
	}

	public String getUserIdOther() {
		return userIdOther;
	}

	public String getUserNameOther() {
		return userNameOther;
	}
}