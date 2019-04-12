package com.rafaelguimas.cardata.ui.manufacturer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.rafaelguimas.cardata.R
import com.rafaelguimas.cardata.extension.changeVisibility
import com.rafaelguimas.cardata.ui.SimpleTextListAdapter
import kotlinx.android.synthetic.main.manufacturer_fragment.*
import org.jetbrains.anko.design.longSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManufacturerFragment : Fragment() {

    private val viewModel: ManufacturerViewModel by viewModel()

    private val adapter = SimpleTextListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.manufacturer_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvManufacturer.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.progressLiveData.observe(this, Observer {
            pbManufacturer.changeVisibility(it)
            rvManufacturer.changeVisibility(!it)
        })
        viewModel.failureLiveData.observe(this, Observer {
            view?.longSnackbar(getString(R.string.error_generic))
        })
        viewModel.manufacturerModelLiveData.observe(this, Observer {
            adapter.updateContent(it.wkda)
        })
    }

}
