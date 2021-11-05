package com.ipl.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Team {
	
	@Id
	@GeneratedValue
	private long id;
	private String teamName;
	private Long totalMatches;
	private Long totalWins;
	
	@Transient
	ArrayList<Match> latestMatches;
	
	public Team() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Long getTotalMatches() {
		return totalMatches;
	}
	public void setTotalMatches(Long totalMatches) {
		this.totalMatches = totalMatches;
	}
	public Long getTotalWins() {
		return totalWins;
	}
	public ArrayList<Match> getLatestMatches() {
		return latestMatches;
	}
	public void setLatestMatches(ArrayList<Match> latestMatches) {
		this.latestMatches = latestMatches;
	}
	public void setTotalWins(Long totalWins) {
		this.totalWins = totalWins;
	}
	public Team(String teamName, Long totalMatches) {
		super();
		this.teamName = teamName;
		this.totalMatches = totalMatches;
	}
	
}
