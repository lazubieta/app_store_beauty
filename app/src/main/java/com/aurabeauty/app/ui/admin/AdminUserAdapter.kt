package com.aurabeauty.app.ui.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aurabeauty.app.R
import com.aurabeauty.app.data.model.User
import com.aurabeauty.app.databinding.ItemAdminUserBinding

class AdminUserAdapter(
    private var users: List<User>,
    private val onEditUserClick: (User) -> Unit,
    private val onToggleBlockClick: (User) -> Unit
) : RecyclerView.Adapter<AdminUserAdapter.AdminUserViewHolder>() {

    inner class AdminUserViewHolder(private val binding: ItemAdminUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            val context = binding.root.context
            binding.tvAdminUserName.text = user.name
            binding.tvAdminUserRole.text = user.getDisplayRole()

            if (user.isBlocked()) {
                binding.tvAdminUserStatusBadge.text = context.getString(R.string.status_blocked)
                binding.tvAdminUserStatusBadge.background = ContextCompat.getDrawable(context, R.drawable.bg_badge_blocked)
                binding.tvAdminUserStatusBadge.setTextColor(ContextCompat.getColor(context, R.color.status_blocked_text))
            } else {
                binding.tvAdminUserStatusBadge.text = context.getString(R.string.status_active)
                binding.tvAdminUserStatusBadge.background = ContextCompat.getDrawable(context, R.drawable.bg_badge_active)
                binding.tvAdminUserStatusBadge.setTextColor(ContextCompat.getColor(context, R.color.status_active_text))
            }

            binding.btnAdminEditUser.setOnClickListener {
                onEditUserClick(user)
            }

            binding.btnAdminUserMore.setOnClickListener {
                onToggleBlockClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminUserViewHolder {
        val binding = ItemAdminUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AdminUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun updateList(newList: List<User>) {
        users = newList
        notifyDataSetChanged()
    }
}
