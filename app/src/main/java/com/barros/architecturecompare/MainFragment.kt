package com.barros.architecturecompare

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.barros.architecturecompare.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.mvcButton.setOnClickListener { view ->
            hideKeyBoard(view)
            view.findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToMvcFragment(binding.searchText.text.toString()))
        }

        binding.mvpButton.setOnClickListener { view ->
            hideKeyBoard(view)
            view.findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToMvpFragment(binding.searchText.text.toString()))
        }

        binding.mvvmButton.setOnClickListener { view ->
            hideKeyBoard(view)
            view.findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToMvvmFragment(binding.searchText.text.toString()))
        }

        binding.mviButton.setOnClickListener { view ->
            hideKeyBoard(view)
            view.findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToMviFragment(binding.searchText.text.toString()))
        }

        return binding.root
    }

    private fun hideKeyBoard(view: View) {
        view.clearFocus()
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
