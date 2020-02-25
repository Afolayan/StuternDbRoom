package com.afolayanseyi.stutern

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afolayanseyi.stutern.data.Result
import com.afolayanseyi.stutern.data.User
import com.afolayanseyi.stutern.db.ApplicationDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val userDao = ApplicationDatabase.getDatabase(this).userDao()
        val userViewModel = UserViewModel(userDao)

        var count = 0

        fab.setOnClickListener { view ->
            val user =
                User(++count, "jaff $count", "afo")
            userViewModel.insertUser(user)
        }


        userViewModel.usersLiveData.observe(this, Observer { result ->
            when (result) {
                is Result.Success -> {
                    result.data?.let { users ->
                        if(result.data.isNotEmpty())count = users.last().uid
                        Toast.makeText(this, "last user: $count", Toast.LENGTH_LONG).show()
                        userRecyclerView.apply {
                            adapter =
                                UserRecyclerAdapter(this@MainActivity, users) { selectedUser ->
                                    Toast.makeText(
                                        this@MainActivity,
                                        "$selectedUser",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            layoutManager = LinearLayoutManager(this@MainActivity)
                        }
                    }
                }
                is Result.Error -> {
                    Toast.makeText(this, "Error loading users", Toast.LENGTH_LONG).show()
                }
                is Result.Loading -> {
                    Toast.makeText(this, "Loading users", Toast.LENGTH_SHORT).show()
                }
            }

        })


    }

}
