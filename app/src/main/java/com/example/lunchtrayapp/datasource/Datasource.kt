package com.example.lunchtrayapp.datasource

import com.example.lunchtrayapp.R
import com.example.lunchtrayapp.model.Tray

object Datasource {
    val entrees = listOf<Tray>(
        Tray(R.string.cauliflower, R.string.cauliflower_desc, 23.0),
        Tray(R.string.three_bean_chili, R.string.three_bean_chili_desc, 25.0),
        Tray(R.string.mushroom_pasta, R.string.mushroom_pasta_desc, 19.0),
        Tray(R.string.spicy_black_bean_skillet, R.string.spicy_black_bean_skillet_desc, 18.0)
    )

    val sideDishes = listOf<Tray>(
        Tray(R.string.summer_salad, R.string.summer_salad_desc, 13.0),
        Tray(R.string.butternut_squash_soup, R.string.butternut_squash_soup_desc, 7.0),
        Tray(R.string.spicy_potatoes, R.string.spicy_potatoes_desc, 17.0),
        Tray(R.string.coconut_rice, R.string.coconut_rice_desc, 14.0)
    )

    val accompaniments = listOf<Tray>(
        Tray(R.string.lunch_roll, R.string.lunch_roll_desc, 7.0),
        Tray(R.string.mixed_berries, R.string.mixed_berries_desc, 9.0),
        Tray(R.string.pickled_veggies, R.string.pickled_veggies_desc, 8.0)
    )

}