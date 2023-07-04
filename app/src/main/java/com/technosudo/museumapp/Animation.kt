package com.technosudo.museumapp

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

@Composable
fun Animation() {

    var currentFrame = remember {
        mutableStateOf(150)
    }
    var isRunning = remember {
        mutableStateOf(false)
    }
    var isForward = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = currentFrame.value, key2 = isRunning.value) {
        delay(30)
        if(
            (currentFrame.value > 150 || isForward.value) &&
            (currentFrame.value < 180 || !isForward.value) &&
            isRunning.value
        ) {
            currentFrame.value = currentFrame.value +
                    if (isForward.value) 1 else -1

            if (
                currentFrame.value == 150 ||
                currentFrame.value == 180 ||
                currentFrame.value !in 150..180
            ) isRunning.value = false
        } else {
            isRunning.value = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painterResource(getFrame(LocalContext.current, currentFrame.value)),
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )
        CornerButton(
            "<",
            Modifier
                .size(100.dp, 150.dp)
                .align(Alignment.BottomStart)
                .clip(CutCornerShape(topEnd = 30.dp)),
            Color(0xFF2A2E32),
            onClick = {
                if (!isRunning.value) {
                    isRunning.value = true
                    isForward.value = false
                }
            }
        )
        CornerButton(
            ">",
            Modifier
                .size(100.dp, 150.dp)
                .align(Alignment.BottomEnd)
                .clip(CutCornerShape(topStart = 30.dp)),
            Color(0xFF2A2E32),
            onClick = {
                if (!isRunning.value) {
                    isRunning.value = true
                    isForward.value = true
                }
            }
        )
    }
}

@SuppressLint("DiscouragedApi")
fun getFrame(context: Context, id: Int): Int {
    return context.resources.getIdentifier(
        "xd0$id",
        "drawable",
        context.packageName
    )
}