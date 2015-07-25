import collection.PatriciaTrie
import collection.StringKeyAnalyzer
import collection.Trie
import java.io.DataInputStream
import java.io.File
import java.util.*

fun createIndex(filePath : String, ifo : Ifo) : PatriciaTrie<String, Pair<Long, Long>> {
  val file = File(filePath)
  val stream = DataInputStream(file.inputStream())

  val index = PatriciaTrie<String, Pair<Long, Long>>(StringKeyAnalyzer.BYTE)
  while(stream.available() > 0){
    val buffer = ByteArray(257)
    var byte : Byte;
    var i = 0
    do{
      byte = stream.readByte()
      buffer[i++] = byte
    } while(byte.compareTo(0) != 0)

    val word = String(buffer, 0, i - 1)

    val wordOffset = if (ifo.idxOffsetBits == BitCount.x64) stream.readLong() else stream.readInt().toLong()
    index.put(word, Pair(wordOffset, stream.readInt().toLong()))
  }
  return index
}