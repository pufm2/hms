package puf.m2.hms.model;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseFactory;
import puf.m2.hms.db.DbException;
import puf.m2.hms.exception.HmsException;
import puf.m2.hms.model.support.Condition;
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
    
    /**
     * This method returns the next free id of a table
     * @return
     * @throws HmsException
     */
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

    /**
     * this method inserts an instance of an HmsEntity-subclass to db
     * @throws HmsException
     */
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

    /**
     * this method update an instance of an HmsEntity-subclass to db
     * @throws HmsException
     */
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
    
    /**
     * this method query an instance of an HmsEntity-subclass by id from db
     * @param id
     * @param clazz HmsEntity-subclass
     * @throws HmsException
     */
    public static <T extends HmsEntity> T getById(int id, Class<T> clazz) throws HmsException {
    	try {
			Map<Integer, T> map = (Map<Integer, T>) clazz.getDeclaredField("MAP").get(null);
			T entity = map.get(id);
			if (entity == null) {
				List<T> entityList = getByCondition(new Condition("id", Integer.toString(id)), clazz);
				if (entityList.size() == 1) {
					entity = entityList.get(0);
				}
			}
			
			return entity;
		} catch (Exception e) {
			throw new HmsException(e);
		}
    }
    
    /**
     * this method query an instance of an HmsEntity-subclass by a condition from db
     * @param c Condition to search
     * @param clazz HmsEntity-subclass
     * @throws HmsException
     */
    public static <T extends HmsEntity> List<T> getByCondition(Condition c, Class<T> clazz) throws HmsException {
    	
    	String where = "";
    	if (c != null) {
    		where = " where " + c;
    	}
    	final String query ="select * from " + clazz.getSimpleName() + where;
    	
    	try {
    		List<T> entityList = new ArrayList<T>();
    		List<Field> fields = new ArrayList<Field>();
    		for (Field field : clazz.getDeclaredFields()) {
    			if (field.getAnnotation(DbProp.class) != null) {
    				fields.add(field);
    			}
    		}
    		
    		Map<Integer, T> map = (Map<Integer, T>) clazz.getDeclaredField("MAP").get(null);
    		ResultSet rs = DB.executeQuery(query);
    		while (rs.next()) {
    			int id = rs.getInt("id");
    			T entity = map.get(id);
    			if (entity == null) {
    				entity = clazz.newInstance();
        			for (Field field : fields) {
	                    String name = field.getName();
	                    String type = field.getType().getSimpleName();
	                    Object val = null;
	                    if ("String".equals(type)) {
	                    	val = rs.getString(name);
	                    } else if ("int".equals(type)) {
	                    	val = rs.getInt(name);
	                    } else if ("boolean".equals(type)) {
	                    	val = rs.getInt(name) == 1 ? true : false;
	                    } else if ("Date".equals(type)) {
	                    	val = DateUtils.parseDate(rs.getString(name));
	                    } else if (HmsEntity.class.isAssignableFrom(field.getType())) {
	                    	int fId = Integer.parseInt(rs.getString(name + "Id"));
	                    	val = getById(fId, (Class<T>) field.getType());
	                    }
	                	field.setAccessible(true);
	                	field.set(entity, val);
	                    
        	        }
        			entity.id = id;
        			map.put(id, entity);
    			}
    			entityList.add(entity);
    		}
    		return entityList;
    	} catch (Exception e) {
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
