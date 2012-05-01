package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import puf.m2.hms.exception.HmsException;
import puf.m2.hms.utils.DateUtils;

public class Schedule extends HmsEntity {

	private static final Map<Integer, Schedule> SCHEDULE_MAP = new HashMap<Integer, Schedule>();

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

	public void save() throws HmsException {
		try {
			id = getNextFreeId();
		} catch (Exception e1) {

		}
		final String queryTemple = "insert into Schedule values({0}, {1}, ''{2}'', ''{3}'', {4})";
		DB.createConnection();

		int available = this.available ? 1 : 0;
		DB.executeUpdate(MessageFormat.format(queryTemple, id,
					physician.getId(), DateUtils.dateToString(startDate),
					DateUtils.dateToString(endDate), available));

		DB.closeConnection();

		SCHEDULE_MAP.put(id, this);
	}

	public void update(int scheduleID) throws Exception {
		final String queryTemple = "update Schedule set physicianId = {0}, startDate = ''{1}'', endDate = ''{2}'', available = {3} where id = {4}";
		DB.createConnection();

		int available = this.available ? 1 : 0;
		DB.executeUpdate(MessageFormat.format(queryTemple, physician.getId(),
				DateUtils.dateToString(startDate),
				DateUtils.dateToString(endDate), available, id));
		DB.closeConnection();
	}

	public static List<Schedule> loadSchedule(Physician doctor)
			throws HmsException {

		List<Schedule> scheduleList = new ArrayList<Schedule>();
		final String queryTemplate = "SELECT * FROM Schedule WHERE physicianId = {0}";

		try {
			DB.createConnection();

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

			DB.closeConnection();
		} catch (SQLException e) {
			throw new HmsException(e);
		}
		return scheduleList;
	}

}
