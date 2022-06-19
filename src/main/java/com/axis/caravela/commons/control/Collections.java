package com.axis.caravela.commons.control;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public interface Collections {

    /**
     * Returns the given list or an empty list if the given list is null.
     */
    static <T> List<T> safeList(List<T> list) {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    /**
     * Replaces the list with the given element at the given index.
     */
    static <T> List<T> replaceElementAtIndex(List<T> list, T element, Integer index) {
        LinkedList<T> linkedList = new LinkedList<>(list);
        linkedList.set(index, element);
        return linkedList;
    }

    /**
     * Inserts the given element at the given index in the given list.
     */
    static <T> List<T> insertElementAtIndex(List<T> list, T element, Integer index) {
        LinkedList<T> linkedList = new LinkedList<>(list);
        linkedList.add(index, element);
        return linkedList;
    }

    /**
     * Checks if the given reference list contains all of the elements of the given value list.
     */
    static <T> Boolean containsAll(List<T> referenceList, List<T> valueList) {
        for (T value : valueList) {
            if (!contains(referenceList, value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given commons contains the given value.
     */
    static <T> boolean contains(Collection<T> collection, T value) {
        return collection != null && collection.contains(value);
    }

    /**
     * Checks if the given array contains the given value.
     */
    static <T> boolean contains(T[] array, T value) {
        List<T> list = Arrays.asList(array);
        return contains(list, value);
    }

    /**
     * Checks that the given list is not null and not empty
     */
    static boolean notNullOrEmpty(Collection list) {
        return !nullOrEmpty(list);
    }

    /**
     * Checks that the given list is null or empty
     */
    static boolean nullOrEmpty(Collection list) {
        return list == null || list.isEmpty();
    }

    /**
     * Returns the first element of the non-empty list.
     */
    static <T> T getFirst(Collection<T> list) {
        if (list.isEmpty()) {
            throw new RuntimeException("Expected a list with 1 or 0 elements");
        }
        return list.iterator().next();
    }

    /**
     * Returns the first element of the list or null if the list is null or empty.
     */
    static <T> T getFirstOrNull(Collection<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.iterator().next();
    }

    static <T> Optional<T> getOptionalOnlyElement(List<T> list) {
        if (list.size() > 1) {
            throw new RuntimeException("Expected a list with 1 or 0 elements");
        }
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    static <T> T getOnlyElement(List<T> list) {
        if (list.size() != 1) {
            throw new RuntimeException("One item expected, got");
        }
        return list.get(0);
    }

    /**
     * Returns the first element of the non-empty list.
     */
    static <T> T getElementAtIndex(T[] array, int index) {
        if (array == null || array.length < index + 1) {
            return null;
        }
        return array[index];
    }

    static <T> List<T> filter(Collection<T> collection, Predicate<T> filter) {
        List<T> filteredList = new ArrayList<>();
        for (T element : collection) {
            if (filter.test(element)) {
                filteredList.add(element);
            }
        }
        return filteredList;
    }

    static <K, V> Map<K, V> filter(Map<K, V> map, BiPredicate<K, V> filter) {
        Map<K, V> filteredMap = new HashMap<>();
        map.forEach((k, v) -> {
            if (filter.test(k, v)) {
                filteredMap.put(k, v);
            }
        });
        return filteredMap;
    }

    static <T> T filterOnlyElement(Collection<T> collection, Predicate<T> filter) {
        List<T> filteredList = filter(collection, filter);
        if (filteredList.size() != 1) {
            throw new RuntimeException("Expected a list with 1 or 0 elements");
        }
        return filteredList.get(0);
    }

    static <T> T filterOnlyOrNullElement(Collection<T> collection, Predicate<T> filter) {
        List<T> filteredList = filter(collection, filter);
        if (filteredList.size() > 1) {
            throw new RuntimeException("Expected a list with 1 or 0 elements");
        }
        return getFirstOrNull(filteredList);
    }

    static <T> Optional<T> filterOptionalOnlyElement(Collection<T> collection, Predicate<T> filter) {
        return Optional.of(filterOnlyOrNullElement(collection, filter));
    }

    static <K, V> List<V> filterValues(Map<K, V> map, BiPredicate<K, V> filter) {
        return new ArrayList<>(filter(map, filter).values());
    }

    static <K, V> V filterOnlyValue(Map<K, V> map, BiPredicate<K, V> filter) {
        List<V> filteredValues = filterValues(map, filter);
        if (filteredValues.size() != 1) {
            throw new RuntimeException("Expected a list with 1 or 0 elements");
        }
        return filteredValues.get(0);
    }

    static <T> List<T> getSortedList(List<T> list, Comparator<T> comparator) {
        List<T> sortedList = new ArrayList<>(list);
        sortedList.sort(comparator);
        return sortedList;
    }
}
