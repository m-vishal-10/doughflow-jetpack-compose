package com.vishal.doughflow.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vishal.doughflow.R
import com.vishal.doughflow.ui.theme.Destructive
import com.vishal.doughflow.ui.theme.TextPrimary
import com.vishal.doughflow.ui.theme.Typography

@Composable
fun TableRow(label: String, modifier: Modifier = Modifier, hasArrow: Boolean = false, isDestructive: Boolean = false, content: (@Composable RowScope.() -> Unit)? = null){
    val textColor = if(isDestructive) Destructive else TextPrimary


    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ){
        Text(text = label, style = Typography.bodyMedium, color = textColor, modifier = Modifier.padding( vertical = 10.dp))
        Spacer(modifier = Modifier.weight(1f))
        if(hasArrow) {
            Icon(
                painterResource(id = R.drawable.chevron_right),
                contentDescription = "Right Arrow",
                modifier = Modifier.padding( vertical = 10.dp)
            )
        }
        if(content != null){
            content()
        }
    }

}