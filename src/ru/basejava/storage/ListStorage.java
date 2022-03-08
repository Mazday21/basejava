package ru.basejava.storage;

import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume r) {
        ListIterator<Resume> iterator = storage.listIterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (r.getUuid().equals(resume.getUuid())) {
                iterator.set(r);
                return;
            } else if (!iterator.hasNext()) {
                throw new NotExistStorageException(r.getUuid());
            }
        }
    }

    @Override
    public void save(Resume r) {
        for (Resume resume : storage) {
            if (r.getUuid().equals(resume.getUuid())) {
                throw new ExistStorageException(r.getUuid());
            }
        }
        storage.add(r);
    }

    @Override
    public Resume get(String uuid) {
        for (Resume resume : storage) {
            if (uuid.equals(resume.getUuid())) {
                return resume;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        Iterator<Resume> iterator = storage.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            if (uuid.equals(r.getUuid())) {
                iterator.remove();
                return;
            } else if (!iterator.hasNext()) {
                throw new NotExistStorageException(uuid);
            }
        }
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }
}
