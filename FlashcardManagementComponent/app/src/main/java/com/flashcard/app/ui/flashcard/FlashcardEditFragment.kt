package com.flashcard.app.ui.flashcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flashcard.app.databinding.FragmentFlashcardEditBinding
import com.google.android.material.snackbar.Snackbar

class FlashcardEditFragment : Fragment() {
    private var _binding: FragmentFlashcardEditBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FlashcardEditViewModel by viewModels()
    private val args: FlashcardEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlashcardEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInputListeners()
        setupObservers()
        setupSaveButton()

        // If editing existing flashcard, load it
        args.flashcard?.let { flashcard ->
            viewModel.setFlashcard(flashcard)
        }
    }

    private fun setupInputListeners() {
        binding.questionEditText.doAfterTextChanged { text ->
            viewModel.setQuestion(text?.toString() ?: "")
        }

        binding.answerEditText.doAfterTextChanged { text ->
            viewModel.setAnswer(text?.toString() ?: "")
        }
    }

    private fun setupObservers() {
        viewModel.question.observe(viewLifecycleOwner) { question ->
            if (binding.questionEditText.text?.toString() != question) {
                binding.questionEditText.setText(question)
            }
        }

        viewModel.answer.observe(viewLifecycleOwner) { answer ->
            if (binding.answerEditText.text?.toString() != answer) {
                binding.answerEditText.setText(answer)
            }
        }

        viewModel.questionError.observe(viewLifecycleOwner) { error ->
            binding.questionInputLayout.error = error
        }

        viewModel.answerError.observe(viewLifecycleOwner) { error ->
            binding.answerInputLayout.error = error
        }

        viewModel.saveEnabled.observe(viewLifecycleOwner) { enabled ->
            binding.saveFab.isEnabled = enabled
        }
    }

    private fun setupSaveButton() {
        binding.saveFab.setOnClickListener {
            try {
                val flashcard = viewModel.createOrUpdateFlashcard()
                // TODO: Save flashcard to repository
                findNavController().navigateUp()
            } catch (e: Exception) {
                Snackbar.make(binding.root, "Failed to save flashcard", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}