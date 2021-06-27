package com.redman.cards

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    lateinit var front_anim:AnimatorSet
    lateinit var back_anim:AnimatorSet
    lateinit var button: Button
    var isFront = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scale:Float = applicationContext.resources.displayMetrics.density

        var frontcard : CardView = this.findViewById(R.id.cardViewfront)
        var backcard : CardView = this.findViewById(R.id.cardViewback)
        var frontbutton : Button = this.findViewById(R.id.buttonfront)
        var backbutton : Button = this.findViewById(R.id.buttonback)
        var incomebutton : Button = this.findViewById(R.id.button4)

        button = this.findViewById<Button>(R.id.menu_button)
        button.setOnClickListener { v: View ->
            showMenu(v, R.menu.category_dropdown) }

        frontcard.cameraDistance = 8000 * scale
        backcard.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.back_animator) as AnimatorSet

        frontbutton.setOnClickListener {

            if (isFront) {
                front_anim.setTarget(frontcard)
                back_anim.setTarget(backcard)
                front_anim.start()
                back_anim.start()
                isFront = false
            }

        }

        backbutton.setOnClickListener {

            if (!isFront) {
                front_anim.setTarget(backcard)
                back_anim.setTarget(frontcard)
                front_anim.start()
                back_anim.start()
                isFront = true
            }


        }



        incomebutton.setOnClickListener {

            incomebutton.setBackgroundColor(Color.parseColor("#64DD17"));

        }







    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(this, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            button.setText(menuItem.title)
            true

        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()


    }


}