package feed.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBManager;

public class FeedDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static FeedDAO instance = new FeedDAO();
	
	public static FeedDAO getInstance() {
		return instance;
	}
	
	public ArrayList<Feed> getFeed() {
		ArrayList<Feed> list = new ArrayList<Feed>();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT title, content, user_code, create_date FROM feeds;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Feed feed = new Feed();
				feed.setTitle(rs.getString(1));
				feed.setContent(rs.getString(2));
				feed.setUserCode(rs.getInt(3));
				feed.setCreateDate(rs.getTimestamp(4));
				list.add(feed);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
}
