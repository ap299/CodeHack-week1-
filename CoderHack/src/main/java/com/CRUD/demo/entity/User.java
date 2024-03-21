package com.CRUD.demo.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	
	

	    @Id
	    private String userId;
	    private String username;
	    private int score;
	    private Set<String> badges;
	    
		public User(String userId, String username, int score, Set<String> badges) {
			super();
			this.userId = userId;
			this.username = username;
			this.score = score;
			this.badges = badges;
		}
		
		
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		public Set<String> getBadges() {
			return badges;
		}
		public void setBadges(Set<String> badges) {
			this.badges = badges;
		}
	    
		
		public void setBadgesFromList(List<String> badgeList) {
	        Set<String> badgeSet = new HashSet<>(badgeList);
	        setBadges(badgeSet);
		}

}
