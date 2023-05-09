// IBookManager.aidl
package com.example.chapter02;

// Declare any non-default types here with import statements

import com.example.chapter02.Book;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}