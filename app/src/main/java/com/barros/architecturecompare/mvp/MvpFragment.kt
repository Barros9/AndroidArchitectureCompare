package com.barros.architecturecompare.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.barros.architecturecompare.R
import com.barros.architecturecompare.model.RedditItem
import com.barros.architecturecompare.utils.ItemGridAdapter
import kotlinx.android.synthetic.main.fragment_mvp.*

class MvpFragment : Fragment(), MvpPresenter.View {

    private lateinit var presenter: MvpPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemGridAdapter
    private var listValues = mutableListOf<RedditItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var search = ""
        val view = inflater.inflate(R.layout.fragment_mvp, container, false)
        arguments?.let {
            search = MvpFragmentArgs.fromBundle(it).mvpSearch
        }
        presenter = MvpPresenter(this, search)
        adapter = ItemGridAdapter()
        recyclerView = view.findViewById(R.id.photos_grid)
        recyclerView.adapter = adapter

        view.findViewById<Button>(R.id.retry_btn).setOnClickListener {
            onRetry()
        }
        return view
    }

    override fun setValues(values: List<RedditItem>) {
        listValues.clear()
        listValues.addAll(values)
        adapter.submitList(listValues)
        adapter.notifyDataSetChanged()
        photos_grid.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
        retry_btn.visibility = View.GONE
    }

    override fun onError() {
        photos_grid.visibility = View.GONE
        progress_bar.visibility = View.GONE
        retry_btn.visibility = View.VISIBLE
        Toast.makeText(context, getString(R.string.error_text), Toast.LENGTH_SHORT)
            .show()
    }

    private fun onRetry() {
        presenter.onRefresh()
        photos_grid.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
        retry_btn.visibility = View.GONE
    }
}
