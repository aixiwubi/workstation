package Schedule;

import java.util.HashMap;

import exception.InvalidDataException;

public class ScheduleImpl implements Schedule{
	private HashMap<Integer, Integer> schedule = new HashMap();
	private int processRate;
	public ScheduleImpl(int inProcessRate) throws InvalidDataException{
		if(inProcessRate<0){
			throw new InvalidDataException("Cannot have a negative process rate");
		}
		this.processRate = inProcessRate;
	}
	public double getProcessDay(int numberofItems){
		return (double)numberofItems/processRate;
	}
	public void showSchedule(int dayStart, int dayEnd) throws InvalidDataException{
		if(dayStart>dayEnd){
			throw new InvalidDataException("Start Day should be smaller than End day");
		}
		for(int i = dayStart; i <= dayEnd;i++){
			if(!schedule.containsKey(i)){
				System.out.print("Day " + i+ ": " + processRate + ". ");
				
			}
			else{
			System.out.print("Day " + i + ": " + schedule.get(i)+ ". ");
			}
		}

	}
	public int getArrivalDate(int startDay,int numberOfItems){
		while(numberOfItems!=0){
			if(!schedule.containsKey(startDay)){
				if(numberOfItems >= processRate){
					numberOfItems = numberOfItems - processRate;
					
					startDay++;
				}
				else{
					
					numberOfItems = 0;
				}
				
			}
			else if(schedule.containsKey(startDay)){
				if(schedule.get(startDay).equals(0)){
					startDay++;
				}
				else{
					if(numberOfItems >= (schedule.get(startDay))){
						numberOfItems = numberOfItems -schedule.get(startDay);
						
						startDay++;
					}
					else{
						
						numberOfItems = 0;
					}
					
					
				}
				
			}
			
			
		}
		return startDay;
			
		
			
	}
	public int setSchedule(int startDay, int numberOfItems){
		while(numberOfItems!=0){
			if(!schedule.containsKey(startDay)){
				if(numberOfItems >= processRate){
					numberOfItems = numberOfItems - processRate;
					schedule.put(startDay, 0);
					startDay++;
				}
				else{
					schedule.put(startDay, processRate-numberOfItems);
					numberOfItems = 0;
				}
				
			}
			else if(schedule.containsKey(startDay)){
				if(schedule.get(startDay).equals(0)){
					startDay++;
				}
				else{
					if(numberOfItems >= (schedule.get(startDay))){
						numberOfItems = numberOfItems -schedule.get(startDay);
						schedule.put(startDay, 0);
						startDay++;
					}
					else{
						schedule.put(startDay, schedule.get(startDay)-numberOfItems);
						numberOfItems = 0;
					}
					
					
				}
				
			}
			
			
		}
		return startDay+1;
			
		
	}
}
