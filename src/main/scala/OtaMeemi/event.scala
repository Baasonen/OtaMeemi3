package OtaMeemi

abstract class Event(name: String, area: Area):
  override def toString: String = this.name

  def checkActive: Boolean

  def activateEvent(player: Player): String

