package com.example.birthdayapp.presentation.screens.birthday

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.birthdayapp.R
import com.example.birthdayapp.presentation.components.BabyLoader
import com.example.birthdayapp.presentation.dialogs.ErrorDialog
import com.example.birthdayapp.presentation.ui.theme.PelicanBlue
import com.example.birthdayapp.utils.Constants
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.birthdayapp.data.models.BirthdayItem
import com.example.birthdayapp.presentation.ui.theme.BirthdayText
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.parameter.parametersOf

@Composable
fun BirthDayScreen(hostIP: String, vm: BirthDayScreenVM = koinViewModel(parameters = { parametersOf(hostIP) })) {

    val uiState by vm.birthdayState.collectAsStateWithLifecycle()
    var errorMessage: String? by rememberSaveable { mutableStateOf(null) }

    when (val us = uiState) {
        BirthdayUIState.Loading -> {
            BabyLoader()
        }

        is BirthdayUIState.ShowBirthdayUI -> {
            BirthDayContent(us.data)
        }
    }

    errorMessage?.let {
        ErrorDialog(
            Constants.Strings.ERROR_DIALOG_TITLE_TXT,
            it,
            Constants.Strings.ERROR_DIALOG_BTN_TXT
        ) {
            errorMessage = null
        }
    }

    LaunchedEffect(hostIP) {
        vm.birthdayUIEvent.collectLatest {
            when(val event = it){
                is BirthdayUIEvent.ShowError -> {
                    errorMessage = event.error.message
                }
            }
        }
    }
}

@Composable
fun BirthDayContent(item: BirthdayItem) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PelicanBlue)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_android_pelican),
            contentDescription = "background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "TODAY ${item.name?.toUpperCase(Locale.current)} IS",
                fontSize = 21.sp,
                textAlign = TextAlign.Center,
                color = BirthdayText,
                letterSpacing = (-0.42).sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(22.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_left_swirls),
                    contentDescription = "age image",
                    modifier = Modifier
                        .width(50.2.dp)
                        .height(43.53.dp),
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_one),
                    contentDescription = "age image",
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(87.95.dp),
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_right_swirls),
                    contentDescription = "age image",
                    modifier = Modifier
                        .width(50.2.dp)
                        .height(43.53.dp),
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "MONTH OLD!",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = BirthdayText,
                letterSpacing = (-0.36).sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Box(modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()){
                Image(
                    painter = painterResource(id = R.drawable.ic_placeholder_blue),
                    contentDescription = "baby image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_camera_blue),
                        contentDescription = "camera icon",
                        modifier = Modifier
                            .size(36.dp)
                            .offset(x = (-36).dp, y = (16).dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_nanit_logo),
                contentDescription = "nanit logo",
                modifier = Modifier
                    .width(68.62.dp)
                    .height(29.45.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

