package puf.m2.hms.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.ScheduleException;
import puf.m2.hms.model.support.Condition;

public class Schedule extends HmsEntity {

	protected static final Map<Integer, Schedule> MAP = new CacheAwareMap<Integer, Schedule>();

	@DbProp
	private Physician physician;
	@DbProp
	private Date startDate;
	@DbProp
	private Date endDate;
	@DbProp
	private boolean available;

	public Schedule() {
		
	}
	
	public Schedule(Physician physician, Date startDate, Date endDate,
			boolean available) {

		this.physician = physician;
		this.startDate = startDate;
		this.endDate = endDate;
		this.available = available;
	}

	public void save() throws ScheduleException {
		
		try {
			super.save();
		} catch (HmsException e) {
			throw new ScheduleException(e);
		}

		MAP.put(id, this);
	}

	public void update() throws ScheduleException {

		try {
			super.update();
		} catch (HmsException e) {
			throw new ScheduleException(e);
		}
	}

	public static List<Schedule> loadSchedule(Physician doctor)
			throws ScheduleException {

		Condition c = new Condition("physicianid", Integer.toString(doctor.id));
		try {
			return getByCondition(c, Schedule.class);
		} catch (HmsException e) {
			throw new ScheduleException(e);
		}
		
		/*List<Schedule> scheduleList = new ArrayList<Schedule>();
		final String queryTemplate = "SELECT * FROM Schedule WHERE physicianId = {0}";

		try {
			ResultSet rs = DB.executeQuery(MessageFormat.format(queryTemplate,
					doctor.getId()));

			while (rs.next()) {
				int id = rs.getInt("id");
				Schedule schedule = SCHEDULE_MAP.get(id);

				if (schedule == null) {
					boolean available = true;
					if (rs.getInt("available") == 0) {
						available = false;
					}
					Date startDate = DateUtils.parseDate(rs
							.getString("startDate"));
					Date endDate = DateUtils.parseDate(rs.getString("endDate"));

					schedule = new Schedule(doctor, startDate, endDate,
							available);
					schedule.id = id;

					SCHEDULE_MAP.put(id, schedule);
				}

				scheduleList.add(schedule);
			}
		} catch (Exception e) {
			throw new ScheduleException(e);
		}
		return scheduleList;*/
	}
	
	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}
