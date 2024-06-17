package com.android.deepdweller.maprouteapp_nojetpack.presentation.base


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.android.deepdweller.maprouteapp_nojetpack.presentation.MainActivity


abstract class BaseFragment(@LayoutRes res: Int) : Fragment(res) {
    protected val mainActivity: MainActivity
        get() = requireActivity() as MainActivity


//    protected open val toolBarMode: ToolBarMode = NoToolbar
//
//
//    protected open val isNeedBottomNav: Boolean
//        get() = true
//
//
//    protected open val animation: TransitionAnimation
//        get() = TransitionAnimation.TransitionAnimationSharedAxisX()
//
//    protected open val fieldsWithErrorMap: Map<String, FieldWithError> = emptyMap()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if (BuildConfig.needTransitionAnimations) applyAnimations()
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setHasOptionsMenu(true)
//        if (isNeedBottomNav) showBottomNavigation() else hideBottomNavigation()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        KeyBoardUtils.hide(requireActivity())
//    }
//
//    private fun applyAnimations() {
//        when (animation) {
//            is TransitionAnimation.TransitionAnimationContainerTransform -> {
//                val animationObject =
//                    animation as TransitionAnimation.TransitionAnimationContainerTransform
//
//                enterTransition = animationObject.enterAnim?.apply {
//                    removeTarget(animationObject.target)
//                }
//                reenterTransition = animationObject.reenterAnim
//                sharedElementEnterTransition = animationObject.sharedElementEnterTrans
//            }
//
//            else -> {
//                enterTransition = animation.enterAnim
//                returnTransition = animation.returnAnim
//                exitTransition = animation.exitAnim
//                reenterTransition = animation.reenterAnim
//            }
//        }
//    }
//
//    protected suspend fun <T> UiState<T>.handleUiState(
//        isNeedIndicator: Boolean = true,
//        onError: ((UiState<T>) -> Unit)? = null,
//        onSuccess: suspend ((UiState<T>) -> Unit) = {},
//        onNullableDataSuccess: ((UiState<T>) -> Unit)? = null,
//    ) {
//        when {
//            isLoading -> if (isNeedIndicator) showCircleIndicator()
//
//            error != null -> {
//                if (isNeedIndicator) hideCircleIndicator()
//                onError?.invoke(this)
//            }
//
//            data != null -> {
//                if (isNeedIndicator) hideCircleIndicator()
//                onSuccess.invoke(this)
//            }
//
//            else -> {
//                if (isNeedIndicator) hideCircleIndicator()
//                onNullableDataSuccess?.invoke(this)
//            }
//        }
//    }
//
//    protected suspend fun <T> UiState<T>.handleUiStateForMain(
//        isNeedIndicator: Boolean = true,
//        onError: ((UiState<T>) -> Unit)? = null,
//        onSuccess: suspend ((UiState<T>) -> Unit) = {},
//        onNullableDataSuccess: ((UiState<T>) -> Unit)? = null,
//    ) {
//        when {
//            isLoading -> if (isNeedIndicator) showCircleIndicatorWithoutBlur()
//
//            error != null -> {
//                if (isNeedIndicator) hideCircleIndicator()
//                onError?.invoke(this)
//            }
//
//            data != null -> {
//                if (isNeedIndicator) hideCircleIndicator()
//                onSuccess.invoke(this)
//            }
//
//            else -> {
//                if (isNeedIndicator) hideCircleIndicator()
//                onNullableDataSuccess?.invoke(this)
//            }
//        }
//    }
//
//    // TODO добавить данный функционал к handleUiState
//    protected suspend fun <T> UiState<T>.handleUiWithError(
//        isNeedIndicator: Boolean = true,
//        onError: ((UiState<T>) -> Unit)? = null,
//        onSuccess: suspend ((UiState<T>) -> Unit) = {},
//        onSuccessWithError: suspend ((UiState<T>) -> Unit) = {},
//        onNullableDataSuccess: ((UiState<T>) -> Unit)? = null,
//    ) {
//        when {
//            isLoading -> if (isNeedIndicator) showCircleIndicator()
//
//            data != null && error != null -> {
//                if (isNeedIndicator) hideCircleIndicator()
//                onSuccessWithError.invoke(this)
//            }
//
//            error != null -> {
//                if (isNeedIndicator) hideCircleIndicator()
//                onError?.invoke(this)
//            }
//
//            data != null -> {
//                if (isNeedIndicator) hideCircleIndicator()
//                onSuccessWithError.invoke(this)
//            }
//
//            else -> {
//                if (isNeedIndicator) hideCircleIndicator()
//                onNullableDataSuccess?.invoke(this)
//            }
//        }
//    }
//
//    protected fun hideCircleIndicator() {
//        mainActivity.hideCircleIndicator()
//    }
//
//    protected fun showCircleIndicator() {
//        mainActivity.showCircleIndicator()
//    }
//
//    protected fun showCircleIndicatorWithoutBlur() {
//        mainActivity.showCircleIndicatorWithoutBlur()
//    }
//
//    protected fun setSupportNavigateUp(enabled: Boolean, @DrawableRes icon: Int? = null) {
//        mainActivity.setSupportNavigateUp(enabled, icon)
//    }
//
//    protected fun setToolBarBackground(@ColorRes colorRes: Int) {
//        mainActivity.setToolBarBackground(colorRes)
//    }
//
//    protected fun setToolbarTitle(title: String) {
//        mainActivity.setToolBarTitle(title)
//    }
//
//    private fun setToolbarSubTitle(title: String) {
//        mainActivity.setToolBarSubTitle(title)
//    }
//
//    protected fun showToolbar() {
//        mainActivity.showToolBar()
//    }
//
//    private fun hideToolbar() {
//        mainActivity.hideToolBar()
//    }
//
//    protected fun showBottomNavigation() {
//        mainActivity.showBottomNavigation()
//    }
//
//    protected fun hideBottomNavigation() {
//        mainActivity.hideBottomNavigation()
//    }
//
//    private fun handleToolBarModeOnCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        when (toolBarMode) {
//            is HomeEnabled -> {
//                showToolbar()
//                setToolbarSubTitle((toolBarMode as HomeEnabled).title)
//                setToolBarBackground(toolBarMode.backgroundColor)
//                setSupportNavigateUp(true)
//            }
//
//            is HomeEnabledTextAction -> {
//                inflater.inflate(R.menu.one_action_menu, menu)
//                showToolbar()
//                val mode = toolBarMode as HomeEnabledTextAction
//                val item = menu.findItem(R.id.actionItem)
//                setToolbarSubTitle(mode.title)
//                setToolBarBackground(toolBarMode.backgroundColor)
//                item.apply {
//                    title = mode.actionText
//                }
//                setSupportNavigateUp(true)
//            }
//
//            is HomeEnabledIconAction -> {
//                inflater.inflate(R.menu.one_action_menu, menu)
//                showToolbar()
//                val mode = toolBarMode as HomeEnabledIconAction
//                val item = menu.findItem(R.id.actionItem)
//                setToolbarSubTitle(mode.title)
//                setToolBarBackground(toolBarMode.backgroundColor)
//                item.apply {
//                    val iconRes = mode.icon
//                    icon = ResourcesCompat.getDrawable(resources, iconRes, mainActivity.theme)
//                }
//                setSupportNavigateUp(true)
//            }
//
//            is NoToolbar -> {
//                hideToolbar()
//                setToolbarTitle("")
//                setToolbarSubTitle("")
//                setToolBarBackground(toolBarMode.backgroundColor)
//                setSupportNavigateUp(false)
//            }
//
//            is HomeDisabledTextAction -> {
//                inflater.inflate(R.menu.one_action_menu, menu)
//                showToolbar()
//                val mode = toolBarMode as HomeDisabledTextAction
//                val item = menu.findItem(R.id.actionItem)
//                setToolbarSubTitle(mode.title)
//                setToolBarBackground(toolBarMode.backgroundColor)
//                item.apply {
//                    title = mode.actionText
//                }
//                setSupportNavigateUp(false)
//            }
//        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        handleToolBarModeOnCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean =
//        handleToolBarModeOnOptionsItemSelected(item)
//
//    private fun handleToolBarModeOnOptionsItemSelected(item: MenuItem): Boolean =
//        when (toolBarMode) {
//            is HomeEnabled -> super.onOptionsItemSelected(item)
//            is HomeEnabledIconAction -> handleToolbarHomeEnabledIconAction(item)
//            is HomeEnabledTextAction -> handleToolbarHomeEnabledTextAction(item)
//            is NoToolbar -> super.onOptionsItemSelected(item)
//            is HomeDisabledTextAction -> handleToolbarHomeDisabledTextAction(item)
//        }
//
//    private fun handleToolbarHomeEnabledTextAction(item: MenuItem): Boolean = when (item.itemId) {
//        R.id.actionItem -> {
//            (toolBarMode as HomeEnabledTextAction).action.invoke()
//            true
//        }
//
//        else -> super.onOptionsItemSelected(item)
//    }
//
//    private fun handleToolbarHomeDisabledTextAction(item: MenuItem): Boolean = when (item.itemId) {
//        R.id.actionItem -> {
//            (toolBarMode as HomeDisabledTextAction).action.invoke()
//            true
//        }
//
//        else -> super.onOptionsItemSelected(item)
//    }
//
//    private fun handleToolbarHomeEnabledIconAction(item: MenuItem): Boolean = when (item.itemId) {
//        R.id.actionItem -> {
//            (toolBarMode as HomeEnabledIconAction).action.invoke()
//            true
//        }
//
//        else -> super.onOptionsItemSelected(item)
//    }
//
//    protected fun handleFieldErrors(fieldErrors: List<FieldError>) {
//        fieldErrors.forEach { fieldError ->
//            val key = fieldError.field
//            if (fieldsWithErrorMap.containsKey(key)) {
//                val fieldWithError = fieldsWithErrorMap[key]!!
//                fieldWithError.reactOnError(fieldWithError.field, fieldError)
//            }
//        }
//    }
}