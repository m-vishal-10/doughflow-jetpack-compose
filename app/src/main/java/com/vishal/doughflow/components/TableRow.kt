package com.vishal.doughflow.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vishal.doughflow.R
import com.vishal.doughflow.ui.theme.Destructive
import com.vishal.doughflow.ui.theme.TextPrimary
import com.vishal.doughflow.ui.theme.Typography

@Composable
fun TableRow(label: String, hasArrow: Boolean = false, isDestructive: Boolean = false){
    val textColor = if(isDestructive) Destructive else TextPrimary

    Row(
    modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 10.dp)
    ){
        Text(text = label, style = Typography.bodyMedium, color = textColor)
        Spacer(modifier = Modifier.weight(1f))
        if(hasArrow) {
            Icon(
                painterResource(id = R.drawable.chevron_right),
                contentDescription = "Right Arrow",
            )
        }
    }
}