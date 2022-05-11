package com.example.shopapp.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shopapp.presentation.navigation.NavigationRouter
import com.example.shopapp.presentation.screen.detailed.DetailedCommentState
import com.example.shopapp.presentation.screen.detailed.DetailedState.*
import com.example.shopapp.repository.remote.models.CommentDto
import com.example.shopapp.repository.remote.models.ProductDto
import com.example.shopapp.repository.remote.models.UserDto
import com.example.shopapp.repository.remote.repository.CommentRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailedViewModel(private val commentRepository: CommentRepository): ViewModel() {

    var currentProduct: ProductDto? = null
    private var _userData: StateFlow<UserDto>? = null

    private var rating by mutableStateOf(0f)

    var commentState by mutableStateOf(RateProduct)

    var state = DetailedCommentState()

    fun calculateRating(productDto: ProductDto): Float {
        var sum = 0f
        for (rate in productDto.comments) {
            sum += rate.rating
        }
        val length = productDto.comments.size
        return if (length != 0)
            (sum / length)
        else
            0f
    }

    fun classifyRating(): String {
        when (rating) {
            in 0.0..1.0 -> {
                return "Плохой товар"
            }
            in 1.0..2.0 -> {
                return ""
            }
            in 2.0..3.0 -> {
                return ""
            }
            in 3.0..4.0 -> {
                return ""
            }
            in 4.0..5.1 -> {
                return "Отличный товар"
            }
        }
        return ""
    }

    fun sendComment() = viewModelScope.launch {
        val comment = if (state.comment.value == "")
            null
        else
            state.comment.value

        val advantages = if (state.advantages.value == "")
            null
        else
            state.advantages.value

        val disadvantages = if (state.disadvantages.value == "")
            null
        else
            state.disadvantages.value

        commentRepository.createComment(
            CommentDto(
                comment = comment,
                advantages = advantages,
                disadvantages = disadvantages,
                rating = rating,
                commentedProduct = currentProduct!!,
                commentedByUser = _userData!!.value
            )
        )
    }

    fun clear() {
        rating = 0f
        commentState = RateProduct
        state = DetailedCommentState()
    }

    fun getCommentRoute(): String {
        if (_userData?.value?.userId == 0L)
            return NavigationRouter.SignIn.route
        return ""
    }

    fun getUserData() = _userData

    fun rating() = rating

    fun updateAdvantages(advantages: String) {
        state.advantages.value = advantages
    }

    fun updateDisadvantages(disadvantages: String) {
        state.disadvantages.value = disadvantages
    }

    fun updateComment(comment: String) {
        state.comment.value = comment
    }

    fun updateRating(rating: Float) {
        this.rating = rating
        commentState = WriteComment
    }

    fun updateUserData(userDataState: StateFlow<UserDto>) {
        _userData = userDataState
    }
}



class DetailedViewModelFactory(private val commentRepository: CommentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailedViewModel(commentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
