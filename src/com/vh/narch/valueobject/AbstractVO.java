/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.valueobject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

/**
 * Implementaçao basica de um ValueObject
 * @see com.vh.narch.valueobject.ValueObject
 */
public abstract class AbstractVO implements ValueObject {

    private Integer id;

    /**
     * @see com.vh.narch.valueobject.ValueObject#getId()
     */
    public Integer getId() {
        return id;
    }

    /**
     * @see com.vh.narch.valueobject.ValueObject#setId(java.lang.Integer)
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @see com.vh.narch.valueobject.ValueObject#equals(Object)
     */
    public boolean equals(Object obj) {
		if (obj instanceof ValueObject) {
			ValueObject vo = (ValueObject)obj;
			return (this == obj || this.getId().equals(vo.getId()));
		}
		return false;
    }

    /**
     * @see com.vh.narch.valueobject.ValueObject#toMap()
     */
    public Map toMap() throws Exception {
        Map result = new Hashtable();
        result.put("id", this.getId());
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String key = fields[i].getName();
            String mthName = "get" + key.substring(0,1).toUpperCase() + key.substring(1);
            Method mth = this.getClass().getDeclaredMethod(mthName, new Class[0]);
            mth.setAccessible(true);
            Object value = mth.invoke(this, new Class[0]);
            if (value != null && !(value instanceof Collection)) {
                if (value instanceof ValueObject) {
                    result.put(key, ((ValueObject)value).getId());
                } else {
                    result.put(key, value);
                }
            }
        }
        return result;
    }

}