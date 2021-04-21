package com.androidgang.mymakinglayout.scenes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.androidgang.mymakinglayout.R

//Класс МэйнАктивити наследуется от класса АппКомпатАктивити
class MainActivity : AppCompatActivity() {
    //переопределяем метод ЖЦ
    override fun onCreate(savedInstanceState: Bundle?) {
        //Передаем аргумент в родительский метод и вызываем его
        super.onCreate(savedInstanceState)

        //Скрываем ActionBar
        supportActionBar?.hide()
        //Устанавливаем макет для класса MainActivity
        setContentView(R.layout.activity_main)

        //Получаем navHostFragment по id
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //Получаем navController у navHostFragment-a
        val navController = navHostFragment.navController
        //Метод в который мы передаем в качестве агрумента navController
        setupNavigationController(navController)
    }

    //Метод принимаемый в качестве параметра навКонтроллер
    private fun setupNavigationController(navController: NavController) {
        //Устранавливаем экшенБар с навКонтроллером, передавая в качестве аргументов контекст(МэйнАктивити) и НавКонтроллер
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}