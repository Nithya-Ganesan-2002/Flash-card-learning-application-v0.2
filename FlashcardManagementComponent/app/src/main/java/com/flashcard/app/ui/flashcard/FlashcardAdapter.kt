package com.flashcard.app.ui.flashcard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flashcard.app.data.model.Flashcard
import com.flashcard.app.databinding.ItemFlashcardBinding

class FlashcardAdapter(
    private val onFlashcardClick: (Flashcard) -> Unit
) : ListAdapter<Flashcard, FlashcardAdapter.FlashcardViewHolder>(FlashcardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashcardViewHolder {
        val binding = ItemFlashcardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FlashcardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlashcardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FlashcardViewHolder(
        private val binding: ItemFlashcardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onFlashcardClick(getItem(position))
                }
            }
        }

        fun bind(flashcard: Flashcard) {
            binding.questionTextView.text = flashcard.question
            binding.answerTextView.text = flashcard.answer
        }
    }

    private class FlashcardDiffCallback : DiffUtil.ItemCallback<Flashcard>() {
        override fun areItemsTheSame(oldItem: Flashcard, newItem: Flashcard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Flashcard, newItem: Flashcard): Boolean {
            return oldItem == newItem
        }
    }
}