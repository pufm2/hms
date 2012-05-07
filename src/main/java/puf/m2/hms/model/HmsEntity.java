package puf.m2.hms.model;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseFactory;
import puf.m2.hms.db.DbException;
import puf.m2.hms.exception.HmsException;
import puf.m2.hms.utils.DateUtils;

public abstract class HmsEntity {
    public static Database DB = DatabaseFactory.DEFAULT_DB;
    
    private static boolean cached = true;

    @DbProp
    protected int id;

    public HmsEntity() {

    }

    public HmsEntity(int id) {
        this.id = id;
    }

    protected int getNextFreeId() throws HmsException {
        int freeId = 1;
        String query = "select max(id) as maxId from " + getClass().getSimpleName();
        try {
            ResultSet rs = DB.executeQuery(query);
            if (rs.next()) {
                freeId = rs.getInt("maxId") + 1;
            }
        } catch (Exception e) {
            throw new HmsException(e);
        }

        return freeId;
    }

    public void save() throws HmsException {
        Map<String, Object> dbPropFields = getDbPropFields();
        
        id = getNextFreeId();
        dbPropFields.put("id", id);
        
        String fields ="";
        String values = "";

        for (String name : dbPropFields.keySet()) {
            if (!"".equals(fields)) {
                fields += ", ";
                values += ", ";
            }
            
            Object val = dbPropFields.get(name);
            if (val instanceof Integer) {
                values += Integer.toString((Integer) val);
            } else if (val instanceof String) {
                values = values + "'" + (String) val + "'";
            } else if (val instanceof Date) {
                values = values + "'" + DateUtils.dateToString((Date) val) + "'";
            } else if (val instanceof Boolean) {
                values += (Boolean) val ? 1 : 0;
            } else if (val instanceof HmsEntity) {
                values += ((HmsEntity) val).id;
                name += "id";
            }
            fields += name; 

        }

        final String queryTemplate = "insert into {0}({1}) values({2})";

        try {
            DB.executeUpdate(MessageFormat.format(queryTemplate, getClass().getSimpleName(),
                    fields, values));
        } catch (DbException e) {
            throw new HmsException(e);
        }

    }

    public void update() throws HmsException {
        Map<String, Object> dbPropFields = getDbPropFields();
        String props = "";

        for (String name : dbPropFields.keySet()) {
            if (!"".equals(props)) {
                props += ", ";

            }
            String prop = "";
            Object val = dbPropFields.get(name);
            if (val instanceof Integer) {
                prop = name + " = " + Integer.toString((Integer) val);
            } else if (val instanceof String) {
                prop = name + " = " + "'" + (String) val + "'";
            } else if (val instanceof Date) {
                prop = name + " = " + "'" + DateUtils.dateToString((Date) val) + "'";
            } else if (val instanceof Boolean) {
                prop = name + " = " + ((Boolean) val ? 1 : 0);
            } else if (val instanceof HmsEntity) {
                prop = name + "id = " + ((HmsEntity) val).id;
            }
            props += prop;
        }

        final String queryTemplate = "update {0} set {1} where id = {2}";

        try {
            DB.executeUpdate(MessageFormat.format(queryTemplate, getClass().getSimpleName(),
                    props, id));
        } catch (DbException e) {
            throw new HmsException(e);
        }

        
    }

    public static boolean isCached() {
        return cached;
    }

    public static void setCached(boolean cached) {
        HmsEntity.cached = cached;
    }

    public int getId() {
        return id;
    }

    private Map<String, Object> getDbPropFields() throws HmsException {
        Map<String, Object> dbPropFields = new HashMap<String, Object>();

        for (Field f : getClass().getDeclaredFields()) {
            if (f.getAnnotation(DbProp.class) != null) {
                try {
                    f.setAccessible(true);
                    dbPropFields.put(f.getName(), f.get(this));
                } catch (Exception e) {
                    throw new HmsException(e);
                }
            }
        }
        return dbPropFields;
    }

}
