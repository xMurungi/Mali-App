package com.joses.mali.ui

import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier.imePadding(),
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier,
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = placeholder
            )
        },
//        colors = TextFieldColors(
//            focusedTextColor = MaterialTheme.colors.onBackground,
//            unfocusedTextColor = MaterialTheme.colors.onBackground,
//            disabledTextColor = Color.LightGray,
//            errorTextColor = Color.Red,
//            focusedContainerColor = Color.Transparent,
//            unfocusedContainerColor = Color.Transparent,
//            disabledContainerColor = Color.Gray,
//            errorContainerColor = Color.Red,
//            cursorColor = MaterialTheme.colors.onBackground,
//            errorCursorColor = Color.Red,
//            textSelectionColors = TextSelectionColors(
//                handleColor = Color.Blue,
//                backgroundColor = Color.Blue
//            ),
//            focusedIndicatorColor = MaterialTheme.colors.onBackground,
//            unfocusedIndicatorColor = Color.Gray,
//            disabledIndicatorColor = Color.LightGray,
//            errorIndicatorColor = Color.Red,
//            focusedLeadingIconColor = Color.Blue,
//            unfocusedLeadingIconColor = Color.Gray,
//            disabledLeadingIconColor = Color.LightGray,
//            errorLeadingIconColor = Color.Red,
//            focusedTrailingIconColor = Color.Green,
//            unfocusedTrailingIconColor = Color.Gray,
//            disabledTrailingIconColor = Color.LightGray,
//            errorTrailingIconColor = Color.Red,
//            focusedLabelColor = MaterialTheme.colors.onBackground,
//            unfocusedLabelColor = Color.Gray,
//            disabledLabelColor = Color.LightGray,
//            errorLabelColor = Color.Red,
//            focusedPlaceholderColor = MaterialTheme.colors.onBackground,
//            unfocusedPlaceholderColor = MaterialTheme.colors.onBackground,
//            disabledPlaceholderColor = Color.LightGray,
//            errorPlaceholderColor = Color.Red,
//            focusedSupportingTextColor = MaterialTheme.colors.onBackground,
//            unfocusedSupportingTextColor = Color.Gray,
//            disabledSupportingTextColor = Color.LightGray,
//            errorSupportingTextColor = Color.Red,
//            focusedPrefixColor = Color.Black,
//            unfocusedPrefixColor = Color.Gray,
//            disabledPrefixColor = Color.LightGray,
//            errorPrefixColor = Color.Red,
//            focusedSuffixColor = Color.Black,
//            unfocusedSuffixColor = Color.Gray,
//            disabledSuffixColor = Color.LightGray,
//            errorSuffixColor = Color.Red
//        )
    )
}
