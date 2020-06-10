package com.semanticsquare.thrillio.entities;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.partner.Shareable;

public class Book extends Bookmark implements Shareable{
	private int publicationYear;
	private String publisher;
	private String[] authors;
	private BookGenre genre;
	private double amazonRatings;
	private String imageUrl;
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String[] getAuthors() {
		return authors;
	}
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	public BookGenre getGenre() {
		return genre;
	}
	public void setGenre(BookGenre genre) {
		this.genre = genre;
	}
	public double getAmazonRatings() {
		return amazonRatings;
	}
	public void setAmazonRatings(double amazonRatings) {
		this.amazonRatings = amazonRatings;
	}
	@Override
	public String toString() {
		final int maxLen = 10;
		return "Book [publicationYear=" + publicationYear + ", publisher=" + publisher + ", authors="
				+ (authors != null ? Arrays.asList(authors).subList(0, Math.min(authors.length, maxLen)) : null)
				+ ", genre=" + genre + ", amazonRatings=" + amazonRatings + "]";
	}

	@Override
	public boolean isKidFriendlyEligible() {
		if(genre.equals(BookGenre.PHILOSOPHY)||(genre.equals(BookGenre.SELF_HELP))) {
		return false;}
	return true;
	}
	public String getItemData() {
	StringBuilder builder=new StringBuilder();
	builder.append("<item>");
	builder.append("<type>Book<\type>");
	builder.append("<title>").append(getTitle()).append("</title>");
	builder.append("<authors>").append(StringUtils.join(authors,",")).append("</authors>");
	builder.append("<pubhliser>").append(publisher).append("</publisher>");
	builder.append("<pubhlicationYear>").append(publicationYear).append("</publicationYear>");
	builder.append("<genre>").append(genre).append("</genre>");
	builder.append("<amazonRatings>").append(amazonRatings).append("</amazonRatings>");
	builder.append("</item>");
	return builder.toString();	
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
