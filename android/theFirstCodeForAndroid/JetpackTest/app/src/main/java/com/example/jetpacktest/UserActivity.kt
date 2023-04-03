package com.example.jetpacktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpacktest.databinding.ActivityUserBinding
import kotlin.concurrent.thread

private const val TAG = "UserActivity"

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.userName.observe(this) { Log.d(TAG, it) }
        binding.changeUser.setOnClickListener {
            val age = (0..100).random()
            userViewModel.changeUser(User("zhang", "san$age", age))
        }

        userViewModel.user.observe(this) {
            Log.d(TAG, it.firstName + " " + it.lastName)
        }
        binding.getUser.setOnClickListener {
            val userId = (0..100).random().toString()
            userViewModel.getUser(userId)
        }

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)

        binding.addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }

        binding.updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }

        binding.deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }

        binding.queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d(TAG, user.toString())
                }
            }
        }
    }
}