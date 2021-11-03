package com.ipl.batch;

import javax.activation.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.ipl.model.Match;

@Configuration
@EnableBatchProcessing
public class MatchBatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFacotry;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	private final String[] HEADER_ROW = new String[] {
			"id","city","date","player_of_match","venue","neutral_venue",
			"team1","team2","toss_winner","toss_decision","winner","result",
			"result_margin","eliminator","method","umpire1","umpire2"

	}; 
	@Bean
	public FlatFileItemReader<MatchInput> reder(){
		return new FlatFileItemReaderBuilder<MatchInput>()
				.name("matchItemReader")
				.resource(new ClassPathResource("match-data.csv"))
				.delimited()
				.names(HEADER_ROW)
				.fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {{
					setTargetType(MatchInput.class);
				}})
				.build();
	}
	@Bean
	public MatchProcessor processor() {
		return new MatchProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Match> writer(javax.sql.DataSource dataSource )
	{
		return new JdbcBatchItemWriterBuilder<Match>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("insert into match "
						+ "id,city,date,player_of_match,venue,team1,team2,toss_winner,"
						+ "toss_wecision,match_winner,result,result_margin,umpire1,umpire2 "
						+ "values "
						+ "(id,:city,:date,:playerOfMatch,:venue,:team1,:team2,:tossWinner,:tossDecision,:matchWinner,"
						+ ":result,:resultMargin,:umpire1,:umpire2) ")
				.dataSource(dataSource)
				.build();
	}
	
	
}
