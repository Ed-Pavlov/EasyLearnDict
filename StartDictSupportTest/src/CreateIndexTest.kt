import collection.Cursor
import collection.PatriciaTrie
import collection.StringKeyAnalyzer
import org.junit.Assert
import org.junit.Test
import java.io.DataInputStream
import java.io.File
import org.hamcrest.core.Is
import java.util.*

class CreateIndexTest{
  val name = "data\\UniversalDeRu"

  Test
  fun IndexShouldReturnRightData(){
    // --arrange
    val ifo = Ifo.create(name + ".ifo")
    val usedMemory = memory.usedMemory

    // --act
    val trie = createIndex(name + ".idx", ifo)
    println("Memory used by index: " + (memory.usedMemory - usedMemory))

    // --assert
    val index = createExpected(name + ".idx", ifo)
        for((word, expected) in index)
          Assert.assertThat(word, trie.get(word), Is.`is`(expected))
  }

  private fun createExpected(filePath : String, ifo : Ifo) : HashMap<String, Pair<Long, Long>> {
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

      val word = String(buffer, 0, i - 1)

      val wordOffset = if (ifo.idxOffsetBits == BitCount.x64) stream.readLong() else stream.readInt().toLong()
      if(map.containsKey(word))
        throw Exception(word)
      map[word] = Pair(wordOffset, stream.readInt().toLong())
    }
    return map;
  }
}