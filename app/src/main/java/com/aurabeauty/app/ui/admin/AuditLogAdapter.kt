package com.aurabeauty.app.ui.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aurabeauty.app.R
import com.aurabeauty.app.data.model.AuditLog
import com.aurabeauty.app.databinding.ItemAuditLogBinding

class AuditLogAdapter(
    private var logs: List<AuditLog>
) : RecyclerView.Adapter<AuditLogAdapter.AuditLogViewHolder>() {

    inner class AuditLogViewHolder(private val binding: ItemAuditLogBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(log: AuditLog) {
            val context = binding.root.context
            binding.tvAuditTimestamp.text = log.timestamp
            binding.tvAuditActionTitle.text = log.actionTitle
            binding.tvAuditActorName.text = log.actorName
            binding.tvAuditActorRole.text = log.actorRole

            // Icon according to actionType
            val iconRes = when (log.actionType) {
                "blocked" -> R.drawable.ic_favorites
                "product_update" -> R.drawable.ic_edit
                "payment" -> R.drawable.ic_payments
                "new_user" -> R.drawable.ic_profile
                else -> R.drawable.ic_lock
            }
            binding.ivAuditActionIcon.setImageResource(iconRes)

            val tintColor = when (log.actionType) {
                "blocked" -> ContextCompat.getColor(context, R.color.status_blocked_text)
                "product_update" -> ContextCompat.getColor(context, R.color.secondary_mint_dark)
                "payment" -> ContextCompat.getColor(context, R.color.secondary_mint)
                else -> ContextCompat.getColor(context, R.color.primary)
            }
            binding.ivAuditActionIcon.setColorFilter(tintColor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuditLogViewHolder {
        val binding = ItemAuditLogBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AuditLogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AuditLogViewHolder, position: Int) {
        holder.bind(logs[position])
    }

    override fun getItemCount(): Int = logs.size

    fun updateList(newList: List<AuditLog>) {
        logs = newList
        notifyDataSetChanged()
    }
}
