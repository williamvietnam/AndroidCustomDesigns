package com.designs.kotlin.custom.navigation

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.designs.kotlin.R
import com.designs.kotlin.databinding.BottomNavigationView1Binding

class BottomNavigationView1 : LinearLayout {

    private lateinit var binding: BottomNavigationView1Binding
    private lateinit var callBack: CallBack

    fun setCallBack(callBack: CallBack) {
        this.callBack = callBack
    }

    constructor(context: Context) : super(context) {
        initialize(context = context, attributeSet = null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initialize(context = context, attributeSet = attributeSet)
    }

    private fun initialize(context: Context, attributeSet: AttributeSet?) {
        val inflater = LayoutInflater.from(context)
        this.binding = BottomNavigationView1Binding.inflate(inflater, this, true)
        if (attributeSet != null) {
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(
                attributeSet, R.styleable.BottomNavigationView1, 0, 0
            )

            when (typedArray.getInt(R.styleable.BottomNavigationView1_tabSize, 0)) {
                5 -> {
                    this.binding.tab1.visibility = View.VISIBLE
                    this.binding.tab2.visibility = View.VISIBLE
                    this.binding.tab3.visibility = View.VISIBLE
                    this.binding.tab4.visibility = View.VISIBLE
                    this.binding.tab5.visibility = View.VISIBLE
                }
                4 -> {
                    this.binding.tab1.visibility = View.VISIBLE
                    this.binding.tab2.visibility = View.VISIBLE
                    this.binding.tab3.visibility = View.VISIBLE
                    this.binding.tab4.visibility = View.VISIBLE
                    this.binding.tab5.visibility = View.GONE
                }
                3 -> {
                    this.binding.tab1.visibility = View.VISIBLE
                    this.binding.tab2.visibility = View.VISIBLE
                    this.binding.tab3.visibility = View.VISIBLE
                    this.binding.tab4.visibility = View.GONE
                    this.binding.tab5.visibility = View.GONE
                }
                2 -> {
                    this.binding.tab1.visibility = View.VISIBLE
                    this.binding.tab2.visibility = View.VISIBLE
                    this.binding.tab3.visibility = View.GONE
                    this.binding.tab4.visibility = View.GONE
                    this.binding.tab5.visibility = View.GONE
                }
                1 -> {
                    this.binding.tab1.visibility = View.VISIBLE
                    this.binding.tab2.visibility = View.GONE
                    this.binding.tab3.visibility = View.GONE
                    this.binding.tab4.visibility = View.GONE
                    this.binding.tab5.visibility = View.GONE
                }
                0 -> {
                    this.binding.tab1.visibility = View.VISIBLE
                    this.binding.tab2.visibility = View.VISIBLE
                    this.binding.tab3.visibility = View.VISIBLE
                    this.binding.tab4.visibility = View.VISIBLE
                    this.binding.tab5.visibility = View.VISIBLE
                }
            }

            //----------------------------- drawable for tab in here -----------------------------
            val drawableResTab1 = typedArray.getInt(R.styleable.BottomNavigationView1_iconTab1, 0)
            val drawableTab1 = typedArray.getDrawable(R.styleable.BottomNavigationView1_iconTab1)
            if (drawableResTab1 != 0) {
                this.binding.icon1.setImageResource(drawableResTab1)
                this.binding.tab1.visibility = View.VISIBLE
            } else if (drawableTab1 != null) {
                this.binding.icon1.setImageDrawable(drawableTab1)
                this.binding.tab1.visibility = View.VISIBLE
            } else {
                this.binding.tab1.visibility = View.GONE
            }

            val drawableResTab2 = typedArray.getInt(R.styleable.BottomNavigationView1_iconTab2, 0)
            val drawableTab2 = typedArray.getDrawable(R.styleable.BottomNavigationView1_iconTab1)
            if (drawableResTab2 != 0) {
                this.binding.icon1.setImageResource(drawableResTab2)
                this.binding.tab1.visibility = View.VISIBLE
            } else if (drawableTab2 != null) {
                this.binding.icon1.setImageDrawable(drawableTab2)
                this.binding.tab1.visibility = View.VISIBLE
            } else {
                this.binding.tab1.visibility = View.GONE
            }

            val drawableResTab3 = typedArray.getInt(R.styleable.BottomNavigationView1_iconTab3, 0)
            val drawableTab3 = typedArray.getDrawable(R.styleable.BottomNavigationView1_iconTab1)
            if (drawableResTab3 != 0) {
                this.binding.icon1.setImageResource(drawableResTab3)
                this.binding.tab1.visibility = View.VISIBLE
            } else if (drawableTab3 != null) {
                this.binding.icon1.setImageDrawable(drawableTab3)
                this.binding.tab1.visibility = View.VISIBLE
            } else {
                this.binding.tab1.visibility = View.GONE
            }

            val drawableResTab4 = typedArray.getInt(R.styleable.BottomNavigationView1_iconTab4, 0)
            val drawableTab4 = typedArray.getDrawable(R.styleable.BottomNavigationView1_iconTab1)
            if (drawableResTab4 != 0) {
                this.binding.icon1.setImageResource(drawableResTab4)
                this.binding.tab1.visibility = View.VISIBLE
            } else if (drawableTab4 != null) {
                this.binding.icon1.setImageDrawable(drawableTab4)
                this.binding.tab1.visibility = View.VISIBLE
            } else {
                this.binding.tab1.visibility = View.GONE
            }

            val drawableResTab5 = typedArray.getInt(R.styleable.BottomNavigationView1_iconTab5, 0)
            val drawableTab5 = typedArray.getDrawable(R.styleable.BottomNavigationView1_iconTab1)
            if (drawableResTab5 != 0) {
                this.binding.icon1.setImageResource(drawableResTab5)
                this.binding.tab1.visibility = View.VISIBLE
            } else if (drawableTab5 != null) {
                this.binding.icon1.setImageDrawable(drawableTab5)
                this.binding.tab1.visibility = View.VISIBLE
            } else {
                this.binding.tab1.visibility = View.GONE
            }

            //------------------------------- title for tab in here --------------------------------
            val titleResTab1 = typedArray.getInt(R.styleable.BottomNavigationView1_titleTab1, 0)
            val titleTab1 = typedArray.getString(R.styleable.BottomNavigationView1_titleTab1)
            if (titleResTab1 != 0) {
                this.binding.title1.setText(titleResTab1)
                this.binding.title1.visibility = View.VISIBLE
            } else if (titleTab1 != null) {
                this.binding.title1.text = titleTab1
                this.binding.title1.visibility = View.VISIBLE
            } else {
                this.binding.title1.visibility = View.GONE
            }

            val titleResTab2 = typedArray.getInt(R.styleable.BottomNavigationView1_titleTab2, 0)
            val titleTab2 = typedArray.getString(R.styleable.BottomNavigationView1_titleTab2)
            if (titleResTab2 != 0) {
                this.binding.title1.setText(titleResTab2)
                this.binding.title1.visibility = View.VISIBLE
            } else if (titleTab2 != null) {
                this.binding.title1.text = titleTab2
                this.binding.title1.visibility = View.VISIBLE
            } else {
                this.binding.title1.visibility = View.GONE
            }

            val titleResTab3 = typedArray.getInt(R.styleable.BottomNavigationView1_titleTab3, 0)
            val titleTab3 = typedArray.getString(R.styleable.BottomNavigationView1_titleTab3)
            if (titleResTab3 != 0) {
                this.binding.title1.setText(titleResTab3)
                this.binding.title1.visibility = View.VISIBLE
            } else if (titleTab3 != null) {
                this.binding.title1.text = titleTab3
                this.binding.title1.visibility = View.VISIBLE
            } else {
                this.binding.title1.visibility = View.GONE
            }

            val titleResTab4 = typedArray.getInt(R.styleable.BottomNavigationView1_titleTab4, 0)
            val titleTab4 = typedArray.getString(R.styleable.BottomNavigationView1_titleTab4)
            if (titleResTab4 != 0) {
                this.binding.title1.setText(titleResTab4)
                this.binding.title1.visibility = View.VISIBLE
            } else if (titleTab4 != null) {
                this.binding.title1.text = titleTab4
                this.binding.title1.visibility = View.VISIBLE
            } else {
                this.binding.title1.visibility = View.GONE
            }

            val titleResTab5 = typedArray.getInt(R.styleable.BottomNavigationView1_titleTab5, 0)
            val titleTab5 = typedArray.getString(R.styleable.BottomNavigationView1_titleTab5)
            if (titleResTab5 != 0) {
                this.binding.title1.setText(titleResTab5)
                this.binding.title1.visibility = View.VISIBLE
            } else if (titleTab5 != null) {
                this.binding.title1.text = titleTab5
                this.binding.title1.visibility = View.VISIBLE
            } else {
                this.binding.title1.visibility = View.GONE
            }
        }

        this.binding.tab1.setOnClickListener {
            callBack.onTab1Clicked()
        }

        this.binding.tab2.setOnClickListener {
            callBack.onTab2Clicked()
        }

        this.binding.tab3.setOnClickListener {
            callBack.onTab3Clicked()
        }

        this.binding.tab4.setOnClickListener {
            callBack.onTab4Clicked()
        }

        this.binding.tab5.setOnClickListener {
            callBack.onTab5Clicked()
        }
    }

    fun setIconTab1(@DrawableRes res: Int) {
        this.binding.icon1.setImageResource(res)
    }

    fun setIconTab1(drawable: Drawable) {
        this.binding.icon1.setImageDrawable(drawable)
    }

    fun setIconTab2(@DrawableRes res: Int) {
        this.binding.icon2.setImageResource(res)
    }

    fun setIconTab2(drawable: Drawable) {
        this.binding.icon2.setImageDrawable(drawable)
    }

    fun setIconTab3(@DrawableRes res: Int) {
        this.binding.icon3.setImageResource(res)
    }

    fun setIconTab3(drawable: Drawable) {
        this.binding.icon3.setImageDrawable(drawable)
    }

    fun setIconTab4(@DrawableRes res: Int) {
        this.binding.icon4.setImageResource(res)
    }

    fun setIconTab4(drawable: Drawable) {
        this.binding.icon4.setImageDrawable(drawable)
    }

    fun setIconTab5(@DrawableRes res: Int) {
        this.binding.icon5.setImageResource(res)
    }

    fun setIconTab5(drawable: Drawable) {
        this.binding.icon5.setImageDrawable(drawable)
    }

    fun setTitleTab1(@StringRes res: Int) {
        this.binding.title1.setText(res)
    }

    fun setTitleTab1(title: String) {
        this.binding.title1.text = title
    }

    fun setTitleTab2(@StringRes res: Int) {
        this.binding.title2.setText(res)
    }

    fun setTitleTab2(title: String) {
        this.binding.title2.text = title
    }

    fun setTitleTab3(@StringRes res: Int) {
        this.binding.title3.setText(res)
    }

    fun setTitleTab3(title: String) {
        this.binding.title3.text = title
    }

    fun setTitleTab4(@StringRes res: Int) {
        this.binding.title4.setText(res)
    }

    fun setTitleTab4(title: String) {
        this.binding.title4.text = title
    }

    fun setTitleTab5(@StringRes res: Int) {
        this.binding.title5.setText(res)
    }

    fun setTitleTab5(title: String) {
        this.binding.title5.text = title
    }

    interface CallBack {
        fun onTab1Clicked()

        fun onTab2Clicked()

        fun onTab3Clicked()

        fun onTab4Clicked()

        fun onTab5Clicked()
    }
}