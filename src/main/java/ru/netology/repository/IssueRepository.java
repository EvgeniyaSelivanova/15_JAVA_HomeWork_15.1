package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public void add(Issue issue) {
        issues.add(issue);
    }

    public List<Issue> getAll() {
        return issues;
    }

    public boolean removeAll() {
        issues.clear();
        return true;
    }

    public boolean remove(Issue issue) {
        return issues.remove(issue);
    }

}
