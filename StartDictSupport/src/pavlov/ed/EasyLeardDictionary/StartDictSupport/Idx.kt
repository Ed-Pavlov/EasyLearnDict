package pavlov.ed.EasyLeardDictionary.StartDictSupport

import android.util.Pair
import collection.*
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream

public data class WordDataReference (val offset : Long, val size : Long)

fun createIndex(filePath : String, ifo : Ifo) : PatriciaTrie<String, WordDataReference> {
  val file = File(filePath)
  val stream = DataInputStream(FileInputStream(file))

  val index = PatriciaTrie<String, WordDataReference>(StringKeyAnalyzer.BYTE)
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
    index.put(word, WordDataReference(wordOffset, stream.readInt().toLong()))
  }
  return index
}