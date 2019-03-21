package com.surroundapps.objectboxdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import io.objectbox.Box
import io.objectbox.query.Query
import java.text.DateFormat
import java.util.*

import android.widget.AdapterView.OnItemClickListener


class NoteActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var addNoteButton: View

    private lateinit var notesBox: Box<Note>
    private lateinit var notesQuery: Query<Note>
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        setUpViews()

        // using ObjectBox Kotlin extension functions (https://docs.objectbox.io/kotlin-support)
        notesBox = ObjectBox.boxStore.boxFor()





        updateNotes()
    }


    /** Manual trigger to re-query and update the UI. For a reactive alternative check [ReactiveNoteActivity].  */
    private fun updateNotes() {
        val notes = notesQuery.find()
        notesAdapter.setNotes(notes)
    }


    private fun setUpViews() {
        notesAdapter = NotesAdapter()

        findViewById<ListView>(R.id.listViewNotes).apply {
            adapter = notesAdapter
            onItemClickListener = noteClickListener
        }

        addNoteButton = findViewById<View>(R.id.buttonAdd).apply {
            isEnabled = false
        }

        editText = findViewById<EditText>(R.id.editTextNote).apply {
            setOnEditorActionListener(editorActionListener)
            addTextChangedListener(textChangedListener)
        }
    }

    // Linked from main.xml
    @Suppress("UNUSED_PARAMETER")
    fun onAddButtonClick(view: View) {
        addNote()
    }

    private fun addNote() {
        val noteText = editText.text.toString()
        editText.setText("")

        val df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)
        val comment = "Added on " + df.format(Date())

        val note = Note(0L,"","",null)
        notesBox.put(note)
        Log.d(App.TAG, "Inserted new note, ID: " + note.label2)

        updateNotes()
    }

    private val noteClickListener = OnItemClickListener { _, _, position, _ ->
        notesAdapter.getItem(position)?.also {
            notesBox.remove(it)
            Log.d(App.TAG, "Deleted note, ID: " + it.label2)
        }
        updateNotes()
    }

    private val editorActionListener = TextView.OnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            addNote()
            true
        } else {
            false
        }
    }

    private val textChangedListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            addNoteButton.isEnabled = s.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable) {
        }
    }

}
