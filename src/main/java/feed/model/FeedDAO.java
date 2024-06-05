package feed.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import feed.controller.PagingManager;
import feed.controller.action.FeedCommentsObject;
import util.DBManager;

public class FeedDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static FeedDAO instance = new FeedDAO();

	public static FeedDAO getInstance() {
		return instance;
	}

	public FeedResponseDTO searchCommentByCommentIndex(FeedRequestDTO feedDto) {
		FeedResponseDTO feed = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT feed_index, user_code, comment FROM feed_comments WHERE feed_comment_index = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getCommentIndex());
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
		String sql = "SELECT feed_comment_index, comment, users.id, users.name, users.code "
				+ "FROM feed_comments " +
				"JOIN users ON users.code = feed_comments.user_code " +
				 "WHERE feed_index = ?";


		try {
			conn = DBManager.getConnection();

			for (int i = 0; i < feeds.size(); i++) {
				Feed feed = feeds.get(i);

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, feed.getFeedIndex());
				rs = pstmt.executeQuery();
				System.out.println(pstmt.toString());
				List<FeedCommentsObject> comments = new ArrayList<>();
				while (rs.next()) {
					comments.add(new FeedCommentsObject(
							rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getInt(5)
					));
				}

				feed.setComments(comments);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return feeds;
	}

	private Feed addCommentToFeedDetail(Feed feed) {

		List<FeedCommentsObject> comments = new ArrayList<>() {
		};
		try {
			String sql = "SELECT feed_comment_index, comment, users.id, users.name, users.code "
					+ "FROM feed_comments " +
					"JOIN users ON users.code = feed_comments.user_code " +
					"WHERE feed_index = ?";
			conn = DBManager.getConnection();


			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feed.getFeedIndex());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				FeedCommentsObject feedCommentsObject = new FeedCommentsObject();
				feedCommentsObject.setFeedCommentsIndex(rs.getInt(1));
				feedCommentsObject.setComment(rs.getString(2));
				feedCommentsObject.setUserId(rs.getString(3));
				feedCommentsObject.setUserName(rs.getString(4));
				feedCommentsObject.setUserCode(rs.getInt(5));
				comments.add(feedCommentsObject);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

		feed.setComments(comments);
		return feed;
	}

	public ArrayList<Feed> getAllFeed(Integer userCode, int pageNumber) {
		ArrayList<Feed> list = new ArrayList<Feed>();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT DISTINCT f.feed_index, f.user_code, title, content, f.create_date, f.mod_date, users.id, users.name, " +
					"(SELECT COUNT(*) FROM favorites WHERE f.feed_index = favorites.feed_index) AS favorite_count " +
					"FROM feeds AS f JOIN users ON users.code = f.user_code " +
					"ORDER BY create_date DESC " +
					"LIMIT ?," +
					PagingManager.LIMIT;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageNumber * PagingManager.LIMIT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Feed feed = new Feed();
				feed.setFeedIndex(rs.getInt(1));
				feed.setUserCode(rs.getInt(2));
				feed.setTitle(rs.getString(3));
				feed.setContent(rs.getString(4));
				feed.setCreateDate(rs.getTimestamp(5));
				feed.setModDate(rs.getTimestamp(6));
				feed.setUserId(rs.getString(7));
				feed.setUserName(rs.getString(8));

				list.add(feed);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		System.out.println(list.size());

		addCommentToFeed(list);
		addImageToFeed(list);
		readFeedFavoriteInfoAll(list);
		if (userCode != null) {
			checkFeedFavoriteAll(list, userCode);
		}

		return list;
	}

	public ArrayList<Feed> getCurrentFeed(Integer userCode, int pageNumber) {
		ArrayList<Feed> list = new ArrayList<Feed>();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT DISTINCT f.feed_index, f.user_code, title, content, f.create_date, f.mod_date, users.id, users.name, " +
					"(SELECT COUNT(*) FROM favorites WHERE f.feed_index = favorites.feed_index) AS favorite_count " +
					"FROM feeds AS f JOIN users ON users.code = f.user_code " +
					"ORDER BY create_date DESC " +
					"LIMIT 0," +
					PagingManager.LIMIT * (pageNumber + 1);

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
				feed.setUserId(rs.getString(7));
				feed.setUserName(rs.getString(8));

				list.add(feed);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		System.out.println(list.size());
		addCommentToFeed(list);
		addImageToFeed(list);
		readFeedFavoriteInfoAll(list);
		if (userCode != null) {
			checkFeedFavoriteAll(list, userCode);
		}

		return list;
	}

	public ArrayList<Feed> getAllFeedByUserCode(int userCode, Integer userCodeViewer) {
		ArrayList<Feed> list = new ArrayList<Feed>();
		String sql = "SELECT DISTINCT f.feed_index, f.user_code, title, content, f.create_date, f.mod_date, users.id, users.name, " +
				"(SELECT COUNT(*) FROM favorites WHERE f.feed_index = favorites.feed_index) AS favorite_count " +
				"FROM feeds AS f JOIN users ON users.code = f.user_code " +
				"WHERE f.user_code = ? " +
				"ORDER BY create_date DESC";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Feed feed = new Feed();
				feed.setFeedIndex(rs.getInt(1));
				feed.setUserCode(rs.getInt(2));
				feed.setTitle(rs.getString(3));
				feed.setContent(rs.getString(4));
				feed.setCreateDate(rs.getTimestamp(5));
				feed.setModDate(rs.getTimestamp(6));
				feed.setUserId(rs.getString(7));
				feed.setUserName(rs.getString(8));

				list.add(feed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		addCommentToFeed(list);
		readFeedFavoriteInfoAll(list);

		if (userCodeViewer != null) {
			checkFeedFavoriteAll(list, userCodeViewer);
		}

		return list;
	}

	public ArrayList<Feed> getAllFeedWithLimit(Integer userCode, int limit) {
		ArrayList<Feed> list = new ArrayList<Feed>();
		String sql = "SELECT DISTINCT f.feed_index, f.user_code, title, content, f.create_date, f.mod_date, users.id, users.name, " +
				"(SELECT COUNT(*) FROM favorites WHERE f.feed_index = favorites.feed_index) AS favorite_count " +
				"FROM feeds AS f JOIN users ON users.code = f.user_code " +
				"ORDER BY create_date DESC " +
				"LIMIT ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, limit);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Feed feed = new Feed();
				feed.setFeedIndex(rs.getInt(1));
				feed.setUserCode(rs.getInt(2));
				feed.setTitle(rs.getString(3));
				feed.setContent(rs.getString(4));
				feed.setCreateDate(rs.getTimestamp(5));
				feed.setModDate(rs.getTimestamp(6));
				feed.setUserId(rs.getString(7));
				feed.setUserName(rs.getString(8));

				list.add(feed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		addCommentToFeed(list);
		readFeedFavoriteInfoAll(list);

		if (userCode != null) {
			checkFeedFavoriteAll(list, userCode);
		}

		return list;
	}

	public ArrayList<Feed> getAllFeedByQuery(Integer userCode, String query) {
		ArrayList<Feed> list = new ArrayList<Feed>();
		String sql = "SELECT feed_index, user_code, title, content, create_date, feeds.mod_date, " +
				"(SELECT COUNT(*) FROM favorites WHERE feeds.feed_index = favorites.feed_index) AS favorite_count " +
				"FROM feeds " +
				"JOIN users ON users.code = feeds.user_code " +
				"WHERE title LIKE ? " +
				"OR content LIKE ? " +
				"ORDER BY create_date DESC";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "%" + query + "%");
			pstmt.setString(2, "%" + query + "%");

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
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		addCommentToFeed(list);
		readFeedFavoriteInfoAll(list);

		if (userCode != null) {
			checkFeedFavoriteAll(list, userCode);
		}

		return list;
	}

	public ArrayList<Feed> getAllFeedByQueryWithLimit(Integer userCode, String query, int limit) {
		ArrayList<Feed> list = new ArrayList<Feed>();
		String sql = "SELECT feed_index, user_code, title, content, create_date, feeds.mod_date, " +
				"(SELECT COUNT(*) FROM favorites WHERE feeds.feed_index = favorites.feed_index) AS favorite_count " +
				"FROM feeds " +
				"JOIN users ON users.code = feeds.user_code " +
				"WHERE title LIKE ? " +
				"OR content LIKE ? " +
				"ORDER BY create_date DESC " +
				"LIMIT ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "%" + query + "%");
			pstmt.setString(2, "%" + query + "%");
			pstmt.setInt(3, limit);

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
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

		addCommentToFeed(list);
		readFeedFavoriteInfoAll(list);

		if (userCode != null) {
			checkFeedFavoriteAll(list, userCode);
		}

		return list;
	}

	public Feed getFeedByFeedIndex(int feedIndex, String userCodeViewer) {
		Feed feed = new Feed();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT DISTINCT f.feed_index, f.user_code, title, content, f.create_date, f.mod_date, users.id, users.name, " +
					"(SELECT COUNT(*) FROM favorites WHERE f.feed_index = favorites.feed_index) AS favorite_count " +
					"FROM feeds AS f JOIN users ON users.code = f.user_code WHERE feed_index=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedIndex);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				feed.setFeedIndex(rs.getInt(1));
				feed.setUserCode(rs.getInt(2));
				feed.setTitle(rs.getString(3));
				feed.setContent(rs.getString(4));
				feed.setCreateDate(rs.getTimestamp(5));
				feed.setModDate(rs.getTimestamp(6));
				feed.setUserId(rs.getString(7));
				feed.setUserName(rs.getString(8));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		addCommentToFeedDetail(feed);
		readFeedFavoriteInfo(feed);
		if(userCodeViewer != null) {
			FeedRequestDTO feedDto = new FeedRequestDTO();
			feedDto.setFeedIndex(feedIndex);
			feedDto.setUserCode(Integer.parseInt(userCodeViewer));
			if(checkFeedFavorite(feedDto).getFeedIndex() == feedIndex && checkFeedFavorite(feedDto).getUserCode() == Integer.parseInt(userCodeViewer)) {
				feed.setFavorite(true);
			}
		}
		return feed;
	}

	public Feed getFeedByUserCodeAndTitleAndContent(FeedRequestDTO feedDto) {
		Feed feed = new Feed();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM feeds WHERE user_code=? AND title=? AND content=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getUserCode());
			pstmt.setString(2, feedDto.getTitle());
			pstmt.setString(3, feedDto.getContent());
			pstmt.execute();
			rs = pstmt.getResultSet();
			if(rs.next()) {
				feed.setFeedIndex(rs.getInt(1));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

		return feed;
	}

	public void addImage(String imageURL, int feedIndex) {
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO feed_images(feed_index, image_url) VALUES(?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedIndex);
			pstmt.setString(2, imageURL);
			pstmt.execute();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}

	public List<Feed> addImageToFeed(List<Feed> feeds) {
		String sql = "SELECT feed_image_index, feed_index, image_url "
				+ "FROM feed_images " +
				"WHERE feed_index = ?";
		try {
			conn = DBManager.getConnection();

			for (int i = 0; i < feeds.size(); i++) {
				Feed feed = feeds.get(i);

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, feed.getFeedIndex());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					feed.setImageURL(rs.getString(3));
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

	public boolean createFeed(FeedRequestDTO feedDto) {
		boolean isCreate = true;
		Feed feed = new Feed();

		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO feeds(user_code, title, content) VALUES(?,?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getUserCode());
			pstmt.setString(2, feedDto.getTitle());
			pstmt.setString(3, feedDto.getContent());
			pstmt.execute();
			feed = getFeedByUserCodeAndTitleAndContent(feedDto);
			addImage(feedDto.getImageURL(), feed.getFeedIndex());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			isCreate = false;
		}finally {
			DBManager.close(conn, pstmt);
		}


		return isCreate;
	}

	public boolean updateFeed(FeedRequestDTO feedDto) {
		boolean isUpdated = true;

		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE feeds SET title=?, content=? WHERE feed_index = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, feedDto.getTitle());
			pstmt.setString(2, feedDto.getContent());
			pstmt.setInt(3, feedDto.getFeedIndex());
			pstmt.execute();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			isUpdated = false;
		}finally {
			DBManager.close(conn, pstmt);
		}

		return isUpdated;
	}

	public boolean deleteFeed(FeedRequestDTO feedDto) {
		boolean isDeleted = true;
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
			isDeleted = false;
		}finally {
			DBManager.close(conn, pstmt);
		}
		return isDeleted;
	}

	public ArrayList<Feed> readFeedFavoriteInfoAll(ArrayList<Feed> list) {
		String sql = "SELECT COUNT(*) FROM favorites WHERE feed_index = ?;";
		try {
			conn = DBManager.getConnection();
			for(int i = 0; i < list.size(); i++) {
				Feed feed = list.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, feed.getFeedIndex());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					feed.setFavoriteCount(rs.getInt(1));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return list;
	}

	public Feed readFeedFavoriteInfo(Feed feed) {
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT COUNT(*) FROM favorites WHERE feed_index = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feed.getFeedIndex());
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

	public List<Feed> checkFeedFavoriteAll(List<Feed> feeds, int userCode) {
		String sql = "SELECT feed_index, user_code FROM favorites WHERE feed_index = ? AND user_code = ?;";
		try {
			conn = DBManager.getConnection();
			for(int i =0; i < feeds.size(); i++) {
				Feed feed = feeds.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, feed.getFeedIndex());
				pstmt.setInt(2, userCode);
				pstmt.execute();
				rs = pstmt.executeQuery();

				if (rs.next()) {
					feed.setIsFavorite(true);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return feeds;
	}

	public FeedResponseDTO checkFeedFavorite(FeedRequestDTO feedDto) {
		FeedResponseDTO feed = new FeedResponseDTO();

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
			System.out.println(feed.getFeedIndex());
			System.out.println(feed.getUserCode());

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
			String sql = "SELECT feed_index, user_code, comment, mod_date FROM feed_comments WHERE feed_index = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getFeedIndex());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Feed feed = new Feed();
				feed.setFeedIndex(rs.getInt(1));
				feed.setUserCode(rs.getInt(2));
				feed.setComment(rs.getString(3));
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

	public boolean createComment(FeedRequestDTO feedDto) {
		boolean isCreate = true;
		FeedResponseDTO feed = new FeedResponseDTO();
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
			isCreate = false;
		}finally {
			DBManager.close(conn, pstmt);
		}

		return isCreate;
	}

	public FeedResponseDTO updateComment(FeedRequestDTO feedDto) {
		FeedResponseDTO feed = null;
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE feed_comments SET comment = ? WHERE feed_comment_index = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, feedDto.getComment());
			pstmt.setInt(2, feedDto.getCommentIndex());
			pstmt.execute();
			feed = searchCommentByCommentIndex(feedDto);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

		return feed;
	}

	public FeedResponseDTO deleteComment(FeedRequestDTO feedDto) {
		FeedResponseDTO feed = null;
		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM feed_comments WHERE feed_comment_index = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, feedDto.getCommentIndex());
			pstmt.execute();
			feed = searchCommentByCommentIndex(feedDto);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

		return feed;
	}

}
