package com.project.tavernskeep

import android.content.res.Configuration
import android.graphics.Insets.add
import android.graphics.drawable.AnimatedImageDrawable
import android.inputmethodservice.Keyboard
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.Gravity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.size.OriginalSize
import com.project.tavernskeep.screens.Screen
import com.project.tavernskeep.screens.home.HomeScreen
import com.project.tavernskeep.ui.theme.Arcade
import com.project.tavernskeep.ui.theme.TavernSkeepTheme
import com.project.tavernskeep.viewsModels.EmployeeViewModel

class MainActivity : ComponentActivity() {

    private val employeeViewModel by viewModels<EmployeeViewModel>()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TavernSkeepTheme {
                Box() {
                    imageBackground()
                    /*Spacer(modifier = Modifier
                        .size(9.dp)
                        .padding(100.dp))*/
                    loginMenu(LocalConfiguration.current.screenHeightDp, LocalConfiguration.current.screenWidthDp)
                    //Greeting(name = "uwu")
                }
                /*employeeViewModel.getEmployeeList()
                navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(
                        route = Screen.Home.route+Screen.Home.arguments
                    ) {
                        HomeScreen(
                            navController = navController,
                            employeeModelList = employeeViewModel.employeeModelListResponse,
                        )
                    }
                    composable(
                        route = Screen.Detail.route+Screen.Detail.arguments,
                        arguments = listOf(
                            navArgument("id") {type = NavType.IntType}
                        ),
                    ) {

                            backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("id")
                        requireNotNull(id)
                        /*
                        DetailScreen(
                            navController = navController,
                            movieModel = movieViewModel.movieModelListResponse[id]
                        )*/

                    }*/
                }
                // A surface container using the 'background' color from the theme
                /*Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }*/
            }
        }
    }

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    TavernSkeepTheme {
        Box(/*modifier = Modifier.padding(9.dp)*/) {
            imageBackground()
            /*Spacer(modifier = Modifier
                .size(9.dp)
                .padding(100.dp))*/
            loginMenu(LocalConfiguration.current.screenHeightDp, LocalConfiguration.current.screenWidthDp)
            //Greeting(name = "uwu")
        }
    }
}

@Composable()
fun loginMenu(f1: Int, f2: Int) {

    var aux: Int = if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT)
        f2
    else
        1

    var dni by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxWidth(1f)
        .fillMaxHeight(1f)
        .padding(top = (aux / 2).dp, bottom = (aux / 2).dp)){
        val context = LocalContext.current
        val imgLoader = ImageLoader.invoke(context).newBuilder()
            .componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder(context))
                } else {
                    add(GifDecoder())
                }
            }.build()
        Image(
            painterResource(id = R.drawable.cartel),
            "",
            modifier = Modifier.matchParentSize(),
            //contentScale = ContentScale.Fit
        )
        Column(modifier = Modifier.fillMaxSize(1f)) {

            Image(
                painter = rememberImagePainter(data = R.drawable.skeepyskeep, imageLoader = imgLoader,
                    builder = {
                        size(OriginalSize)
                    }),
                "",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.5f)
            )
            Box(modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)){
                Column(modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(25.dp))
                    OutlinedTextField(
                        value = dni,
                        onValueChange = { dni = it },
                        label = { Text(text = stringResource(id = R.string.dni),
                            style = TextStyle(
                                fontFamily = Arcade,
                            )) },
                        textStyle = TextStyle(fontFamily = Arcade, color = Color.White),
                        modifier = Modifier
                            .height(55.dp)
                            .width(250.dp)
                    )
                    OutlinedTextField(
                        value = pwd,
                        onValueChange = { pwd = it },
                        label = { Text(text = stringResource(id = R.string.password),
                            style = TextStyle(
                                fontFamily = Arcade,
                            )) },
                        textStyle = TextStyle(color = Color.White),
                        modifier = Modifier
                            .height(55.dp)
                            .width(250.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Button(
                        shape = RoundedCornerShape(30),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent
                        ),
                        contentPadding = PaddingValues(),
                        onClick = { loginClicked()}){
                        Box(
                            modifier = Modifier
                                .background(brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        MaterialTheme.colors.primaryVariant,
                                        MaterialTheme.colors.primary
                                    )
                                ))
                                .padding(horizontal = 16.dp, vertical = 11.dp),
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = "Start", color = Color.White, style = TextStyle(fontFamily = Arcade))
                        }
                    }
                }
            }
        }
    }
}

fun loginClicked() {

}

@Composable
fun imageBackground(){
    Box(modifier = Modifier.fillMaxSize(1f)) {
        Image(
            painterResource(id = R.drawable.fondo),
            "",
            modifier = Modifier.matchParentSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop

        )
        Image(
            painterResource(id = R.drawable.orcwithgrog),
            "",
            modifier = Modifier.matchParentSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
            /*modifier = Modifier
            .clip(CircleShape)
            .background(Color.Blue)
            .size(32.dp)*/
        )

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", color = MaterialTheme.colors.onBackground,
    modifier = Modifier.padding(top = 5.dp))
}

