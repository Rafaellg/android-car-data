package com.rafaelguimas.cardata.ui.summary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class SummaryViewModel : ViewModel() {

    val manufacturerLiveData = MutableLiveData<String>()
    val mainTypeLiveData = MutableLiveData<String>()
    val builtDateLiveData = MutableLiveData<String>()

    fun saveArgs(args: SummaryFragmentArgs) {
        manufacturerLiveData.value = args.manufacturer
        mainTypeLiveData.value = args.mainType
        builtDateLiveData.value = args.builtDate
    }
}
