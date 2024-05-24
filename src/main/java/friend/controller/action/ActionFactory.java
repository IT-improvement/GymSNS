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
			case "add":
				action = new FriendAddAction();
				break;
			case "delete":
				action = new FriendDeleteAction();
				break;
				
			// friend request
			case "read_friend_request_all":
				action = new FriendRequestReadAllAction();
				break;
			case "add_friend_request":
				action = new FriendRequestAddAction();
				break;
			case "delete_friend_request":
				action = new FriendRequestDeleteAction();
				break;
		}

		return action;
	}
}
