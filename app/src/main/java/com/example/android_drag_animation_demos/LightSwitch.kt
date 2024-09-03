package com.example.android_drag_animation_demos

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LightSwitch(){

    val geoConfig = LocalConfiguration.current


    //Gets the density of the screen
    val d = LocalDensity.current

    var offsetY by remember { mutableStateOf(600f) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box (contentAlignment = Alignment.Center){



            Box(
                modifier = Modifier
                    .height((geoConfig.screenHeightDp / 2).dp)
                    .width(10.dp)
                    .background(Color.Black, RoundedCornerShape(10.dp)),
            )



            Box(
                modifier = Modifier
                    .offset(y = (offsetY / d.density).dp)

                    .height(70.dp)
                    .width(250.dp)
                    .background(Color.Red, RoundedCornerShape(10.dp))
                    .pointerInput(Unit){
                        detectDragGestures { change, dragAmount ->
                            change.consume()

                            //Update the offset values
                            offsetY += dragAmount.y
                            Log.d("OffsetY", "OffsetY Change: $offsetY")

                            Log.d("OffsetY", "Offset Max Amount Change: ${(offsetY / d.density).dp}")

                            //if the offset starts to reach to much, stop it
                            if((offsetY / d.density).dp < -(500 / d.density).dp){
                                //stop the offset

                                offsetY = -(500 / d.density).dp.toPx()

                            }

                            if((offsetY / d.density).dp > (500 / d.density).dp){
                                //stop the offset

                                offsetY = (500 / d.density).dp.toPx()

                            }


                        }
                    }
                    .shadow(70.dp),
                contentAlignment = Alignment.Center


            ){
                Text(text = "Power",
                    color = Color.White,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,

                )
            }













        }


    }

}



@Preview(showBackground = true)
@Composable
fun LightSwitchPreview() {
    LightSwitch()
}