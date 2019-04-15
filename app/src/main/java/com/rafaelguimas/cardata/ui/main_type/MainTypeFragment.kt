package com.rafaelguimas.cardata.ui.main_type

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
import kotlinx.android.synthetic.main.main_type_fragment.*
import org.jetbrains.anko.design.longSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainTypeFragment : Fragment() {

    private val args: MainTypeFragmentArgs by navArgs()
    private val viewModel: MainTypeViewModel by viewModel()

    private val adapter = SimpleTextListAdapter {
        findNavController().navigate(MainTypeFragmentDirections.actionMainTypeFragmentToBuiltDateFragment(args.manufacturerId, it.first, args.manufacturerValue))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_type_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMainType.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(tbMainType)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.saveArgs(args)
        viewModel.getMainTypes()

        viewModel.progressLiveData.observe(this, Observer {
            pbMainType.changeVisibility(it)
            rvMainType.changeVisibility(!it)
        })
        viewModel.failureLiveData.observe(this, Observer {
            view?.longSnackbar(getString(R.string.error_generic))
        })
        viewModel.mainTypeModelLiveData.observe(this, Observer {
            adapter.updateContent(it.wkda)
        })
        viewModel.manufacturerLiveData.observe(this, Observer {
            tvMainTypeManufacturer.text = getString(R.string.label_manufacturer_param, it)
        })
    }

}
