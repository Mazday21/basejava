package ru.basejava.storage;

import org.junit.Test;

public class ListStorageTest extends AbstractArrayStorageTest {

    @Override
    @Test
    public void saveOverflow() {
    }

    public ListStorageTest() {
        super(new ListStorage());
    }
}