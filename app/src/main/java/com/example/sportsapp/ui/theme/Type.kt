package com.example.sportsapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sportsapp.R

val ttNormsFamily = FontFamily(
    Font(R.font.ttnorms_medium, FontWeight.Normal),
    Font(R.font.ttnorms_bold, FontWeight.Bold)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = ttNormsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h2 = TextStyle(
        fontFamily = ttNormsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    h3 = TextStyle(
        fontFamily = ttNormsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    body1 = TextStyle(
        fontFamily = ttNormsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)