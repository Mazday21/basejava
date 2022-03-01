package ru.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;
import ru.basejava.storage.Storage;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume("uuid1");
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(UUID_1));
    }

    @Test
    public void getAll() throws Exception {
        Resume resume = new Resume("uuid1");
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(resume, resumes[0]);
    }

    @Test
    public void save() throws Exception {
        Resume resume = new Resume("uuidSave");
        storage.save(resume);
        Assert.assertEquals(resume, storage.get("uuidSave"));
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void get() throws Exception {
        Resume resume = new Resume("uuid1");
        Assert.assertEquals(resume, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}
