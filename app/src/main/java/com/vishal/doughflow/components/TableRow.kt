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
fun TableRow(
    modifier: Modifier = Modifier,
    label: String? = null,
    hasArrow: Boolean = false,
    isDestructive: Boolean = false,
    detailContent: (@Composable RowScope.() -> Unit)? = null,
    content: (@Composable RowScope.() -> Unit)? = null
){
    val textColor = if (isDestructive) Destructive else TextPrimary

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ){
        if (label != null) {
            Text(
                text = label,
                style = Typography.bodyMedium,
                color = textColor,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
        if (content != null) {
            content()
        }
        if(hasArrow) {
            Icon(
                painterResource(id = R.drawable.chevron_right),
                contentDescription = "Right Arrow",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
        if(detailContent != null){
            detailContent()
        }
    }

}