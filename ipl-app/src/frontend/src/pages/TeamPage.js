import { React } from 'react'
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallDetailCard } from '../components/MatchSmallDetailCard';

export const TeamPage = () => {
  return (
    <div className="TeamPage">
      <h1>Team Name</h1>
    <h2>Latest matches</h2>
    <MatchDetailCard></MatchDetailCard>
    <MatchSmallDetailCard/>
    <MatchSmallDetailCard/>
    <MatchSmallDetailCard/>
    
        </div>
  );
}

