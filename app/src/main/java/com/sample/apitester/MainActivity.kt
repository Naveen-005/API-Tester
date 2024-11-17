package com.sample.apitester

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.apitester.ui.theme.APITesterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APITesterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ApiDetails()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ApiDetails( modifier: Modifier = Modifier) {

    var apiUrl by remember { mutableStateOf("") }
    var methodSelected by remember { mutableStateOf("") }
    var mExpanded by remember { mutableStateOf(false) }
    val icon=if(mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    var keyList = remember { mutableStateListOf("") }
    var valueList = remember { mutableStateListOf("") }


    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 15.dp, top = 65.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)) {

        //Spacer(modifier = Modifier.height(60.dp))

        OutlinedTextField(
            value = apiUrl,
            onValueChange = { apiUrl = it },
            label = { Text("API URL:") }
        )

        OutlinedTextField(
            value = methodSelected,
            onValueChange = { methodSelected = it },
            readOnly = true,
            label = { Text("Method:") },
            trailingIcon = {
                Icon(icon,"dropdown",
                    Modifier.clickable { mExpanded=!mExpanded })
            },
            modifier=Modifier.clickable { mExpanded=!mExpanded },



        )

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false }
        ) {
            DropdownMenuItem(text = { Text(text = "GET") }, onClick = { methodSelected="GET"
                mExpanded=false})
            DropdownMenuItem(text = { Text(text = "POST")}, onClick = { methodSelected="POST"
                mExpanded=false})

        }

        Row {
            Text(text = "Data",modifier = Modifier.fillMaxWidth(0.85f))

            Icon(Icons.Filled.Add, contentDescription = "Add new data",
                modifier=Modifier
                    .clickable { keyList.add("")
                        valueList.add("")}

            )
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {

            items(keyList.size){ i->

                Row(modifier=Modifier.padding(end = 25.dp)) {
                    OutlinedTextField(
                        value = keyList[i],
                        onValueChange = { keyList[i] = it },
                        label = { Text("Key:") },
                        modifier = Modifier.width(80.dp)
                    )

                    OutlinedTextField(
                        value = valueList[i],
                        onValueChange = { valueList[i] = it },
                        label = { Text("Value:") }
                    )
                }

            }

            item {
                Button(onClick = { println("Send") }, modifier = Modifier.padding(top = 10.dp, bottom = 30.dp)) {
                    Text(text = "Submit")
                }
            }



        }
        

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    APITesterTheme {
        Greeting("Android")
    }
}