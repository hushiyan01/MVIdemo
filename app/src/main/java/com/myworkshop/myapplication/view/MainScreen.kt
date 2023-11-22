package com.myworkshop.myapplication.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myworkshop.myapplication.model.Item
import com.myworkshop.myapplication.model.ResultState
import com.myworkshop.myapplication.ui.theme.Purple80
import com.myworkshop.myapplication.ui.theme.PurpleGrey40
import com.myworkshop.myapplication.view_model.ItemViewModel

@Composable
fun MainScreen(viewModel: ItemViewModel,navController: NavController){
    val data = viewModel.items.collectAsState()
    LaunchedEffect(Unit){
        viewModel.triggerAction(action = ItemViewModel.Action.LoadingAllItems)
    }
    Box(
        Modifier
            .fillMaxSize()
            .padding(20.dp)) {
        FloatingActionButton(
            onClick = {
                navController.navigate("AddScreen")
            },
            Modifier.align(Alignment.BottomEnd),
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 6.dp,
                hoveredElevation = 10.dp
            )
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }

        when(val resultState = data.value ){
            is ResultState.Loading ->{
                CircularProgressIndicator(color = Purple80, strokeWidth = 8.dp, modifier = Modifier.align(
                    Alignment.Center))
            }
            is ResultState.Error -> {

            }
            is ResultState.Success ->{
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter)
                        .padding(20.dp)){
                    items(resultState.body){
                        ItemCard(item = it, modifier = Modifier.clickable{
                            viewModel.setUpdateItem(it)
                            navController.navigate("AddScreen")
                        })
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemCard(modifier: Modifier=Modifier,item: Item=Item(1,"test","description")){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Purple80,
            contentColor = PurpleGrey40
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(10.dp)) {
            Text(text = "id: ${item.id}", style = TextStyle(fontSize = 16.sp))
            Divider(Modifier.fillMaxWidth(), thickness = 2.dp, color = Color.Black)
            Text(text = "name: ${item.name}",style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "description: ${item.description}")
        }
    }
}