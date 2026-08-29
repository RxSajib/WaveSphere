package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R

@Composable
fun ContactGroup() {
    Column(modifier = Modifier.fillMaxWidth().border(
        width = 0.5.dp,
        color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f),
        shape = RoundedCornerShape(10.dp)
    ).padding(16.dp)) {
        Contact(
            icon = painterResource(R.drawable.icon_support),
            title = stringResource(R.string.contact_support),
            details = stringResource(R.string.contact_support_details),
            actionButtonTitle = stringResource(R.string.chat_now)
        )
        HeightGap(height = 10.dp)
        Contact(
            icon = painterResource(R.drawable.icon_email),
            title = stringResource(R.string.email_us),
            details = stringResource(R.string.email_us_details),
            actionButtonTitle = stringResource(R.string.send_email)
        )
    }
}

@Composable
@Preview
fun ContactGroupPreview() {
    ContactGroup()
}