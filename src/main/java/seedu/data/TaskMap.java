package seedu.data;

import seedu.task.DateSorter;
import seedu.task.PrioritySorter;
import seedu.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TaskMap {
    public static final int MAX_NUM_TASKS = 10000;
    private LinkedHashMap<Integer, Task> tasksMap;

    public TaskMap() {
        tasksMap = new LinkedHashMap<>(MAX_NUM_TASKS);
    }

    public TaskMap(Collection<Task> tasks) {
        tasksMap = new LinkedHashMap<>(MAX_NUM_TASKS);
        for (Task task : tasks) {
            tasksMap.put(task.getTaskID(), task);
        }
    }

    public Collection<Task> getValues() {
        return tasksMap.values();
    }

    public void addTask(Task task) {
        tasksMap.put(task.getTaskID(), task);
    }

    public int size() {
        return tasksMap.size();
    }

    public Task get(Integer key) {
        return tasksMap.get(key);
    }

    public TaskMap sortListByDate() {
        // Sort by date
        List<Task> sorted = tasksMap.values().stream()
            .sorted(new DateSorter())
            .collect(Collectors.toList());
        return new TaskMap(sorted);
    }

    public TaskMap sortListByPriority() {
        // Sort by priority, same priority then date
        List<Task> sorted = tasksMap.values().stream()
            .sorted(new DateSorter())
            .sorted(new PrioritySorter())
            .collect(Collectors.toList());
        return new TaskMap(sorted);
    }

    public TaskMap searchByDescription(String toSearch) {
        List<Task> found = tasksMap.values().stream()
            .filter(task -> task.getDescription().toLowerCase().contains(toSearch.toLowerCase()))
            .collect(Collectors.toList());
        return new TaskMap(found);
    }

    public TaskMap searchByDate(LocalDate date) {
        List<Task> found = tasksMap.values().stream()
            .filter(task -> task.getDate().equals(date))
            .collect(Collectors.toList());
        return new TaskMap(found);
    }

    public void clear() {
        tasksMap.clear();
    }

    public void delete(Integer key) {
        tasksMap.remove(key);
    }

}
