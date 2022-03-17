package ru.basejava.storage;

import org.junit.Test;
import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;

import static org.junit.Assert.*;
import static ru.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
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
}