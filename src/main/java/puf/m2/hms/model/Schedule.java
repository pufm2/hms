package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseFactory;
import puf.m2.hms.utils.DateUtils;

public class Schedule {
	private static final Database DB = DatabaseFactory.DEFAULT_DB;
	private static final Map<Integer, Schedule> SCHEDULE_MAP = new HashMap<Integer, Schedule>();
	
	private int id;
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
		id = getNextFreeId();
		final String queryTemple = "insert into Schedule values({0}, {1}, ''{2}'', ''{3}'', {4})";
		try {
			DB.createConnection();

			int available = this.available ? 1 : 0;
			DB.executeUpdate(MessageFormat.format(queryTemple, id, physician.getId(),
					DateUtils.dateToString(startDate), DateUtils.dateToString(endDate),
					available));
			DB.closeConnection();

			SCHEDULE_MAP.put(id, this);
		} catch (SQLException e) {
			throw new HmsException(e);
		}

	}

	public void update(int scheduleID) throws HmsException {
		final String queryTemple = "update Schedule set physicianId = {0}, startDate = ''{1}'', endDate = ''{2}'', available = {3} where id = {4}";
		try {
			DB.createConnection();

			int available = this.available ? 1 : 0;
			DB.executeUpdate(MessageFormat.format(queryTemple, physician.getId(),
					DateUtils.dateToString(startDate), DateUtils.dateToString(endDate),
					available, id));
			DB.closeConnection();
		} catch (SQLException e) {
			throw new HmsException(e);
		}

	}
	

	public static List<Schedule> loadSchedule(Physician doctor) throws HmsException {
		
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		final String queryTemplate = "SELECT * FROM Schedule WHERE physicianId = {0}";
		
		try {
			DB.createConnection();

			ResultSet rs = DB.executeQuery(MessageFormat.format(queryTemplate, doctor.getId()));

			while (rs.next()) {
				int id = rs.getInt("ScheduleId");
				Schedule schedule = SCHEDULE_MAP.get(id);

				if (schedule == null) {
					boolean available = true;
					if (rs.getInt("available") == 0) {
						available = false;
					}
					Date startDate = DateUtils.parseDate(rs.getString("startDate"));
            		Date endDate = DateUtils.parseDate(rs.getString("endDate"));
            		
					schedule = new Schedule(doctor, startDate, endDate, available);
					schedule.id = id;

					SCHEDULE_MAP.put(id, schedule);
				}

				scheduleList.add(schedule);
			}

			DB.closeConnection();
		} catch (Exception e) {
			throw new HmsException(e);
		}
		return scheduleList;
	}
	
	private int getNextFreeId() throws HmsException {
    	int freeId = 1;
		try {
			DB.createConnection();
		
		String query = "select max(id) as maxId from Schedule";

		ResultSet rs = DB.executeQuery(query);

		if (rs.next()) {
			freeId = rs.getInt("maxId") + 1;
		}

		DB.closeConnection();
		} catch (SQLException e) {
			throw new HmsException(e);
		}

		return freeId;
    }

}
