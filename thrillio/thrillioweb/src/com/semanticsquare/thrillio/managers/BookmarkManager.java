package com.semanticsquare.thrillio.managers;

import java.util.Collection;
import java.util.List;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.dao.BookmarkDao;
import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.Movie;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.entities.WebLink;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao= new BookmarkDao();

	private BookmarkManager( ) { }
	public static BookmarkManager getInstance() {
		return instance ;
		}

	public Movie createMovie( long id,String title, String profileUrl,int releaseYear,String[] cast, String[] directors, MovieGenre genre,
		double imdbRatings) {
		Movie movie=new Movie();
		movie.setId(id); 
		movie.setTitle(title);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRatings(imdbRatings);
		return movie;
		}
		
	public WebLink createWeblink(long id,String title,String url, String host) {
	WebLink weblink=new WebLink();
	weblink.setId(id);
	weblink.setTitle(title);
	weblink.setUrl(url);
	weblink.setHost(host);
	 return weblink;
	 }



	public Book  createBook(long id,String title,String imageUrl,int publicationYear, String publisher, String[] authors,BookGenre genre,double amazonRatings) {
	Book book	=new Book();
    book.setId(id);
    book.setTitle(title);
    book.setImageUrl(imageUrl);
	book.setPublicationYear(publicationYear);
	book.setPublisher(publisher);
	book.setAuthors(authors);
	book.setGenre(genre);
	book.setAmazonRatings(amazonRatings);
	return book;
	}
	public List<List<Bookmark>> getBookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);
		
		
		dao.saveUserBookmark(userBookmark);
	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyChoice, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyChoice);
		bookmark.setKidFriendlyMarkedBy(user);
		
		dao.updateKidFriendlyStatus(bookmark);
		System.out.println("Kid friendly status: "+ kidFriendlyChoice + ", Marked by: "+ user.getEmail() + ", "+ bookmark);
	}

	public static void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);
		System.out.println("Data to be shared: ");
//		we need to do downcasting so that we can use the proper getItemData() function
//		If we do not downcast Bookmark, at compile time, the compiler will look for an implementation of the getItemData() method
//		but since it does not exist in the Bookmark class, we will get an error
		if(bookmark instanceof Book) {
			System.out.println(((Book)bookmark).getItemData());	
		}
		else if(bookmark instanceof WebLink) {
			System.out.println(((WebLink)bookmark).getItemData());	
		}
		
	dao.updateSharedBy(bookmark);}

	


         public Collection<Bookmark> getBooks(boolean isBookmarked, long id){
           return dao.getBooks(isBookmarked, id);
}
        public Bookmark getBook(long bid){
        return dao.getBook(bid);
}   

}
