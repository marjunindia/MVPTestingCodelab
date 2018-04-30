package com.example.arjun_mu.mvptestingcodelab;

/**
 * Created by arjun_mu on 4/30/2018.
 */

public class Injection {

    public static ImageFile provideImageFile() {
        return new FakeImageFileImpl();
    }

    public static NotesRepository provideNotesRepository() {
        return NoteRepositories.getInMemoryRepoInstance(new FakeNotesServiceApiImpl());
    }
}
