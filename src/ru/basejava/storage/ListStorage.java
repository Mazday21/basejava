package ru.basejava.storage;

import ru.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void updating(Resume r, int index) {
        storage.set(index, r);
    }

    @Override
    protected void saving(Resume r, int index) {
        storage.add(r);
    }

    @Override
    protected void deleting(int index) {
        storage.remove(index);
    }

    @Override
    protected Resume getting(int index) {
        return storage.get(index);
    }
}
