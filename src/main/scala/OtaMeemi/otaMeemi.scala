package OtaMeemi

class OtaMeemi:
  val otaniemi = GameWorld()
  val player = Player(otaniemi)

  val title = "OtaMeemi3 (1, 2 ja 2.1 kuluivat toimivatn projektin luontiin intelliJ:ssa"

  val welcomeMessage = s"Aamusi alkaa Metroasemalta, kello on ${otaniemi.getTime}, nyt on kiire luennolle"

  def playTurn(command: String) =
    val action = Action(command)