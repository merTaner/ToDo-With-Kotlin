package com.example.mytodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())
        val database = FirebaseDatabase.getInstance().reference

        rv_todo_items.adapter = todoAdapter
        rv_todo_items.layoutManager = LinearLayoutManager(this)

        var nextTodoId = 1
        btn_add_button.setOnClickListener {
            val todoTitle = et_todo_edit.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todo.id = nextTodoId++.toString()
                database.child("Todos").child(todo.id).setValue(todo)

                // Uptade to UI
                todoAdapter.addTodo(todo)
                et_todo_edit.text.clear()
            }
        }

        btn_delete_button.setOnClickListener {
            //delete all done todos
            todoAdapter.todos.forEach { todo ->
                if (todo.isDone) {
                    database.child("Todos").child(todo.id).removeValue()
                }
            }

            todoAdapter.deleteDoneTodos()
        }
    }

}