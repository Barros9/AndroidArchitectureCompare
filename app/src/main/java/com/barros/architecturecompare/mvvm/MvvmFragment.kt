package com.barros.architecturecompare.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.barros.architecturecompare.R
import com.barros.architecturecompare.databinding.FragmentMvvmBinding
import com.barros.architecturecompare.utils.ItemGridAdapter

class MvvmFragment : Fragment() {

    private lateinit var viewModel: MvvmViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var search = ""
        arguments?.let {
            search = MvvmFragmentArgs.fromBundle(it).mvvmSearch
        }

        val viewModelFactory = ViewModelFactory(search)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MvvmViewModel::class.java)

        viewModel.navigateToItem.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController()
                    .navigate(MvvmFragmentDirections.actionMvvmFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        }

        viewModel.errorState.observe(viewLifecycleOwner) { error ->
            if (error) {
                Toast.makeText(context, getString(R.string.error_text), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return FragmentMvvmBinding.inflate(inflater).apply {
            lifecycleOwner = this@MvvmFragment
            mvvmViewModel = viewModel

            photosGrid.adapter = ItemGridAdapter(ItemGridAdapter.OnClickListener {
                viewModel.displayPropertyDetails(it)
            })
        }.root
    }
}
