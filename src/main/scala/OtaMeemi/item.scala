package OtaMeemi

abstract class Item(name: String, desc: String, value: Double, weight: Int):

  override def toString = this.name
  def examine() = this.desc

  def use(player: Player): Boolean
