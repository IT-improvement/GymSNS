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
			case "get_list":
				action = new FriendListAction();
				break;
			case "add":
				action = new FriendAddAction();
				break;
			case "delete":
				action = new FriendDeleteAction();
				break;
			case "get_friend_request_list":
				action = new FriendRequestListAction();
				break;
			case "add_friend_request":
				action = new FriendRequestAddAction();
				break;
			case "delete_friend_request":
				action = new FriendRequestDeleteAction();
				break;
		}
		
		System.out.println(action);

		return action;
	}
}


