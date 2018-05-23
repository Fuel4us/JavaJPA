/**
 *
 */
package eapli.framework.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author pgsou_000
 *
 */
public final class Collections {

    private Collections() {
	// to make sure this is an utility class
    }

    public static int size(Iterable<?> col) {
	int i = 0;
	for (@SuppressWarnings({ "unused", "squid:S1481" })
	final Object o : col) {
	    i++;
	}
	return i;
    }

    public static <T extends Enum<T>> List<T> listOfEnum(Class<T> e) {
	final List<T> res = new ArrayList<>();
	for (final T type : e.getEnumConstants()) {
	    res.add(type);
	}
	return res;
    }
    
    /**
     * Tranforms an iterable in List.
     * @param <T> Object Type
     * @param iterable to transform in List
     * @return the List
     */
    public static <T> List<T> iteratorToList(Iterator<T> it) {
        List<T> copy = new ArrayList<>();
        while (it.hasNext()) {
            copy.add(it.next());
        }
        return copy;
    }
}
