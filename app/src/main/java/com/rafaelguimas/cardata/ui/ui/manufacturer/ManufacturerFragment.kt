package com.rafaelguimas.cardata.ui.ui.manufacturer

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafaelguimas.cardata.ui.R

class ManufacturerFragment : Fragment() {

    companion object {
        fun newInstance() = ManufacturerFragment()
    }

    private lateinit var viewModel: ManufacturerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.manufacturer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ManufacturerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
