package com.rafaelguimas.cardata.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rafaelguimas.cardata.R
import kotlinx.android.synthetic.main.summary_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SummaryFragment : Fragment() {

    private val args: SummaryFragmentArgs by navArgs()
    private val viewModel: SummaryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.summary_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btSummaryReset.setOnClickListener {
            findNavController().navigate(SummaryFragmentDirections.actionSummaryFragmentToManufacturerFragment())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.saveArgs(args)

        viewModel.manufacturerLiveData.observe(this, Observer {
            tvSummaryManufacturer.text = getString(R.string.label_manufacturer_param, it)
        })
        viewModel.mainTypeLiveData.observe(this, Observer {
            tvSummaryMainType.text = getString(R.string.label_main_type_param, it)
        })
        viewModel.builtDateLiveData.observe(this, Observer {
            tvSummaryBuiltDate.text = getString(R.string.label_built_date_param, it)
        })
    }

}
