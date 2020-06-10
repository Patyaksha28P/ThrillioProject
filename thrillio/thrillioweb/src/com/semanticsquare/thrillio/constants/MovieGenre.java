package com.semanticsquare.thrillio.constants;

public enum MovieGenre {
	 ART("art"),
	 BIOGRAPHY("biography"),
	 CHILDREN("children"),
	 FICTION("fiction"),
	 HISTORY("history"),
	 MYSTERY("mystery"),
	 PHILOSOPHY("philosophy"),
	 RELIGION("religion"),
	 SELF_HELP("Self help"),
	 TECHNICAL("technical"),
	 SPORTS("SPORTS"),
	 ADVENTURE("adventure"),
	 FAMILY("family"),
	 HORROR("horror"),
	 SCI_FI("sci-fi"),
	 COMEDY("comedy"),
	 DRAMA("drama"),
	 THRILLER("thriller"),
	 ACTION("action"),
	 ROMANCE("romance"),
	 CRIME("crime"),
	 CLASSICS("classics"),
	 TV_SHOWS("tvshows");
	private MovieGenre(String name) {
	this.name= name;
		}
	public String name;
	public String getName(){
		return name;
	}
}
