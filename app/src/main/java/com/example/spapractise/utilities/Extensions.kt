package com.example.spapractise.utilities

import android.content.Context
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.spapractise.R

class Extensions {
    fun TextView.setSpan(
        clickableString: String,
        clickableFont: Int,
        clickableColor: Int,
        isUnderline: Boolean = false,
        onClick: () -> Unit
    ) {

        val clickablePartStart = text.indexOf(clickableString)

        val spannableString = SpannableString(text)
        Log.e("TAG", "setSpan1: ${spannableString}", )
        Log.e("TAG", "setSpan: ${clickablePartStart}", )
        Log.e("TAG", "setSpan3: ${clickableString}", )
        highlightColor = ContextCompat.getColor(context, R.color.white)


        spannableString.setSpan(
            CustomTypefaceSpan(ResourcesCompat.getFont(context!!, clickableFont), ""),
            clickablePartStart,
            clickablePartStart + clickableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) = onClick.invoke()
            override fun updateDrawState(ds: TextPaint) {
                ds.color = ContextCompat.getColor(context, clickableColor)
                if (isUnderline)
                    ds.isUnderlineText = true
            }
        }
        spannableString.setSpan(
            clickableSpan,
            clickablePartStart,
            clickablePartStart + clickableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        this.text = spannableString
        this.movementMethod = LinkMovementMethod.getInstance()
    }


}
fun Context.getColorFromResource(@ColorRes colorInt: Int): Int {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        this.resources.getColor(colorInt, theme)
    else
        this.resources.getColor(colorInt)
}