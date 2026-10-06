package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.component.WidthGap
import com.zenbyte.studio.presentation.viewmodel.premium.PremiumViewModel
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar
import com.zenbyte.studio.presentation.ui.component.MyCustomButton
import com.zenbyte.studio.presentation.ui.component.PremiumBenefitsCard
import com.zenbyte.studio.presentation.ui.component.SubscriptionPlanCardItem

@Composable
fun PremiumScreen() {

    val viewModel: PremiumViewModel = hiltViewModel()
    val selectedItem = viewModel.selectedItem.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            MyCustomAppBar(
                title = stringResource(R.string.premium_lowercase),
                isPremiumEnable = false
            ) { }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize().background(color = MaterialTheme.colorScheme.surface)
                .padding(top = innerPadding.calculateTopPadding())
                .verticalScroll(state = rememberScrollState())
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            Text(
                text = stringResource(R.string.go_premium),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            HeightGap(height = 5.dp)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(R.string.premium_details),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.fillMaxWidth(.6f),
                    textAlign = TextAlign.Center
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(R.drawable.premum_crown), contentDescription = null,
                    modifier = Modifier.fillMaxWidth(.8f)
                )
            }

            PremiumBenefitsCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            HeightGap(height = 20.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                SubscriptionPlanCardItem(
                    modifier = Modifier.weight(1f),
                    price = "1.99",
                    duration = stringResource(R.string.monthly),
                    savePercentage = "35",
                    isDurationSelected = false,
                    note = if (selectedItem.value == 0) stringResource(R.string.saving) else null,
                    isSelected = selectedItem.value == 0,
                    title = stringResource(R.string.month),
                    onClickListener = {
                        viewModel.onSelectedItemChanged(0)
                    }
                )

                WidthGap(width = 20.dp)

                SubscriptionPlanCardItem(
                    modifier = Modifier.weight(1f),
                    price = "14.99",
                    duration = stringResource(R.string.yearly),
                    savePercentage = "35",
                    title = stringResource(R.string.year),
                    note = if (selectedItem.value == 1) stringResource(R.string.best_value) else null,
                    isDurationSelected = false,
                    isSelected = selectedItem.value == 1,
                    onClickListener = {
                        viewModel.onSelectedItemChanged(1)
                    }
                )

            }

            HeightGap(height = 20.dp)
            MyCustomButton(
                title = stringResource(R.string.start_premium),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClickButton = {},
            )
            HeightGap(height = 20.dp)
        }
    }
    //  }
}


@Composable
@Preview
fun PremiumScreenPreview() {
    PremiumScreen()
}