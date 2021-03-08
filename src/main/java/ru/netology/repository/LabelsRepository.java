package ru.netology.repository;

import java.util.*;

public class LabelsRepository {
    private HashSet<String> labels = new HashSet<>();

    public void add(String labelName) {
        labels.add(labelName);
    }

    public boolean removeAll() {
        return labels.removeAll(labels);
    }

    public boolean remove(String label) {
        return labels.remove(label);
    }

    public boolean containsLabel(String label) {
        return labels.contains(label);
    }

}
