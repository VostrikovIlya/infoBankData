package com.example.myapplication.ui

import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.Data

@Composable
fun MainScreen() {
    val viewModel = viewModel(modelClass = MainViewModel::class.java)
    val listCard by viewModel.listData.observeAsState(emptyList());
    val responseCode by viewModel.responseCode.observeAsState(200)

    Surface(modifier = Modifier.fillMaxWidth()) {
        Column() {
            Row() {
                val bin = remember { mutableStateOf("") }
                var isError = responseCode != 200
                TextField(
                    bin.value,
                    {
                        bin.value = it
                        isError = false
                    },
                    modifier = Modifier
                        .padding(5.dp)
                        .height(60.dp),
                    trailingIcon = {
                        if (isError)
                            Icon(
                                Icons.Filled.Warning,
                                "error",
                                tint = MaterialTheme.colors.error
                            )
                    },
                    isError = isError,
                    textStyle = TextStyle(fontSize = 18.sp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    shape = RoundedCornerShape(5.dp)
                )
                //"45717360"
                Button(
                    onClick = { viewModel.findBin(bin.value) },
                    modifier = Modifier
                        .padding(5.dp)
                        .height(60.dp)
                        .width(200.dp),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text("FIND", fontSize = 18.sp)
                }
            }

            if (listCard.isNotEmpty()) {
                PaintCards(listCard)
            }
        }
    }
}


@Composable
fun PaintCards(list: List<Data>) {
    LazyColumn() {
        items(list.asReversed()) { item ->
            InfoCard(data = item)
        }
    }
}

@Composable
fun InfoCard(data: Data) {
    val viewModel = viewModel(modelClass = MainViewModel::class.java)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "BIN/IIN: ${data.bin}", fontSize = 18.sp, modifier = Modifier
                        .padding(10.dp)
                )
                IconButton(
                    onClick = { viewModel.removeData(data) },
                ) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
            }
            Row() {
                Column(modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "SCHEME/NETWORK",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 5.dp, bottom = 1.dp, top = 5.dp)
                    )
                    Text(
                        text = "${data.scheme}",
                        modifier = Modifier.padding(start = 5.dp, bottom = 5.dp, top = 1.dp)
                    )

                    Text(
                        text = "BRAND",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 5.dp, bottom = 1.dp, top = 5.dp)
                    )

                    Text(
                        text = "${data.brand}",
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 5.dp,
                            top = 1.dp
                        )
                    )
                    Text(
                        text = "CARD NUMBER",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 1.dp,
                            top = 5.dp
                        )
                    )
                    Row() {
                        Column() {
                            Text(
                                text = "LENGTH",
                                fontSize = 10.sp,
                                modifier = Modifier.padding(
                                    start = 5.dp,
                                    end = 5.dp,
                                    bottom = 1.dp,
                                    top = 1.dp
                                ),
                            )
                            Text(
                                text = "${data.number?.length}",
                                modifier = Modifier.padding(
                                    start = 5.dp,
                                    end = 5.dp,
                                    bottom = 5.dp,
                                    top = 1.dp
                                )
                            )
                        }
                        Column() {
                            Text(
                                text = "LUHN",
                                fontSize = 10.sp,
                                modifier = Modifier.padding(
                                    start = 5.dp,
                                    end = 5.dp,
                                    bottom = 1.dp,
                                    top = 1.dp
                                ),
                            )
                            Text(
                                text = "${data.number?.luhn}",
                                modifier = Modifier.padding(
                                    start = 5.dp,
                                    end = 5.dp,
                                    bottom = 5.dp,
                                    top = 1.dp
                                )
                            )
                        }
                    }
                }
                Column(modifier = Modifier.padding(5.dp)) {
                    Text(
                        text = "TYPE",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 5.dp, bottom = 1.dp, top = 5.dp)
                    )
                    Text(
                        text = "${data.type}",
                        modifier = Modifier.padding(start = 5.dp, bottom = 5.dp, top = 1.dp)
                    )
                    Text(
                        text = "PREPAID",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 5.dp, bottom = 1.dp, top = 5.dp)
                    )
                    Text(
                        text = "${data.prepaid}",
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 5.dp,
                            top = 1.dp
                        )
                    )
                    Text(
                        text = "COUNTRY",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 1.dp,
                            top = 5.dp
                        )
                    )
                    Text(
                        text = "${data.country?.emoji} ${data.country?.name}",
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 1.dp,
                            top = 1.dp
                        )
                    )
                    Text(
                        text = "(latitude: ${data.country?.latitude}, longitude: ${data.country?.longitude})",
                        fontSize = 9.sp,
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 5.dp,
                            top = 1.dp
                        )
                    )
                }
                Column(modifier = Modifier.padding(5.dp)) {
                    Text(
                        text = "BANK",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 5.dp, bottom = 1.dp, top = 5.dp)
                    )
                    Text(
                        text = "${data.bank?.name}  ${data.bank?.city}",
                        modifier = Modifier
                            .padding(start = 5.dp, bottom = 5.dp, top = 1.dp)
                    )

                    Text(
                        text = "${data.bank?.url}",
                        fontSize = 11.sp,
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 5.dp,
                            top = 1.dp
                        )
                    )


                    Text(
                        text = "${data.bank?.phone}",
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 5.dp,
                            top = 1.dp
                        )
                    )

                }
            }
        }
    }
}

