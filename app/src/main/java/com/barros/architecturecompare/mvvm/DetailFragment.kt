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

    private lateinit var item: RedditItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            item = DetailFragmentArgs.fromBundle(it).item
        }

        val detailViewModelFactory = DetailViewModelFactory(item)
        val viewModel =
            ViewModelProvider(this, detailViewModelFactory).get(DetailViewModel::class.java)

        return FragmentDetailBinding.inflate(inflater).apply {
            fragmentViewModel = viewModel
            lifecycleOwner = this@DetailFragment
        }.root
    }
}
