package ru.basejava.storage;

import ru.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public interface Storage {

    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    List<Resume> getAllSorted();

    int size();

    Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> {
        int i = o1.getFullName().compareTo(o2.getFullName());
        if (i != 0) {
            return i;
        } else return o1.getUuid().compareTo(o2.getUuid());
    };
}
