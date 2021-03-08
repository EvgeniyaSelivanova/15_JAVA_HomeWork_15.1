package ru.netology.manager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private static Manager manager = new Manager();

    private Issue first, second, third, forth, fifth;

    @BeforeEach
    public void setUpLabels() {
        manager.addLabels("111");
        manager.addLabels("222");
        manager.addLabels("333");
        manager.addLabels("444");
        first = new Issue(1, "First", "description1", true, 25022021, "author1", "assignee1", new HashSet<>(manager.containsLabel("111", "222")));
        second = new Issue(2, "Second", "description2", true, 26022021, "author2", "assignee2", new HashSet<>(manager.containsLabel("111", "777")));
        third = new Issue(3, "Third", "description3", false, 27022021, "author2", "assignee2", new HashSet<>(manager.containsLabel("111", "222")));
        forth = new Issue(4, "Forth", "description4", false, 28022021, "author1", "assignee2", new HashSet<>(manager.containsLabel("111", "222")));
        fifth = new Issue(5, "Fifth", "description5", true, 29022021, "author2", "assignee1", new HashSet<>(manager.containsLabel("111", "333")));
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(fifth);
    }

    @AfterEach
    public void deleteAll() {
        manager.removeAll();
    }

    @Test
    public void shouldReturnAllIssues() {
        List<Issue> expected = List.of(fifth, forth, third, second, first);
        List<Issue> actual = manager.getAll();
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldDeleteAllIssues() {
        assertTrue(manager.removeAll());
    }

    @Test
    public void shouldReturnOpenedIssues() {
        List<Issue> expected = List.of(fifth, second, first);
        List<Issue> actual = manager.openedIssue();
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldReturnClosedIssues() {
        List<Issue> expected = List.of(forth, third);
        List<Issue> actual = manager.closedIssue();
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldReturnByAuthorIssues() {
        List<Issue> expected = List.of(forth, first);
        List<Issue> actual = manager.filterByAuthor("author1");
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldReturnByAssigneeIssues() {
        List<Issue> expected = List.of(forth, third, second);
        List<Issue> actual = manager.filterByAssignee("assignee2");
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldReturnByLabelIssues() {
        List<Issue> expected = List.of(fifth);
        List<Issue> actual = manager.filterByLabel("333");
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldReturnByAllLabelIssues() {
        List<Issue> expected = List.of(fifth, forth, third, second, first);
        List<Issue> actual = manager.filterByLabel("111");
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldReturnSortedByOldestIssues() {
        List<Issue> expected = List.of(first, second, third, forth, fifth);
        List<Issue> actual = manager.sortedByOldest(manager.getAll());
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldCloseIssue() {
        Issue expected = first;
        Issue actual = manager.closeIssue(1);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveIssue() {
        List<Issue> expected = List.of(forth, third, second, first);
        manager.removeIssue(fifth);
        List<Issue> actual = manager.getAll();
        assertIterableEquals(expected, actual);
    }

}