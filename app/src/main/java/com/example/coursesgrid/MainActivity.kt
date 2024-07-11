package com.example.coursesgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coursesgrid.data.DataSource
import com.example.coursesgrid.model.Topic
import com.example.coursesgrid.ui.theme.CoursesGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesGridTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoursesGridLayout(
                        topics = DataSource.topics,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CoursesGridLayout(topics: List<Topic>, modifier: Modifier = Modifier) {
    val minColumnSize = 150.dp
    val interCardPadding = 8.dp

    Column {
        Spacer(modifier = Modifier.height(64.dp))

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(minColumnSize),
            verticalItemSpacing = interCardPadding,
            horizontalArrangement = Arrangement.spacedBy(interCardPadding),
            modifier = modifier
        ) {
            items(topics) { topic ->
                TopicCard(topic = topic)
            }
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    val padding = 16.dp
    val halfPadding = 8.dp
    val imageSize = 68.dp

    Card(modifier = modifier.height(imageSize)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(topic.imageId),
                contentDescription = null,
                modifier = Modifier.size(imageSize)
            )

            Column(modifier = Modifier.padding(start = padding, top = padding, end = padding)) {
                Text(
                    text = stringResource(topic.topicId),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = halfPadding)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null
                    )
                    Text(
                        text = topic.numClasses.toString(),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(start = halfPadding)
                            .wrapContentSize()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoursesGridPreview() {
    CoursesGridTheme {
        TopicCard(DataSource.topics[12])
//        CoursesGridLayout(topics = DataSource.topics)
    }
}
