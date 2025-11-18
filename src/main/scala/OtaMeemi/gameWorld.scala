package OtaMeemi
import scala.util.Random

class GameWorld:
  private var currentTime = (8*60)+15

  object spagu extends Event("spagumayhem", taafa):
    override def checkActive(player: Player) =
      currentTime > (11*60)

    override def activateEvent(player: Player): Any =
      val itemToDrop = player.inventory(Random.between(0, player.inventory.length - 1))
      player.drop(itemToDrop)
      s"Oivoi, jonon ihmismassojen seassa joku pääsi nappaamaan taskustasi ${itemToDrop}:n"

  object liukuhihna extends Event("Liukuhihna", smokki):
    override def checkActive(player: Player) =
      currentTime > (19*60)

    override def activateEvent(player: Player): String =
      "Teema on leffaa bro"

  object stigulaatio extends Event("stigulaatio", ok20):
    override def checkActive(player: Player) =
      (currentTime > (17*60)) && (player.location.getDepth > 1)

    override def activateEvent(player: Player): String =
      player.removeItem("phone")
      player.addDebuff(DokattuDebuff(120, currentTime))
      "Mitö vi..,,., olemn näköjm vhäm dokatui(mut ei sale sle pljaojn....)"

  object taafa extends Area("Taafa"):
    val neighbors = Vector((kandi, 5),(dipoli, 2))
    val subDesc = Vector("Saavut täffälle, betonibrutalismin kukkanen pistää silmään","Yrität mennä sisälle. Huomaat että olet spagujonossa ilman lounaslippua. Joudut odottamaan jonossa muiden rahvaiden kanssa.","Menet sisälle. Ohitat koko jonon fastlanea käyttäen koska sinulla on lounaslippu. Oppiipahan.","Du försöker att komma in genom övre dörren men det är stängt. Tyvärr har du ingen nyckel för du är inte medlem av Teknologföreningen. Medlemsansökan tas emot på vardagar mellan 9.15 och 9.20 och processeras vanligtvis inom 3-5 dagar. Anmäl dig genom länken här: https://registration.teknologforeningen.fi/. Samma på finska!")
    val event = Some(spagu)

  object smokki extends Area("Smökki"):
    val neighbors = Vector((otaranta, 5), (ok20, 2))
    val subDesc = Vector("Saavuit smökin pihaan, sisältä kuuluu musiikkia.","Käynnissä on ilmeisesti sikajuhlat, et pääse sisälle koska sinulla ei ole lippua","Yrität lahjoa portsarin, hän ei päästä sinua sisälle koska et ole tutalla","Lahjot portsarin, hän päästää sinut sisälle. Saat outoja katseita koska olet haalarit päällä frakkitapahtumassa")

  object ok20 extends Area("Ok20"):
    val neighbors = Vector((kandi,10),(rantasauna,10))
    val subDesc = Vector()

  object dipoli extends Area("Dipoli"):
    val neighbors = Vector((taafa,5),(kandi,10),(knmcdonalds,15))
    val subDesc = Vector[String](("Saavut dipolille, frakkien määrän perusteella sisällä on meneillään jotain tärkeää"), ("Syöt ruokaa tavalliselta linjastolta"), ("Kävelet sisään. Kävelet suoraan ulos hämmästyneenä pöhinän määrästä"))

  object knmcdonalds extends Area("Keilaniemi Mcdonalds"):
    val neighbors = Vector((dipoli,15),(sornainen,10),(klahtimetro,10))
    val subDesc = Vector("Saavut miljardin dollarin konserniin Keilaniemessä. Vakiotyöpaikka tutalaisille","Mitä saisi olla? Hei ootko sä tutalla?")

  object sornainen extends Area("Sörnäisten metroasema"):
    val neighbors = Vector(knmcdonalds,klahtimetro)
    val subDesc = Vector("Ilmeisesti sammuit metroon Stigulaation jälkeen ja päädyit Sörnäisiin")

  object rantasauna extends Area("Rantasauna"):
    val neighbors = Vector((narnia,1200),(ok20,10))
    val subDesc = Vector("Saavut rantasaunalle, palju ja sauna ovat lämpimiä","Ice age 3 pyörii valkokankaalla, Mario Kart löytyy sivuhuoneesta","Palju on melko täynnä, onneksi paljuun mahtuu aina n+1 ihmistä","“Top kolmosessa ei ole yhtään oikeaa pelaajaa. Kaiken lisäksi switchin laturi on hukassa ja ohjaimista alkaa loppua virta")

  object klahtimetro extends Area("Kivenlahden metroasema"):
    val neighbors = Vector((knmcdonalds,30),(sornainen, 60))
    val subDesc = Vector()

  object narnia extends Area("Narnia"):
    val neighbors = Vector(rantasauna,1200)
    val subDesc = Vector("Miten tässä näin kävi?")

  object abloc extends Area("A Bloc"):
    val neighbors = Vector((kandi,2),(ttalo,10),(klahtimetro,30),(knmcdonalds,10),(sornainen,20))
    val subDesc = Vector("O Block mutta aallossa")

  object kandi extends Area("Kandikeskus"):
    val neighbors = Vector((abloc,20),(designfactory,20),(ok20,30),(taafa,30),(dipoli,20))
    val subDesc = Vector()

  object tuas extends Area("TUAS"):
    val neighbors = Vector(ttalo,5)
    val subDesc = Vector()

  object ttalo extends Area("Tietotalo"):
    val neighbors = Vector((abloc,10),(designfactory,10))
    val subDesc = Vector("Saavut tietotalolle, täällä asuville suihku on vieras käsite","Käyt syömässä subwayssa hunajaoreganosubin #pleaseburgercheese")

  object designfactory extends Area("Aalto Design Factory"):
    val neighbors = Vector((kandi,15),(ttalo,10))
    val subDesc = Vector("Saavut Design factoryn pihaan, sisältä kuuluu pöhinää","Menet sisälle pöhisemään")

  object otaranta extends Area("Otaranta"):
    val neighbors = Vector((smokki,15),(rantasauna,10))
    val subDesc = Vector("Saavut otarantaan. Kylmä tuuli puhaltaa mereltä","Menet uimaan, vesi on kylmää (yllättyneet parijonoon)")

  private val areas =
    Vector[Area](taafa,smokki,ok20,dipoli,knmcdonalds,sornainen,rantasauna,klahtimetro,narnia,abloc,kandi,tuas,ttalo,designfactory,otaranta)
  
  def getAreas : Vector[Area] = areas

  def getTime = currentTime

  def passTime(timeToPass: Int) =
    currentTime += timeToPassr