package puf.m2.hms.model;

import java.util.Date;

public class Schedule {
	private int ID;
	private Physician physician;
	private Date startDate;
	private Date endDate;
	private boolean available;

	public Schedule(Physician physician, Date startDate, Date endDate,
			boolean available) {

		this.physician = physician;
		this.startDate = startDate;
		this.endDate = endDate;
		this.available = available;
	}

	public void save() {
		// check if start date is in other schedule, if yes raise error

		// check if end date is in other schedule, if yes raise error

		// insert new schedule to database

	}

	public void update(int scheduleID) {
		// check if scheduleID is exists

		// update startDate, endDate and available

	}

}
