package ru.basejava.model;

import java.util.UUID;

/**
 * ru.javawebinar.basejava.model.Resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    public Resume() {
        this.uuid = (UUID.randomUUID().toString());
        this.fullName = ("dummy");
    }

    public Resume(String fullName) {
        this.uuid = (UUID.randomUUID().toString());
        this.fullName = fullName;
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!getFullName().equals(resume.getFullName())) return false;
        return getUuid().equals(resume.getUuid());
    }

    @Override
    public int hashCode() {
        int result = getUuid().hashCode();
        result = 31 * result + getFullName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
