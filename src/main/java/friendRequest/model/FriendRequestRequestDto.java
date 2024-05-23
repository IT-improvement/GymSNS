package friendRequest.model;

public class FriendRequestRequestDto {
	private	int userCodeSelf;
	private	int userCodeOther;
	private String userIdOther;
	private String userNameOther;
	
	public FriendRequestRequestDto() { }

	public FriendRequestRequestDto(int userCodeSelf) {
		this.userCodeSelf = userCodeSelf;
	}
	
	public FriendRequestRequestDto(int userCodeSelf, int userCodeOther) {
		this.userCodeSelf = userCodeSelf;
		this.userCodeOther = userCodeOther;
	}

	public FriendRequestRequestDto(int userCodeSelf, int userCodeOther, String userIdOther, String userNameOther) {
		this.userCodeSelf = userCodeSelf;
		this.userCodeOther = userCodeOther;
		this.userIdOther = userIdOther;
		this.userNameOther = userNameOther;
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

	public String getUserIdOther() {
		return userIdOther;
	}

	public void setUserIdOther(String userIdOther) {
		this.userIdOther = userIdOther;
	}

	public String getUserNameOther() {
		return userNameOther;
	}

	public void setUserNameOther(String userNameOther) {
		this.userNameOther = userNameOther;
	}
}