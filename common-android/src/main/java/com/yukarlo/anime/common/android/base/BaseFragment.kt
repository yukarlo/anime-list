package com.yukarlo.anime.common.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.yukarlo.anime.common.android.ui.theme.AnimeTheme

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AnimeTheme {
                SetContent()
            }
        }
    }

    @Composable
    protected abstract fun SetContent()
}