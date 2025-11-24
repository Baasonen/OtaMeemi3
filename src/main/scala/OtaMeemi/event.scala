package OtaMeemi

abstract class Event(name: String):
  var activated = false
  override def toString: String = this.name

  def setActivated(a: Boolean) =
    activated = a

  def checkActive(player: Player): Boolean

  def activateEvent(player: Player): String

