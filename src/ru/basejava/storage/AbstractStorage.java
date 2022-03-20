package ru.basejava.storage;

import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<T> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract T getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, T searchKey);

    protected abstract boolean isExist(T searchKey);

    protected abstract void doSave(Resume r, T searchKey);

    protected abstract Resume doGet(T searchKey);

    protected abstract void doDelete(T searchKey);

    protected abstract List<Resume> getAll();

    public void update(Resume r) {
        LOG.info("Update " + r);
        T searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        LOG.info("Save " + r);
        T searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        T searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        T searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = getAll();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    private T getExistedSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private T getNotExistedSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public static Comparator<Resume> RESUME_COMPARATOR
            = Comparator.naturalOrder();
}
