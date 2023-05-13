// IBookManager.aidl
package com.example.bundletest;

// Declare any non-default types here with import statements
import com.example.bundletest.Book;
import com.example.bundletest.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}