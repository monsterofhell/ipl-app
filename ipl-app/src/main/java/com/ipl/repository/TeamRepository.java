package com.ipl.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ipl.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long>{

	public Optional<Team> findByTeamName(String teamName);
	
}
