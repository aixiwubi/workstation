package Schedule;

import exception.InvalidDataException;

public class ScheduleFactory {
	public Schedule createSchedule(int inProcessRate) throws InvalidDataException{
		return new ScheduleImpl(inProcessRate);
		
	}
}
