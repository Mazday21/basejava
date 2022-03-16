package ru.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static ru.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static ru.basejava.storage.AbstractStorage.RESUME_COMPARATOR;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    protected static final String FULL_NAME_1 = "ABC1";
    protected static final String FULL_NAME_2 = "ABC2";
    protected static final String FULL_NAME_3 = "ABC3";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1, FULL_NAME_1));
        storage.save(new Resume(UUID_2, FULL_NAME_2));
        storage.save(new Resume(UUID_3, FULL_NAME_3));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1, "Update");
        storage.update(resume);
        assertSame(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = new ArrayList<>(List.of(new Resume(UUID_1, FULL_NAME_1), new Resume(UUID_2, FULL_NAME_2), new Resume(UUID_3, FULL_NAME_3)));
        list.sort(RESUME_COMPARATOR);
        assertEquals(list, storage.getAllSorted());
    }

    @Test
    public void save() {
        Resume resume = new Resume("uuid4", "Save");
        storage.save(resume);
        assertEquals(resume, storage.get("uuid4"));
        assertEquals(4, storage.size());
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            while (storage.size() < STORAGE_LIMIT) {
                storage.save(new Resume("FullName"));
            }
        } catch (StorageException e) {
            fail("Overflow ahead of time");
        }
        storage.save(new Resume("FullName"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1, FULL_NAME_1));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        storage.delete(UUID_2);
        Resume resume = new Resume(UUID_3, FULL_NAME_3);
        assertEquals(resume, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void get() {
        assertEquals(new Resume(UUID_1, FULL_NAME_1), storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}
