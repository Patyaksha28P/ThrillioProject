package com.semanticsquare.thrillio.constants;

public enum BookGenre {
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
	 DRAMA("drama");
	 private BookGenre(String name) 
	{this.name=name;
		 }
	 private String name;
	 public String getName(){
	return name; }
}
