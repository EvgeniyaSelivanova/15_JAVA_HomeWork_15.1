package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;
import ru.netology.domain.IssueNewestComparator;
import ru.netology.domain.IssueOldestComparator;
import ru.netology.repository.IssueRepository;
import ru.netology.repository.LabelsRepository;

import java.util.*;
import java.util.List;
import java.util.function.Predicate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    private IssueRepository repository = new IssueRepository();
    private List<Issue> issues = new ArrayList<>();
    private LabelsRepository labelsRepository = new LabelsRepository();
    private Issue issue;

    public void addLabels(String labelName) {
        labelsRepository.add(labelName);
    }

    public ArrayList containsLabel(String... labels) {
        ArrayList tmp = new ArrayList();
        for (String label : labels) {
            if (labelsRepository.containsLabel(label)) {
                tmp.add(label);
            }
        }
        return tmp;
    }


    public void add(Issue issue) {
        repository.add(issue);
    }

    public List<Issue> getAll() {
        List<Issue> tmp = new ArrayList<>();
        tmp = repository.getAll();
        Collections.sort(tmp, new IssueNewestComparator());
        return tmp;
    }

    public boolean removeAll() {
        return repository.removeAll();
    }

    public List<Issue> openedIssue() {
        List<Issue> tmp = new ArrayList<>();
        issues = repository.getAll();
        for (Issue issue : issues) {
            if (issue.opened) {
                tmp.add(issue);
            }
        }
        Collections.sort(tmp, new IssueNewestComparator());
        return tmp;
    }

    public List<Issue> closedIssue() {
        List<Issue> tmp = new ArrayList<>();
        issues = repository.getAll();
        for (Issue issue : issues) {
            if (!issue.opened) {
                tmp.add(issue);
            }
        }
        Collections.sort(tmp, new IssueNewestComparator());
        return tmp;
    }

    public List<Issue> filterByAuthor(String author) {
        List<Issue> tmp = new ArrayList<>();
        issues = repository.getAll();
        for (Issue issue : issues) {
            if (filterAuthor(author).test(issue)) {
                tmp.add(issue);
            }
        }
        Collections.sort(tmp, new IssueNewestComparator());
        return tmp;
    }

    public List<Issue> filterByAssignee(String assignee) {
        List<Issue> tmp = new ArrayList<>();
        issues = repository.getAll();
        for (Issue issue : issues) {
            if (filterAssignee(assignee).test(issue)) {
                tmp.add(issue);
            }
        }
        Collections.sort(tmp, new IssueNewestComparator());
        return tmp;
    }

    public List<Issue> filterByLabel(String label) {
        List<Issue> tmp = new ArrayList<>();
        issues = repository.getAll();
        for (Issue issue : issues) {
            if (filterLabel(label).test(issue)) {
                tmp.add(issue);
            }
        }
        Collections.sort(tmp, new IssueNewestComparator());
        return tmp;
    }

    public Issue closeIssue(int id) {
        issues = repository.getAll();
        Issue tmp = new Issue();
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.opened = false;
                tmp = issue;
            }
        }
        return tmp;
    }

    public void removeIssue(Issue issue) {
       repository.remove(issue);
    }

    public List<Issue> sortedByOldest(List<Issue> issues) {
        Collections.sort(issues, new IssueOldestComparator());
        return issues;
    }

    public static Predicate<Issue> filterAuthor(String author) {
        return issue -> issue.getAuthor().equals(author);
    }

    public static Predicate<Issue> filterAssignee(String assignee) {
        return issue -> issue.getAssignee().equals(assignee);
    }

    public static Predicate<Issue> filterLabel(String label) {
        return issue -> issue.labels.contains(label);
    }

}
