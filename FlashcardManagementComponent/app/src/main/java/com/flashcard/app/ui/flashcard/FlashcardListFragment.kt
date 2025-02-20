package com.flashcard.app.ui.flashcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.flashcard.app.R
import com.flashcard.app.databinding.FragmentFlashcardListBinding

class FlashcardListFragment : Fragment() {
    private var _binding: FragmentFlashcardListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FlashcardListViewModel by viewModels()
    private lateinit var flashcardAdapter: FlashcardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlashcardListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        flashcardAdapter = FlashcardAdapter { flashcard ->
            // Handle flashcard click
            // TODO: Navigate to flashcard detail/edit screen
        }
        binding.flashcardRecyclerView.apply {
            adapter = flashcardAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupObservers() {
        viewModel.flashcards.observe(viewLifecycleOwner) { flashcards ->
            flashcardAdapter.submitList(flashcards)
            binding.emptyStateTextView.visibility = 
                if (flashcards.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.loadingProgressBar.visibility = 
                if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun setupClickListeners() {
        binding.addFlashcardFab.setOnClickListener {
            // TODO: Navigate to add flashcard screen
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}