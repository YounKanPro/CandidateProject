package com.example.candidateproject.features.imageDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.candidateproject.R
import com.example.candidateproject.base.BaseInterfaceUI
import com.example.candidateproject.databinding.FragmentImageDetailBinding
import com.example.candidateproject.databinding.FragmentImageListBinding
import com.example.candidateproject.features.imageList.ImageAdapter
import com.example.candidateproject.features.imageList.ImageListViewModel
import com.example.candidateproject.helper.EndlessRecyclerViewScrollListener
import com.example.candidateproject.services.responds.ImageListRespond

class ImageDetailFragment : Fragment(), BaseInterfaceUI {

    private val viewModel: ImageDetailViewModel by lazy {
        ViewModelProvider(this)[ImageDetailViewModel::class.java]
    }

    private val binding: FragmentImageDetailBinding by lazy { FragmentImageDetailBinding.inflate(layoutInflater) }

    var imageDetail: ImageListRespond? = null

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
        imageDetail = arguments?.get("detail") as ImageListRespond
        binding.authorTv.text = "Author : ${imageDetail?.author}"
        binding.urlTv.text = "URL : ${imageDetail?.url}"
        binding.widthTv.text = "real width : ${imageDetail?.width}"
        binding.heightTv.text = "real height : ${imageDetail?.height}"
        loadImage()
    }

    override fun initViewModel() {
    }

    override fun initControl() {
        binding.backBtn.setOnClickListener { activity?.onBackPressed() }

        binding.toggleButton.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if(isChecked){
                when(checkedId) {
                    R.id.normalBtn -> {
                        loadImage()
                    }
                    R.id.blurBtn -> {
                        loadImage("blur")
                    }
                    R.id.grayscaleBtn -> {
                        loadImage("grayscale")
                    }
                }
            }
        }
    }

    fun loadImage(mode: String = ""){
        context?.let {
            Glide.with(it)
                .load(viewModel.getImageUrlMode(imageDetail ?: ImageListRespond(), mode))
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fitCenter()
                .into(binding.iv)
        }

    }
}