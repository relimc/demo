package com.example.databindingtest

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class BookViewModel: BaseObservable() {
    var book: Book? = null
        set(book) {
            field = book
            notifyChange()
        }

    @get:Bindable
    val name: String?
        get() = book?.name
}