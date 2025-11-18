package OtaMeemi

class Area(name: String, desc: Vector[String],var connections: Vector[(Area, Int)]):
  private var events = Vector[Event]()
  private var currentDepth = 0

  override def toString: String = this.name

  def initialDescription = desc.head
  def getConnections = connections

  def addEvent(eventToAdd: Event) = events = events.appended(eventToAdd)

