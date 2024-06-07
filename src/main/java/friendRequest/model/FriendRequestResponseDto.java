package friendRequest.model;

public class FriendRequestResponseDto {
	private	int userCodeSelf;
	private	int userCodeOther;
	private String userIdOther;
	private String userNameOther;
	private String userProfileImageOther;

	public FriendRequestResponseDto(int userCodeSelf, int userCodeOther, String userIdOther, String userNameOther) {
		this.userCodeSelf = userCodeSelf;
		this.userCodeOther = userCodeOther;
		this.userIdOther = userIdOther;
		this.userNameOther = userNameOther;
	}

	public FriendRequestResponseDto(int userCodeSelf, int userCodeOther, String userIdOther, String userNameOther, String userProfileImageOther) {
		this.userCodeSelf = userCodeSelf;
		this.userCodeOther = userCodeOther;
		this.userIdOther = userIdOther;
		this.userNameOther = userNameOther;
		this.userProfileImageOther = userProfileImageOther;
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

	public String getUserProfileImageOther() {
		return userProfileImageOther;
	}

	public void setUserProfileImageOther(String userProfileImageOther) {
		this.userProfileImageOther = userProfileImageOther;
	}
}