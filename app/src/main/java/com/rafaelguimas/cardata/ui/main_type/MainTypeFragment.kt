package com.rafaelguimas.cardata.ui.main_type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rafaelguimas.cardata.R
import com.rafaelguimas.cardata.extension.hide
import com.rafaelguimas.cardata.extension.show
import com.rafaelguimas.cardata.util.SimpleTextPagedListAdapter
import kotlinx.android.synthetic.main.main_type_fragment.*
import org.jetbrains.anko.design.longSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainTypeFragment : Fragment() {

    private val args: MainTypeFragmentArgs by navArgs()
    private val viewModel: MainTypeViewModel by viewModel()

    private val adapter = SimpleTextPagedListAdapter {
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

        tbMainType.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.saveArgs(args)
        viewModel.getMainTypes()

        viewModel.failureLiveData.observe(this, Observer {
            view?.longSnackbar(getString(R.string.error_generic))
        })
        viewModel.mainTypePagedListLiveData.observe(this, Observer {
            if (pbMainType.isVisible) {
                pbMainType.hide()
                rvMainType.show()
            }

            adapter.submitList(it)
        })
        viewModel.manufacturerLiveData.observe(this, Observer {
            tvMainTypeManufacturer.text = getString(R.string.label_manufacturer_param, it)
        })
    }

}
