package com.example.jc.samples

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jc.ui.theme.JCTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

/*
how to handle side-effects that scape the scope of a composable function
 */

@Composable
fun HandlingE() {
    var text by remember { mutableStateOf("") }

    JCTheme {
        LaunchedEffect(key1 = text) {
            delay(1000L)
            println("The text is $text")
        }
    }
}

class LaunchedEffectedViewModel : ViewModel() {
    private val _sharedFlow = MutableSharedFlow<ScreenEvents>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            _sharedFlow.emit(ScreenEvents.ShowSnackBar("Hello, Compose!"))
        }
    }

    sealed class ScreenEvents {
        data class ShowSnackBar(val message: String) : ScreenEvents()
        data class Navigate(val route: String) : ScreenEvents()
    }
}

@Composable
fun LaunchedEffectFlowDemo(viewModel: LaunchedEffectedViewModel) {
    LaunchedEffect(true) {
        viewModel.sharedFlow.collect { event ->
            when (event) {
                is LaunchedEffectedViewModel.ScreenEvents.ShowSnackBar -> {

                }

                is LaunchedEffectedViewModel.ScreenEvents.Navigate -> {

                }
            }
        }
    }
}

@Composable
fun LaunchedEffectAnimation(counter: Int) {
    val animatable = remember { androidx.compose.animation.core.Animatable(0f) }

    LaunchedEffect(key1 = counter) { animatable.animateTo(counter.toFloat()) }
}

@Composable
fun RememberCoroutineScopeDemo() {
    val scope = rememberCoroutineScope()

    Button(onClick = {
        scope.launch {
            delay(1000L)
            println("Hello, Compose!")
        }
    }) {

    }
}

@Composable
fun RememberUpdatedStateDemo(onTimeout: () -> Unit) {
    val updatedOnTimeout by rememberUpdatedState(newValue = onTimeout)

    LaunchedEffect(true) {
        delay(3000L)
        updatedOnTimeout()
    }
}

@Composable
fun DisposableEffectDemo() {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner) {

        val observer = LifecycleEventObserver { _, event ->

            if (event == Lifecycle.Event.ON_PAUSE) println("On pause called")
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
}

@Composable
fun SideEffectDemo(nonComposeCounter: Int) {
    SideEffect {
        // should be used when something that it not composable state is called or passed
        println("Called after every successful composition.")
    }
}

@Composable
fun ProducedStateDemo(countUpTo: Int): State<Int> {
    return produceState(initialValue = 0) {
        while (value < countUpTo) {
            delay(1000L)
            value++
        }
    }
}

@Composable
fun DerivedStateofDemo() {
    var counter by remember {
        mutableStateOf(0)
    }
    val counterText = "The counter is $counter"

    Button(onClick = { counter++ }) {
        Text(counterText)
    }
}

@Composable
fun SnapshotFlowDemo() {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = scaffoldState) {
        snapshotFlow { scaffoldState.snackbarHostState }
            .mapNotNull { it.currentSnackbarData?.message }
            .distinctUntilChanged()
            .collect { message ->
                println("A \'snackbar\' with message $message was shown")
            }
    }
}