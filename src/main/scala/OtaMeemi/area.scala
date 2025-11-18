package OtaMeemi

abstract class Area(name: String):
  val subDesc: Vector[String]
  private var currentDepth = 0

  var items: Vector[Item] = Vector()
  val neighbors: Vector[(Area, Int)]
  val event: Vector[Event] = Vector()

  def description: String = this.subDesc(0)

  def examineArea(): String =
    if (subDesc(currentDepth + 1).nonEmpty) then
      currentDepth += 1
      subDesc(currentDepth)
    else
      "Ei pääse enää syvemmälle bro \uD83D\uDC80"

  def move(destination: Area): Boolean =
    neighbors.contains(destination)

  def getNeighbors: Vector[Area] =
    neighbors.map(_._1)

  def availableItems = this.items

  def getDepth = currentDepth


  override def toString: String = this.name
