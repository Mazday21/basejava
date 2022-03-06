package ru.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;

import static org.junit.Assert.*;
import static ru.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
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
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        assertSame(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume resume = new Resume("uuid4");
        storage.update(resume);
    }

    @Test
    public void getAll() {
        Resume resume1 = new Resume(UUID_1);
        Resume resume2 = new Resume(UUID_2);
        Resume resume3 = new Resume(UUID_3);
        Resume[] resumes = {resume1, resume2, resume3};
        assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void save() {
        Resume resume = new Resume("uuidSave");
        storage.save(resume);
        assertEquals(resume, storage.get("uuidSave"));
        assertEquals(4, storage.size());
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            while (storage.size() < STORAGE_LIMIT) {
                Resume r = new Resume();
                storage.save(r);
            }
        } catch (StorageException e) {
            fail("Overflow ahead of time");
        }
        Resume r = new Resume();
        storage.save(r);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        Resume resume = new Resume(UUID_1);
        storage.save(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
        assertEquals(2, storage.size());
    }

    @Test
    public void get() {
        Resume resume = new Resume(UUID_1);
        assertEquals(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }
}
