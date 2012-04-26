package puf.m2.hms.exception;

import java.util.Date;

import puf.m2.hms.model.Physician;
import puf.m2.hms.utils.DateUtils;

public class ScheduleException extends Exception {

	private Physician physician;
	private Date startDate;
	private Date endDate;
	private boolean available;

	private static final long serialVersionUID = 1L;

	public ScheduleException(Physician physician, Date startDate, Date endDate,
			boolean available) {
		this.physician = physician;
		this.startDate = startDate;
		this.endDate = endDate;
		this.available = available;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Physician getPhysician() {
		return physician;
	}

	public Date getStartDate() {
		return startDate;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String getMessage() {

		String result = "\n Error at physicianID" + physician.getId()
				+ "\n Start date:" + DateUtils.dateToString(startDate)
				+ "\n End date:" + DateUtils.dateToString(endDate)
				+ "\n Available: " + available;
		return result;
	}

}
