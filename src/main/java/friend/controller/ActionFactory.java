package friend.controller;

import friend.controller.action.*;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() { }
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		switch (command) {
			// friend CRUD
			case "read_all":
				action = new FriendReadAllAction();
				break;
			case "read_one":
				action = new FriendReadOneAction();
				break;
			case "read_relationship_status":
				action = new FriendRelationshipStatusAction();
				break;
			case "create":
				action = new FriendCreateAction();
				break;
			case "delete":
				action = new FriendDeleteAction();
				break;
				
			// friend request CRUD
			case "read_all_friend_request_received":
				action = new FriendRequestReceivedReadAllAction();
				break;
			case "read_one_friend_request_sent":
				action = new FriendRequestSentReadAction();
				break;
			case "accept_friend_request":
				action = new FriendRequestAcceptAction();
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
