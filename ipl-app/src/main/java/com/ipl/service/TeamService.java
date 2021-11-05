package com.ipl.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipl.model.Match;
import com.ipl.model.Team;
import com.ipl.repository.MatchRepository;
import com.ipl.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	TeamRepository teamRepository;
	@Autowired
	MatchRepository matchRepository;
	
	public Optional<Team> getTeam(String teamName) {
		return this.teamRepository.findByTeamName(teamName);
	}

	public ArrayList<Match> getLatest4Matches(String teamName1, String teamName2) {
		Pageable pageable = PageRequest.of(0, 4);
		return this.matchRepository.getByTeam1OrTeam2OrderByDateDesc(teamName1,teamName2,pageable);
	}
}
