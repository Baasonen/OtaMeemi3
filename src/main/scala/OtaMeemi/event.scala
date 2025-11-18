package OtaMeemi

abstract class Event(name: String):
  override def toString: String = this.name

  def checkActive(player: Player): Boolean

  def activateEvent(player: Player): Any

