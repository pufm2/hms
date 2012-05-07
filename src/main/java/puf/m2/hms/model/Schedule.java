package puf.m2.hms.model;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import puf.m2.hms.db.DbException;
import puf.m2.hms.exception.ScheduleException;
import puf.m2.hms.utils.DateUtils;

public class Schedule extends HmsEntity {

	private static final Map<Integer, Schedule> SCHEDULE_MAP = new CacheAwareMap<Integer, Schedule>();

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

	public void save() throws ScheduleException {
		try {
			id = getNextFreeId();
		} catch (Exception e1) {

		}
		final String queryTemple = "insert into Schedule values({0}, {1}, ''{2}'', ''{3}'', {4})";
		try {
            int available = this.available ? 1 : 0;
            DB.executeUpdate(MessageFormat.format(queryTemple, id,
                        physician.getId(), DateUtils.dateToString(startDate),
                        DateUtils.dateToString(endDate), available));
        } catch (DbException e) {
            throw new ScheduleException(e);
        }

		

		SCHEDULE_MAP.put(id, this);
	}

	public void update(int scheduleID) throws Exception {
		final String queryTemple = "update Schedule set physicianId = {0}, startDate = ''{1}'', endDate = ''{2}'', available = {3} where id = {4}";
		
		int available = this.available ? 1 : 0;
		DB.executeUpdate(MessageFormat.format(queryTemple, physician.getId(),
				DateUtils.dateToString(startDate),
				DateUtils.dateToString(endDate), available, id));
	}

	public static List<Schedule> loadSchedule(Physician doctor)
			throws ScheduleException {

		List<Schedule> scheduleList = new ArrayList<Schedule>();
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
		return scheduleList;
	}

}
