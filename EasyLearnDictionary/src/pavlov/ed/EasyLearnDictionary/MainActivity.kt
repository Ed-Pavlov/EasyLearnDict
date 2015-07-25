package pavlov.ed.EasyLearnDictionary

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import org.jetbrains.anko.*

class MainActivity : Activity(){
  override fun onCreate(state : Bundle?){
    super<Activity>.onCreate(state)

    linearLayout(){
      orientation = LinearLayout.VERTICAL

      textView("Kotlin ANKO").layoutParams(width = matchParent, height = wrapContent)
    }
  }
}