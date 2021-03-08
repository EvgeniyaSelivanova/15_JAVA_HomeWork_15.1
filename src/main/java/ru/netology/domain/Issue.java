package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    public int id;
    public String title;
    public String description;
    public boolean opened;
    public int date;
    public String author;
    public String assignee;
    public HashSet<String> labels;
}
