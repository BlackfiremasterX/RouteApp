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

}