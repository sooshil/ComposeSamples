package com.sukajee.codesamplesone

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sukajee.codesamplesone.ui.theme.CodeSamplesOneTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeSamplesOneTheme {
                MyApp(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier
) {

    var shouldShowOnBoarding by rememberSaveable {
        mutableStateOf(true)
    }

    Surface(
        modifier = modifier
    ) {
        if (shouldShowOnBoarding) {
            OnBoardingScreen {
                shouldShowOnBoarding = false
            }
        } else {
            Greetings(modifier = modifier)
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(100) { "$it" }
) {
    LazyColumn(
        modifier = modifier
            .padding(vertical = 4.dp)
    ) {
        items(items = names) { item ->
            Greeting(name = item)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        CardContent(name = name)
    }
}

@Composable
fun CardContent(name: String) {
    var expanded by rememberSaveable { //try using remember and scrolling and check expanded state
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = "kdfald akdf adf ald kdfja dkl adkf adf aidf akdf akdfja dfka dfja kdf adkf adkfakd adkf adkfa dfa dfkla dkfadkf adlkf akdf alkdf alkdf aldfa ldfkjdlf adkjf adkf alkjf asdkjf aksdf".repeat(
                        3
                    ),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        IconButton(
            onClick = {
                expanded = !expanded
            }
        ) {
            if(expanded) {
                Icon(
                    imageVector = Icons.Default.ExpandLess,
                    contentDescription = "Show less"
                )
            } else {
                Icon(
                    imageVector = Icons.Default.ExpandMore,
                    contentDescription = "Show more"
                )
            }
        }
    }
}

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit
) {
    Surface {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Welcome to sample code lab!")
            Button(
                modifier = modifier.padding(vertical = 16.dp),
                onClick = onContinueClick
            ) {
                Text(text = "Continue")
            }
        }
    }
}

@Preview(
    showBackground = true,
    heightDp = 360,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(showBackground = true, heightDp = 360)
@Composable
fun DefaultPreview() {
    CodeSamplesOneTheme {
        Greetings()
    }
}

@Preview(showBackground = true, heightDp = 360)
@Composable
fun OnBoardingScreenPreview() {
    CodeSamplesOneTheme {
        OnBoardingScreen(onContinueClick = {})
    }
}