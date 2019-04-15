package com.rafaelguimas.cardata.ui.built_date

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rafaelguimas.cardata.R
import com.rafaelguimas.cardata.extension.changeVisibility
import com.rafaelguimas.cardata.ui.SimpleTextListAdapter
import kotlinx.android.synthetic.main.built_date_fragment.*
import org.jetbrains.anko.design.longSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class BuiltDateFragment : Fragment() {

    private val args: BuiltDateFragmentArgs by navArgs()
    private val viewModel: BuiltDateViewModel by viewModel()

    private val adapter = SimpleTextListAdapter {
        findNavController().navigate(BuiltDateFragmentDirections.actionBuiltDateFragmentToSummaryFragment(args.manufacturerValue, it.second, args.mainType))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.built_date_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvBuiltDate.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(tbBuiltDate)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.saveArgs(args)
        viewModel.getBuildDates()

        viewModel.progressLiveData.observe(this, Observer {
            pbBuiltDate.changeVisibility(it)
            rvBuiltDate.changeVisibility(!it)
        })
        viewModel.failureLiveData.observe(this, Observer {
            view?.longSnackbar(getString(R.string.error_generic))
        })
        viewModel.builtDateModelLiveData.observe(this, Observer {
            adapter.updateContent(it.wkda)
        })
        viewModel.manufacturerLiveData.observe(this, Observer {
            tvBuiltDateManufacturer.text = getString(R.string.label_manufacturer_param, it)
        })
        viewModel.mainTypeLiveData.observe(this, Observer {
            tvBuiltDateMainType.text = getString(R.string.label_main_type_param, it)
        })
    }

}
