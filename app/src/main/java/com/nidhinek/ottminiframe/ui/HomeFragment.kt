package com.nidhinek.ottminiframe.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nidhinek.ottminiframe.data.BaseModel
import com.nidhinek.ottminiframe.data.ImdbSearchResponse
import com.nidhinek.ottminiframe.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import net.simplifiedcoding.data.network.Resource

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
        const val CAROUSAL = "Movies"
        const val GRID = "Now showing"
    }

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: HomeFragmentBinding
    private var homeAdapter: HomeAdapter? = null
    val playList: ArrayList<BaseModel> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        initPlayListRecyclerAdapter(onClicked = { mediaList, position ->
            {
                Log.d("clicked", "clicked")
            }
        })
        return binding.root
    }

    private fun initPlayListRecyclerAdapter(onClicked: (ArrayList<BaseModel>?, Int) -> Unit) {
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding?.recyclerView.layoutManager = layoutManager
        homeAdapter = HomeAdapter(onClicked)
        binding.recyclerView?.adapter = homeAdapter
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val itemPosition = layoutManager.findFirstVisibleItemPosition()
                Log.d("pos", itemPosition.toString())
                if (itemPosition >= 0 && itemPosition < playList.size) {
                    val title = playList.get(itemPosition).railViewTitle
                    Log.d("title", title.toString())
                    title?.let { viewModel.setToolbarTitle(it) }
                }
            }
        })
    }

    private fun addToPlaylist(playList: List<BaseModel>) {
        this.playList.addAll(playList)
        homeAdapter?.addToPlaylist(playList)
    }

    private fun release() {
        homeAdapter?.clearList()
        homeAdapter = null
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getMovies("drama")
        viewModel.searchResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    Log.d("success", "success")
                    var data = (it.value as? ImdbSearchResponse)
                    var uiComponentRails = arrayListOf<BaseModel>()
                    val dataLayoutSet1 = data?.copy(railTitle = CAROUSAL)
                    dataLayoutSet1?.let { it1 -> uiComponentRails.add(it1) }
                    val dataLayoutSet2 = data?.copy(railTitle = GRID)
                    dataLayoutSet2?.let { it1 ->
                        uiComponentRails.add(it1)
                        uiComponentRails.add(it1)
                    }
                    addToPlaylist(uiComponentRails)
                }
                is Resource.Loading -> {
                    // handle progress
                }
                is Resource.Failure -> {
                    // handleApiError
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        release()
    }
}
