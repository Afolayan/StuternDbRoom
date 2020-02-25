package com.afolayanseyi.stutern

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afolayanseyi.stutern.data.User

class UserRecyclerAdapter(private val context: Context,
                          private val userList: List<User>,
                          private val onItemClick: ((User) -> Unit)) :
    RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFirstName: TextView? = null
        var tvLastName: TextView? = null

        init {
            tvFirstName = itemView.findViewById(R.id.firstName)
            tvLastName = itemView.findViewById(R.id.lastName)
        }

        fun bind(user: User){
            tvFirstName?.text = user.firstName
            tvLastName?.text = user.lastName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_single_user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onItemClick.invoke(user)
        }
    }
}