package pavlov.ed.EasyLearnDictionary

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import org.jetbrains.anko.*

class MainActivity : Activity(){
  override fun onCreate(state : Bundle?){
    super<Activity>.onCreate(state)

    linearLayout{
      orientation = LinearLayout.VERTICAL

      relativeLayout{
        gravity = Gravity.TOP or Gravity.FILL_HORIZONTAL
        orientation = LinearLayout.HORIZONTAL

        textView("Top toolbar").layoutParams(width = matchParent)
        button("Dic"){
          onClick {
//            val ifo = Ifo.Create()
          }
        }
        .layoutParams{alignParentRight()}

//        editText("Enter a word").layoutParams(width = )
      }

      textView("Content").layoutParams(width = matchParent, height = matchParent)
    }
  }
}