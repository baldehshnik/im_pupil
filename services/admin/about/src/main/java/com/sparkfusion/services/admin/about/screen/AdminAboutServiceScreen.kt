package com.sparkfusion.services.admin.about.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.about.R
import com.sparkfusion.services.admin.about.component.AboutInfoItem
import com.sparkfusion.services.admin.about.model.AboutBlockModel

@Composable
fun AdminAboutServiceScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {
    val items = listOf(
        AboutBlockModel("", "Desctifjshfbdjhg b sgh sdg dskhfkjdshf hkdsh fhsdjkhf jkhsjdkfkjs d"),
        AboutBlockModel("2", "gd df gdfgdfg dfg zfgd fgz hzfdhzzfd fdh df hzdfhz hf"),
        AboutBlockModel("", "hdf zhz dzf hzdfhdfh zdfh zdfhz fzh"),
        AboutBlockModel("3", "hdf hdzfhdfh zfh  zfz "),
        AboutBlockModel("2", "hdfzh dzfhr ehzreh e")
    )

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {
            item {
                TopBar(title = "About", onBackClick = onBackClick)
            }

            items(items.size) { index ->
                AboutInfoItem(item = items[index])
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            onClick = onEditClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pencil_icon),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AdminAboutServiceScreenPreview() {
    AdminAboutServiceScreen(
        onEditClick = {},
        onBackClick = {}
    )
}
































