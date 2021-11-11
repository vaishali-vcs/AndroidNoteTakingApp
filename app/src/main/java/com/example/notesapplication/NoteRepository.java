package com.example.notesapplication;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao notedao;

    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application)
    {
        NoteDatabase database = NoteDatabase.getInstance(application);
        notedao = database.noteDao();

        allNotes = notedao.getAllNotes();

    }

    public void insert(Note note){
        new InsertNoteAsyncTask(notedao).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsyncTask(notedao).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsyncTask(notedao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNoteAsyncTask(notedao).execute();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>
    {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao notedao)
        {
            this.noteDao = notedao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);

            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void>
    {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao notedao)
        {
            this.noteDao = notedao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);

            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void>
    {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao notedao)
        {
            this.noteDao = notedao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);

            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private NoteDao noteDao;

        private DeleteAllNoteAsyncTask(NoteDao notedao)
        {
            this.noteDao = notedao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();

            return null;
        }
    }

}
