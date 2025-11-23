package OtaMeemi

class Area(name: String, desc: Vector[String],var connections: Vector[(Area, Int)], tradingAllowed: Boolean):
  private var events = Vector[Event]()
  private var currentDepth = 0
  private var items = Vector[Item]()

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
      "There doesn't seem to be much more to see here"

  def escape =
    if currentDepth != 0 then
      currentDepth = 0
      "You escaped"
    else
      "Escape?, to where"

  def addItem(itemToAdd: Item) =
    items = items.appended(itemToAdd)

  def removeItem(itemToRemove: String): Boolean =
    if items.map(_.toString.toLowerCase).contains(itemToRemove) then
      val indexToRemove = items.map(_.toString.toLowerCase).indexOf(itemToRemove)
      items = items.patch(indexToRemove, Nil, 1)
      true
    else
      false

  def getItems = items

  def getToString = items.mkString("\n")
  def getCurrentDepth: Int = currentDepth

  def isTradingAllowed = tradingAllowed

  def addEvent(eventToAdd: Event) = events = events.appended(eventToAdd)
