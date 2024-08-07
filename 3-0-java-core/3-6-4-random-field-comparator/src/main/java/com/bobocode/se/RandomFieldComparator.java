package com.bobocode.se;

import com.bobocode.util.ExerciseNotCompletedException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * If no field is available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 *<p><p>
 *  <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 *  <p>
 *
 * @author Stanislav Zabramnyi
 */
public class RandomFieldComparator<T> implements Comparator<T> {

    private Field field;

    private String targetTypeName;

    public RandomFieldComparator(Class<T> targetType) {
        if(targetType == null){
            throw new NullPointerException();
        } else if(targetType.isInterface() || targetType.getDeclaredFields().length == 0) {
            throw new IllegalArgumentException();
        }
        if(targetType.getDeclaredFields().length == 1) {
            throw new IllegalArgumentException();
        }
        targetTypeName = targetType.getName().substring(targetType.getName().lastIndexOf('$') + 1);
        field = Arrays.stream(targetType.getDeclaredFields())
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value greater than a non-null value.
     *
     * @param o1
     * @param o2
     * @return positive int in case of first parameter {@param o1} is greater than second one {@param o2},
     *         zero if objects are equals,
     *         negative int in case of first parameter {@param o1} is less than second one {@param o2}.
     */
    @Override
    public int compare(T o1, T o2) {
        try {
            field.setAccessible(true);
            Object obj1 = field.get(o1);
            Object obj2 = field.get(o2);

            if (obj1 == null && obj2 == null) return 0;
            if (obj1 == null) return 1;
            if (obj2 == null) return -1;

            if (obj1 instanceof Comparable && obj2 instanceof Comparable) {
                return ((Comparable<Object>) obj1).compareTo(obj2);
            } else {
                return obj1.toString().compareTo(obj2.toString());
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error accessing field", e);
        }
    }

    /**
     * Returns the name of the randomly-chosen comparing field.
     */
    public String getComparingFieldName() {
        return field.getName();
    }

    /**
     * Returns a statement "Random field comparator of class '%s' is comparing '%s'" where the first param is the name
     * of the type T, and the second parameter is the comparing field name.
     *
     * @return a predefined statement
     */
    @Override
    public String toString() {
        return String.format("Random field comparator of class '%s' is comparing '%s'", targetTypeName, field.getName()); // todo: implement this method;
    }
}
