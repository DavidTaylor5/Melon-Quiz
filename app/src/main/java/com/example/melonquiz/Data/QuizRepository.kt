package com.example.melonquiz.Data

import com.example.melonquiz.Model.MelonQuestion
import com.example.melonquiz.R

object QuizRepository {

    val melonNames = listOf(
        R.string.ambrosia_melon,
        R.string.apollo_melon,
        R.string.autumn_melon,
        R.string.bailan_melon,
        R.string.banana_melon,
        R.string.bitter_melon,
        R.string.cantaloupe,
        R.string.casaba_melon,
        R.string.crane_melon,
        R.string.cucamelon_melon,
        R.string.gac_melon,
        R.string.galia_melon,
        R.string.honey_globe_melon,
        R.string.honeydew_melon,
        R.string.santa_claus_melon
    )

    val melonQuestions = listOf(
        MelonQuestion(R.string.ambrosia_melon, R.drawable.ambrosia),
        MelonQuestion(R.string.apollo_melon, R.drawable.apollo),
        MelonQuestion(R.string.autumn_melon, R.drawable.autumn_sweet),
        MelonQuestion(R.string.bailan_melon, R.drawable.bailan),
        MelonQuestion(R.string.banana_melon, R.drawable.banana),
        MelonQuestion(R.string.bitter_melon, R.drawable.bitter),
        MelonQuestion(R.string.cantaloupe, R.drawable.cantaloupe),
        MelonQuestion(R.string.casaba_melon, R.drawable.casaba),
        MelonQuestion(R.string.crane_melon, R.drawable.crane),
        MelonQuestion(R.string.cucamelon_melon, R.drawable.cucamelon),
        MelonQuestion(R.string.gac_melon, R.drawable.gac),
        MelonQuestion(R.string.galia_melon, R.drawable.galia),
        MelonQuestion(R.string.honey_globe_melon, R.drawable.honey_globe),
        MelonQuestion(R.string.honeydew_melon, R.drawable.honeydew),
        MelonQuestion(R.string.santa_claus_melon, R.drawable.santa_claus),
    )



}