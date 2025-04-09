package com.example.tcpm.project.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.tcpm.R
import com.example.tcpm.project.data.Project

@Composable
fun ProjectTile(project: Project) {
    val PROJECT_IMAGE_SIZE = 64.dp
    val PADDING = 6.dp
    val CARD_HEIGHT = PROJECT_IMAGE_SIZE + PADDING + PADDING

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(CARD_HEIGHT),
        shape = RoundedCornerShape(6.dp),
        onClick = {}) {
        Row(
            modifier = Modifier
                .padding(PADDING)
                .fillMaxSize()
        ) {
            if (project.imgUrl.isBlank()) {
                Icon(
                    imageVector = Icons.Filled.Image,
                    contentDescription = stringResource(R.string.desc_icon_project),
                    modifier = Modifier
                        .size(PROJECT_IMAGE_SIZE)
                        .background(
                            color = colorResource(R.color.theme_green),
                            shape = RoundedCornerShape(6.dp)
                        ),
                    tint = Color.White
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(project.imgUrl),
                    contentDescription = stringResource(R.string.desc_icon_project),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(PROJECT_IMAGE_SIZE)
                        .clip(shape = RoundedCornerShape(6.dp)),
                )
            }
            Column(modifier = Modifier.padding(horizontal = 6.dp).fillMaxHeight()) {
                Text(
                    project.title,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(project.description, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}