package OtaMeemi

abstract class Area(name: String):
  val initialDesc: String
  val subDesc: Vector[String] = Vector()
  var currentDepth = 0

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
    if neighbors.contains(destination) then
      true
    else
      false

  def getNeighbors: Vector[Area] =
    neighbors.map(_._1)

  def availableItems = this.items


  override def toString: String = this.name
