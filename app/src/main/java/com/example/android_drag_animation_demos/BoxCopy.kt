package com.example.android_drag_animation_demos

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.ShortcutInfoCompat.Surface
import com.example.android_drag_animation_demos.ui.theme.Android_Drag_Animation_DemosTheme


@Composable
fun BoxCopy(){

    //Box that contains the draggables and the screen
    //This box is the background box

    //Gets the density of the screen
    val d = LocalDensity.current

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)

    ){


        //Box the user will drag on
        Box(
            modifier = Modifier
                //Offset modifier controlls the movement of the box
                .offset(
                     (offsetX / d.density).dp,
                    (offsetY / d.density).dp
                )

                .size(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Red)
                .pointerInput(Unit){
                    detectDragGestures { change, dragAmount ->
                        change.consume()

                        //Update the offset values
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                        Log.d("OffsetY", "OffsetY Change: $offsetY")
                        Log.d("OffsetX", "OffsetX Change: $offsetX")

                    }
                }
            ,
            contentAlignment = Alignment.Center
        ){

            Text(text = "Dragster",
                color = Color.White
            )

        }



    }




}



@Preview(showBackground = true)
@Composable
fun BoxDemoPreview() {
        BoxCopy()
}