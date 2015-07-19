import org.junit.Test

class CreateIndexTest{
  Test
  fun MemoryTest(){
    val name = "data\\BeerDeRu"
    val ifo = Ifo.create(name + ".ifo")

    val usedMemory = memory.usedMemory
    val index = createIdx(name + ".idx", ifo)
    println(memory.usedMemory - usedMemory)
  }
}