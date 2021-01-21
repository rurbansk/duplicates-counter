package pl.rurbansk.duplicates_counter;

import java.util.*;

public class DuplicatesCounter {

    public Map<String, Integer> countDuplicates(String elements) {
        Map<String, Integer> result = new HashMap<>(35);
        String[] listOfElements = elements.split(",");
        for (String element : listOfElements) {
            int counter = Optional.ofNullable(result.get(element)).orElse(0);
            result.put(element, ++counter);
        }
        return result;
    }

    public Map<String, Integer> countDuplicatesOptimized(String elements) {
        Map<String, Integer> result = new HashMap<>(35);
        List<String> listOfElements = Arrays.asList(elements.split(","));
        return binarySearch(result, listOfElements);
    }

    private Map<String, Integer> binarySearch(Map<String, Integer> map, List<String> elements) {
        if (elements.isEmpty()) {
            return map;
        }
        String element = elements.get(0);
        if (elements.size() == 1 || !element.equals(elements.get(1))) {
            map.put(element, 1);
            return binarySearch(map, elements.subList(1, elements.size()));
        } else {
            int lastPosition = 0;
            int left = 0;
            int right = elements.size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (element.equals(elements.get(mid))) {
                    left = mid + 1;
                    lastPosition = mid;
                } else if (element.compareTo(elements.get(mid)) < 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            map.put(element, lastPosition + 1);
            return binarySearch(map, elements.subList(lastPosition + 1, elements.size()));
        }
    }
}
