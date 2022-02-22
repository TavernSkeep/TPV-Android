package com.project.tavernskeep

import android.content.Context
import android.content.res.Configuration
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.size.OriginalSize
import com.project.tavernskeep.ui.theme.Arcade
import com.project.tavernskeep.ui.theme.TavernSkeepTheme
import com.project.tavernskeep.viewsModels.EmployeeViewModel
import com.project.tavernskeep.viewsModels.MesasViewModel
import com.project.tavernskeep.viewsModels.ProductosViewModel
import com.project.tavernskeep.viewsModels.TicketViewModel
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {

    //private val employeeViewModel by viewModels<EmployeeViewModel>()
    private val employeeModel by viewModels<EmployeeViewModel>()
    private val mesasViewModel by viewModels<MesasViewModel>()
    private val productosViewModel by viewModels<ProductosViewModel>()
    private val ticketViewModel by viewModels<TicketViewModel>()
    private var employeeViewComprobator by mutableStateOf(false)
    //private lateinit var navController: NavHostController

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TavernSkeepTheme {
                var context = LocalConfiguration.current
                Box {
                    imageBackground(employeeModel.logged)
                    /*Spacer(modifier = Modifier
                        .size(9.dp)
                        .padding(100.dp))*/
                    loginMenu(LocalConfiguration.current.screenHeightDp,
                        LocalConfiguration.current.screenWidthDp,
                        employeeModel, employeeViewComprobator, employeeModel.logged)
                    openWindow(employeeModel, mesasViewModel, productosViewModel,
                    ticketViewModel, context, LocalContext.current)
                    //Greeting(name = "uwu")
                }
                /*employeeViewModel.getEmployeeList()
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
    @Override
    override fun onBackPressed() {
    }
}
/*
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview(employeeModel: EmployeeViewModel) {
    TavernSkeepTheme {
        Box(/*modifier = Modifier.padding(9.dp)*/) {
            imageBackground()
            /*Spacer(modifier = Modifier
                .size(9.dp)
                .padding(100.dp))*/
            loginMenu(LocalConfiguration.current.screenHeightDp, LocalConfiguration.current.screenWidthDp, employeeModel)
            //Greeting(name = "uwu")
        }
    }
}
*/

@Composable
fun loginMenu(f1: Int, f2: Int, employeeModel: EmployeeViewModel, employeeComprobator: Boolean, logged: Boolean) {

    if(!logged){
        var aux: Int = if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT)
            f2
        else
            1

        var aux2: Int = if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT)
            19
        else
            11

        var aux3: Int = if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT)
            16
        else
            8

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
                            modifier = Modifier.height(50.dp).width(90.dp),
                            shape = RoundedCornerShape(30),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent
                            ),
                            contentPadding = PaddingValues(),
                            onClick = { loginClicked(employeeModel, employeeComprobator, dni, pwd, context)}){
                            Box(
                                modifier = Modifier
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                MaterialTheme.colors.primaryVariant,
                                                MaterialTheme.colors.primary
                                            )
                                        )
                                    )
                                    .padding(horizontal = aux2.dp, vertical = aux3.dp),
                                contentAlignment = Alignment.Center
                            ){
                                Text(text = "Start", color = Color.White, style = TextStyle(fontFamily = Arcade), fontSize = 10.sp)
                            }
                        }
                    }
                }
            }
        }
    }

}

fun loginClicked(employeeModel: EmployeeViewModel, employeeComp: Boolean, dni: String, pwd: String, context: Context) {
    employeeModel.get1Employee(dni, pwd, context)
    if(employeeModel.loginFailures == 1){
        exitProcess(0)
    }

}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun openWindow(employeeModel: EmployeeViewModel, mesasViewModel: MesasViewModel, productosViewModel: ProductosViewModel, ticketViewModel: TicketViewModel, context: Configuration, context2: Context){
    var employeeViewComprobator by remember {
        mutableStateOf(false)
    }
    employeeViewComprobator = employeeModel.employeeComprobator

    if(employeeViewComprobator) {
        AppNavigation(mesasModel = mesasViewModel, productosModel = productosViewModel, ticketModel = ticketViewModel, context = context, employeeModel = employeeModel, context2 = context2)
        employeeModel.logged = true
    }
}

@Composable
fun imageBackground(logged: Boolean){
    if(!logged){
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
    }else{
        Box(modifier = Modifier.fillMaxSize(1f).background(MaterialTheme.colors.background))
    }

}


