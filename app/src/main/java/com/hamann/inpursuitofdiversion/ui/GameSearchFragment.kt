package com.hamann.inpursuitofdiversion.ui

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamann.inpursuitofdiversion.DiversionApp
import com.hamann.inpursuitofdiversion.R
import com.hamann.inpursuitofdiversion.databinding.FragmentSearchBinding
import com.hamann.inpursuitofdiversion.di.ViewModelFactory
import com.hamann.inpursuitofdiversion.models.Status
import com.hamann.inpursuitofdiversion.ui.adapters.GameAdapter
import com.hamann.inpursuitofdiversion.viewmodels.GameSearchViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameSearchFragment: Fragment() {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: GameSearchViewModel

    private lateinit var binding: FragmentSearchBinding

    private val adapter = GameAdapter { gameGuid ->
        findNavController()
            .navigate(R.id.action_home_to_game_detail, Bundle().also {
                it.putString("gameGuid", gameGuid)
            })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.games.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameSearchViewModel::class.java)
        observeData()
        setupSearch()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupSearch() {
        binding.searchView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                startSearch()
                true
            } else {
                false
            }
        }
        binding.searchView.setOnKeyListener { _, keyCode, action ->
            if (action.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                startSearch()
                true
            } else {
                false
            }
        }
    }

    private fun startSearch() {
        binding.searchView.text.trim().toString().let {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.fetchGames(it)
            }
        }
    }

    private fun observeData() {
        binding.games.adapter = adapter
        viewModel.games.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.ERROR -> {
                    Toast.makeText(requireContext(), "Something went wrong with the search: ${result.message}", Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    result.data?.let { games ->
                        adapter.setGames(games)
                    } ?: Toast.makeText(requireContext(), "Was expecting games but none were found...", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}