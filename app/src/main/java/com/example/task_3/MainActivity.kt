package com.example.task_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task_3.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task_3Theme {
                Surface(color = MaterialTheme.colors.background) {
                    SignUpScreen()
                }
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var isNameValid by remember { mutableStateOf(false) }
    var isLastNameValid by remember { mutableStateOf(false) }
    var isacceptEulaaccepted by remember { mutableStateOf(false) }
    val backgroundImage = painterResource(R.drawable.background)

    Box(modifier = Modifier.fillMaxSize()) {
        // Add Image composable for the custom background
        Image(
            painter = backgroundImage,
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState(100)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 40.sp, color = Color(0xFF6441A5))) {
                        append("S")
                    }
                    withStyle(style = SpanStyle(fontSize = 30.sp, color = Color.Black)) {
                        append("i")
                    }
                    withStyle(style = SpanStyle(fontSize = 30.sp, color = Color.Black)) {
                        append("g")
                    }
                    withStyle(style = SpanStyle(fontSize = 30.sp, color = Color.Black)) {
                        append("n")
                    }
                    withStyle(style = SpanStyle(fontSize = 40.sp, color = Color(0xFF6441A5))) {
                        append(" U")
                    }
                    withStyle(style = SpanStyle(fontSize = 30.sp, color = Color.Black)) {
                        append("p")
                    }
                },
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,

            )
            Spacer(modifier = Modifier.height(32.dp))

            CustomTextField(
                value = name,
                onValueChange = { newValue ->
                    name = newValue
                    isNameValid = newValue.isNotEmpty()
                },
                label = "Name",
                isError = name.isNotEmpty() && !isNameValid,
                leadingIcon = Icons.Default.Face,
                trailingIcon = if (isNameValid) Icons.Default.Check else Icons.Default.Close,
                trailingIconTint = if (isNameValid) Color.Green else Color.Red
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = lastname,
                onValueChange = { newValue ->
                    lastname = newValue
                    isLastNameValid = newValue.isNotEmpty()
                },
                label = "LastName",
                isError = lastname.isNotEmpty() && !isLastNameValid,
                leadingIcon = Icons.Default.Info,
                trailingIcon = if (isLastNameValid) Icons.Default.Check else Icons.Default.Close,
                trailingIconTint = if (isLastNameValid) Color.Green else Color.Red
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isacceptEulaaccepted,
                    onCheckedChange = { isacceptEulaaccepted = it },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFFEAAFC8))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("I accept the EULA",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            val isButtonEnabled = isNameValid && isLastNameValid && isacceptEulaaccepted

            Button(
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp) // Change the height to match CustomTextField
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF654EA3), Color(0xFFEAAFC8)),
                            startX = 0f,
                            endX = Float.POSITIVE_INFINITY
                        )
                    ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                ),
                enabled = isButtonEnabled,
                elevation = ButtonDefaults.elevation(0.dp)
            ) {
                Text("Sign Up",
                    color = Color.White,
                    style = MaterialTheme.typography.button,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector,
    trailingIconTint: Color
) {
    TextField(
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) }
        ,
        isError = isError,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = trailingIcon,
                contentDescription = null,
                tint = trailingIconTint,
                modifier = Modifier.padding(8.dp)
            )
        },
        modifier = Modifier.border(
            width = 2.dp,
            brush = Brush.horizontalGradient(listOf(Color(0xFF654EA3), Color(0xFFEAAFC8))),
            shape = RoundedCornerShape(12.dp)
        ),
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 20.sp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {})
    )
}
@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    SignUpScreen()
}