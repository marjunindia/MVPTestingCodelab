package com.example.arjun_mu.mvptestingcodelab;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by arjun_mu on 5/2/2018.
 */
public class NotesPresenterTest {
    private static List<Note> NOTES = Lists.newArrayList(new Note("Title1", "Description1"),
            new Note("Title2", "Description2"));

    private static List<Note> EMPTY_NOTES = new ArrayList<>(0);

    @Mock
    private NotesRepository mNotesRepository;

    @Mock
    private NotesContract.View mNotesView;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<NotesRepository.LoadNotesCallback> mLoadNotesCallbackCaptor;

    private NotesPresenter mNotesPresenter;


    @Before
    public void setUp() throws Exception {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mNotesPresenter = new NotesPresenter(mNotesRepository, mNotesView);
    }

    @Test
    public void loadNotesFromRepositoryAndLoadIntoView() {
        // Given an initialized NotesPresenter with initialized notes
        // When loading of Notes is requested
       mNotesPresenter.loadNotes(true);

        // Callback is captured and invoked with stubbed notes
        verify(mNotesRepository).getNotes(mLoadNotesCallbackCaptor.capture());
        mLoadNotesCallbackCaptor.getValue().onNotesLoaded(NOTES);

        // Then progress indicator is hidden and notes are shown in UI
        verify(mNotesView).setProgressIndicator(false);
        verify(mNotesView).showNotes(NOTES);
    }

    @Test
    public void clickOnFab_ShowsAddsNoteUi() {
        // When adding a new note
        mNotesPresenter.addNewNote();

        // Then add note UI is shown
        verify(mNotesView).showAddNote();
    }

    @Test
    public void clickOnNote_ShowsDetailUi() {
        // Given a stubbed note
        Note requestedNote = new Note("Details Requested", "For this note");

        // When open note details is requested
        mNotesPresenter.openNoteDetails(requestedNote);

        // Then note detail UI is shown
        verify(mNotesView).showNoteDetailUi(any(String.class));
    }
}