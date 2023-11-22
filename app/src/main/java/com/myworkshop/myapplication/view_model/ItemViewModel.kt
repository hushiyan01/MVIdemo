package com.myworkshop.myapplication.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myworkshop.myapplication.model.Item
import com.myworkshop.myapplication.model.LocalRepository
import com.myworkshop.myapplication.model.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val repository: LocalRepository) : ViewModel() {

    private val _itemsFlow: MutableStateFlow<ResultState<List<Item>>> =
        MutableStateFlow(ResultState.Loading())
    val items: StateFlow<ResultState<List<Item>>> = _itemsFlow

    private val _updateItem: MutableLiveData<Item> = MutableLiveData()
    val updateItem: LiveData<Item> = _updateItem

    fun setUpdateItem(item: Item?) {
        _updateItem.postValue(item)
    }

    private fun getAllItems() {
        _itemsFlow.value = ResultState.Loading()
        viewModelScope.launch {
            try {
                delay(1000)
                repository.getAllItems().collect {
                    _itemsFlow.value = ResultState.Success(it)
                }
            } catch (e: Exception) {
                _itemsFlow.value = ResultState.Error(e.toString())
            }
        }
    }

    private fun addItem(item: Item) {
        viewModelScope.launch {
            repository.insertItem(item)
        }
    }

    private fun updateItem(item: Item) {
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }


    fun triggerAction(action: Action) {
        when (action) {
            is Action.LoadingAllItems -> {
                getAllItems()
            }

            is Action.AddingItem -> {
                addItem(item = action.item)
            }

            is Action.UpdatingItem ->{
                updateItem(item = action.item)
            }
        }
    }

    sealed class Action {
        object LoadingAllItems : Action()
        class AddingItem(val item: Item) : Action()
        class UpdatingItem(val item: Item) : Action()
    }
}