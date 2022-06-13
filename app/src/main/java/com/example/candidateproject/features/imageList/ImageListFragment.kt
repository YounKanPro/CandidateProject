package com.example.candidateproject.features.imageList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.candidateproject.R
import com.example.candidateproject.base.BaseInterfaceUI
import com.example.candidateproject.base.RecyclerInterface
import com.example.candidateproject.databinding.FragmentImageListBinding
import com.example.candidateproject.helper.EndlessRecyclerViewScrollListener
import com.example.candidateproject.services.responds.ImageListRespond

class ImageListFragment : Fragment(), BaseInterfaceUI {

    private val viewModel: ImageListViewModel by lazy {
        ViewModelProvider(this)[ImageListViewModel::class.java]
    }

    private val binding: FragmentImageListBinding by lazy { FragmentImageListBinding.inflate(layoutInflater) }

    private val imageAdapter = ImageAdapter()
    private var imageLoadMoreListener : EndlessRecyclerViewScrollListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewData()
        initViewModel()
        initControl()
    }
    override fun initViewData() {
        viewModel.getImageList(1)
    }

    override fun initViewModel() {
        viewModel.imageListData.observe(viewLifecycleOwner, Observer {
            imageAdapter.list = it
            hideLoading()
        })
    }

    override fun initControl() {
        val loadMoreLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        imageLoadMoreListener = object : EndlessRecyclerViewScrollListener(loadMoreLayoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                showLoading()
                viewModel.getImageList(page + 1)
            }
        }

        imageAdapter.apply {
            mContext = context
            listener = object : RecyclerInterface<ImageListRespond> {
                override fun onItemClick(data: ImageListRespond?) {
                    navigateToImageDetail(data)
                }
            }
        }

        binding.imageRv.apply {
            imageLoadMoreListener?.let {
                adapter = imageAdapter
                layoutManager = loadMoreLayoutManager
                addOnScrollListener(it)
            }

        }
    }

    fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun navigateToImageDetail(data: ImageListRespond?) {
        val bundle = Bundle()
        bundle.putParcelable("detail", data)
        view?.findNavController()?.navigate(R.id.action_imageListFragment_to_imageDetailFragment, bundle)
    }

}