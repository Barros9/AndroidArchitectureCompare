package com.barros.architecturecompare.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.barros.architecturecompare.databinding.FragmentDetailBinding
import com.barros.architecturecompare.model.RedditItem

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var item: RedditItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            item = DetailFragmentArgs.fromBundle(it).item
        }

        val binding = FragmentDetailBinding.inflate(inflater)
        val detailViewModelFactory = DetailViewModelFactory(item)
        viewModel = ViewModelProvider(this, detailViewModelFactory).get(DetailViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}
