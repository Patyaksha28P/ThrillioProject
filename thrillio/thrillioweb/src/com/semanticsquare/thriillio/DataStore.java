package com.semanticsquare.thriillio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.constants.Gender;
import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import com.semanticsquare.thrillio.managers.UserManager;

public class DataStore {
	private static List<User> users = new ArrayList<>();
	// all our possible bookmarks
	private static  List<List<Bookmark>> bookmarks = new ArrayList<>();
	private static List<UserBookmark> userBookmarks = new ArrayList<>();
	// bookmarkCount keeps track of the number of bookmarks a user has chosen

	// getters for users and bookmarks to be called on by Data Access Objects since
	// we have no database
	public static List<User> getUsers() {
		return users;
	}

	public static List< List<Bookmark>> getBookmarks() {
		return bookmarks;
	}

	public static void loadData() {
		// Load JDBC Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

		// Now that we have loaded our driver, let's connect to Database
		// try-with-resources ==> conn & stmt will be closed
		// Connection string: <protocol>:<sub-protocol>:<data-source details>
		// Statement is used for executing the actual queries
		// Connection and Statement are from java.sql package
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false","root","casper04");
				Statement stmt = conn.createStatement();) {
			loadUsers(stmt);
			loadWebLinks(stmt);
			loadBooks(stmt);
			loadMovies(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void loadUsers(Statement stmt) throws SQLException {
		String query = "select * from User";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			long id = rs.getLong("id");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			Gender gender = Gender.values()[rs.getInt("gender_id")];
			UserType userType = UserType.values()[rs.getInt("user_type_id")];
			User user = UserManager.getInstance().createUser(id, email, password, firstName, lastName, gender,
					userType);
			users.add(user);
		}
	}

	private static void loadWebLinks(Statement stmt) throws SQLException {
		String query = "select * from Weblink";
		ResultSet rs = stmt.executeQuery(query);
		List<Bookmark> data = new ArrayList<>();
		while(rs.next()) {
			long id = rs.getLong("id");
			String title = rs.getString("title");
			String url = rs.getString("url");
			String host = rs.getString("host");
			Bookmark bookmark = BookmarkManager.getInstance().createWeblink(id, title, url, host);
			data.add(bookmark);
		}
	

	}

	private static void loadMovies(Statement stmt) throws SQLException {
		String query = "select m.id,title, release_year, movie_genre_id, imdb_rating,"
				+ "GROUP_CONCAT(a.name separator ',') as cast, "
				+ "GROUP_CONCAT(d.name separator ',') as directors "
				+ "from Movie m, Actor a, Movie_Actor ma, Movie_Director md, "
				+ "Director d  where m.id = ma.movie_id and a.id = ma.actor_id and m.id = md.movie_id"
				+ " and d.id = md.director_id group by m.id";
		ResultSet rs =  stmt.executeQuery(query);

		List<Bookmark> data = new ArrayList<>();
		while (rs.next()) {
			long id = rs.getLong("id");
			String title = rs.getString("title");
			int releaseYear = rs.getInt("release_year");
			String[] cast = rs.getString("cast").split(",");
			String[] directors = rs.getString("directors").split(",");
			int genre_id = rs.getInt("movie_genre_id");
			MovieGenre genre = MovieGenre.values()[genre_id];
			double imdbRating = rs.getDouble("imdb_rating");
			Bookmark bookmark = BookmarkManager.getInstance().createMovie(id, title, "-",releaseYear, cast, directors,genre,imdbRating);
			data.add(bookmark);
		}
		 
	}

	private static void loadBooks(Statement stmt) throws SQLException {
		String query = "select b.id,title, publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors,"
				+ " book_genre_id, amazon_rating, created_date from Book b, Publisher p, Author a, Book_Author ba"
				+ " where b.publisher_id = p.id and b.id = ba.book_id and ba.author_id = a.id group by b.id";

		ResultSet rs = stmt.executeQuery(query);
		List<Bookmark> data = new ArrayList<>();
		try {
			while (rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String imageUrl=rs.getString("image_url");
				int publicationYear = rs.getInt("publication_year");
				String publisher = rs.getString("name");
				String[] authors = rs.getString("authors").split(",");
				int genre_id = rs.getInt("book_genre_id");
				BookGenre genre = BookGenre.values()[genre_id];
				double amazonRating = rs.getDouble("amazon_rating");
				Bookmark bookmark = BookmarkManager.getInstance().createBook(id, title,imageUrl, publicationYear, publisher,authors, genre, amazonRating);
				data.add(bookmark);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks.add(userBookmark);
	}

}
