package ru.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.basejava.storage.AbstractStorage.RESUME_COMPARATOR;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    protected static final String FULL_NAME_1 = "ABC1";
    protected static final String FULL_NAME_2 = "ABC2";
    protected static final String FULL_NAME_3 = "ABC3";

    protected static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);
    protected static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);
    protected static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);

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
        List<Resume> list = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
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

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        storage.delete(UUID_2);
        assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}
