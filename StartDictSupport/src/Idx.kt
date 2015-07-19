import java.io.DataInputStream
import java.io.File
import java.util.*

data class Idx(val word : String, val offset : Long, val size : Long )

fun createIdx(filePath : String, ifo : Ifo) : HashMap<String, Pair<Long, Long>>{
  val file = File(filePath)
  val stream = DataInputStream(file.inputStream())

  val map = HashMap<String, Pair<Long, Long>>()
  while(stream.available() > 0){
    val buffer = ByteArray(257)
    var byte : Byte;
    var i = 0
    do{
      byte = stream.readByte()
      buffer[i++] = byte
    } while(byte.compareTo(0) != 0)
//    byte = stream.readByte()

    val word = String(buffer, 0, i - 1).toLowerCase()

    val wordOffset = if (ifo.idxOffsetBits == BitCount.x64) stream.readLong() else stream.readInt().toLong()
    map[word] = Pair(wordOffset, stream.readInt().toLong())
  }
  return map;
}
