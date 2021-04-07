package com.ambial.simpletodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ambial.simpletodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TodoListAdapter(mutableListOf())

        val recyclerView = binding.rvTodoitems
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val btnAddTodo = binding.btnAddTodo
        val btnDeleteDone = binding.btnDeleteDone
        val etNewTodo = binding.etNewTodo

        btnAddTodo.setOnClickListener {
            val txt = etNewTodo.text.toString()
            if (txt.isNotEmpty()) {
                adapter.addTodoItem(TodoListItem(txt))
                etNewTodo.text.clear()
            }
        }

        btnDeleteDone.setOnClickListener {
            adapter.deleteDoneEntries()
        }
    }
}