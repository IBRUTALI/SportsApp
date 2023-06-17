package com.example.sportsapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.sportsapp.R
import com.example.sportsapp.domain.network.model.Result
import com.example.sportsapp.utils.Screen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MainScreen(
    navController: NavController,
    list: State<List<Result>?>,
    modifier: Modifier = Modifier,
    isLoading: State<Boolean>,
    onRefresh: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.h2
                    )
                },
                actions = {WebViewAction(navController)}
            )
        }, content = {
            modifier.padding(paddingValues = it)
                val swiperRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading.value)
                SwipeRefresh(
                    state = swiperRefreshState,
                    onRefresh = onRefresh
                ) {
                    LazyColumn{
                        itemsIndexed(
                            list.value!!
                        ) { _, item ->
                            ColumnItem(
                                item = item,
                                navController = navController
                            )
                        }
                    }
                }

        }
    )

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ColumnItem(
    item: Result,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Card(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .padding(3.dp),
        elevation = 5.dp
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row {
                GlideImage(
                    model = item.home_team_logo,
                    modifier = modifier
                        .size(60.dp, 60.dp)
                        .padding(vertical = 2.dp, horizontal = 5.dp),
                    contentScale = ContentScale.Fit,
                    contentDescription = stringResource(R.string.home_team_logo)

                ) {
                    it
                        .error(item.country_logo)
                        .load(item.home_team_logo)
                }
                Divider(
                    modifier = modifier
                        .fillMaxHeight()
                        .width(1.dp),
                    color = Color.LightGray
                )
            }

            Column(
                modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.league_name,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = item.event_final_result,
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = item.event_time,
                    style = MaterialTheme.typography.h3
                )
            }
            Row {
                Divider(
                    modifier = modifier
                        .fillMaxHeight()
                        .width(1.dp),
                    color = Color.LightGray
                )
                GlideImage(
                    model = item.away_team_logo,
                    modifier = modifier
                        .size(60.dp, 60.dp)
                        .padding(vertical = 2.dp, horizontal = 5.dp),
                    contentScale = ContentScale.Fit,
                    contentDescription = stringResource(R.string.away_team_logo)
                ) {
                    it
                        .error(item.country_logo)
                        .load(item.away_team_logo)
                }
            }
        }
    }
}

@Composable
fun WebViewAction(
    navController: NavController
) {
    val context = LocalContext.current
    IconButton(onClick = {
        navController.navigate("${Screen.WebScreen.route}/123")
    }) {
        Icon(
            imageVector = Icons.Filled.Send,
            tint = Color.White,
            contentDescription = "web_view"
        )
    }
}
