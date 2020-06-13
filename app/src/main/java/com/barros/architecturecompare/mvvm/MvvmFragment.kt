package com.barros.architecturecompare.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.barros.architecturecompare.R
import com.barros.architecturecompare.databinding.FragmentMvvmBinding
import com.barros.architecturecompare.utils.ItemGridAdapter

class MvvmFragment : Fragment() {

    private lateinit var viewModel: MvvmViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var search = ""
        arguments?.let {
            search = MvvmFragmentArgs.fromBundle(it).mvvmSearch
        }

        val viewModelFactory = ViewModelFactory(search)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MvvmViewModel::class.java)

        return FragmentMvvmBinding.inflate(inflater).apply {
            mvvmViewModel = viewModel
            lifecycleOwner = this@MvvmFragment
            photosGrid.adapter = ItemGridAdapter()

            viewModel.errorState.observe(viewLifecycleOwner, Observer { error ->
                if (error) {
                    Toast.makeText(context, getString(R.string.error_text), Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }.root
    }
}
