import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.io.File
import java.util.*

data class Ifo(val version: Version,
               val name: String,
               val wordCount: Long,
               val synWordCount: Int?,
               val idxFileSize: Long,
               val idxOffsetBits: BitCount,

               val dictDataType: CharArray?,
               val date: DateTime,
               val author: String?,
               val email: String?,
               val website: String?,
               val description: String?) {


  companion object {
    fun create(filePath: String): Ifo {
      val ifoFile = File(filePath)

      val map: Map<String, String> = ifoFile.reader().useLines {
        it.filter { it.contains('=') }.map { it.split('=') }.toMap({ it[0] }, { it[1] })
      }

      val bitCountString = map[DictProperty.idxOffsetBits]
      val bitCount = if (bitCountString == null || bitCountString == "32") BitCount.x32 else BitCount.x64;

      return Ifo(
        Version(map.get(DictProperty.version)),
        map[DictProperty.name] ?: "",
        map[DictProperty.wordCount]!!.toLong(),
        parseInt(map[DictProperty.synWordCount]),
        map[DictProperty.idxFileSize]!!.toLong(),
        bitCount,
        map[DictProperty.sameTypeSequence]?.toCharArray(),
        DateTimeFormat.forPattern("yyyy.mm.dd") parseDateTime map[DictProperty.date],
        map[DictProperty.author],
        map[DictProperty.email],
        map[DictProperty.website],
        map[DictProperty.description])
    }
  }
}