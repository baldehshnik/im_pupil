package com.sparkfusion.services.admin.about.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.about.R
import com.sparkfusion.services.admin.about.component.AddBlockItem
import com.sparkfusion.services.admin.about.model.AboutBlockModel

@Composable
fun AdminEditAboutServiceScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val items = listOf(
        AboutBlockModel("", "Desctifjshfbdjhg b sgh sdg dskhfkjdshf hkdsh fhsdjkhf jkhsjdkfkjs d"),
        AboutBlockModel("2", ""),
        AboutBlockModel("", "hdf zhz dzf hzdfhdfh zdfh zdfhz fzh"),
        AboutBlockModel("3", "hdf hdzfhdfh zfh  zfz "),
        AboutBlockModel("2", "hdfzh dzfhr ehzreh e")
    )

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {
            item {
                TopBar(title = "Edit", onBackClick = onBackClick)

                SFProRoundedText(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 12.dp),
                    color = descriptionColor(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    content = "There are two ways to describe: adding an image and adding a text description. This is done using blocks, the number of which is as many as you wish. You can combine text and image, or add just one option (empty blocks will not be added)."
                )
            }

            items(items.size) { index ->
                AddBlockItem(item = items[index])
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    TextButton(onClick = { /*TODO*/ }) {
                        SFProRoundedText(
                            content = "Add block",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            onClick = { }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.check_icon),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AdminEditAboutServiceScreenPreview() {
    AdminEditAboutServiceScreen(
        onBackClick = {}
    )
}































