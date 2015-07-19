import java.io.InputStream
import java.util.*

fun <T> catch2null(func : () -> T) : T{
  try{
    return func()
  } catch(_: Exception){
    return null
  }
}

fun parseInt(string : String?) = if (string == null) null else
catch2null {
  string.toInt()
}

fun Int.toLongAsUnsigned() = this.toLong() and 0x00000000ffffffff

fun InputStream.readString(): String {
  val buffer = ArrayList<Byte>()

  do{
    val byte = this.read().toByte();
    buffer.add(byte);
  } while(byte.compareTo(0) != 0)
  return String(buffer.toByteArray())
}

fun InputStream.readString(length : Int): String{
  val buffer = ByteArray(length)
  this.read(buffer, 0, buffer.count())
  return String(buffer)
}