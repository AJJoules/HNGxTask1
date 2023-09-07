package ng.com.ajsprojects.hngxtask1

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import ng.com.ajsprojects.hngxtask1.ui.theme.HNGxTask1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HNGxTask1Theme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowUser by rememberSaveable {
        mutableStateOf(true)
    }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowUser) {
            UserScreen { shouldShowUser = false }
        } else {
            LoadWebPage(url = "https://github.com/ajjoules")
        }

    }
}

@Composable
fun UserScreen(
    onContinueClicked: () -> Unit
) {
    Box (modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().matchParentSize(),
            contentScale = ContentScale.FillHeight
        )

        Column(modifier =Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "AJ_Joules", fontSize = 36.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Track: Mobile", fontSize = 18.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold
            )
            Image(
                modifier = Modifier
                    .padding(15.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(20.dp)),
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = null
            )
            Button(
                onClick = { onContinueClicked() },
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "Open GitHub", textAlign = TextAlign.Center)
            }
        }
    }
}


@Composable
fun LoadWebPage(
    url: String
) {
    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    })
}

@Preview(showBackground = true)
@Composable
fun UserPreview() {
    HNGxTask1Theme {
        MyApp()
    }
}
