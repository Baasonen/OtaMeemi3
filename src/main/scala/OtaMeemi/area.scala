package OtaMeemi

abstract class Area(name: String):
  val initialDesc: String
  private val subDesc: Vector[String] = Vector()
  private var currentDepth = 0

  var items: Vector[Item] = Vector()
  val neighbors: Vector[(Area, Int)]
  val event: Option[Event]

  def description: String = this.initialDesc

  def examineArea(): String =
    if (subDesc(currentDepth + 1).nonEmpty) then
      currentDepth += 1
      subDesc(currentDepth)
    else
      "Ei pääse enää syvemmälle bro *pääkalloemoji*"

  def move(destination: Area): Boolean =
    neighbors.contains(destination)

  def getNeighbors: Vector[Area] =
    neighbors.map(_._1)

  def availableItems = this.items

  def getDepth = currentDepth


  override def toString: String = this.name
