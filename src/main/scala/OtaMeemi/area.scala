package OtaMeemi

abstract class Area(name: String):
  val initialDesc: String
  val subDesc: Vector[String] = Vector()
  var currentDepth = 0

  def items: Vector[Item] = Vector()
  def neighbors: Vector[Area]

  def examine(): String =
    if (subDesc(currentDepth +1).exists) then
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
    neighbors

  override def toString: String = this.name
