import org.hamcrest.MatcherAssert.*
import org.hamcrest.core.Is.`is`
import org.testng.annotations.Test
import pavlov.ed.EasyLeardDictionary.StartDictSupport.*

class CreateIfoTest {
  val basePath = "Test\\data"

  Test
  public fun AllPropertiesTest(){
    val ifo = Ifo.create(basePath + "\\All properties test.ifo")

    assertThat("version", ifo.version, `is`(Version(3, 2, 1)))
    assertThat("wordCount", ifo.wordCount, `is`(35L))
    assertThat("dictDataType", ifo.dictDataType, `is`(charArrayOf('x', 'g')))
    assertThat("idxFileSize", ifo.idxFileSize, `is`(67L))
    assertThat("idxOffsetBits", ifo.idxOffsetBits, `is`(BitCount.x64))
  }

  Test
  public fun BigNumberTest(){
    val ifo = Ifo.create(basePath + "\\big numbers test.ifo")

    assertThat("version is 2.4.2", ifo.version, `is`(Version(2, 4, 2)))
    assertThat("dictDataType is 'xtM'", ifo.dictDataType, `is`(charArrayOf('x', 't', 'M')))
    assertThat("idxFileSize is 1099511627775", ifo.idxFileSize, `is`(1099511627775))
    assertThat("idxOffsetBits is x32", ifo.idxOffsetBits, `is`(BitCount.x32))
    assertThat("wordCount is 4294967295", ifo.wordCount, `is`(4294967295))
  }
}