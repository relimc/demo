// IOnNewBookArrivedListener.aidl
package com.example.bundletest;

// Declare any non-default types here with import statements
import com.example.bundletest.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}