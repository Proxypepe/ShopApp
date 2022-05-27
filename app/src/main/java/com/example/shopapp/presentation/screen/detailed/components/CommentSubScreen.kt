package com.example.shopapp.presentation.screen.detailed.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopapp.domain.DetailedViewModel
import com.example.shopapp.presentation.navigation.NavigationRouter
import com.example.shopapp.presentation.screen.detailed.components.rataing.CustomRatingBar
import com.example.shopapp.repository.remote.models.CommentDto
import com.example.shopapp.repository.remote.models.UserDto
import com.example.shopapp.ui.theme.AppTheme


@Composable
fun CommentSubScreen(
    detailedViewModel: DetailedViewModel,
    userDto: UserDto,
    navController: NavHostController
) {
    with(detailedViewModel.currentProduct!!) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppTheme.colors.primary)
                    .height(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.fillMaxWidth(0.3f))
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Отзывы",
                        style = AppTheme.typography.h4,
                        color = Color.White,
                    )
                }
            }

            LazyColumn {
                item {
                    LeaveComment(
                        commentsAmount = comments.size,
                        rating = detailedViewModel.calculateRating(detailedViewModel.currentProduct!!),
                        onClear = detailedViewModel::clear,
                        currentUser = userDto,
                        navController = navController
                    )
                }
                items(comments) { comment ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CommentCard(comment)
                    }
                }
            }
        }
    }
}

// rename
@Composable
fun LeaveComment(
    commentsAmount: Int,
    rating: Float,
    onClear: () -> Unit,
    currentUser: UserDto,
    navController: NavHostController
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .background(AppTheme.colors.background)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Отзывы",
                    style = AppTheme.typography.h5,
                    color = AppTheme.textColors.primaryTextColor
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = commentsAmount.toString(),
                    style = AppTheme.typography.body1,
                    color = AppTheme.textColors.subtitle1Text
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    if (currentUser.userId != 0L || currentUser.email != "") {
                        navController.navigate(NavigationRouter.CommentWrite.route)
                        onClear()
                    } else {
                        Toast.makeText(context, "Пожалуйста, авторизуйтесь", Toast.LENGTH_LONG)
                            .show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Text(
                    text = "Написать отзыв",
                    color = AppTheme.textColors.primaryButtonText
                )
            }
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Общий рейтинг: $rating",
                style = AppTheme.typography.body1,
                color = AppTheme.textColors.primaryTextColor
            )

            CustomRatingBar(
                value = rating,
                onValueChange = {},
                onRatingChanged = {}
            )
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}

@Composable
fun CommentCard(
    comment: CommentDto
) {
    with(comment) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            elevation = 5.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                comment.comment?.let {
                    Text(
                        text = "Комментарий: $it",
                        style = AppTheme.typography.body1,
                        color = AppTheme.textColors.primaryTextColor
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                comment.advantages?.let {
                    Text(
                        text = "Достоинтства: $it",
                        style = AppTheme.typography.body1,
                        color = AppTheme.textColors.primaryTextColor
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                comment.disadvantages?.let {
                    Text(
                        text = "Недостатки: $it",
                        style = AppTheme.typography.body1,
                        color = AppTheme.textColors.primaryTextColor
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Рейтинг: $rating",
                    style = AppTheme.typography.body1,
                    color = AppTheme.textColors.primaryTextColor
                )
                Spacer(modifier = Modifier.height(3.dp))
                CustomRatingBar(
                    value = rating,
                    onValueChange = {},
                    onRatingChanged = {}
                )
            }
        }
    }
}
