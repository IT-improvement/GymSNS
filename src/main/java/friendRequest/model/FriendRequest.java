package friendRequest.model;

public class FriendRequest {
	private	int userCodeSelf;
	private	int userCodeOther;

	public FriendRequest(int userCodeSelf, int userCodeOther) {
		this.userCodeSelf = userCodeSelf;
		this.userCodeOther = userCodeOther;
	}

	public int getUserCodeSelf() {
		return userCodeSelf;
	}

	public int getUserCodeOther() {
		return userCodeOther;
	}
}