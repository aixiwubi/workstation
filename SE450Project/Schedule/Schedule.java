package Schedule;

import exception.InvalidDataException;

public interface Schedule {
	 void showSchedule(int dayStart, int dayEnd) throws InvalidDataException;
	 int setSchedule(int startDay, int numberOfItems);
	 int getArrivalDate(int startDay, int numberOfitems);
	 double getProcessDay(int numberOfItems);
	
}
