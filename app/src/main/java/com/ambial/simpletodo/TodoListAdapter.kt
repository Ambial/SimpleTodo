package com.ambial.simpletodo

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter(
    private val listOfTodos: MutableList<TodoListItem>
) : RecyclerView.Adapter<TodoListAdapter.TodoItemViewHolder>() {
    inner class TodoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTodo: TextView = itemView.findViewById(R.id.tv_todo_item)
        val checkBox: CheckBox = itemView.findViewById(R.id.cb_todo_item_done)
    }

    fun addTodoItem(item: TodoListItem) {
        listOfTodos.add(item)
        notifyItemInserted(listOfTodos.size - 1)
    }

    fun deleteDoneEntries() {
        listOfTodos.removeAll { item -> item.done }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        //parent = the recView, context = MainActivity
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.todo_item,
            parent,
            false
        )
        return TodoItemViewHolder(itemView)
    }

    fun toggleStrikeThrough(tvTodo: TextView, isDone: Boolean) {
        if (isDone) {
            tvTodo.paintFlags = tvTodo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodo.paintFlags = tvTodo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    //called over and over again, when items are filled with data or item is updated with new data
    //performance-critical, can be called many times per second (ie fast scrolling)
    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val currentItem = listOfTodos[position]

        holder.tvTodo.text = currentItem.text
        holder.checkBox.isChecked = currentItem.done

        toggleStrikeThrough(holder.tvTodo, currentItem.done)
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(tvTodo = holder.tvTodo, isChecked)
            currentItem.done = !currentItem.done
        }
    }

    override fun getItemCount(): Int {
        return listOfTodos.size
    }
}