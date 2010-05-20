package dk.yousee.model

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:33:38 PM
 */

class Abonnement(val id : Int, val juridisk : Int, val leveringsAftaler : List[LeveringsAftale], val betalingsAftaler : List[BetalingsAftale], val status : AbonnementStatus.Value)  {
  def persist {
    //todo kald repository
    println("Persister Abonnement " + id)
  }
}

object AbonnementStatus extends Enumeration {
  val Lukket,Bero,Aktiv = Value
}