package feed.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class FeedDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static FeedDAO instance = new FeedDAO();
	
	public static FeedDAO getInstance() {
		return instance;
	}
	
	public FeedResponseDTO searchCommentByFeedIndexAndUserCode(FeedRequestDTO feedDto) {
		FeedResponseDTO feed = new FeedResponseDTO();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT feed_index, user_code, comment FROM feed_comments WHERE feed_index =? AND user_code= ? AND comment = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getFeedIndex());
			pstmt.setInt(2, feedDto.getUserCode());
			pstmt.setString(3, feedDto.getComment());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				feed.setFeedIndex(rs.getInt(1));
				feed.setUserCode(rs.getInt(2));
				feed.setComment(rs.getString(3));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return feed;
	}


	private List<Feed> addCommentToFeed(List<Feed> feeds) {
		String sql = "SELECT content "
					+ "FROM feed_comments "
					+ "WHERE feed_index = ?";

		try {
			conn = DBManager.getConnection();

			for (int i = 0; i < feeds.size(); i++) {
				Feed feed = feeds.get(i);

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, feed.getFeedIndex());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					feed.addComment(rs.getString(1));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return feeds;
	}

	public ArrayList<Feed> getAllFeed() {
		ArrayList<Feed> list = new ArrayList<Feed>();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT DISTINCT f.feed_index, f.user_code, title, content, f.create_date, mod_date, " +
					"(SELECT COUNT(*) FROM favorites WHERE f.feed_index = favorites.feed_index) AS favorite_count " +
					"FROM feeds AS f " +
					"JOIN favorites AS fav ON fav.feed_index = f.feed_index ";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Feed feed = new Feed();
				feed.setFeedIndex(rs.getInt(1));
				feed.setUserCode(rs.getInt(2));
				feed.setTitle(rs.getString(3));
				feed.setContent(rs.getString(4));
				feed.setCreateDate(rs.getTimestamp(5));
				feed.setModDate(rs.getTimestamp(6));

				list.add(feed);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
            DBManager.close(conn, pstmt);
        }

		addCommentToFeed(list);

		return list;
	}
	
	public FeedResponseDTO getFeedByFeedIndex(int feedIndex) {
		FeedResponseDTO feedDto = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT title, content, user_code, create_date FROM feeds WHERE feed_index = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedIndex);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String title = rs.getString(1);
				String content = rs.getString(2);
				int userCode = rs.getInt(3);
				Timestamp createDate = rs.getTimestamp(4);
				
				feedDto = new FeedResponseDTO(title, content, userCode, createDate);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
            DBManager.close(conn, pstmt);
        }
		return feedDto;
	}
	
	public FeedResponseDTO createFeed(FeedRequestDTO feedDto) {
		FeedResponseDTO feed = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO feeds(user_code, title, content) VALUES(?,?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getUserCode());
			pstmt.setString(2, feedDto.getTitle());
			pstmt.setString(3, feedDto.getContent());
			pstmt.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
            DBManager.close(conn, pstmt);
        }
		
		return feed;
	}
	
	public FeedResponseDTO updateFeed(FeedRequestDTO feedDto) {
		FeedResponseDTO feed = null;		
		
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE feeds SET title=?, content=? WHERE feed_index = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, feedDto.getTitle());
			pstmt.setString(2, feedDto.getContent());
			pstmt.setInt(3, feedDto.getFeedIndex());
			pstmt.execute();
			feed = getFeedByFeedIndex(feedDto.getFeedIndex());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
            DBManager.close(conn, pstmt);
        }
		
		return feed;
	}
	
	public void deleteFeed(FeedRequestDTO feedDto) {
		try {
			conn = DBManager.getConnection();
			System.out.println(feedDto.getFeedIndex());
			String sql = "DELETE FROM feeds WHERE feed_index = ?;";
			pstmt = conn.prepareStatement(sql);
			System.out.println(feedDto.getFeedIndex());
			pstmt.setInt(1, feedDto.getFeedIndex());
			pstmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
            DBManager.close(conn, pstmt);
        }
	}
	
	public FeedResponseDTO readFeedFavoriteInfo(FeedRequestDTO feedDto) {
		FeedResponseDTO feed = new FeedResponseDTO();
		
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT COUNT(*) FROM favorites WHERE feed_index = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getFeedIndex());
			pstmt.execute();
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				feed.setFavoriteCount(rs.getInt(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
            DBManager.close(conn, pstmt);
        }
		return feed;
	}
	
	public FeedResponseDTO createFeedFavorate(FeedRequestDTO feedDto) {
		FeedResponseDTO feed = new FeedResponseDTO();
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO favorites(feed_index, user_code) VALUES(?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getFeedIndex());
			pstmt.setInt(2, feedDto.getUserCode());
			pstmt.execute();


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
            DBManager.close(conn, pstmt);
        }
		
		return feed;
	}

	public FeedResponseDTO checkFeedFavorite(FeedRequestDTO feedDto) {
		FeedResponseDTO feed = null;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT feed_index, user_code FROM favorites WHERE feed_index = ? AND user_code = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getFeedIndex());
			pstmt.setInt(2, feedDto.getUserCode());
			pstmt.execute();
			rs = pstmt.executeQuery();

			if (rs.next()) {
				feed.setFeedIndex(rs.getInt(1));
				feed.setUserCode(rs.getInt(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

		return feed;
	}

	public FeedResponseDTO deleteFeedFavorite(FeedRequestDTO feedDto) {
		FeedResponseDTO feed = null;
		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM favorites WHERE feed_index = ? AND user_code = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getFeedIndex());
			pstmt.setInt(2, feedDto.getUserCode());
			pstmt.execute();

			feed = checkFeedFavorite(feedDto);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}


		return feed;
	}

	public ArrayList<Feed> commentRead(FeedRequestDTO feedDto) {
		ArrayList<Feed> list = new ArrayList<Feed>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT feed_index, user_code, content, mod_date FROM feed_comments WHERE feed_index = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getFeedIndex());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Feed feed = new Feed();
				feed.setFeedIndex(rs.getInt(1));
				feed.setUserCode(rs.getInt(2));
//				feed.setComment(rs.getString(3));
				feed.setModDate(rs.getTimestamp(4));
				list.add(feed);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

		return list;
	}

	public FeedResponseDTO createComment(FeedRequestDTO feedDto) {
		FeedResponseDTO feed = null;
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO feed_comments(feed_index, user_code, comment) VALUES(?, ?, ?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getFeedIndex());
			pstmt.setInt(2, feedDto.getUserCode());
			pstmt.setString(3, feedDto.getComment());
			pstmt.execute();
			feed = searchCommentByFeedIndexAndUserCode(feedDto);

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

		return feed;
	}

}
