package com.ipl.batch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.format.datetime.DateFormatter;

import com.ipl.model.Match;

public class MatchProcessor implements ItemProcessor<MatchInput, Match>{

	
	private static final Logger log = LoggerFactory.getLogger(MatchProcessor.class);

	
	@Override
	public Match process(MatchInput item) throws Exception {
		Match match = new Match();
		
		match.setId(Long.parseLong(item.getId()));
		match.setCity(item.getCity());

		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		match.setDate(LocalDate.parse(item.getDate(),df));
		match.setPlayerOfMatch(item.getPlayer_of_match());
		match.setMatchWinner(item.getWinner());
		match.setResult(item.getResult());
		match.setResultMargin(item.getResult_margin());
		
		String firstInningTeam;
		String secondInningTeam;
		if(item.getToss_decision().equals("bat")) {
			firstInningTeam = item.getToss_winner();
			secondInningTeam = item.getToss_winner().equals(item.getTeam1()) ? 
					item.getTeam2() :  item.getTeam1();
		}
		else {
			secondInningTeam = item.getToss_winner();
			firstInningTeam = 	item.getToss_winner().equals(item.getTeam1()) ?
					item.getTeam2() : item.getTeam1();
			}
		match.setTeam1(firstInningTeam);
		match.setTeam2(secondInningTeam);
		
		match.setTossDecision(item.getToss_decision());
		match.setTossWinner(item.getToss_winner());
		match.setUmpire1(item.getUmpire1());
		match.setUmpire2(item.getUmpire2());
		match.setVenue(item.getVenue());
		
		log.info("Conversion of data from MatchInput to Match is completed");
		return match;
	}

}
