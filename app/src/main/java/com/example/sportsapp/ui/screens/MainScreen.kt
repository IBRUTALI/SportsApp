package com.example.sportsapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.sportsapp.R
import com.example.sportsapp.domain.network.model.Result

@Composable
fun MainScreen(
    navController: NavController,
    list: State<List<Result>?>,
    modifier: Modifier = Modifier
) {

    LazyColumn {
        itemsIndexed(
            list.value!!
        ) { _, item ->
            ColumnItem(item)
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ColumnItem(
    item: Result,
    modifier: Modifier = Modifier
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

                ){
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
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = modifier
                        .height(IntrinsicSize.Max),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.league_name,
                        style = MaterialTheme.typography.h2,
                        modifier = modifier
                            .padding(horizontal = 5.dp)
                    )
                        GlideImage(
                            model = item.league_logo,
                            modifier = modifier
                                .size(15.dp, 15.dp),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = stringResource(R.string.league_logo)
                        ){
                            it
                                .error(item.country_logo)
                                .load(item.league_logo)
                        }
                }
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
