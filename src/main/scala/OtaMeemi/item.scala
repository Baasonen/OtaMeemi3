package OtaMeemi

abstract class Item(name: String, desc: String, value: Double, weight: Int):

  override def toString = this.name
  def examine() = this.desc

  def use(player: Player): String

  def combine(player: Player, combineWith: Item): String

  def getValue = value

  def eat(player: Player): String 
