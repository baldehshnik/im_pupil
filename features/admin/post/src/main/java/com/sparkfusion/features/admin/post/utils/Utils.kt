package com.sparkfusion.features.admin.post.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sparkfusion.features.admin.post.R
import com.sparkfusion.features.admin.post.entity.AdditionalBlockEntity

@Composable
fun getAdditionalBlocks(): Array<AdditionalBlockEntity> {
    return arrayOf(
        AdditionalBlockEntity(
            R.drawable.date_info_icon,
            stringResource(id = R.string.date),
            stringResource(id = R.string.date_block_icon_description)
        ),
        AdditionalBlockEntity(
            R.drawable.place_icon,
            stringResource(id = R.string.place),
            stringResource(id = R.string.place_block_icon_description)
        ),
        AdditionalBlockEntity(
            R.drawable.reward_icon,
            stringResource(id = R.string.reward),
            stringResource(id = R.string.reward_block_icon_description)
        ),
        AdditionalBlockEntity(
            R.drawable.sort_icon,
            stringResource(id = R.string.sorting),
            stringResource(id = R.string.sorting_block_icon_description)
        )
    )
}