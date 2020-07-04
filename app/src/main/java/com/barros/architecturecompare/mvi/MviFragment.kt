package com.barros.architecturecompare.mvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.barros.architecturecompare.R
import com.barros.architecturecompare.databinding.FragmentMviBinding
import com.barros.architecturecompare.mvi.states.MviIntent
import com.barros.architecturecompare.mvi.states.MviState
import com.barros.architecturecompare.utils.ItemGridAdapter
import kotlinx.android.synthetic.main.fragment_mvi.*
import kotlinx.coroutines.launch

class MviFragment : Fragment(), com.barros.architecturecompare.mvi.interfaces.View<MviState> {

    private lateinit var viewModel: MviViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMviBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(MviViewModel::class.java)

        arguments?.let {
            viewModel.search.value = MviFragmentArgs.fromBundle(it).mviSearch
        }

        adapter = ItemGridAdapter(ItemGridAdapter.OnClickListener {
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
        })
        recyclerView = binding.photosGrid
        recyclerView.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner, Observer {
            render(it)
        })

        lifecycleScope.launch {
            viewModel.intents.send(MviIntent.FetchList)
        }

        binding.retryBtn.setOnClickListener {
            lifecycleScope.launch {
                viewModel.intents.send(MviIntent.RefreshList)
            }
        }

        return binding.root
    }

    override fun render(state: MviState) {
        with(state) {
            progress_bar.visibility = if (isLoading) View.VISIBLE else View.GONE

            retry_btn.visibility = if (isError) {
                Toast.makeText(context, getString(R.string.error_text), Toast.LENGTH_SHORT).show()
                View.VISIBLE
            } else {
                View.GONE
            }

            adapter.submitList(redditItemList)
            adapter.notifyDataSetChanged()
        }
    }
}
