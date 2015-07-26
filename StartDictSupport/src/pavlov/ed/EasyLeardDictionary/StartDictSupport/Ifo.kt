package pavlov.ed.EasyLeardDictionary.StartDictSupport

import java.io.File
import java.io.FileReader
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

public data class Ifo(val version: Version,
               val name: String,
               val wordCount: Long,
               val synWordCount: Int?,
               val idxFileSize: Long,
               val idxOffsetBits: BitCount,

               val dictDataType: CharArray?,
               val date: Date,
               val author: String?,
               val email: String?,
               val website: String?,
               val description: String?) {


  companion object {
    fun create(filePath: String): Ifo {
      val ifoFile = FileReader(File(filePath))

      val map: Map<String, String> = ifoFile.useLines {
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
        SimpleDateFormat("yyyy.mm.dd").parse(map[DictProperty.date]),
        map[DictProperty.author],
        map[DictProperty.email],
        map[DictProperty.website],
        map[DictProperty.description])
    }
  }
}