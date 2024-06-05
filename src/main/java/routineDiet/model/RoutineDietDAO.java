package routineDiet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import routine.model.RoutineRequestDTO;
import routine.model.RoutineResponseDTO;
import util.DBManager;

public class RoutineDietDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private RoutineDietDAO() {

    }

    private static RoutineDietDAO instance = new RoutineDietDAO();

    public static RoutineDietDAO getInstance() {return instance;}

    public List<RoutineDietResponseDTO> readRoutineDiet(int code){
        List< RoutineDietResponseDTO> list = new ArrayList<RoutineDietResponseDTO>();

        conn = DBManager.getConnection();
        try {
            String sql ="SELECT f.food_name, fc.category_name, r.day, f.food_index, r.routine_index\r\n"
                    + "FROM foods f, food_categories fc, routines r, routine_diet_details rd\r\n"
                    + "WHERE f.food_index IN (\r\n"
                    + "    SELECT rd.food_index\r\n"
                    + "    FROM routine_diet_details rd\r\n"
                    + "    WHERE rd.routine_index in(\r\n"
                    + "		SELECT routine_index\r\n"
                    + "        FROM routines\r\n"
                    + "        WHERE user_code=?\r\n"
                    + "    )\r\n"
                    + ")\r\n"
                    + "and f.food_category_index = fc.food_category_index\r\n"
                    + "and r.routine_index = rd.routine_index\r\n"
                    + "and f.food_index = rd.food_index\r\n"
                    + "order by day asc\r\n"
                    + ";";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, code);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                RoutineDietResponseDTO dto = new RoutineDietResponseDTO();
                dto.setName(rs.getString(1));
                dto.setCategory(rs.getString(2));
                dto.setDay(rs.getString(3));
                dto.setFoodIndex(rs.getInt(4));
                dto.setRoutineIndex(rs.getInt(5));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("루틴 읽기 실패");
        }finally {
            DBManager.close(conn, pstmt,rs);
        }

        return list;
    }

    private RoutineDietRequestDTO findRoutineDietIndex(RoutineDietRequestDTO dto) {
        RoutineDietRequestDTO temp = dto;

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
        }finally {
            DBManager.close(conn, pstmt,rs);
        }
        return temp;
    }

    public void inesrtRoutineDiet(RoutineDietRequestDTO dto) {
        RoutineDietRequestDTO requestDTO = findRoutineDietIndex(dto);

        conn = DBManager.getConnection();
        try {
            String sql = "INSERT INTO routine_details(routine_index, food_index, create_date)"
                    + " VALUES(?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, requestDTO.getRoutineIndex());
            pstmt.setInt(2, requestDTO.getFoodIndex());
            pstmt.setTimestamp(3, requestDTO.getDate());

            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("루틴 생성 실패");
        }finally {
            DBManager.close(conn, pstmt,rs);
        }
    }

    public void deleteRoutineDiet(RoutineDietRequestDTO dto) {
        RoutineDietRequestDTO requestDTO = findRoutineDietIndex(dto);

        conn = DBManager.getConnection();
        try {
            String sql = "DELETE FROM routine_details WHERE routine_index=? and food_index=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, requestDTO.getRoutineIndex());
            pstmt.setInt(2, requestDTO.getFoodIndex());

            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("루틴 삭제 오류");
        }finally {
            DBManager.close(conn, pstmt,rs);
        }
    }

}
