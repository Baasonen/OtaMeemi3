package OtaMeemi
import scala.util.Random

class GameWorld:
  private var currentTime = (8*60)+15

  object spagu extends Event("spagumayhem", taafa):
    override def checkActive: Boolean =
      currentTime > (11*60)

    override def activateEvent(player: Player): Any =
      val itemToDrop = player.inventory(Random.between(0, player.inventory.length - 1))
      player.drop(itemToDrop)
      s"Oivoi, jonon ihmismassojen seassa joku pääsi nappaamaan taskustasi ${itemToDrop}:n"

  object liukuhihna extends Event("Liukuhihna", smokki):
    override def checkActive: Boolean =
      currentTime > (19*60)

    override def activateEvent(player: Player): String =
      "Teema on leffaa bro"

  object stigulaatio extends Event("stigulaatio", ok20):
    override def checkActive: Boolean =
      currentTime > (17*60)

    override def activateEvent(player: Player): String =
      player.removeItem("phone")
      player.addDebuff()
      "Mitö vi..,,., olemn näköjm vhäm dokatui(mut ei sale sle pljaojn....)"

  object taafa extends Area("Taafa"):
    val neighbors = Vector((kandi, 5),(dipoli, 2))
    val subDesc = Vector()
    val event = Some(spagu)

  object smokki extends Area("Smökki"):
    val neighbors = Vector((otaranta, 5), (ok20, 2))
    val subDesc = Vector()

  object ok20 extends Area("Ok20"):
    val neighbors = Vector(kandi,smokki,rantasauna)
    val subDesc = Vector()

  object dipoli extends Area("Dipoli"):
    val neighbors = Vector(taafa,kandi,knmcdonalds)
    val subDesc = Vector()

  object knmcdonalds extends Area("Keilaniemi Mcdonalds"):
    val neighbors = Vector(dipoli,sornainen,klahtimetro)
    val subDesc = Vector()

  object sornainen extends Area("Sörnäisten metroasema"):
    val neighbors = Vector(knmcdonalds,klahtimetro)
    val subDesc = Vector()

  object rantasauna extends Area("Rantasauna"):
    val neighbors = Vector(narnia,rantasauna,ok20)
    val subDesc = Vector()

  object klahtimetro extends Area("Kivenlahden metroasema"):
    val neighbors = Vector((knmcdonalds 30),(sornainen, 60))
    val subDesc = Vector()

  object narnia extends Area("Narnia"):
    val neighbors = Vector(rantasauna, 120)
    val subDesc = Vector()

  object abloc extends Area("A Bloc"):
    val neighbors = Vector(kandi,ttalo,klahtimetro,knmcdonalds,sornainen)
    val subDesc = Vector()

  object kandi extends Area("Kandikeskus"):
    val neighbors = Vector(abloc,designfactory,ok20,taafa,dipoli)
    val subDesc = Vector()

  object tuas extends Area("TUAS"):
    val neighbors = Vector(ttalo)
    val subDesc = Vector()

  object ttalo extends Area("Tietotalo"):
    val neighbors = Vector(abloc,designfactory)
    val subDesc = Vector()

  object designfactory extends Area("Aalto Design Factory"):
    val neighbors = Vector(kandi,ttalo)
    val subDesc = Vector()

  object otaranta extends Area("Otaranta"):
    val neighbors = Vector(smokki,rantasauna)
    val subDesc = Vector()

  private val areas =
    Vector[Area](taafa,smokki,ok20,dipoli,knmcdonalds,sornainen,rantasauna,klahtimetro,narnia,abloc,kandi,tuas,ttalo,designfactory,otaranta)
  
  def getAreas : Vector[Area] = areas

  def getTime = currentTime

  def passTime(timeToPass: Int) =
    currentTime += timeToPass