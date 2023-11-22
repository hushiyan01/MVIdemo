package com.myworkshop.myapplication.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myworkshop.myapplication.model.Item
import com.myworkshop.myapplication.view_model.ItemViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(viewModel: ItemViewModel, navController: NavController) {

    val context = LocalContext.current
    var item = viewModel.updateItem.observeAsState()
    var name by remember { mutableStateOf(item.value?.name) }
    var description by remember { mutableStateOf(item.value?.description) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Column {
            TextField(
                value = name?:"",
                onValueChange = { name = it },
                label = { Text(text = "Item Name") })
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = description?:"",
                onValueChange = { description = it },
                label = { Text(text = "Item Description") })
            Spacer(modifier = Modifier.height(50.dp))
            Button(onClick = {
                if(item.value==null){
                    if(name !=null  && description!=null){
                        viewModel.triggerAction(
                            ItemViewModel.Action.AddingItem(Item(name = name!!, description = description!!))
                        )
                        navController.navigate("MainScreen")
                    }else{
                        Toast.makeText(context, "Please complete both blanks", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    if(name !=null  && description!=null){
                        viewModel.triggerAction(
                           ItemViewModel.Action.UpdatingItem(Item(id = item.value!!.id, name = name!!, description = description!!))
                        )
                    }else{
                        Toast.makeText(context, "Please complete both blanks", Toast.LENGTH_SHORT).show()
                    }
                    viewModel.setUpdateItem(null)
                    navController.navigate("MainScreen")
                }


            }) {
                if(item.value == null){
                    Text(text = "Submit")
                }else{
                    Text(text = "Update")
                }

            }
        }
    }
}