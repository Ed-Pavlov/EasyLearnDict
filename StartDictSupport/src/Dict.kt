import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*

class Dict public constructor(filePath : String, dataTypes : CharArray?){

  val reader : FileInputStream
  val dataTypes : CharArray?

  init{
    reader = FileInputStream(File(filePath))
    this.dataTypes = dataTypes
  }

  public fun ReadData(offset : Long, size : Long) : List<String> {
    reader.getChannel().position(offset)
    val endPosition = offset + size

    val data = ArrayList<String>()

    if (dataTypes == null) {
      while(reader.getChannel().position() < endPosition){
        val dataType = reader.read().toChar()
        data.add(ReadData(dataType, { -> reader.readString()}))
      }
    }
    else {
      for(dataType in dataTypes) {
        val readString = if(dataType == dataTypes.last())
          { ->  reader.readString((offset + size - reader.getChannel().position()).toInt())}
        else { ->  reader.readString()}

        data.add(ReadData(dataType, readString))
      }
    }
    return data;
  }

  private fun ReadData(dataType : Char, readString : () -> String): String {
    return when(dataType){
      'm' -> readString()
      'l' -> "locale encoding?"
      'g' -> "pango: " + readString()
      't' -> readString()
      'x' -> "xdxf: " + readString()
      else -> "unknonw yet: " + dataType
    }
  }
}