package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.ContactGroup
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.component.HelpPopularTopicGroup
import com.zenbyte.studio.presentation.ui.component.HelpSupportHeader
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar
import com.zenbyte.studio.presentation.ui.component.SupportHelpBanner
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.viewmodel.utils.debounceClickable

@Composable
fun HelpAndSupportScreen() {
    Scaffold(
        topBar = {
            MyCustomAppBar(
                title = stringResource(R.string.help_support)
            ) {

            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .verticalScroll(state = rememberScrollState())
                .padding(16.dp)
        ) {
            HelpSupportHeader()
            HeightGap(height = 20.dp)
            Text(
                text = stringResource(R.string.popular_topics),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = adjustedFontSize(14f),
                ),
                modifier = Modifier.fillMaxWidth()
            )
            HeightGap(height = 10.dp)
            HelpPopularTopicGroup()
            HeightGap(height = 20.dp)
            Text(
                text = stringResource(R.string.need_more_help),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = adjustedFontSize(14f),
                ),
                modifier = Modifier.fillMaxWidth()
            )
            HeightGap(height = 10.dp)
            ContactGroup(
                onClickContact = {},
                onClickEmail = {}
            )
            HeightGap(height = 10.dp)
            Box(modifier = Modifier.fillMaxWidth().border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f),
                shape = RoundedCornerShape(10.dp)
            ).clip(shape = RoundedCornerShape(10.dp)).debounceClickable{}.padding(16.dp)) {
                SupportHelpBanner()
            }

        }
    }
}