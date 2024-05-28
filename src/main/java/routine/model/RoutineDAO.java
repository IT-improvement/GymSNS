package routine.model;

public class RoutineDAO {
	
	private RoutineDAO() {
	}
	
	private static RoutineDAO instance = new RoutineDAO();
	
	public static RoutineDAO getInstance() {
		return instance;
	}
	
}
