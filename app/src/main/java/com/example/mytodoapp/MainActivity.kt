package com.example.mytodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        rv_todo_items.adapter = todoAdapter
        rv_todo_items.layoutManager = LinearLayoutManager(this)

        btn_add_button.setOnClickListener {
            val todoTitle = et_todo_edit.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                et_todo_edit.text.clear()
            }
        }
        btn_delete_button.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}