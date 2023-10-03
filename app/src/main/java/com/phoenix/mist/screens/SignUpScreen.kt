package com.phoenix.mist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phoenix.mist.R
import com.phoenix.mist.components.CheckBoxComponent
import com.phoenix.mist.components.ClickableLoginTextComponent
import com.phoenix.mist.components.DividerTextComponent
import com.phoenix.mist.components.HeadingTextComponent
import com.phoenix.mist.components.MyButtonComponent
import com.phoenix.mist.components.MyTextField
import com.phoenix.mist.components.NormalTextComponent
import com.phoenix.mist.components.PasswordTextField
import com.phoenix.mist.data.signup.SignupUIEvent
import com.phoenix.mist.data.signup.SignupViewModel
import com.phoenix.mist.navigation.MistRouter
import com.phoenix.mist.navigation.Screen

@Composable
fun SignUpScreen(signupViewModel: SignupViewModel = viewModel()) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
        ){
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                NormalTextComponent(value = stringResource(id = R.string.hello))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(16.dp))
                MyTextField(
                    labelValue = stringResource(id = R.string.first_name),
                    imageIcon = Icons.Outlined.Person,
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
                )
                MyTextField(
                    labelValue = stringResource(id = R.string.last_name),
                    imageIcon = Icons.Outlined.Person,
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
                )
                MyTextField(
                    labelValue = stringResource(id = R.string.email),
                    imageIcon = Icons.Outlined.Email,
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.emailError
                )
                MyTextField(
                    labelValue = stringResource(id = R.string.tel),
                    imageIcon = Icons.Outlined.Call,
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.PhoneChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.phoneError
                )


                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    imageIcon = Icons.Outlined.Lock,
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.passwordError
                )
                CheckBoxComponent(
                    value = stringResource(id = R.string.terms_and_conditions),
                    onTextSelected = { MistRouter.navigateTo(Screen.TermsAndConditionsScreen)},
                    onCheckedChange ={ signupViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it)) } )

                Spacer(modifier = Modifier.height(16.dp))

                MyButtonComponent(
                    labelValue = stringResource(id = R.string.register),
                    onButtonClicked = {
                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = signupViewModel.allValidationsPassed.value
                )
                Spacer(modifier = Modifier.height(16.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true,onTextSelected = {
                    MistRouter.navigateTo(Screen.LoginScreen)
                })
            }

        }

        if (signupViewModel.signUpInProgress.value){
            CircularProgressIndicator()
        }
    }

}

@Preview
@Composable
fun DefaultSignUpPreview(){
    SignUpScreen()
}