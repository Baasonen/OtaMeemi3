package OtaMeemi

abstract class Debuff(name: String, duration: Int, startTime: Int):
  override def toString: String = this.name

  def isActive(currentTime: Int) = (startTime + duration) > currentTime

class DokattuDebuff(duration: Int, startTime: Int) extends Debuff("Dokattu", duration, startTime)

class ParinaDebuff(duration: Int, startTime: Int) extends Debuff("Pärinä", duration, startTime)

