package com.example.mytodoapp


import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.cb_todo_check
import kotlinx.android.synthetic.main.item_todo.view.tv_todo_title


class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isDone
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tv_todo_title: TextView, cb_todo_check: Boolean) {
        if (cb_todo_check) {
            tv_todo_title.paintFlags = tv_todo_title.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tv_todo_title.paintFlags = tv_todo_title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tv_todo_title.text = curTodo.title
            cb_todo_check.isChecked = curTodo.isDone
            toggleStrikeThrough(tv_todo_title, curTodo.isDone)
            cb_todo_check.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tv_todo_title, isChecked)
                curTodo.isDone = !curTodo.isDone
            }
        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }
}