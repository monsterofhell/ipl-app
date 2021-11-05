package com.ipl.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ipl.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long>{

	
	ArrayList<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String team2
			,Pageable pageable);
}
