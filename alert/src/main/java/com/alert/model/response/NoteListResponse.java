package com.alert.model.response;

import com.alert.model.Note;

import java.util.List;

public class NoteListResponse {
    List<Note> noteList;

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }
}
