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
      case "examine"   => Some(actor.location.examine(actor))
      case "escape"    => Some(actor.location.escape)
      case "inventory" => Some(actor.inventory.mkString(", "))
      case "use"       => Some(actor.useItem(this.modifiers))
      case other       => None
