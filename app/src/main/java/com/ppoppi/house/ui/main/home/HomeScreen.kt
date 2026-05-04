package com.ppoppi.house.ui.main.home

import android.graphics.BlurMaskFilter
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ppoppi.house.R
import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.Disease
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.component.PpoPpiTextField
import com.ppoppi.house.ui.main.home.component.DiagnosisCard
import com.ppoppi.house.ui.main.home.component.DiseaseCard
import com.ppoppi.house.ui.main.home.component.PetAddButton
import com.ppoppi.house.ui.main.home.component.PetButton
import com.ppoppi.house.ui.main.home.component.ToDiagnosisCard
import com.ppoppi.house.ui.main.setting.edit.PetInfoActivity
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary200
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.White
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun HomeScreen(
    navigateToDiagnosis: () -> Unit,
    modifier: Modifier = Modifier,
    date: LocalDate = LocalDate.now(),
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var keyword by remember { mutableStateOf("") }
    var isInitialized by remember { mutableStateOf(false) }
    val diseases by viewModel.diseases.collectAsState()
    val todayDiagnosis by viewModel.todayDiagnosis.collectAsState()

    LaunchedEffect(keyword) {
        if (!isInitialized) {
            isInitialized = true
            return@LaunchedEffect
        }
        viewModel.loadDiseases(keyword)
    }
    val pets =
        listOf(
            Pet(
                name = "뽀삐",
                species = SPECIES.DOG,
                breed = "뽀삐",
                age = 1,
                sex = SEX.MALE,
                color = COLOR.PRIMARY50,
            ),
            Pet(
                name = "뽀빠",
                species = SPECIES.CAT,
                breed = "뽀삐",
                age = 1,
                sex = SEX.MALE,
                color = COLOR.PRIMARY50,
            ),
        )
    var selectedPet by remember { mutableStateOf(pets.first()) }

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .padding(18.dp),
        contentAlignment = Alignment.TopEnd,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_home),
            contentDescription = null,
            modifier = Modifier,
            tint = Primary200,
        )
    }
    Column(
        modifier =
            modifier
                .padding(top = 26.dp)
                .padding(horizontal = 26.dp),
    ) {
        Text(
            text = "홈",
            style = PpoPpiTheme.typography.headline1,
            color = Black,
        )

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            pets.forEach { pet ->
                PetButton(
                    onClick = { selectedPet = pet },
                    pet = pet,
                    isSelected = selectedPet == pet,
                )
            }

            if (pets.size < 3) {
                PetAddButton(
                    onClick = {
                        val intent = PetInfoActivity.newIntent(context)
                        context.startActivity(intent)
                    },
                )
            }
        }

        Text(
            text = "${date.monthValue}월 ${date.dayOfMonth}일 (${
                date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
            })",
            style = PpoPpiTheme.typography.title1,
            color = Primary400,
            modifier = Modifier.padding(top = 12.dp),
        )

        if (todayDiagnosis != null) {
            DiagnosisCard(
                navigateToDiagnosis = navigateToDiagnosis,
                diagnosis = todayDiagnosis!!,
                modifier = Modifier.padding(top = 18.dp),
            )
        } else {
            ToDiagnosisCard(
                onClick = (navigateToDiagnosis),
                name = selectedPet.name,
                modifier = Modifier.padding(top = 18.dp),
            )
        }

        Text(
            text = "오늘의 질병",
            style = PpoPpiTheme.typography.title1,
            color = Black,
            modifier = Modifier.padding(top = 30.dp),
        )

        DiseaseTextField(
            keyword = keyword,
            onValueChanged = { keyword = it },
        )

        DiseaseSection(
            diseases = diseases,
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    PpoPpiTheme {
        HomeScreen(navigateToDiagnosis = {})
    }
}

@Composable
private fun DiseaseSection(
    diseases: List<Disease>?,
    modifier: Modifier = Modifier,
) {
    when {
        diseases == null -> Unit
        diseases.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "검색 결과가 없어요",
                    style = PpoPpiTheme.typography.body1,
                    color = Gray300,
                    textAlign = TextAlign.Center,
                )
            }
        }
        else -> {
            val pagerState = rememberPagerState(pageCount = { diseases.size })
            LaunchedEffect(diseases) {
                pagerState.scrollToPage(0)
            }
            Column(modifier = modifier) {
                HorizontalPager(state = pagerState) { page ->
                    DiseaseCard(disease = diseases[page])
                }
                if (diseases.size > 1) {
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        repeat(diseases.size) { index ->
                            Box(
                                modifier =
                                    Modifier
                                        .padding(horizontal = 3.dp)
                                        .size(if (pagerState.currentPage == index) 8.dp else 6.dp)
                                        .background(
                                            color = if (pagerState.currentPage == index) Primary400 else Gray200,
                                            shape = CircleShape,
                                        ),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DiseaseTextField(
    keyword: String,
    onValueChanged: (keyword: String) -> Unit,
) {
    PpoPpiTextField(
        value = keyword,
        onValueChanged = { onValueChanged(it) },
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .drawBehind {
                    drawIntoCanvas { canvas ->
                        val paint = Paint()
                        paint.asFrameworkPaint().apply {
                            isAntiAlias = true
                            color = Color.argb(64, 0, 0, 0)
                            maskFilter = BlurMaskFilter(2.dp.toPx(), BlurMaskFilter.Blur.NORMAL)
                        }
                        canvas.drawRoundRect(
                            left = 0f,
                            top = 2.dp.toPx(),
                            right = size.width,
                            bottom = size.height + 2.dp.toPx(),
                            radiusX = 16.dp.toPx(),
                            radiusY = 16.dp.toPx(),
                            paint = paint,
                        )
                    }
                }.background(White, shape = RoundedCornerShape(16.dp))
                .border(1.dp, Gray100, RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp, vertical = 14.dp),
        maxLength = 10,
        placeHolder = "궁금한 질병을 검색해 보세요",
        prefix = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                modifier = it.size(14.dp),
                tint = Gray300,
            )
        },
    )
}
