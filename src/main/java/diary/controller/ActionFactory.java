package diary.controller;import org.apache.jasper.compiler.NewlineReductionServletWriter;

public class ActionFactory {

	private ActionFactory() {

	}

	private static ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		
		if (command.equals("read")) {
			System.out.println(new DiaryReadAction());
			action = new DiaryReadAction();
		}
		
		return action;
	}

}
