package com.aurabeauty.app.ui.buyer

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aurabeauty.app.data.model.ToneOption
import com.aurabeauty.app.databinding.ItemToneSelectorBinding

class ToneAdapter(
    private var tones: List<ToneOption>,
    private val onToneSelected: (ToneOption) -> Unit
) : RecyclerView.Adapter<ToneAdapter.ToneViewHolder>() {

    inner class ToneViewHolder(private val binding: ItemToneSelectorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tone: ToneOption) {
            // Fill background color
            val shape = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.parseColor(tone.colorHex))
            }
            binding.viewToneColor.background = shape

            // Selected ring visibility
            binding.viewToneSelectedRing.visibility = if (tone.isSelected) View.VISIBLE else View.INVISIBLE

            binding.root.setOnClickListener {
                tones.forEach { it.isSelected = false }
                tone.isSelected = true
                notifyDataSetChanged()
                onToneSelected(tone)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToneViewHolder {
        val binding = ItemToneSelectorBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ToneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToneViewHolder, position: Int) {
        holder.bind(tones[position])
    }

    override fun getItemCount(): Int = tones.size
}
