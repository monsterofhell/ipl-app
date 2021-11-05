package com.ipl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ipl.model.Team;
import com.ipl.service.TeamService;

@RestController
public class TeamController {

	@Autowired
	TeamService teamService;
	
	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team fetchedTeam;
		if(this.teamService.getTeam(teamName).isPresent()) {
			fetchedTeam = this.teamService.getTeam(teamName).get();
			fetchedTeam.setLatestMatches(teamService.getLatest4Matches(teamName,teamName));
			return fetchedTeam;
			}
		else {
			return new Team();
		}
	}
}
