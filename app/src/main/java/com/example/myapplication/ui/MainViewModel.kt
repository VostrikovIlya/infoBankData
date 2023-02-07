package com.example.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.BankService
import com.example.myapplication.data.Bank
import com.example.myapplication.data.Country
import com.example.myapplication.database.AppRoomDatabase
import com.example.myapplication.database.DataRoomDao
import com.example.myapplication.data.Data
import com.example.myapplication.data.Number
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bankService: BankService,
    database: AppRoomDatabase,
) : ViewModel() {

    private val dataDao: DataRoomDao = database.getRoomDao()

    private val _listData = MutableLiveData(emptyList<Data>())
    val listData: LiveData<List<Data>>
        get() = _listData

    private val _responseCode = MutableLiveData<Int>()
    val responseCode: LiveData<Int>
        get() = _responseCode

    init {
        viewModelScope.launch {
            _listData.value = dataDao.getAllData()
        }
    }

//    fun makeIntent(type: IntentHelper.Action, detail: String) {
//        when(type){
//            IntentHelper.Action.MAPS -> intentHelper.intentMap(detail)
//            IntentHelper.Action.PHONE -> intentHelper.intentDial(detail)
//            IntentHelper.Action.URL -> intentHelper.intentUrl(detail)
//        }
//    }

    fun findBin(bin: String) = viewModelScope.launch {
        if (bin.isNotEmpty()) {
            val response = bankService.getData(bin)
            _responseCode.value = response.code()
            if (response.isSuccessful) {
                val data = response.body()!!
                replaceNull(data)
                data.bin = bin
                dataDao.addData(data)
                _listData.value = _listData.value?.plus(data)
            }
        }
    }

    fun removeData(data: Data) = viewModelScope.launch {
        dataDao.removeData(data)
        _listData.value = _listData.value?.minus(data)
    }

    fun replaceNull(data: Data) {
        if (data.brand == null)
            data.brand = ""
        if (data.number == null)
            data.number = Number()
        if (data.number?.luhn == null)
            data.number?.luhn = false
        if (data.bank == null)
            data.bank = Bank()
        if (data.country == null)
            data.country = Country()
        if (data.prepaid == null)
            data.prepaid = false
        if (data.scheme == null)
            data.scheme = ""
    }
}