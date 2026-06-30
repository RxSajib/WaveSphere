package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MyCustomInputFiled(
    placeHolderText: String,
    text: String,
    onValueChange: (String) -> Unit,
    isPasswordVisibility: Boolean = false,
    readOnly: Boolean = false,
    isNumberType: Boolean = false,
    rightIcon: Painter? = null,
    isSearchEnable: Boolean = false,
    onSearch: ((String) -> Unit)? = null,
    modifier: Modifier,
    onClick: () -> Unit,

    ) {

    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isNumberType) KeyboardType.Number else KeyboardType.Text,
            imeAction = if (isSearchEnable) ImeAction.Search else ImeAction.Done
        ),
        enabled = !readOnly,
        readOnly = readOnly,
        textStyle = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.W500,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch?.invoke(text)
                keyboardController?.hide()
            }
        ),
        value = text,

        onValueChange = {  inputValue ->
            onValueChange.invoke(inputValue)
        },
        modifier = modifier.fillMaxWidth().clip(shape = CircleShape)
            .background(color = Color.Gray.copy(alpha = 0.1f))
            .clickable(readOnly) { onClick.invoke() },
        shape = CircleShape,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),


        trailingIcon = {
            rightIcon?.let { rightIcon ->
                Icon(
                    painter = rightIcon,
                    contentDescription = null
                )
            }
        },

        visualTransformation = if (!isPasswordVisibility) PasswordVisualTransformation() else VisualTransformation.None,

        placeholder = {
            Text(
                text = placeHolderText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
            )
        },
        maxLines = 1,
    )


}

