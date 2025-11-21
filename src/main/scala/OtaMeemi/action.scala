package OtaMeemi

class Action(input: String):
  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim
  def execute(actor: Player): Option[String] =
    this.verb match
      case "go"        => Some(actor.go(this.modifiers))
      case "rest"      => Some(actor.rest())
      case "quit"      => Some(actor.quit())
      case "examine"   =>
        if this.modifiers.isEmpty then Some(actor.location.examine(actor))
        else Some(actor.examineItem(this.modifiers))
      case "escape"    => Some(actor.location.escape)
      case "inventory" => Some(actor.inventory.mkString(", "))
      case "use"       => Some(actor.useItem(this.modifiers))
      case "sus"    => Some(actor.sus())
      case "help"   => Some("Valid commands include but are not limited to: go, rest, quit, examine (item or location), escape, inventory, use (item)")
      case other    => Some("Not a valid command")
/*      case "eat"    =>
        if actor.isatAbloc then
          this.modifiers match 
            case "konnichiwa" => "Söit Konnichiwassa, viikon budjetista on nyt käytetty 70%. Teit kurkkuasetelman sushin viereen"
            case "kotkot" => "Söit Kotkotissa. En osaa sanoa tästä mitään hauskaa kun en ole vielä kokeillut. Viikon budjetista kului vain 30%"
            case "poke bowl" => "Söit Poke Bowlissa. En ole tätäkään kokeillut koska se on auki kummallisiin aikoihin."
            case other => "Että mikä oli?"
        else 
            "Nothing to eat here"
          
*/