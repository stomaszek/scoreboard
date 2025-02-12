# Scoreboard

## Technical assumptions
- project defined and build managed using maven
- no javadoc comments added 
- additional libraries reduced to minimum - just java used

## Non-technical assumptions  
- teams identified by name - uniques of team name assumed
- domain terminology used in code whenever possible even if confusing (no home and away team on World Cup) 
- match result as sum of goals (no info about 1st/2nd half, additional time, penalty, etc.)
- just last reported result of match stored 
- each valid result update is possible (for example 3:1 -> 0:0)
