package com.example.notekeeper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

    static DataManager sDataManager;
    @BeforeClass
    public static void classSetup()
    {
        sDataManager=DataManager.getInstance();
    }
    @Before
    public void setUp()
    {
       //ataManager dm = DataManager.getInstance();
        sDataManager.getNotes().clear();
        sDataManager.initializeExampleNotes();
    }

    @Test
    public void createNewNote() {

        final CourseInfo courseInfo= sDataManager.getCourse("android_async");
        final String noteTitle="Test note title";
        final String noteText="This is the body text of my test note";
        int noteIndex=sDataManager.createNewNote();
        NoteInfo newNote=sDataManager.getNotes().get(noteIndex);
        newNote.setText(noteText);
        newNote.setTitle(noteTitle);
        newNote.setCourse(courseInfo);

        NoteInfo compareNote=sDataManager.getNotes().get(noteIndex);
        assertEquals(compareNote.getCourse(), courseInfo);
        assertEquals(compareNote.getTitle(),noteTitle);
        assertEquals(compareNote.getText(), noteText);
    }
    @Test
    public void findSimilarNotes()
    {

        final CourseInfo courseInfo= sDataManager.getCourse("android_async");
        final String noteTitle="Test note title";
        final String noteText1="This is the body text of my test note";
        final String noteText2="This is the body of my second  test note";
        int noteIndex1=sDataManager.createNewNote();
        NoteInfo newNote1=sDataManager.getNotes().get(noteIndex1);
        newNote1.setText(noteText1);
        newNote1.setTitle(noteTitle);
        newNote1.setCourse(courseInfo);

        int noteIndex2=sDataManager.createNewNote();
        NoteInfo newNote2=sDataManager.getNotes().get(noteIndex2);
        newNote2.setText(noteText2);
        newNote2.setTitle(noteTitle);
        newNote2.setCourse(courseInfo);


        int foundIndex1=sDataManager.findNote(newNote1);
        assertEquals(noteIndex1, foundIndex1);

        int foundIndex2=sDataManager.findNote(newNote2);
        assertEquals(noteIndex2, foundIndex2);
    }
    @Test
    public void createNewNoteOneStepCreation(){
        final CourseInfo course= sDataManager.getCourse("android_async");
        final String noteTitle="Test note title";
        final String noteText="This is the body text of my test note";
        int noteIndex=sDataManager.createNewNote(course, noteTitle, noteText);

        NoteInfo compareNote=sDataManager.getNotes().get(noteIndex);
        assertEquals(compareNote.getCourse(), course);
        assertEquals(compareNote.getTitle(),noteTitle);
        assertEquals(compareNote.getText(), noteText);
    }
}

