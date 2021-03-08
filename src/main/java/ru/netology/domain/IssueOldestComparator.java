package ru.netology.domain;

import java.util.Comparator;

public class IssueOldestComparator implements Comparator<Issue> {
    public int compare(Issue o1, Issue o2) {
        return o1.getDate() - o2.getDate();
    }
}
