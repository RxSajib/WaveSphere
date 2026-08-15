package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
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
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.viewmodel.utils.rememberDebouncedClick

@Composable
fun ServerError(onClick: () -> Unit){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            MyLottie(
                animationResId = R.raw.no_internet_connection,
                modifier = Modifier.size(150.dp)
            )

            HeightGap(height = 15.dp)
            Text(
                text = stringResource(R.string.oops),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = stringResource(R.string.something_went_wrong),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = adjustedFontSize(12f),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.W500
                )
            )

            Text(
                text = stringResource(R.string.something_went_wrong_details),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = adjustedFontSize(10f),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                ),
            )
            HeightGap(height = 15.dp)
            MyCustomButton(
                isEnable = true, title = stringResource(R.string.retry),
                leftIcon = painterResource(R.drawable.refresh_cw_alt_svgrepo_com),
                onClickButton = rememberDebouncedClick { 
                    onClick.invoke()
                }
            )
        }
    }
}

@Composable
@Preview
fun ServerErrorPreview(){
    ServerError(
        onClick = {

        }
    )
}