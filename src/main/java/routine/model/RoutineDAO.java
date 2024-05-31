package routine.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class RoutineDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private RoutineDAO() {
		
	}
	
	private static RoutineDAO instance = new RoutineDAO();
	
	public static RoutineDAO getInstance() {
		return instance;
	}
	
	public List<RoutineResponseDTO> readRoutine(int code){
		List<RoutineResponseDTO> list = new ArrayList<RoutineResponseDTO>();
		
		conn = DBManager.getConnection();
		try {
			String sql ="SELECT e.name, ec.name, r.day, e.exercise_index\r\n"
					+ "FROM exercises e, exercise_categories ec, routines r, routine_details rd\r\n"
					+ "WHERE e.exercise_index IN (\r\n"
					+ "    SELECT rd.exercise_index\r\n"
					+ "    FROM routine_details rd\r\n"
					+ "    WHERE rd.routine_index in(\r\n"
					+ "		SELECT routine_index\r\n"
					+ "        FROM routines\r\n"
					+ "        WHERE user_code=?\r\n"
					+ "    )\r\n"
					+ ")\r\n"
					+ "and e.exercise_category_index = ec.exercise_category_index\r\n"
					+ "and r.routine_index = rd.routine_index\r\n"
					+ "and e.exercise_index = rd.exercise_index\r\n"
					+ "order by day asc\r\n"
					+ ";";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RoutineResponseDTO dto = new RoutineResponseDTO();
				dto.setName(rs.getString(1));
				dto.setCategory(rs.getString(2));
				dto.setDay(rs.getString(3));
				dto.setExerciseIndex(rs.getInt(4));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("루틴 읽기 실패");
		}
		
		return list;
	}
	
	private RoutineRequestDTO findRoutineIndex(RoutineRequestDTO dto) {
		RoutineRequestDTO temp = dto;
		
		conn = DBManager.getConnection();
		try {
			String sql="SELECT routine_index FROM routines WHERE user_code=? and day=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserCode());
			pstmt.setString(2, dto.getDay());
			
			rs = pstmt.executeQuery();
			if(rs.next())
				temp.setRoutineIndex(rs.getInt(1));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("루틴인덱스 찾기 실패");
		}
		return temp;
	}
	
	public void inesrtRoutine(RoutineRequestDTO dto) {
		RoutineRequestDTO requestDTO = findRoutineIndex(dto);
		
		conn = DBManager.getConnection();
		try {
			String sql = "INSERT INTO routine_details(routine_index, exercise_index, create_date)"
					+ " VALUES(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, requestDTO.getRoutineIndex());
			pstmt.setInt(2, requestDTO.getExerciseIndex());
			pstmt.setTimestamp(3, requestDTO.getDate());
			
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("루틴 생성 실패");
		}
	}
	
	public void deleteRoutine(RoutineRequestDTO dto) {
		RoutineRequestDTO requestDTO = findRoutineIndex(dto);
		
		conn = DBManager.getConnection();
		try {
			String sql = "DLETE FROM routine_index WHERE routine_index=? and exercise_index";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, requestDTO.getRoutineIndex());
			pstmt.setInt(2, requestDTO.getExerciseIndex());
			
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("루틴 삭제 오류");
		}
	}
}
