package OtaMeemi

class Area(name: String, desc: Vector[String],var connections: Vector[(Area, Int)]):
  private var events = Vector[Event]()
  private var currentDepth = 0

  override def toString: String = this.name

  def initialDescription = desc.head
  def getConnections = connections

  def examine(player: Player): String =
    if events.forall(_.checkActive(player)) && events.nonEmpty then
      val activeEvents = events.sortBy(_.checkActive(player))
      activeEvents.head.activateEvent(player)
    else if (currentDepth < desc.length - 1) then
      currentDepth += 1
      desc(currentDepth)
    else
      "There doesn't seem to bee much more to see here"

  def escape =
    if currentDepth != 0 then
      currentDepth = 0
      "You escaped"
    else
      "Escape?, to where"

  def addEvent(eventToAdd: Event) = events = events.appended(eventToAdd)

