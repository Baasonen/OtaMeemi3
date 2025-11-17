package OtaMeemi


class GameWorld:


  object taafa extends Area("Taafa"):
    def neighbors = Vector(kandi,dipoli)
    def subDesc = Vector()

  object smokki extends Area("Smökki"):
    def neighbors = Vector(otaranta,ok20)
    def subDesc = Vector()

  object ok20 extends Area("Ok20"):
    def neighbors = Vector(kandi,smokki,rantasauna)
    def subDesc = Vector()

  object dipoli extends Area("Dipoli"):
    def neighbors = Vector(taafa,kandi,knmcdonalds)
    def subDesc = Vector()

  object knmcdonalds extends Area("Keilaniemi Mcdonalds"):
    def neighbors = Vector(dipoli,sornainen,klahtimetro)
    def subDesc = Vector()

  object sornainen extends Area("Sörnäisten metroasema"):
    def neighbors = Vector(knmcdonalds,klahtimetro)
    def subDesc = Vector()

  object rantasauna extends Area("Rantasauna"):
    def neighbors = Vector(narnia,rantasauna,ok20)
    def subDesc = Vector()

  object klahtimetro extends Area("Kivenlahden metroasema"):
    def neighbors = Vector(knmcdonalds,sornainen)
    def subDesc = Vector()

  object narnia extends Area("Narnia"):
    def neighbors = Vector(rantasauna)
    def subDesc = Vector()

  object abloc extends Area("A Bloc"):
    def neighbors = Vector(kandi,ttalo,klahtimetro,knmcdonalds,sornainen)
    def subDesc = Vector()

  object kandi extends Area("Kandikeskus"):
    def neighbors = Vector(abloc,designfactory,ok20,taafa,dipoli)
    def subDesc = Vector()

  object tuas extends Area("TUAS"):
    def neighbors = Vector(ttalo)
    def subDesc = Vector()

  object ttalo extends Area("Tietotalo"):
    def neighbors = Vector(abloc,designfactory)
    def subDesc = Vector()

  object designfactory extends Area("Aalto Design Factory")
    def neighbors = Vector(kandi,ttalo)
    def subDesc = Vector()

  object otaranta extends Area("Otaranta")
    def neighbors = Vector(smokki,rantasauna)
    def subDesc = Vector()

  private val areas =
    Vector[Area](taafa,smokki,ok20,dipoli,knmcdonalds,sornainen,rantasauna,klahtimetro,narnia,abloc,kandi,tuas,ttalo,designfactory,otaranta)
  
  def getAreas : Vector[Area] = areas