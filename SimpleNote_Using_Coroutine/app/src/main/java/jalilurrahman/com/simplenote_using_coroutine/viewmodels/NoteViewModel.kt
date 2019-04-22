package jalilurrahman.com.simplenote_using_coroutine.viewmodels

import jalilurrahman.com.simplenote_using_coroutine.db.bean.Note
import jalilurrahman.com.simplenote_using_coroutine.db.repository.NoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRespository: NoteRepository) : BaseViewModel() {
    val noteList = noteRespository.list()

    fun add(
        content: String,
        onSuccess: (() -> Unit)? = null,
        onError: ((throwable: Throwable) -> Unit)? = null,
        onFinish: (() -> Unit)? = null
    ) {
        launchOnUITryCatch(
            {
                noteRespository.add(content)
                onSuccess?.invoke()
            },
            {
                onError?.invoke(it)
            },
            {
                onFinish?.invoke()
            },
            true
        )
    }

    fun update(note: Note) {
        GlobalScope.launch {
            noteRespository.update(note)
        }
    }

    fun del(id: Long) {
        GlobalScope.launch {
            noteRespository.del(id)
        }
    }
}