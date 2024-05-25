package friend.controller.action;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() { }
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		switch (command) {
			// friend
			case "read_all":
				action = new FriendReadAllAction();
				break;
			case "read_one":
				action = new FriendReadOneAction();
				break;
			case "create":
				action = new FriendCreateAction();
				break;
			case "delete":
				action = new FriendDeleteAction();
				break;
				
			// friend request
			case "read_all_friend_request":
				action = new FriendRequestReadAllAction();
				break;
			case "create_friend_request":
				action = new FriendRequestCreateAction();
				break;
			case "delete_friend_request":
				action = new FriendRequestDeleteAction();
				break;
		}

		return action;
	}
}
