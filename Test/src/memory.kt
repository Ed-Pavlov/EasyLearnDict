class memory{
  companion object{
    public val usedMemory: Long
      get() {
        val runtime = Runtime.getRuntime()
        val totalMemory = runtime.totalMemory()
        val freeMemory = runtime.freeMemory()
        return totalMemory - freeMemory
      }
  }
}