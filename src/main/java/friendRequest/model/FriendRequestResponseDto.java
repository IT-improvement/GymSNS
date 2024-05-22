package friendRequest.model;

public class FriendRequestResponseDto {
	private	int userCodeSelf;
	private	int userCodeOther;

	public FriendRequestResponseDto(int userCodeSelf, int userCodeOther) {
		this.userCodeSelf = userCodeSelf;
		this.userCodeOther = userCodeOther;
	}

	public int getUserCodeSelf() {
		return userCodeSelf;
	}

	public void setUserCodeSelf(int userCodeSelf) {
		this.userCodeSelf = userCodeSelf;
	}

	public int getUserCodeOther() {
		return userCodeOther;
	}

	public void setUserCodeOther(int userCodeOther) {
		this.userCodeOther = userCodeOther;
	}
}