package com.zenbyte.studio.wavesphere.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.zenbyte.studio.wavesphere.R

@Composable
fun adjustedFontSize(baseSize: Float): TextUnit {
    val density = LocalDensity.current

    return with(density) {
        if (density.density > 2f) {
            (baseSize + 2).sp
        } else {
            baseSize.sp
        }
    }
}


@Composable
fun getMaterialTypography(): Typography {

    val poppinsRegular = FontFamily(
        androidx.compose.ui.text.font.Font(
            R.font.poppins_regular,
            weight = FontWeight.Normal
        )
    )

    val typography = Typography(
        bodyLarge = TextStyle(fontFamily = poppinsRegular)
    )


    return Typography(

        bodyLarge = TextStyle(
            fontFamily = poppinsRegular,
            fontWeight = FontWeight.Bold,
            fontSize = adjustedFontSize(16.0f),
            lineHeight = adjustedFontSize(24.0f),
            //letterSpacing = 0.5.sp,
            color = MaterialTheme.colorScheme.onPrimary
        ),

        bodyMedium = TextStyle(
            fontFamily = poppinsRegular,
            fontWeight = FontWeight.Normal,
            fontSize = adjustedFontSize(14.0f),
            lineHeight = adjustedFontSize(20.0f),
            //letterSpacing = 0.15.sp,
            color = MaterialTheme.colorScheme.onPrimary
        ),

        bodySmall = TextStyle(
            fontFamily = poppinsRegular,
            fontWeight = FontWeight.Normal,
            fontSize = adjustedFontSize(12.0f),
            lineHeight = adjustedFontSize(16.0f),
            //letterSpacing = 0.10.sp,
            color = MaterialTheme.colorScheme.onPrimary
        ),

        titleMedium = TextStyle(
            fontFamily = poppinsRegular,
            fontWeight = FontWeight.Bold,
            fontSize = adjustedFontSize(18f),
            lineHeight = adjustedFontSize(24f),
            //letterSpacing = 0.15.sp,
            color = MaterialTheme.colorScheme.onPrimary,
        ),

        titleLarge = TextStyle(
            fontFamily = poppinsRegular,
            fontWeight = FontWeight.Bold,
            fontSize = adjustedFontSize(24f),
            lineHeight = adjustedFontSize(30f),
            //letterSpacing = 0.15.sp,
            color = MaterialTheme.colorScheme.onPrimary
        ),


        )
}