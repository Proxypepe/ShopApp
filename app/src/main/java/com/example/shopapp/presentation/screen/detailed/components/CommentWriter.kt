package com.example.shopapp.presentation.screen.detailed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopapp.domain.DetailedViewModel
import com.example.shopapp.presentation.screen.detailed.DetailedState
import com.example.shopapp.presentation.screen.detailed.components.rataing.CustomRatingBar
import com.example.shopapp.repository.remote.models.UserDto
import com.example.shopapp.ui.theme.AppTheme


@Composable
fun MakeComment(
    detailedViewModel: DetailedViewModel,
    userData: UserDto,
    navController: NavHostController
) {
    when(detailedViewModel.commentState) {
        DetailedState.RateProduct -> RateProduct(detailedViewModel, navController)
        DetailedState.WriteComment -> FillExtraInfo(detailedViewModel, userData, navController)
    }
}

@Composable
fun FillExtraInfo(
    detailedViewModel: DetailedViewModel,
    userData: UserDto,
    navController: NavHostController
) {
    with(detailedViewModel.state) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CommentTopBar(detailedViewModel, navController)

            Spacer(modifier = Modifier.height(10.dp))

            CustomRatingBar(
                value = detailedViewModel.rating(),
                onValueChange = {},
                onRatingChanged = {}
            )
            Text(
                text = detailedViewModel.classifyRating(),
                style = AppTheme.typography.subtitle2,
                color = AppTheme.textColors.secondaryTextColor
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(65.dp)
                    .background(Color.Gray),
                shape = RoundedCornerShape(2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "info"
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Если качество покупки вас устроило, можете поставить только оценку товара. Оставльные поля необязательно.",
                        style = AppTheme.typography.subtitle2,
                        color = AppTheme.textColors.primaryTextColor
                    )
                }
            }

            Spacer(modifier = Modifier.fillMaxHeight(0.4f))

            WriterTextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(47.dp),
                value = advantages.value,
                onValueChange = detailedViewModel::updateAdvantages,
                placeholder = "Достоинства"
            )
            Spacer(modifier = Modifier.height(10.dp))
            WriterTextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(47.dp),
                value = disadvantages.value,
                onValueChange = detailedViewModel::updateDisadvantages,
                placeholder = "Недостатки"
            )
            Spacer(modifier = Modifier.height(10.dp))
            WriterTextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(47.dp),
                value = comment.value,
                onValueChange = detailedViewModel::updateComment,
                placeholder = "Комментарии"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    detailedViewModel.sendComment(userData)
                },
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(
                    text = "Создать отзыв"
                )
            }
        }
    }
}
